/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: WikittyPublicationContext.java 805 2011-04-13 16:59:23Z bpoussin $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/WikittyPublicationContext.java $
 * %%
 * Copyright (C) 2010 CodeLutin, Benjamin Poussin, Benjamin Poussin
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package unUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.ArgumentsParserException;
import org.nuiton.util.ObjectUtil;
import org.nuiton.util.StringUtil;
import org.nuiton.wikitty.WikittyConfig;
import org.nuiton.wikitty.WikittyConfigOption;
import org.nuiton.wikitty.WikittyException;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyService;
import org.nuiton.wikitty.WikittyServiceFactory;
import org.nuiton.wikitty.entities.WikittyExtension;
import org.nuiton.wikitty.entities.WikittyLabelAbstract;
import org.nuiton.wikitty.entities.WikittyTreeNodeAbstract;
import org.nuiton.wikitty.entities.WikittyUserAbstract;
import org.nuiton.wikitty.publication.entities.WikittyPubDataAbstract;
import org.nuiton.wikitty.publication.entities.WikittyPubTextAbstract;

/**
 * Context de la requete en cours.
 *
 * Les parametres de la requete sont parses et mis dans deux variables
 * <li>arguments
 * <li>argumentFiles
 *
 * un parametre simple sera dans arguments:
 * <li> [filename]=[valeur du champs]
 *
 * un parametre de type upload aura des entrees dans les deux
 * dans arguments:
 * <li> [fieldname]-upload=true
 * <li> [fieldname]-filename=[nom du fichier]
 * <li> [fieldname]-contentType=[type mime du fichier]
 * dans argumentFiles:
 * <li> [fieldname]=[contenu binaire du fichier]
 *
 * @author poussin
 * @version $Revision: 805 $
 *
 * Last update: $Date: 2011-04-13 18:59:23 +0200 (mer., 13 avr. 2011) $
 * by : $Author: bpoussin $
 */
public class WikittyPublicationContext {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(WikittyPublicationContext.class);

    /** configuration option name FIXME poussin 20101206 use OptionDef */
    static final public String CONFIG_FILE = "wikitty.publication.config.pattern";
    static final public String ACTION_PREFIX = "wikitty.publication.action";

    static final protected Map<String, WikittyService> services =
            new HashMap<String, WikittyService>();
    static final protected Map<String, WikittyPublicationAction> actions =
            new HashMap<String, WikittyPublicationAction>();

    protected ApplicationConfig appConfig;
    protected HttpServletRequest req;
    protected HttpServletResponse resp;
    protected String wsContext = null;
    protected String actionName = null;
    protected String path = "";
    protected List<String> mandatoryArguments = new ArrayList<String>();
    protected Map<String, String> arguments = new HashMap<String, String>();
    protected Map<String, byte[]> argumentFiles = new HashMap<String, byte[]>();
    protected WikittyProxy proxy = null;

    protected String contentType = "text/html";

    public WikittyPublicationContext(ApplicationConfig appConfig) {
        this.appConfig = appConfig;                    

        // force load of action in configuration
        for(String key : appConfig.getFlatOptions().stringPropertyNames()) {
            if (key.startsWith(ACTION_PREFIX)) {
                String action = key.substring(ACTION_PREFIX.length() + 1);
                getAction(action);
            }
        }
    }

    /**
     * add here all extension that WikittyService must know
     */
    protected void addRequiredExtension(WikittyService ws) {
        List<WikittyExtension> exts = new ArrayList<WikittyExtension>();
        
        exts.addAll(WikittyUserAbstract.extensions);
        exts.addAll(WikittyLabelAbstract.extensions);
        exts.addAll(WikittyTreeNodeAbstract.extensions);
        exts.addAll(WikittyPubTextAbstract.extensions);
        exts.addAll(WikittyPubDataAbstract.extensions);

        ws.storeExtension(null, exts);
    }

    /**
     * set attributes:
     * <li> req
     * <li> wsContext
     * <li> actionName
     * <li> mandatoryArguments
     * <li> arguments
     *
     * @param req
     */
    public void parse(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        
        String path = req.getPathInfo();

        // path start with '/' then comps[0] == empty
        String[] comps = StringUtil.split(path, "/");
        if (comps.length > 0) {
            wsContext = comps[1];
        }
        if (comps.length > 1) {
            actionName = comps[2];
        }


        for (int i = 3; i < comps.length; i++) {
            mandatoryArguments.add(comps[i]);
            path += "/" + comps[i];
        }


        boolean isMultipart = ServletFileUpload.isMultipartContent(getRequest());
        if (isMultipart == true) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Process the uploaded items
            // Parse the request
            try {
                List<FileItem> items = upload.parseRequest(getRequest());
                for (FileItem item : items) {
                    String name = item.getFieldName();
                    if (item.isFormField()) {
                        String value = item.getString();
                        arguments.put(name, value);
                    } else {
                        String filename = item.getName();
                        String mime = item.getContentType();
                        byte[] value = item.get();
                        log.info(String.format(
                                "Argument file '%s' of type '%s'",
                                filename, mime));
                        argumentFiles.put(name, value);
                        arguments.put(name+"-upload", "true");
                        arguments.put(name+"-filename", filename);
                        arguments.put(name+"-contentType", mime);
                    }
                }
            } catch (FileUploadException eee) {
                log.error("Can't get uploaded file", eee);
            }
        } else {
            for (Enumeration<String> e = req.getParameterNames(); e.hasMoreElements();) {
                String name = e.nextElement();
                String value = req.getParameter(name);
                arguments.put(name, value);
            }
        }
        log.info(String.format(
                "path %s => ws: %s action: %s mandatoryArguments: %s arguments: %s",
                path, wsContext, actionName, mandatoryArguments, arguments));
    }

    public HttpServletRequest getRequest() {
        return req;
    }

    public HttpServletResponse getResponse() {
        return resp;
    }

    public ApplicationConfig getAppConfig() {
        return appConfig;
    }

    /**
     * le nom de l'action a faire
     * @return
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * le reste de l'url apres l'action
     * @return
     */
    public String getPath() {
        return path;
    }


    public WikittyProxy getWikittyProxy() {
        if (proxy == null) {
            proxy = new WikittyProxy(getAppConfig(), getWikittyService());
        }
        return proxy;
    }

    /**
     * add context to the url and parameter if necessary
     * @param url
     * @return
     */
    public String makeUrl(String url) {
        String finalUrl = url;
        if (!finalUrl.startsWith("/")) {
            finalUrl = "/" + finalUrl;
        }
        finalUrl = getRequest().getContextPath() + "/" + wsContext + finalUrl;
        finalUrl = getResponse().encodeURL(finalUrl);
        if (log.isInfoEnabled()) {
            log.info(String.format("transforme url from '%s' to '%s'", url, finalUrl));
        }
        return finalUrl;
    }

    public WikittyService getWikittyService() {
        WikittyService result = services.get(wsContext);
        if (result == null) {
            String patternConfigFilename = appConfig.getOption(CONFIG_FILE);

            // load default configuration for all wikitty service
            String filename = String.format(patternConfigFilename, "default");
            log.info(String.format("Try to load config file '%s'", filename));
            ApplicationConfig wsConfigDefault;

            wsConfigDefault = WikittyConfig.getConfig(filename);

            // change juste data dir with context path
            String dataDir = wsConfigDefault.getOption(
                    WikittyConfigOption.WIKITTY_DATA_DIR.getKey());
            wsConfigDefault.setOption(
                    WikittyConfigOption.WIKITTY_DATA_DIR.getKey(),
                    dataDir + File.separator + wsContext);

            // read specifique configuration with default config as default properties
            filename = String.format(patternConfigFilename, wsContext);
            log.info(String.format("Try to load config file '%s'", filename));
            ApplicationConfig wsConfig;
            try {
                wsConfig = new ApplicationConfig(wsConfigDefault.getFlatOptions(false));
                wsConfig.setConfigFileName(filename);
                wsConfig.parse(null);
            } catch (ArgumentsParserException eee) {
                throw new WikittyException(String.format(
                        "Can't parse configuration %s", filename), eee);
            }
            synchronized (services) {
                result = services.get(wsContext);
                if (result == null) {
                    result = WikittyServiceFactory.buildWikittyService(wsConfig);
                    addRequiredExtension(result);
                    services.put(wsContext, result);
                }
            }
        }
        return result;
    }

    public List<String> getMandatoryArguments() {
        return mandatoryArguments;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public Map<String, byte[]> getArgumentFiles() {
        return argumentFiles;
    }

    public String getArgument(String name, String defaultValue) {
        String result = defaultValue;
        if (getArguments().containsKey(name)) {
            result = getArguments().get(name);
        }
        return result;
    }

    public WikittyPublicationAction getAction() {
        WikittyPublicationAction result = getAction(actionName);
        return result;
    }


    public <E extends WikittyPublicationAction> E getAction(Class<E> actionClass) {
        E result = null;
        for (WikittyPublicationAction a : actions.values()) {
            if (actionClass.isInstance(a)) {
                result = (E)a;
                break;
            }
        }
        return result;
    }

    public WikittyPublicationAction getAction(String actionName) {
        if (log.isInfoEnabled()) {
            log.info(String.format("Looking for class for '%s' action", actionName));
        }
        WikittyPublicationAction result = actions.get(actionName);
        if (result == null) {
            String action = ACTION_PREFIX + "." + actionName;
            log.info(String.format("try to load action %s", action));
            try {
                Class<WikittyPublicationAction> clazz = (Class<WikittyPublicationAction>)
                        appConfig.getOptionAsClass(action);
                Collection args = Collections.singleton(appConfig);
                result = ObjectUtil.newInstance(clazz, args, true);
                result.setMapping(actionName);
                actions.put(actionName, result);
            } catch (Exception eee) {
                log.error(String.format("Can't find action %s", action), eee);
                result = new ActionError(eee);
            }
        }
        return result;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
         String path = req.getPathInfo();
        String result = String.format("WPContext [path: %s\n"
                + " ws: %s\n"
                + " action: %s\n"
                + " mandatoryArguments: %s\n"
                + " arguments: %s\n"
                + "]", path, wsContext, actionName, mandatoryArguments, arguments);
        return result;
    }

}

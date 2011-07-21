/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: ActionEdit.java 856 2011-05-04 10:12:53Z mfortun $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/ActionEdit.java $
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.CollectionUtil;
import org.nuiton.util.StringUtil;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyUtil;
import org.nuiton.wikitty.entities.FieldType;
import org.nuiton.wikitty.entities.FieldType.TYPE;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyExtension;
import org.nuiton.wikitty.entities.WikittyImpl;
import org.nuiton.wikitty.publication.entities.WikittyPubData;
import org.nuiton.wikitty.publication.entities.WikittyPubDataHelper;
import org.nuiton.wikitty.search.Criteria;
import org.nuiton.wikitty.search.operators.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author poussin
 * @version $Revision: 856 $
 * 
 *          Last update: $Date: 2011-05-04 12:12:53 +0200 (mer., 04 mai 2011) $
 *          by : $Author: mfortun $
 */
public class ActionEdit extends AbstractActionOnWikitty {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(ActionError.class);

    static final protected String exampleUsage = "edit/WikittyPubText.name=Wiki\n"
            + "edit/Tuto\n"
            + "edit/WikittyPubText.name=Tuto\n"
            + "edit/Command.id=df\n";

    protected ApplicationConfig appConfig;

    public ActionEdit(ApplicationConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public Object doAction(WikittyPublicationContext context) {
        String result;
        Wikitty w;

        WikittyProxy proxy = context.getWikittyProxy();

        if (context.getArguments().containsKey("delete")) {
            // on nous demande supprimer le wikitty, on l'efface et on
            // affichera un wikitty vide
            String id = context.getArguments().get("id");
            proxy.delete(id);
            // apres un effacement on reprend l'edition d'un tout nouveau
            // wikitty
            w = new WikittyImpl();
        } else {
            // recherche du Wikitty a editer ou creation d'un nouveau si
            // necessaire
            Criteria criteria = searchCriteria(context.getMandatoryArguments());
            if (criteria == null) {
                w = new WikittyImpl();
            } else {
                w = proxy.findByCriteria(criteria);
            }

            // si on ne retrouve pas le wikitty, mais qu'il vient d'etre cree
            // pour l'edition, on recree un wikitty avec ce meme identifiant
            if (w == null
                    && "0.0".equals(context.getArguments().get("version"))) {
                // c'est un nouvel objet, il n'a pas encore ete sauve, mais on
                // veut le faire
                String id = context.getArguments().get("id");
                w = new WikittyImpl(id);
            }

            if (w == null) {
                // si le wikitty est null, et qu'on etait pas en edition
                // cela signifie qu'on ne retrouve pas le wikitty a editer
                // on creer un nouveau wikitty vide que l'on editera
                w = new WikittyImpl();
            } else {
                // on met a jour le wikitty avec les infos trouvees dans les
                // arguments

                // ajout des extensions deja existante si necessaire
                String extensions = context.getArgument("extensions", null);
                if (extensions != null) {
                    String[] exts = StringUtil.split(extensions
                            .replace("[", "").replace("]", ""), ",");
                    for (String extName : exts) {
                        WikittyExtension ext = proxy
                                .restoreExtensionLastVersion(extName);
                        if (ext != null) {
                            w.addExtension(ext);
                        }
                    }
                }

                // ajout de l'extension demande par l'utilisateur
                String extName = context.getArgument("newExtension", null);
                if (extName != null && !"".equals(extName)) {
                    WikittyExtension ext = proxy
                            .restoreExtensionLastVersion(extName);
                    if (ext != null) {
                        w.addExtension(ext);
                    }
                }

                Map<String, Object> args = new HashMap<String, Object>();
                args.putAll(context.getArguments());
                args.putAll(context.getArgumentFiles());
                for (Map.Entry<String, Object> field : args.entrySet()) {
                    String key = field.getKey();
                    Object value = null;
                    if (key.contains(WikittyUtil.FQ_FIELD_NAME_SEPARATOR)) {
                        String ext = WikittyExtension.extractExtensionName(key);
                        String fieldName = WikittyExtension
                                .extractFieldName(key);

                        if (w.hasField(ext, fieldName)) {
                            if (!"true".equals(context.getArgument("isNull-"
                                    + key, "false"))) {

                                value = field.getValue();
                            }

                            FieldType extFieldType = w.getExtension(ext)
                                    .getFieldType(fieldName);
                            if (extFieldType.isCollection()
                                    && extFieldType.getType() == TYPE.STRING
                                    && value != null) {

                                String valueString = value.toString();

                                valueString = new String(valueString.substring(
                                        1, valueString.length() - 1));

                                Collection<String> list = new ArrayList<String>();

                                String[] valuesString = StringUtil.split(
                                        valueString, ",");

                                for (String element : valuesString) {
                                    list.add(element.trim());
                                }

                                w.setField(ext, fieldName, list);

                            } else {
                                w.setField(ext, fieldName, value);
                            }
                            // si w est un WikittyPubData on essai de mettre a
                            // jour si besoin les champs mimetype et name
                            if (ext.equals(WikittyPubData.EXT_WIKITTYPUBDATA)
                                    && fieldName
                                            .equals(WikittyPubData.FIELD_WIKITTYPUBDATA_CONTENT)) {
                                if (null == WikittyPubDataHelper.getName(w)) {
                                    WikittyPubDataHelper.setName(
                                            w,
                                            String.valueOf(args.get(key
                                                    + "-filename")));
                                }
                                if (null == WikittyPubDataHelper.getMimeType(w)) {
                                    WikittyPubDataHelper.setMimeType(
                                            w,
                                            String.valueOf(args.get(key
                                                    + "-contentType")));
                                }
                            }
                        }
                    }
                }

                if (context.getArguments().containsKey("store")) {
                    // on nous demande la sauvegarde
                    proxy.store(w);
                }
            }
        }

        // forward l'affichage de la page vers la jsp d'edition
        context.setContentType("forward/jsp");
        context.getRequest().setAttribute(ActionEval.CONTEXT_VAR, context);
        context.getRequest().setAttribute(ActionEval.WIKITTY_VAR, w);
        result = "/WEB-INF/jsp/edit.jsp";

        return result;
    }

    /**
     * create url to edit wikitty object, assume that edition already start add
     * context.getActionName() return edit action name.
     * 
     * @param context
     * @param w
     * @return
     */
    public String makeUrl(WikittyPublicationContext context, Wikitty w) {
        String url = getMapping() + "/" + Element.ELT_ID + SEARCH_SEPARATOR
                + w.getId();
        url = context.makeUrl(url);
        return url;
    }

    @Override
    protected String getExampleUsage() {
        return exampleUsage;
    }

}

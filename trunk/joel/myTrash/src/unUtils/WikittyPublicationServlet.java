/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: WikittyPublicationServlet.java 650 2010-12-23 11:44:57Z sletellier $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/WikittyPublicationServlet.java $
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


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.ArgumentsParserException;

/**
 * url:
 * <pre>
 * /[context]/[action]/[action argument]?[action argument]#[fragment]
 * </pre>
 *
 * example:
 * <pre>
 * /codelutin/raw/WikittyPubData.name=lutin.jpg/WikittyPubData.content?mimetype=WikittyPubData.mimetype
 * </pre>
 *
 * <li>la config du WikittyService sera lu dans la config 
 * wikitty.publication.config.pattern en remplacant %s par 'default'
 * la valeur de 'wikitty.data.directory' est modifier pour lui ajouter le
 * context par defaut en plus, puis le fichier de config specifique au context est lu
 * qui peut alors ecraser 'wikitty.data.directory' et toutes les autres valeurs
 * par defaut
 * <li>l'action executee sera 'raw' la classe associee sera trouve dans le fichier 
 * de config WikittyPublication.
 * <li> le reste sont des arguments specifique a l'action que l'action pourra
 * trouver dans: {@link WikittyPublicationContext#getMandatoryArguments()} et
 * {@link WikittyPublicationContext#getArguments()}.
 *
 * @author poussin
 * @version $Revision: 650 $
 *
 * Last update: $Date: 2010-12-23 12:44:57 +0100 (jeu., 23 déc. 2010) $
 * by : $Author: sletellier $
 */
public class WikittyPublicationServlet extends HttpServlet {

    static public ApplicationConfig appConfig;

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(WikittyPublicationServlet.class);

    public void init() throws ServletException {
        try {
            appConfig = new ApplicationConfig();
            appConfig.setConfigFileName("wikitty-publication.properties");
            appConfig.parse(null);
        } catch(ArgumentsParserException eee) {
            throw new ServletException("Can't get filename config prefix", eee);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Object result;
        WikittyPublicationContext context = new WikittyPublicationContext(appConfig);
        try {
            context.parse(req, resp);

            if ("true".equals(context.getArguments().get("debug"))) {
                // debug asked, not do action, but show context
                result = context.toString();
                context.setContentType("text/plain");
            } else {
                WikittyPublicationAction action = context.getAction();
                result = action.doAction(context);
            }
        } catch (Throwable eee) {
            WikittyPublicationAction action = new ActionError(eee);
            result = action.doAction(context);
        }

        String contentType = context.getContentType();
        if (contentType != null && contentType.startsWith("forward")) {
                req.getRequestDispatcher(String.valueOf(result)).forward(req, resp);
        } else {
            if (contentType != null) {
                resp.setContentType(contentType);
            }
            if (result instanceof byte[]) {
                ServletOutputStream out = resp.getOutputStream();
                out.write((byte[]) result);
                out.flush();
            } else {
                PrintWriter out = resp.getWriter();
                out.write(String.valueOf(result));
                out.flush();
            }
        }
    }

}

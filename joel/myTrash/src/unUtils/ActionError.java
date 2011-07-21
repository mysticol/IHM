/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: ActionError.java 650 2010-12-23 11:44:57Z sletellier $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/ActionError.java $
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


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.util.ExceptionUtil;

/**
 *
 * @author poussin
 * @version $Revision: 650 $
 *
 * Last update: $Date: 2010-12-23 12:44:57 +0100 (jeu., 23 déc. 2010) $
 * by : $Author: sletellier $
 */
public class ActionError extends AbstractAction {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(ActionError.class);

    protected Throwable error = null;

    public ActionError(Throwable error) {
        this.error = error;
    }

    @Override
    public Object doAction(WikittyPublicationContext context) {
        error.printStackTrace();
        
        HttpServletRequest req = context.getRequest();
        String result = "<html><body>Error: "
                + "<br>context: " + context
                + "<br>"
                + "<br>getContextPath: " + req.getContextPath()
                + "<br>getMethod: " + req.getMethod()
                + "<br>getPathInfo: " + req.getPathInfo()
                + "<br>getPathTranslated: " + req.getPathTranslated()
                + "<br>getQueryString: " + req.getQueryString()
                + "<br>getRemoteUser: " + req.getRemoteUser()
                + "<br>getRequestURI: " + req.getRequestURI()
                + "<br>getRequestURI: " + req.getRequestURI()
                + "<br>getRequestedSessionId: " + req.getRequestedSessionId()
                + "<br>getServletPath: " + req.getServletPath()
                + "<br>getCharacterEncoding: " + req.getCharacterEncoding()
                + "<br>getContentType: " + req.getContentType()
                + "<br>getLocalAddr: " + req.getLocalAddr()
                + "<br>getLocalName: " + req.getLocalName()
                + "<br>getProtocol: " + req.getProtocol()
                + "<br>getRemoteAddr: " + req.getRemoteAddr()
                + "<br>getRemoteHost: " + req.getRemoteHost()
                + "<br>getScheme: " + req.getScheme()
                + "<br>getServerName: " + req.getServerName()
                + "<br>"
                + "<br>error:<pre>" + StringEscapeUtils.escapeHtml(ExceptionUtil.stackTrace(error)) + "</pre>"
                + "</body></html>";
        return result;
    }

}

/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: ActionView.java 756 2011-04-05 12:34:58Z mfortun $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/ActionView.java $
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
import org.nuiton.util.StringUtil;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.search.Criteria;
import org.nuiton.wikitty.search.PagedResult;
import org.nuiton.wikitty.search.Search;
import org.nuiton.wikitty.search.operators.Element;

/**
 *
 * @author poussin
 * @version $Revision: 756 $
 *
 * Last update: $Date: 2011-04-05 14:34:58 +0200 (mar., 05 avr. 2011) $
 * by : $Author: mfortun $
 */
public class ActionView extends AbstractAction {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(ActionError.class);

    protected ApplicationConfig appConfig;

    public ActionView(ApplicationConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public Object doAction(WikittyPublicationContext context) {
        WikittyProxy proxy = context.getWikittyProxy();

        String r = context.getArguments().get("r");
        if (r == null || "".equals(r)) {
            r = "*";
        }
        String first = context.getArgument("first", "0");
        String end = context.getArgument("end", "100");
        int firstIndex = StringUtil.toInt(first);
        int endIndex = StringUtil.toInt(end);

        
        
        Criteria criteria = Search.query().keyword(r).criteria();
        criteria.setFirstIndex(firstIndex);
        criteria.setEndIndex(endIndex);

        PagedResult<Wikitty> pagedResult = proxy.findAllByCriteria(criteria);

        String id = context.getArguments().get("id");
        Wikitty w = proxy.restore(id);

        // forward l'affichage de la page vers la jsp d'edition
        context.setContentType("forward/jsp");
        context.getRequest().setAttribute(ActionEval.CONTEXT_VAR, context);
        context.getRequest().setAttribute(ActionEval.WIKITTY_VAR, w);
        context.getRequest().setAttribute("pagedResult", pagedResult);
        String result = "/WEB-INF/jsp/view.jsp";

        return result;
    }

    /**
     * create url to edit wikitty object, assume that edition already start
     * add context.getActionName() return edit action name.
     * 
     * @param context
     * @param w
     * @return
     */
    static public String makeUrl(WikittyPublicationContext context, Wikitty w) {
        String url = context.getActionName() + "/"
                + Element.ELT_ID + "=" + w.getId();
        url = context.makeUrl(url);
        return url;
    }

}

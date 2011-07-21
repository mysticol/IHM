/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: AbstractActionOnWikitty.java 650 2010-12-23 11:44:57Z sletellier $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/AbstractActionOnWikitty.java $
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
import org.nuiton.wikitty.WikittyUtil;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyExtension;
import org.nuiton.wikitty.publication.entities.WikittyPubData;
import org.nuiton.wikitty.publication.entities.WikittyPubDataHelper;
import org.nuiton.wikitty.publication.entities.WikittyPubText;
import org.nuiton.wikitty.publication.entities.WikittyPubTextHelper;
import org.nuiton.wikitty.search.Criteria;
import org.nuiton.wikitty.search.Search;

import java.util.List;

/**
 *
 * @author poussin
 * @version $Revision: 650 $
 *
 * Last update: $Date: 2010-12-23 12:44:57 +0100 (jeu., 23 déc. 2010) $
 * by : $Author: sletellier $
 */
public abstract class AbstractActionOnWikitty extends AbstractAction {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(AbstractActionOnWikitty.class);

    static final public int ARG_QUERY = 0;
    static final public String ARG_MIMETYPE = "mimetype";
    static final public String ARG_CONTENT_FIELD = "contentField";
    static final public String SEARCH_SEPARATOR = ":";

    abstract protected String getExampleUsage();

    /**
     * Recherche le type mime dont on a besoin.
     * Par defaut recherche dans les arguments si on a dans l'ordre de preference
     * <li> ARG_MIMETYPE + SEARCH_SEPARATOR + name
     *      (ex: mimetype:MyCommand.name:df=text/plain)
     * <li> ARG_MIMETYPE
     *      (ex: mimetype=MyCommand.mimetype)
     * <li> si w a l'extension WikittyPubText alors on prend la valeur du champs mimetype
     * <li> si w a l'extension WikittyPubData alors on prend la valeur du champs mimetype
     * <li> on retourne null
     *
     * @param context
     * @param name la chaine utilise pour faire la recherche du wikitty
     * @param w le wikitty trouve grace a name
     * @return le mimetype ou null
     */
    protected String getMimeType(WikittyPublicationContext context,
            String name, Wikitty w) {
        // looking for mimetype field
        String mimetype = context.getArgument(
                ARG_MIMETYPE + SEARCH_SEPARATOR + name, null);
        if (mimetype == null) {
            mimetype = context.getArgument(ARG_MIMETYPE, null);
        }
        if (mimetype != null) {
            int i = mimetype.indexOf(WikittyUtil.FQ_FIELD_NAME_SEPARATOR);
            if (i > 0) { // perhaps fully qualified field
                String extName = WikittyExtension.extractExtensionName(mimetype);
                String fieldName = WikittyExtension.extractFieldName(mimetype);
                if (w.hasField(extName, fieldName)) {
                    // mimetype target field in wikitty
                    // replace with field value
                    mimetype = w.getFieldAsString(extName, fieldName);
                }
            }
        } else if (w.hasExtension(WikittyPubText.EXT_WIKITTYPUBTEXT)) {
            mimetype = WikittyPubTextHelper.getMimeType(w);
        } else if (w.hasExtension(WikittyPubData.EXT_WIKITTYPUBDATA)) {
            mimetype = WikittyPubDataHelper.getMimeType(w);
        }
        return mimetype;
    }

    /**
     * Retourne le critere pour recherche l'objet sur lequel faire l'action.
     * Le nom du critere doit etre convenablement positionné avec la chaine
     * qui a permit la recherche
     *
     * @param context
     * @return
     */
    protected Criteria searchCriteria(List<String> subContext) {
        Criteria result;
        if (subContext.size() <= 0) {
            result = null;
        } else {
            String searchString = subContext.get(ARG_QUERY);
            if (searchString.contains(SEARCH_SEPARATOR)) {
                // on a un field=value
                String[] arg = searchString.split(SEARCH_SEPARATOR);
                result = Search.query().eq(arg[0], arg[1]).criteria(searchString);
            } else {
                // on a pas le champs, alors par defaut on recherche dans
                // WikittyPubText.name et WikittyPubData.name
                result = Search.query().or()
                    .eq(WikittyPubText.FQ_FIELD_WIKITTYPUBTEXT_NAME, searchString)
                    .eq(WikittyPubData.FQ_FIELD_WIKITTYPUBDATA_NAME, searchString)
                    .criteria(searchString);
            }
        }
        return result;
    }

    /**
     * Recherche le champs contenant le content dont on a besoin.
     * Par defaut recherche dans les arguments si on a dans l'ordre de preference
     * <li> ARG_CONTENT_FIELD + SEARCH_SEPARATOR + name
     *      (ex: contentField:MyCommand.name:df=MyCommand.script)
     * <li> ARG_CONTENT_FIELD
     *      (ex: contentField=MyCommand.script)
     * <li> si w a l'extension WikittyPubText alors on prend le champs content
     * <li> si w a l'extension WikittyPubData alors on prend le champs content
     * <li> on retourne null
     *
     * @param context
     * @param name la chaine utilise pour faire la recherche du wikitty
     * @param w le wikitty trouve grace a name
     * @return le champs contenant le content ou null
     */
    protected String getContentFieldName(WikittyPublicationContext context,
            String name, Wikitty w) {
        String contentField = context.getArgument(
                ARG_CONTENT_FIELD + SEARCH_SEPARATOR + name, null);
        if (contentField == null) {
            contentField = context.getArgument(ARG_CONTENT_FIELD, null);
        }
        if (contentField == null) {
            if (w.hasExtension(WikittyPubText.EXT_WIKITTYPUBTEXT)) {
                contentField = WikittyPubText.FQ_FIELD_WIKITTYPUBTEXT_CONTENT;
            } else if (w.hasExtension(WikittyPubData.EXT_WIKITTYPUBDATA)) {
                contentField = WikittyPubData.FQ_FIELD_WIKITTYPUBDATA_CONTENT;
            }
        }
        return contentField;
    }

    protected String getError(WikittyPublicationContext context) {
        context.setContentType("text/html");
        String result = String.format(
                "<h1>bad query %s</h1>"
                + "Usage example"
                + "<pre>%s</pre>", context.getRequest().getPathInfo(), getExampleUsage());
        return result;
    }

}

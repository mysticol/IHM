/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: ActionEval.java 769 2011-04-08 13:46:27Z bpoussin $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/ActionEval.java $
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.StringUtil;
import org.nuiton.wikitty.ScriptEvaluator;
import org.nuiton.wikitty.WikittyException;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyExtension;
import org.nuiton.wikitty.publication.entities.WikittyPubText;
import org.nuiton.wikitty.search.Criteria;

/**
 * Permet d'evaluer un WikittyPubText et de retourner la valeur de l'evaluation.
 * La variable de nom {@link WikittyPublicationContext#CONTEXT_VAR} et de type
 * {@link WikittyPublicationContext} est positionnee dans l'environnement
 * d'evaluation.
 *
 * Le script doit positionner convenablement la valeur de
 * {@link WikittyPublicationContext#setContentType(java.lang.String)} par
 * rapport a l'objet retourne
 *
 * L'evaluateur est recherche via la valeur du champs
 * {@link WikittyPubText#getMimeType()}.
 *
 * @author poussin
 * @version $Revision: 769 $
 *
 * Last update: $Date: 2011-04-08 15:46:27 +0200 (ven., 08 avr. 2011) $
 * by : $Author: bpoussin $
 */
public class ActionEval extends AbstractActionOnWikitty {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    static private Log log = LogFactory.getLog(ActionError.class);

    /** variable contenant l'instance de la classe ActionEval */
    static final public String EVAL_VAR = "wpEval";
    /** variable name use to put context in script and jsp */
    static final public String CONTEXT_VAR = "wpContext";
    /** contient la liste des arguments mandatory non encore utilise */
    static final public String SUBCONTEXT_VAR = "wpSubContext";
    /**
     * contient le nom de la page WikittyPubText (ex: Wiki) ou la requete ayant
     * permis de trouver la page (ex: MyScript.name=df)
     */
    static final public String PAGE_NAME_VAR = "wpPage";
    /**
     * contient le wikitty utilise comme script
     */
    static final public String WIKITTY_VAR = "wpWikitty";

    static final protected String exampleUsage =
            "eval/WikittyPubText.name=Wiki/WikittyPubText.content?mimetype=WikittyPubText.mimetype\n"
            + "eval/Tuto\n"
            + "eval/Tuto/WikittyPubText.content\n"
            + "eval/WikittyPubText.name=Tuto/WikittyPubText.content\n"
            + "eval/WikittyPubText.name=Tuto/WikittyPubText.content?mimetype=WikittyPubText.mimetype\n"
            + "eval/Command.id=df/Command.script?mimetype=Command.mimetype\n";

    protected ApplicationConfig appConfig;
    protected ScriptEngineManager scriptEnginManager;

    public ActionEval(ApplicationConfig appConfig) {
        this.appConfig = appConfig;

        scriptEnginManager = new ScriptEngineManager();
    }

    public Object doAction(WikittyPublicationContext context, List<String> subContext) {
        log.info("path " + subContext);

        Object result;

        Criteria criteria = searchCriteria(subContext);

        if (criteria == null) {
            // rien a evaluer, on retourne une chaine vide
            result = "";
        } else {
            WikittyProxy proxy = context.getWikittyProxy();
            Wikitty w = proxy.findByCriteria(criteria);

            if (w == null) {
                context.setContentType("text/plain");
                result = String.format(
                        "no data found for criteria '%s'", criteria);
            } else {
                String contentField = getContentFieldName(context, criteria.getName(), w);

                if (contentField == null) {
                    result = getError(context);
                } else {
                    String extName = WikittyExtension.extractExtensionName(contentField);
                    String fieldName = WikittyExtension.extractFieldName(contentField);

                    String mimetype = getMimeType(context, criteria.getName(), w);
                    String content = w.getFieldAsString(extName, fieldName);

                    // supprime de subcontext ce qui a ete utilise dans cette methode
                    subContext = new ArrayList<String>(subContext.subList(1, subContext.size()));

                    Map<String, Object> bindings = new HashMap<String, Object>();
                    bindings.put(PAGE_NAME_VAR, criteria.getName());
                    bindings.put(CONTEXT_VAR, context);
                    bindings.put(SUBCONTEXT_VAR, subContext);
                    bindings.put(WIKITTY_VAR, w);
                    bindings.put(EVAL_VAR, this);

                    result = ScriptEvaluator.eval(
                            null, criteria.getName(), content, mimetype, bindings);
                }
            }
        }

        return result;        
    }

    public Object doAction(WikittyPublicationContext context, String subContextAsText) {
        Object result;
        if (subContextAsText == null || "".equals(subContextAsText)) {
            result = getError(context);
        } else {
            if (subContextAsText.startsWith("/")) {
                subContextAsText = subContextAsText.substring(1);
            }
            String[] subContextArray = StringUtil.split(subContextAsText, "/");
            List<String> subContext = Arrays.asList(subContextArray);
            result = doAction(context, subContext);
        }
        return result;
    }

    @Override
    public Object doAction(WikittyPublicationContext context) {
        Object result;
        if (context.getMandatoryArguments().size() <= 0) {
            result = getError(context);
        } else {
            result = doAction(context, context.getMandatoryArguments());
        }
        return result;
    }

    @Override
    protected String getExampleUsage() {
        return exampleUsage;
    }

}

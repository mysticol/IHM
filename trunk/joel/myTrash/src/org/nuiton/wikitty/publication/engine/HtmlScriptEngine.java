package org.nuiton.wikitty.publication.engine;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.util.ArrayUtil;
import org.nuiton.util.StringUtil;

/**
 * Script engine for html code.
 * Use to handle binds element invocation inside html code.
 *
 * @author mfortun
 *
 */
public class HtmlScriptEngine implements ScriptEngine {

    
    final static Log log = LogFactory.getLog(HtmlScriptEngine.class);
    
    /*
     * algo super simple en fait.
     * 
     * On lit le code. pour tout ce qui est dans les clés des bindings on fait
     * l'invocation java qui va avec.
     */

    /**
     * usefull to match element contained inside the binding map that on which
     * method are called. like : objectName.getName() objectName.setName("truc")
     */
    static public String REGEX_BIND_ELEMENT_METHOD = "\\.\\w+\\([\\w\"\\d]*\\)";
    static public String REGEX_END = "$";
    static public String REGEX_EMPTY = " +";
    static public String REGEX_EMPTY_END = REGEX_EMPTY + REGEX_END;
    /**
     * regex to match string method argument
     */
    static public String REGEX_STRING_ARG = "\"[\\w\\d]*\"";

    protected ScriptEngineFactory factory;
    protected ScriptContext context;
    protected Map<String, Object> attributes;

    @Override
    public Object eval(String script, ScriptContext context)
            throws ScriptException {
        if (context.getBindings(ScriptContext.ENGINE_SCOPE) == null) {
            return script;
        }
        return evaluateCodeInsideHtml(script,
                context.getBindings(ScriptContext.ENGINE_SCOPE));
    }

    @Override
    public Object eval(Reader reader, ScriptContext context)
            throws ScriptException {
        try {
            return this.eval(IOUtils.toString(reader), context);
        } catch (IOException e) {
            // TODO mfortun-2011-07-25 handle this, change ?
            return StringUtils.EMPTY;
        }
    }

    @Override
    public Object eval(String script) throws ScriptException {

        if (context.getBindings(ScriptContext.ENGINE_SCOPE) == null) {
            return script;
        }
        return evaluateCodeInsideHtml(script,
                context.getBindings(ScriptContext.ENGINE_SCOPE));
    }

    @Override
    public Object eval(Reader reader) throws ScriptException {
        return this.eval(reader, getContext());

    }

    @Override
    public Object eval(String script, Bindings n) throws ScriptException {
        return evaluateCodeInsideHtml(script, n);
    }

    @Override
    public Object eval(Reader reader, Bindings n) throws ScriptException {
        try {
            return this.eval(IOUtils.toString(reader), n);
        } catch (IOException e) {
            // TODO mfortun-2011-07-25 handle this, change ?
            return StringUtils.EMPTY;
        }
    }

    @Override
    public void put(String key, Object value) {
        attributes.put(key, value);
    }

    @Override
    public Object get(String key) {
        return attributes.get(key);
    }

    @Override
    public Bindings getBindings(int scope) {
        return context.getBindings(scope);
    }

    @Override
    public void setBindings(Bindings bindings, int scope) {
        context.setBindings(bindings, scope);
    }

    @Override
    public Bindings createBindings() {
        return new SimpleBindings();
    }

    @Override
    public ScriptContext getContext() {
        return context;
    }

    @Override
    public void setContext(ScriptContext context) {
        this.context = context;
    }

    @Override
    public ScriptEngineFactory getFactory() {
        if (factory == null) {
            factory = new HtmlScriptEngineFactory();
        }
        return factory;
    }

    /**
     * Finaly handle html to found invokation to bindings element
     * @param code the html code
     * @param binds the binding
     * @return code with binds value/invokation replacement
     */
    protected String evaluateCodeInsideHtml(String code, Bindings binds) {
        String result = new String(code);

        for (String bindingElement : binds.keySet()) {

            Object oo = binds.get(bindingElement);
            
            // parse to search method invokation on binds element
            String[] hmtl = result.split(bindingElement
                    + REGEX_BIND_ELEMENT_METHOD);
            
            String pageModified = hmtl[0];
            for (int i = 0; i < hmtl.length - 1; i++) {

                int begin = code.indexOf(hmtl[i]);
                begin = begin + hmtl[i].length();

                int end = code.indexOf(hmtl[i + 1]);
                // end = end;
                // Isolate method invocation
                String codeToExecude = code.substring(begin, end);
                codeToExecude = codeToExecude.trim();

                //invoke
                String resultCode = invokeFromName(oo, codeToExecude, binds);

                // replace at position by result
                pageModified += resultCode;
                pageModified += hmtl[i + 1];
            }

            pageModified = pageModified.replaceAll(bindingElement + REGEX_END
                    + "|" + REGEX_EMPTY + bindingElement + REGEX_EMPTY,
                    oo.toString());
            // pageModified = pageModified.replaceAll(bindingElement +
            // REGEX_EMPTY_END,
            // oo.toString() + " ");

            result = pageModified;

            /*
             * Voir si on match aussi les trucs plus générique avec déclaration
             * de classe et tout le tintouint
             * 
             * Si ça match le nom et avec le nom associé à la regex method cette
             * derniuère est prioritaire. Et on éxécute la méthode sur l'objet.
             * 
             * Si ça match seulement avec le nom alors on fait
             * getValue().tostring
             */
        }
        return result;
    }

    /**
     * use to invoke method on the object contained in the bindinds
     * @param oo the object bind
     * @param invokation the string containing the invokation
     * @param binds binding used inside evaluation 
     * @return result of the invokation or if method not found the invokation 
     * string
     */
    protected String invokeFromName(Object oo, String invokation, Bindings binds) {

        int dotPosition = invokation.indexOf(".");
        int openBracketPosition = invokation.indexOf("(");
        int closingBracketPosition = invokation.indexOf(")");
        // if no method found just let the same string in place
        String result = invokation;
        // .substring(0,dotPosition);;

        //
        String methodName = invokation.substring(dotPosition + 1,
                openBracketPosition);
        // obtain param list
        String params = invokation.substring(openBracketPosition + 1,
                closingBracketPosition);
        // parse params
        String[] parsedParam = StringUtil.split(params, ",");

        List<Object> listParam = new LinkedList<Object>();
        List<Class<?>> listType = new LinkedList<Class<?>>();
        
        // found param type and values
        for (String argument : parsedParam) {
            Object value = argument;
            /*
             * check if argument is contained inside binding
             */

            if (argument.matches(REGEX_STRING_ARG)){
                value = argument.substring(1, argument.length()-1);
                listParam.add(value);
                listType.add(String.class);
                continue;
            }
            
            if (binds.containsKey(argument)) {
                value = binds.get(argument);
                listParam.add(value);
                listType.add(value.getClass());
                continue;
            }
            // dummy algo, try to determine type of the argument
            // if exception throwned try another type of object
            try {
                value = Double.parseDouble(argument);
            } catch (Exception e) {
                try {
                    value = Integer.parseInt(argument);
                } catch (Exception ee) {
                    try {
                        value = Boolean.parseBoolean(argument);
                    } catch (Exception eee) {

                    }
                }
            }
            listParam.add(value);
            listType.add(value.getClass());
        }

        Object[] args = listParam.toArray();

        Class<?>[] types = ArrayUtil.toArray(listType, Class.class);

        try {

            Method methodBindin = oo.getClass().getMethod(methodName, types);

            result = methodBindin.invoke(oo, args).toString();

        } catch (Exception e) {
            log.debug("Method "+methodName+ " not found on:" + oo);
        }

        return result;

    }

}

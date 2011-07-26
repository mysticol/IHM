package org.nuiton.wikitty.publication.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public class HtmlScriptEngineFactory implements ScriptEngineFactory {

    static protected List<String> extensions;
    static protected List<String> names;
    static protected List<String> mimesTypes;

    static {
        extensions = new ArrayList<String>(5);
        extensions.add("xhtml");
        extensions.add("xht");
        extensions.add("xml");
        extensions.add("html");
        extensions.add("htm");
        extensions = Collections.unmodifiableList(extensions);

        names = new ArrayList<String>(2);
        names.add("HtmlScriptEngine");
        names.add("WikittyPublicationHtmlEngine");
        names = Collections.unmodifiableList(names);

        mimesTypes = new ArrayList<String>(2);
        mimesTypes.add("application/xhtml+xml");
        mimesTypes.add("text/html");

        mimesTypes = Collections.unmodifiableList(mimesTypes);
    }

    @Override
    public String getEngineName() {
        return "HTML";

    }

    @Override
    public String getEngineVersion() {
        return "0.1";
    }

    @Override
    public List<String> getExtensions() {
        return extensions;
    }

    @Override
    public List<String> getMimeTypes() {
        return mimesTypes;
    }

    @Override
    public List<String> getNames() {
        return names;
    }

    @Override
    public String getLanguageName() {
        return "HTML";
    }

    @Override
    public String getLanguageVersion() {
        return "5.0";
    }

    @Override
    public Object getParameter(String key) {

        if (key.equals(ScriptEngine.ENGINE)) {
            return this.getScriptEngine();
        }
        if (key.equals(ScriptEngine.ENGINE_VERSION)) {
            return this.getEngineVersion();
        }
        if (key.equals(ScriptEngine.NAME)) {
            return this.getEngineName();
        }
        if (key.equals(ScriptEngine.LANGUAGE)) {
            return this.getLanguageName();
        }
        if (key.equals(ScriptEngine.LANGUAGE_VERSION)) {
            return this.getLanguageVersion();
        }

        throw new IllegalArgumentException("Invalid key");

    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        String ret = obj + "." + m + "(";
        int len = args.length;
        if (len == 0) {
            ret += ")";
            return ret;
        }

        for (int i = 0; i < len; i++) {
            ret += args[i];
            if (i != len - 1) {
                ret += ",";
            } else {
                ret += ")";
            }
        }
        return ret;
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return toDisplay;
    }

    @Override
    public String getProgram(String... statements) {
        int len = statements.length;
        String ret = "";
        for (int i = 0; i < len; i++) {
            ret += statements[i] + ";";
        }

        return ret;
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new HtmlScriptEngine();
    }
}

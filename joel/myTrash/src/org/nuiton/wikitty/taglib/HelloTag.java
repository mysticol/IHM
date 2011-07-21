package org.nuiton.wikitty.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import org.nuiton.wikitty.entities.Wikitty;

public class HelloTag extends TagSupport {

    private Map attributes = new HashMap();

    protected String valeur = "";
    
    protected String name ="";
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Wikitty wikity;

    public Wikitty getWikity() {
        return wikity;
    }

    public void setWikity(Wikitty wikity) {
        this.wikity = wikity;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public int doStartTag() throws JspException {

        try {
            pageContext.getOut().println(
                    "Hello World ! " + valeur + wikity.getId());
            System.out.println(name);
        } catch (IOException e) {
            throw new JspException("I/O Error", e);
        }
        return SKIP_BODY;
    }

  
}
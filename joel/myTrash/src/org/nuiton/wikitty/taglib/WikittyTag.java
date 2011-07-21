package org.nuiton.wikitty.taglib;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.entities.FieldType;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyExtension;

public class WikittyTag extends SimpleTagSupport {

    protected Wikitty wikitty;
    protected WikittyProxy proxy;
    protected String url;
    protected String type;

    public Wikitty getWikitty() {
        return wikitty;
    }

    public void setWikitty(Wikitty wikitty) {
        this.wikitty = wikitty;
    }

    public WikittyProxy getProxy() {
        return proxy;
    }

    public void setProxy(WikittyProxy proxy) {
        this.proxy = proxy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        String result = formTemplateEditWikitty();

        out.print(result);
    }

    protected String formTemplateEditWikitty() {
        String result = "";

       
        result += "<form class=\"edit\" action=\"  " + url
                + " \" method=\"post\" enctype=\"multipart/form-data\" >";
        result += "<input type=\"hidden\" name=\"id\" value=\" "
                + wikitty.getId() + " \" />";
        result += "<input type=\"hidden\" name=\"version\" value=\" "
                + wikitty.getVersion() + "\" />";
        result += "<input type=\"hidden\" name=\"extensions\" value=\" "
                + wikitty.getExtensionNames() + " \"/>";
        result += "<fieldset>";
        result += "<legend><span class=\"legend\">Extensions</span></legend>";
        result += "Current extension:  " + wikitty.getExtensionNames();

        Collection<String> allExt = proxy.getAllExtensionIds();
        if (allExt != null && allExt.size() > 0) {

            result += "<select name=\"newExtension\" size=\"1\">";
            result += "<option value=\"\" selected=\"true\"></option>";

            for (String extId : allExt) {
                String extName = WikittyExtension.computeName(extId);
                if (!wikitty.hasExtension(extName)) {

                    result += "<option value=\" " + extName + " \"> " + extName
                            + " </option>";

                }
            }

            result += "</select>";

        }

        result += "<input type=\"submit\" name=\"addExtension\" value=\"Add\" />";
        result += "</fieldset>";

        int index = 0;
        for (WikittyExtension ext : wikitty.getExtensions()) {
            String extName = ext.getName();

            result += "<fieldset>";
            result += "<legend><span class=\"legend\">" + extName
                    + " </span></legend>";

            for (String fieldName : ext.getFieldNames()) {
                FieldType fieldType = wikitty.getExtension(extName)
                        .getFieldType(fieldName);

                result += "<div>";
                result += "<label for=\"" + extName + "." + fieldName
                        + "\" tabindex=\" " + (++index)
                        + " \"><span class=\"label\">" + fieldName
                        + "</span></label>";

                switch (fieldType.getType()) {
                case BINARY:

                    result += "<input type=\"hidden\" name=\" " + extName + "."
                            + fieldName + "\" value=\"BINARY\" />";
                    result += "<input type=\"file\" name=\"File\" label =\"File\"/>";

                    break;
                case BOOLEAN:
                    boolean valueBool = wikitty.getFieldAsBoolean(
                            ext.getName(), fieldName);
                    String checked = valueBool ? "checked='true'" : "";

                    result += "<input type=\"checkbox\" name=\" " + extName
                            + "." + fieldName + " \" value=\"true\"  "
                            + checked + " />";

                    break;
                default:
                    Object valueObject = wikitty.getFieldAsObject(
                            ext.getName(), fieldName);
                    String valueString = "";
                    String checkedNull = "";
                    if (valueObject != null) {
                        valueString = String.valueOf(valueObject);
                    } else {
                        checkedNull = "checked='true'";
                    }

                    valueString = StringEscapeUtils.escapeHtml(valueString);
                    if (valueString.contains("\n")
                            || "true"
                                    .equals(fieldType.getTagValue("multiline"))) {

                        result += "<textarea id=\" " + extName + "."
                                + fieldName
                                + " \" cols=\"80\" rows=\"20\" name=\" "
                                + extName + "." + fieldName
                                + " \"> "+valueString +" </textarea>";

                    } else {

                        result += "<input type=\"text\" name=\" " + extName
                                + "." + fieldName
                                + " \" value=\" "+valueString +"\" />";

                    }

                    result += "<input type=\"checkbox\" name=\"isNull- "
                            + extName + "." + fieldName
                            + " \" value=\"true\"  " + checkedNull + "/>(null)";

                }

                result += fieldType.toDefinition("");
                result += "</div>";

            }

            result += "</fieldset>";

        }

        result += "<input type=\"submit\" name=\"store\" value=\"Store\" />";
        result += "<input type=\"submit\" name=\"delete\" value=\"Delete\" />";

        result += "</form>";

        return result;
    }

}

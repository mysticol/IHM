package tempagain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nuiton.util.StringUtil;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyUtil;
import org.nuiton.wikitty.entities.FieldType;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyExtension;
import org.nuiton.wikitty.entities.WikittyImpl;
import org.nuiton.wikitty.entities.FieldType.TYPE;
import org.nuiton.wikitty.publication.entities.WikittyPubData;
import org.nuiton.wikitty.publication.entities.WikittyPubDataHelper;
import org.nuiton.wikitty.search.Criteria;
import org.nuiton.wikitty.search.operators.Element;

import com.opensymphony.xwork2.ActionContext;

public class PublicationActionEdit  {

//    /**
//     * 
//     */
//    private static final long serialVersionUID = -590087371230933701L;
//
//    // protected String newExtension;
//
//    protected Wikitty wikitty;
//    protected Map<String, Object> wikittyFieldMap;
//
//    static public PublicationActionEdit getAction() {
//        return (PublicationActionEdit) ActionContext.getContext().get(
//                CONTEXT_ACTION_KEY);
//    }
//
//    @Override
//    public String execute() throws Exception {
//        WikittyProxy proxy = getWikittyPublicationProxy();
//
//        String localId = getArgument("id", "");
//        if (localId.equals("")) {
//            System.out.println("id vide");
//            List<String> argsString = new ArrayList<String>();
//            String args = ActionContext.getContext().getParameters()
//                    .get(ARGS_KEY).toString();
//            String[] argsTab = StringUtil.split(args, SEPARATOR);
//
//            for (String arg : argsTab) {
//                argsString.add(arg);
//            }
//
//            // recherche du Wikitty a editer ou creation d'un nouveau si
//            // necessaire
//            Criteria criteria = searchCriteria(argsString);
//            if (criteria != null) {
//                System.out.println("criteria");
//                wikitty = proxy.findByCriteria(criteria);
//            }
//
//        } else {
//
//            if (wikitty!=null && !wikitty.getId().equals(localId)) {
//                wikitty = proxy.restore(localId);
//            }
//        }
//
//        if (wikitty == null){
//        wikitty = new WikittyImpl();
//        }
//       // doAction();
//
//        System.out.println(ActionContext.getContext().getParameters());
//
//        return SUCCESS;
//    }
//
//    public void doAction() {
//
//        WikittyProxy proxy = getWikittyPublicationProxy();
//
//        Map<String, Object> param = ActionContext.getContext().getParameters();
//
//        if (param.containsKey("delete")) {
//            // on nous demande supprimer le wikitty, on l'efface et on
//            // affichera un wikitty vide
//            String id = getArgument("id", "");
//            proxy.delete(id);
//            // apres un effacement on reprend l'edition d'un tout nouveau
//            // wikitty
//            wikitty = new WikittyImpl();
//        } else {
//
////            // si on ne retrouve pas le wikitty, mais qu'il vient d'etre
////            // cree
////            // pour l'edition, on recree un wikitty avec ce meme identifiant
////            if (wikitty == null && "0.0".equals(getArgument("version", ""))) {
////                // c'est un nouvel objet, il n'a pas encore ete sauve, mais
////                // on
////                // veut le faire
////                String id = getArgument("id", "");
////
////                wikitty = new WikittyImpl(id);
////            }
//
////            if (wikitty == null) {
////                // si le wikitty est null, et qu'on etait pas en edition
////                // cela signifie qu'on ne retrouve pas le wikitty a editer
////                // on creer un nouveau wikitty vide que l'on editera
////                wikitty = new WikittyImpl();
////            } else {
//                // on met a jour le wikitty avec les infos trouvees dans les
//                // arguments
//
//                // ajout des extensions deja existante si necessaire
//                String extensions = getArgument("extensions", null);
//                if (extensions != null) {
//                    String[] exts = StringUtil.split(extensions
//                            .replace("[", "").replace("]", ""), ",");
//                    for (String extName : exts) {
//                        WikittyExtension ext = proxy
//                                .restoreExtensionLastVersion(extName);
//                        if (ext != null) {
//                            wikitty.addExtension(ext);
//                        }
//                    }
//                }
//
//                // ajout de l'extension demande par l'utilisateur
//                String extName = getArgument("newExtension", null);
//                if (extName != null && !"".equals(extName)) {
//                    WikittyExtension ext = proxy
//                            .restoreExtensionLastVersion(extName);
//                    if (ext != null) {
//                        wikitty.addExtension(ext);
//                    }
//                }
//
//                Map<String, Object> args1 = new HashMap<String, Object>();
//                args1.putAll(getFieldArguments());
//
//                for (Map.Entry<String, Object> field : args1.entrySet()) {
//                    String key = field.getKey();
//                    Object value = null;
//                    if (key.contains(WikittyUtil.FQ_FIELD_NAME_SEPARATOR)) {
//                        String ext = WikittyExtension.extractExtensionName(key);
//                        String fieldName = WikittyExtension
//                                .extractFieldName(key);
//
//                        if (wikitty.hasField(ext, fieldName)) {
//                            if (!"true".equals(getArgument("isNull-" + key,
//                                    "false"))) {
//
//                                value = field.getValue();
//                            }
//
//                            FieldType extFieldType = wikitty.getExtension(ext)
//                                    .getFieldType(fieldName);
//                            if (extFieldType.isCollection()
//                                    && extFieldType.getType() == TYPE.STRING
//                                    && value != null) {
//
//                                String valueString = value.toString();
//
//                                valueString = new String(valueString.substring(
//                                        1, valueString.length() - 1));
//
//                                Collection<String> list = new ArrayList<String>();
//
//                                String[] valuesString = StringUtil.split(
//                                        valueString, ",");
//
//                                for (String element : valuesString) {
//                                    list.add(element.trim());
//                                }
//
//                                wikitty.setField(ext, fieldName, list);
//
//                            } else {
//                                wikitty.setField(ext, fieldName, value);
//                            }
//                            // si w est un WikittyPubData on essai de mettre a
//                            // jour si besoin les champs mimetype et name
//                            if (ext.equals(WikittyPubData.EXT_WIKITTYPUBDATA)
//                                    && fieldName
//                                            .equals(WikittyPubData.FIELD_WIKITTYPUBDATA_CONTENT)) {
//                                if (null == WikittyPubDataHelper
//                                        .getName(wikitty)) {
//                                    WikittyPubDataHelper.setName(
//                                            wikitty,
//                                            String.valueOf(args1.get(key
//                                                    + "-filename")));
//                                }
//                                if (null == WikittyPubDataHelper
//                                        .getMimeType(wikitty)) {
//                                    WikittyPubDataHelper.setMimeType(
//                                            wikitty,
//                                            String.valueOf(args1.get(key
//                                                    + "-contentType")));
//                                }
//                            }
//                        }
//                    }
//                }
//
//                if (param.containsKey("store")) {
//                    // on nous demande la sauvegarde
//                    proxy.store(wikitty);
//                }
//            }
//        }
//    
//
//    protected Map<String, Object> getFieldArguments() {
//        wikittyFieldMap = new HashMap<String, Object>();
//        for (Entry<String, Object> en : ActionContext.getContext()
//                .getParameters().entrySet()) {
//
//            if (en.getKey().startsWith("Wikitty")) {
//
//                String value = null;
//
//                if (en.getValue() instanceof String[]) {
//                    for (String occu : (String[]) en.getValue()) {
//                        value += occu;
//                    }
//                } else {
//                    value = String.valueOf(en.getValue());
//                }
//
//                wikittyFieldMap.put(en.getKey(), value);
//            }
//
//        }
//
//        return wikittyFieldMap;
//    }
//
//    public Wikitty getWikitty() {
//        return wikitty;
//    }
//
//    public void setWikitty(Wikitty wikitty) {
//        this.wikitty = wikitty;
//    }
//
//    public List<String> getWikittyExtentionAviable() {
//        List<String> result = new ArrayList<String>();
//        result.add("ponay");
//        result.add("machin");
//
//        return result;
//    }
//
//    // public String getNewExtension() {
//    // return newExtension;
//    // }
//    //
//    // /*
//    // * <s:combobox list="wikittyExtentionAviable" label="test"
//    // * name="newExtension" />
//    // */
//    // public void setNewExtension(String newExtension) {
//    // this.newExtension = newExtension;
//    // }
//
//    public void setWikittyProperty(String name, Object value) {
//        wikitty.setFqField(name, value);
//    }

}

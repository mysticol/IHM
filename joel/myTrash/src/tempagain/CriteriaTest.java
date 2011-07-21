package tempagain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.nuiton.wikitty.WikittyException;
import org.nuiton.wikitty.WikittyUtil;
import org.nuiton.wikitty.entities.FieldType;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyLabel;
import org.nuiton.wikitty.entities.FieldType.TYPE;
import org.nuiton.wikitty.publication.entities.WikittyPubData;
import org.nuiton.wikitty.publication.entities.WikittyPubText;
import org.nuiton.wikitty.search.Criteria;
import org.nuiton.wikitty.search.PagedResult;
import org.nuiton.wikitty.search.Search;
import org.nuiton.wikitty.search.operators.And;
import org.nuiton.wikitty.search.operators.AssociatedRestriction;
import org.nuiton.wikitty.search.operators.Between;
import org.nuiton.wikitty.search.operators.BinaryOperator;
import org.nuiton.wikitty.search.operators.Contains;
import org.nuiton.wikitty.search.operators.Element;
import org.nuiton.wikitty.search.operators.False;
import org.nuiton.wikitty.search.operators.In;
import org.nuiton.wikitty.search.operators.Keyword;
import org.nuiton.wikitty.search.operators.Not;
import org.nuiton.wikitty.search.operators.Null;
import org.nuiton.wikitty.search.operators.Or;
import org.nuiton.wikitty.search.operators.Restriction;
import org.nuiton.wikitty.search.operators.RestrictionName;
import org.nuiton.wikitty.search.operators.True;


public class CriteriaTest {

    static public void main(String[] args) {

        boolean isRecur=true;
        String label = "wp";
        
        // Construct the criteria
        Criteria labelCriteria;
        Search mainRequest = Search.query();
        Search subRoqu = mainRequest.or();

        // must have the type of wikittypubtext/wikittypubdata
        subRoqu.exteq(WikittyPubText.EXT_WIKITTYPUBTEXT).exteq(
                WikittyPubData.EXT_WIKITTYPUBDATA);
        if (isRecur) {

            // and extension with the name that containt the label (recursivity)
            labelCriteria = mainRequest.exteq(WikittyLabel.EXT_WIKITTYLABEL)
                    .sw(WikittyLabel.FQ_FIELD_WIKITTYLABEL_LABELS, label)
                    .criteria();

        } else {

            // and extension with the name strictly equals to the label (no
            // recursivity)
            labelCriteria = mainRequest.exteq(WikittyLabel.EXT_WIKITTYLABEL)
                    .eq(WikittyLabel.FQ_FIELD_WIKITTYLABEL_LABELS, label)
                    .criteria();

        }
        
        
        
        System.out.println(((BinaryOperator)labelCriteria.getRestriction()).getValue());
        
        
        
    }
    

    public boolean checkRestriction(Restriction restriction, Wikitty w) {
        if (restriction instanceof BinaryOperator) {
            BinaryOperator binOp = (BinaryOperator) restriction;

            String fqfieldName = binOp.getElement().getName();

            // Checks on extensions
            if (Element.ELT_EXTENSION.equals(fqfieldName)) {
                boolean checked = false;

                switch (restriction.getName()) {
                case NOT_EQUALS:
                    checked = !w.getExtensionNames().contains(binOp.getValue());
                    break;
                case EQUALS:
                    checked = w.getExtensionNames().contains(binOp.getValue());
                    break;
                }

                return checked;

                // Checks on id
            } else if (Element.ELT_ID.equals(fqfieldName)) {

                boolean checked = false;

                switch (restriction.getName()) {
                case NOT_EQUALS:
                    checked = !w.getId().equals(binOp.getValue());
                    break;
                case EQUALS:
                    checked = w.getId().equals(binOp.getValue());
                    break;
                }

                return checked;
            }

            // si les wikitty n'ont meme pas l'extension concerné
            // Le check restriction, ne doit pas tester les champs
            // si les wikitty n'ont meme pas l'extension concerné
            String[] extName = fqfieldName.split("\\.");
            if (!w.hasField(extName[0], extName[1])) {

                // return true in case of not equals
                if (RestrictionName.NOT_EQUALS == restriction.getName()) {
                    return true;
                }

                return false;
            }
            // recupere la valeur dans le wikitty
            Object o = w.getFqField(fqfieldName);
            // recupere le type de la valeur
            FieldType t = w.getFieldType(fqfieldName);
            // convertie la valeur a verifier dans le meme type que la valeur
            // du wikitty
            Object value = binOp.getValue();
            if (!(value instanceof Collection) && t.isCollection()) {
                // on doit encapsuler dans une collection, car la creation
                // de la requete ajoute autant de v == o && ... que de valeurs
                // dans la collection (champs multi-value solr). Mais
                // dans le inmemory on doit retrouve des collections et non pas
                // des objets seuls :(
                value = Collections.singleton(value);
            }
            value = t.getValidValue(value);
            boolean checked = false;

            switch (restriction.getName()) {
            case EQUALS:
                if (value instanceof String && o instanceof String) {

                    String pattern = (String) value;
                    pattern = pattern.replace("*", "\\p{ASCII}*");
                    pattern = pattern.replace("?", "\\p{ASCII}");

                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher((String) o);
                    checked = m.matches();
                } else {
                    checked = value.equals(o);
                }
                break;
            case LESS:
                checked = ((Comparable) o).compareTo(value) < 0;
                break;
            case LESS_OR_EQUAL:
                checked = ((Comparable) o).compareTo(value) <= 0;
                break;
            case GREATER:
                checked = ((Comparable) o).compareTo(value) > 0;
                break;
            case GREATER_OR_EQUAL:
                checked = ((Comparable) o).compareTo(value) >= 0;
                break;
            case NOT_EQUALS:
                checked = !value.equals(o);
                break;
            case ENDS_WITH:
                if (t.getType() != TYPE.STRING) {
                    throw new WikittyException(
                            "Can't search for contents that 'ends with' on attribute type different of String. "
                                    + "Attribute "
                                    + fqfieldName
                                    + " is "
                                    + t.getType().name());
                }
                checked = ((String) o).endsWith((String) value);
                break;
            case STARTS_WITH:
                if (t.getType() != TYPE.STRING) {
                    throw new WikittyException(
                            "Can't search for contents that 'starts with' on attribute type different of String. "
                                    + "Attribute "
                                    + fqfieldName
                                    + " is "
                                    + t.getType().name());
                }
                checked = ((String) o).startsWith((String) value);
                break;
            }
            return checked;
        } else if (restriction instanceof Null) {
            Null nullRes = (Null) restriction;

            String fqfieldName = nullRes.getFieldName();

            // check my wikitty got the right extension before doing anything.
            String[] extName = fqfieldName.split("\\.");
            if (!w.hasField(extName[0], extName[1])) {
                return false;
            }
            // get the value in the wikitty
            Object o = w.getFqField(fqfieldName);

            // No null on extensions, always return false
            if (fqfieldName.equals(Element.ELT_EXTENSION)) {
                return false;
            }

            // No null on ids, always return false
            if (fqfieldName.equals(Element.ELT_ID)) {
                return false;
            }

            boolean checked = false;

            switch (nullRes.getName()) {
            case IS_NULL:
                checked = (o == null);
                break;
            case IS_NOT_NULL:
                checked = (o != null);
                break;
            }

            return checked;

        } else if (restriction instanceof In) {
            In in = (In) restriction;
            String fqfieldName = in.getElement().getName();
            String testedValue = String.valueOf(w.getFqField(fqfieldName));
            for (String value : in.getValue()) {
                if (testedValue.equals(value)) {
                    return true;
                }
            }

            return false;

        } else if (restriction instanceof True) {
            return true;
        } else if (restriction instanceof False) {
            return false;
        } else if (restriction instanceof Contains) {
            Contains contains = (Contains) restriction;

            String fqfieldName = contains.getElement().getName();
            List<String> values = contains.getValue();

            String extension = WikittyUtil
                    .getExtensionNameFromFQFieldName(fqfieldName);
            String fieldName = WikittyUtil
                    .getFieldNameFromFQFieldName(fqfieldName);

            if (!w.hasField(extension, fieldName)) {
                return false;
            }

            // Get field as string and then split it to take into account not
            // multivalued fields.
            String testedValuesAsString = w.getFieldAsString(extension,
                    fieldName);

            if ('[' == testedValuesAsString.charAt(0)) {
                testedValuesAsString = testedValuesAsString.substring(1,
                        testedValuesAsString.length());
            }

            List<String> testedValues = Arrays.asList(testedValuesAsString
                    .split(","));

            for (Object value : values) {
                if (!testedValues.contains(String.valueOf(value))) {
                    return false;
                }
            }

            return true;

        } else if (restriction instanceof And) {
            And and = (And) restriction;
            for (Restriction sub : and.getRestrictions()) {
                if (!checkRestriction( sub, w)) {
                    return false;
                }
            }
            return true;
        } else if (restriction instanceof Or) {
            Or or = (Or) restriction;
            for (Restriction sub : or.getRestrictions()) {
                if (checkRestriction( sub, w)) {
                    return true;
                }
            }
            return false;
        } else if (restriction instanceof Keyword) {
            Keyword keyword = (Keyword) restriction;

            String value = keyword.getValue();

            for (String fieldName : w.getAllFieldNames()) {
                String testedValue = String.valueOf(w.getFqField(fieldName));
                if (testedValue.contains(value)) {
                    return true;
                }
            }
            return false;
        } else if (restriction instanceof Not) {
            Not or = (Not) restriction;
            Restriction sub = or.getRestriction();
            return !checkRestriction( sub, w);
        } else if (restriction instanceof AssociatedRestriction) {

            AssociatedRestriction ass = (AssociatedRestriction) restriction;

            String fqfieldName = ass.getElement().getName();

            // check my wikitty got the right extension before doing anything.
            String[] extName = fqfieldName.split("\\.");
            if (!w.hasField(extName[0], extName[1])) {
                return false;
            }
            // get the value in the wikitty, it is a wikitty's id
            Object o = w.getFqField(fqfieldName);

            // Get sub-restriction
            Restriction sub = ass.getRestriction();

            Criteria associatedSearch = new Criteria();
            associatedSearch.setRestriction(sub);

            // find everything that validate the sub-restriction
            PagedResult<String> associatedResult = findAllByCriteria(
                     associatedSearch);

            List<String> associatedList = associatedResult.getAll();

            // Check that my field is contained in the sub-restriction results.
            return associatedList.contains(String.valueOf(o));
        } else if (restriction instanceof Between) {

            Between op = (Between) restriction;

            Object max = op.getMax();
            Object min = op.getMin();

            // No between on extensions, always return false
            if (op.getElement().getName().equals(Element.ELT_EXTENSION)) {
                return false;
            }

            // No between on ids, always return false
            if (op.getElement().getName().equals(Element.ELT_ID)) {
                return false;
            }

            String fqfieldName = op.getElement().getName();

            // si les wikitty n'ont meme pas l'extension concerné
            // Le check restriction, ne doit pas tester les champs
            // si les wikitty n'ont meme pas l'extension concerné
            String[] extName = fqfieldName.split("\\.");
            if (!w.hasField(extName[0], extName[1])) {
                return false;
            }

            // recupere la valeur dans le wikitty
            Object o = w.getFqField(fqfieldName);

            // recupere le type de la valeur
            FieldType t = w.getFieldType(fqfieldName);

            if (!(min instanceof Collection) && t.isCollection()) {
                // on doit encapsuler dans une collection, car la creation
                // de la requete ajoute autant de v == o && ... que de valeurs
                // dans la collection (champs multi-value solr). Mais
                // dans le inmemory on doit retrouve des collections et non pas
                // des objets seuls :(
                min = Collections.singleton(min);
            }
            min = t.getValidValue(min);

            if (!(max instanceof Collection) && t.isCollection()) {
                // on doit encapsuler dans une collection, car la creation
                // de la requete ajoute autant de v == o && ... que de valeurs
                // dans la collection (champs multi-value solr). Mais
                // dans le inmemory on doit retrouve des collections et non pas
                // des objets seuls :(
                max = Collections.singleton(max);
            }
            max = t.getValidValue(max);

            return ((Comparable) o).compareTo(min) >= 0
                    && ((Comparable) o).compareTo(max) <= 0;
        } else {
            throw new UnsupportedOperationException(restriction.getName()
                    + " Search Not yet implemented");
        }
    }
    
    
//dummy code
    public PagedResult<String> findAllByCriteria(Criteria criteria) {
        // throw new UnsupportedOperationException("Not supported yet.");
        int firstIndex = criteria.getFirstIndex();
        int endIndex = criteria.getEndIndex();
        List<String> ids = new LinkedList<String>();
        int currentIndex = 0;
       
        //dummy iterate on nothing
        for (Entry<String, Wikitty> entry : new ArrayList<Map.Entry<String, Wikitty>>()) {
            Wikitty w = entry.getValue();
            String id = entry.getKey();
            Restriction dto = criteria.getRestriction();
            if (!w.isDeleted() && checkRestriction(dto, w)) {
                currentIndex++;
                if (currentIndex > firstIndex) {
                    ids.add(id);
                }
                if (endIndex >= 0 && currentIndex >= endIndex) {
                    break;
                }
            }
        }
        return new PagedResult<String>(firstIndex, ids.size(), criteria.getRestriction().toString(), null, ids);
    }

}

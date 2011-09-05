import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.wikitty.ScriptEvaluator;
import org.nuiton.wikitty.publication.AbstractDecoredClass;
import org.nuiton.wikitty.entities.*;
import org.nuiton.wikitty.publication.entities.*;
import org.nuiton.wikitty.publication.*;
import java.util.*;

public class clearProxy extends AbstractDecoredClass {
    public Object eval(Map<String, Object> bindings) throws Exception {
        PublicationContext wpContext = (PublicationContext) bindings
                .get("wpContext");
        EvalInterface wpEval = (EvalInterface) bindings.get("wpEval");
        String wpPage = (String) bindings.get("wpPage");
        List<String> wpSubContext = (List<String>) bindings.get("wpSubContext");
        Wikitty wpWikitty = (Wikitty) bindings.get("wpWikitty");
        String result = "Proxy Clear";
        wpContext.getWikittyProxy().clear();

        String youhou = "youhou " + wpPage;

        return youhou;

    }
}

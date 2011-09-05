import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.wikitty.ScriptEvaluator;
import org.nuiton.wikitty.publication.externalize.AbstractDecoredClass;
import org.nuiton.wikitty.entities.*;
import org.nuiton.wikitty.publication.entities.*;
import org.nuiton.wikitty.publication.action.*;
import java.util.*;

public class Home extends AbstractDecoredClass {
    public Object eval(Map<String, Object> bindings) throws Exception {
        Object result = null;
        String content = "try {\n\nvar result =\n\"\"+\n\"    <div class='menu'>\\n\"+\nwpEval.doAction(wpContext, \"WikiMenu\")+\n\"    </div>\\n\"+\n\"    <h1>Bonjour Les Lutins,</h1>\\n\"+\n\"    Bienvenue sur le Wikitty Wiki\\n\";wpContext.setContentType(\"text/html\");\nresult;\n} catch (eee) {\nwpContext.setContentType(\"text/plain\");\neee\n}\n";
        String mimeType = "text/javascript";
        String criteriaName = "elt_id:5e4eb8bf-3cc4-43ea-9ffc-8797f53f72b2";
        result = ScriptEvaluator.eval(null, criteriaName, content, mimeType,
                bindings);
        return result;
    }
}

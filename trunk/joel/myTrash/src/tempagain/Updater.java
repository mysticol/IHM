package tempagain;

import java.util.List;

import org.nuiton.util.ApplicationConfig;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyServiceFactory;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyLabel;
import org.nuiton.wikitty.entities.WikittyLabelHelper;
import org.nuiton.wikitty.entities.WikittyUserHelper;
import org.nuiton.wikitty.entities.WikittyUserImpl;
import org.nuiton.wikitty.publication.entities.WikittyPubData;
import org.nuiton.wikitty.publication.entities.WikittyPubText;
import org.nuiton.wikitty.search.Criteria;
import org.nuiton.wikitty.search.Search;

public class Updater {

    static public void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();

        boolean isRecur=true;
        String label = "wp";
        
        applicationConfig.setOption("wikitty.WikittyService.components",
                "org.nuiton.wikitty.services.WikittyServiceCajoClient");

        applicationConfig.setOption("wikitty.service.server.url",
                "http://localhost:2222/wikitty");

        // real code:
        WikittyProxy remoteWikittyService = new WikittyProxy(
                WikittyServiceFactory.buildWikittyService(applicationConfig));

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
       
        
        
        
        List<Wikitty> wik = remoteWikittyService.findAllByCriteria(labelCriteria).getAll();
        
        for (Wikitty wi: wik){
            WikittyLabelHelper.addLabels(wi, "pony");
            wi.addExtension(WikittyUserImpl.extensionWikittyUser);
            WikittyUserHelper.setLogin(wi, "pony aussi");
            
        }
    
        remoteWikittyService.storeWikitty(wik);
    }

}

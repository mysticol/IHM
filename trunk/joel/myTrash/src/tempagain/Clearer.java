package tempagain;

import org.nuiton.util.ApplicationConfig;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyServiceFactory;

public class Clearer {

    /**
     * @param args
     */
    public static void main(String[] args) {
       ApplicationConfig applicationConfig = new ApplicationConfig();
       
            
       applicationConfig.setOption("wikitty.WikittyService.components",
       "org.nuiton.wikitty.services.WikittyServiceCajoClient");

       applicationConfig.setOption("wikitty.service.server.url", "http://localhost:1111/wikitty");

        // real code:
        WikittyProxy remoteWikittyService = new WikittyProxy(
                WikittyServiceFactory.buildWikittyService(applicationConfig));

        remoteWikittyService.clear();
        
        applicationConfig.setOption("wikitty.service.server.url", "http://localhost:2222/wikitty");
        remoteWikittyService = new WikittyProxy(
                WikittyServiceFactory.buildWikittyService(applicationConfig));
       

        
        remoteWikittyService.clear();
    }

}

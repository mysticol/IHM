package tempagain;

import java.util.ArrayList;

import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.ArgumentsParserException;
import org.nuiton.util.StringUtil;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyServiceFactory;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyImpl;
import org.nuiton.wikitty.entities.WikittyUserHelper;
import org.nuiton.wikitty.entities.WikittyUserImpl;

public class CreateWikitty {

    /**
     * @param args
     * @throws ArgumentsParserException
     */
    public static void main(String[] args) throws ArgumentsParserException {

        ApplicationConfig applicationConfig = new ApplicationConfig();

        applicationConfig.setOption("wikitty.WikittyService.components",
                "org.nuiton.wikitty.services.WikittyServiceCajoClient");

        applicationConfig.setOption("wikitty.service.server.url",
                "http://localhost:2222/wikitty");

        // real code:
        WikittyProxy remoteWikittyService = new WikittyProxy(
                WikittyServiceFactory.buildWikittyService(applicationConfig));


        Wikitty user = new WikittyImpl();
        user.addExtension(WikittyUserImpl.extensionWikittyUser);
        WikittyUserHelper.setLogin(user, "user");
        WikittyUserHelper.setPassword(user, StringUtil.encodeMD5("user"));

               
        
        ArrayList<Wikitty> lit= new ArrayList<Wikitty>();
        lit.add(user);
        
        
        remoteWikittyService.getWikittyService().store(null,lit, false);

        
       
        
        
        
        
    }
    
}

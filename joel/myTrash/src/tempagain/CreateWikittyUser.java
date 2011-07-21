package tempagain;

import java.util.ArrayList;

import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.ArgumentsParserException;
import org.nuiton.util.StringUtil;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyServiceFactory;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyImpl;
import org.nuiton.wikitty.entities.WikittyTokenImpl;
import org.nuiton.wikitty.entities.WikittyUser;
import org.nuiton.wikitty.entities.WikittyUserHelper;
import org.nuiton.wikitty.entities.WikittyUserImpl;

public class CreateWikittyUser {

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

        ApplicationConfig applicationConfig21 = new ApplicationConfig();

        applicationConfig21.setOption("wikitty.WikittyService.components",
                "org.nuiton.wikitty.services.WikittyServiceCajoClient");

        applicationConfig21.setOption("wikitty.service.server.url",
                "http://localhost:1111/wikitty");

        // real code:
        WikittyProxy remoteWikittyService2 = new WikittyProxy(
                WikittyServiceFactory.buildWikittyService(applicationConfig21));

        // WikittyUser user = new WikittyUserImpl();

        Wikitty user = new WikittyImpl();
        user.addExtension(WikittyUserImpl.extensionWikittyUser);
        WikittyUserHelper.setLogin(user, "user");
        WikittyUserHelper.setPassword(user, StringUtil.encodeMD5("user"));

        
        
        
        ArrayList<Wikitty> lit= new ArrayList<Wikitty>();
        lit.add(user);
        
        
       remoteWikittyService.getWikittyService().store(null,lit, false);

        Wikitty user2 = new WikittyImpl();
        user2.addExtension(WikittyUserImpl.extensionWikittyUser);
        WikittyUserHelper.setLogin(user2, "chausette");
        WikittyUserHelper.setPassword(user2, "chausette");
                
                //StringUtil.encodeMD5("user"));

        ArrayList<Wikitty> lit2= new ArrayList<Wikitty>();
        lit2.add(user2);
       
        
        
        
        remoteWikittyService2.getWikittyService().store(null,lit2, false);

        /*
         * user.setLogin("Kows");
         * user.setPassword(StringUtil.encodeMD5("Kows"));
         */

    }

}

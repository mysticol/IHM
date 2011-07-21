package tempagain;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.activation.MimeTypeParameterList;

import org.nuiton.util.ApplicationConfig;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyServiceFactory;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.publication.WikittyPublicationConfig;
import org.nuiton.wikitty.search.Search;

public class Explor {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        
   ApplicationConfig applicationConfig = new ApplicationConfig();
        
        
        
     
        
        applicationConfig.setOption("wikitty.WikittyService.components",
        "org.nuiton.wikitty.services.WikittyServiceCajoClient");

        applicationConfig.setOption("wikitty.service.server.url", "http://localhost:1111/wikitty");

         // real code:
         WikittyProxy remoteWikittyService = new WikittyProxy(
                 WikittyServiceFactory.buildWikittyService(applicationConfig));
         
          
         //remoteWikittyService.login("chausette", "chausette");
     
         
         System.out.println(remoteWikittyService.findAllByCriteria(Search.query().keyword("*").criteria()).getAll().size());
         
         
         for (Wikitty w : remoteWikittyService.findAllByCriteria(Search.query().keyword("*").criteria()).getAll()){
             
             System.out.println(w == null);
             
         }
        
         
         for (String w : remoteWikittyService.findAllIdByCriteria(Search.query().keyword("*").criteria()).getAll()){
             
             System.out.println(w);
             
         }

    }

}

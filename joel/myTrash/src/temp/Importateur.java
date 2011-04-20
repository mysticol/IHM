package temp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.ArgumentsParserException;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyServiceFactory;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.publication.synchro.WikittyPublicationFileSystem;



public class Importateur {

 

 

    static protected ApplicationConfig applicationConfig;

    /*
     * 
     * need to set better name
     */
    


    static public String WITTY_SERVICE_KEY = "wikitty.service.server.url";

    static public String DIRECTORY_KEY = "directory";
    
    
    static public String HESSIAN_PROTOCOL_KEY = "hessian";

    static public String NO_RECURSION_KEY = "norecursion";

    /**
     * @param args
     * @throws ArgumentsParserException
     */
    static public void main(String[] args) throws Exception {

        /*
         * 
         */

        // on va creer un wikitty proxy pour le lien avec le wikitty qui stock
        // mes trucs
        // et on va avoir un wikittypublicationfilesystem pour stocker sur le
        // local et tout
        // soucis du wikittyFS c'est de savoir dans quel dossier il va taffer ?
        // a moins qu'il prenne un directory de travail

        /*
         * ws.properties :wikitty.service= http://www.adresse.com:8080
         * 
         * wikittypubs.properties script.js=numéroVersion7 id.script.js= id du
         * wikitty scripttut.js=numéroVersion id.scripttut.js= id du wikitty
         * image.png=numéroVersion id.image.png= id du wikitty label=
         * racine.directory2, racine.directory22
         */

        applicationConfig = new ApplicationConfig();

        // allias for the url of the wikitty service
        applicationConfig.addAlias("--ws", "--option", WITTY_SERVICE_KEY);

        applicationConfig.addAlias("--dir", "--option", DIRECTORY_KEY);

        /*
         * 
         * enumclass for initialisation
         */

        // allias for norecursion
        applicationConfig.addAlias("--norecursion", "--option",
                NO_RECURSION_KEY, "true");

        // allias for the protocole
        applicationConfig.addAlias("--hessian", "--option",
                HESSIAN_PROTOCOL_KEY, "true");

        // allias for all the action
        applicationConfig.addAlias("wp import", "--option", "import");
        applicationConfig.addAlias("wp checkout", "--option", "checkout");
        applicationConfig.addAlias("wp relocate", "--option", "relocate");
        applicationConfig.addAlias("wp commit", "--option", "commit");
        applicationConfig.addAlias("wp delete", "--option", "delete");
        applicationConfig.addAlias("wp update", "--option", "update");

        applicationConfig
                .addActionAlias("import",
                        "temp.Importateur#importToWikitty");

        applicationConfig
                .addActionAlias("checkout",
                        "org.nuiton.wikitty.publication.WikittyPublication#checkoutFromWikitty");

        applicationConfig
                .addActionAlias("relocate",
                        "org.nuiton.wikitty.publication.WikittyPublication#relocateWikitty");

        applicationConfig
                .addActionAlias("commit",
                        "org.nuiton.wikitty.publication.WikittyPublication#commitToWikitty");

        applicationConfig
                .addActionAlias("delete",
                        "org.nuiton.wikitty.publication.WikittyPublication#deleteFromWikitty");

        applicationConfig
                .addActionAlias("update",
                        "org.nuiton.wikitty.publication.WikittyPublication#updateFromWikitty");

        // parsing
        applicationConfig.parse(args);
        // execution
        applicationConfig.doAction(0);

    }

    /**
     * Method that import the content of a directory into a wikitty service
     */
    static public void importToWikitty() throws Exception {

        boolean noRecur = applicationConfig
                .getOptionAsBoolean(NO_RECURSION_KEY);
        File dir = applicationConfig.getOptionAsFile(DIRECTORY_KEY);
       // String wikittyService = applicationConfig.getOption(WITTY_SERVICE_KEY);

        // usage: wp --norecursion --ws http://truc.com import --dir /home/Manou

        applicationConfig.setOption("wikitty.WikittyService.components",
                "org.nuiton.wikitty.services.WikittyServiceCajoClient");

        System.out.println(applicationConfig
                .getOption("wikitty.service.server.url"));

        // real code:
        WikittyProxy remoteWikittyService = new WikittyProxy(
                WikittyServiceFactory.buildWikittyService(applicationConfig));

        remoteWikittyService.clear();
        // load the list of file
        List<File> toTransfert = listFile(dir, !noRecur);

        List<Wikitty> listWikitty = new ArrayList<Wikitty>();
        // transform file into wikitties

        for (File fileToTransform : toTransfert) {
            listWikitty.add(WikittyPublicationFileSystem.fileToWikitty(
                    fileToTransform, dir));
        }

        // send the wikitties
        remoteWikittyService.storeWikitty(listWikitty);


    }

    /*
   
     * the method linked: importToWikitty
     */
    static public void printDirectory(File dir, boolean recur) {

        System.out.println("<dir " + dir.getName() + ">");
        for (File child : dir.listFiles()) {
            if (child.isDirectory() && recur
                    && !child.getName().equals(WikittyPublicationFileSystem.PROPERTY_DIRECTORY)) {
                printDirectory(child, recur);
            } else if (!child.isDirectory()) {
                System.out.println(child.getAbsolutePath());
            }
        }
        System.out.println("</dir " + dir.getName() + ">");
    }

    /**
     * Method that checkout a label recursivly or not into a local directory
     * from a wikitty service
     *
    static public void checkoutFromWikitty(String label) throws Exception {

        boolean noRecur = applicationConfig
                .getOptionAsBoolean(WikittyPublication.NO_RECURSION_KEY);
        File dir = applicationConfig.getOptionAsFile(WikittyPublication.DIRECTORY_KEY);
        String wikittyService = applicationConfig.getOption(WikittyPublication.WITTY_SERVICE_KEY);
        boolean hessianProtocole = applicationConfig
                .getOptionAsBoolean(WikittyPublication.HESSIAN_PROTOCOL_KEY);

        log.info("checkout : wikittyservice: " + wikittyService
                + " noresursion=" + noRecur + " directory= "
                + dir.getAbsolutePath() + "Label a checkout " + label
                + "HessianProtocol=" + hessianProtocole);

        if (hessianProtocole) {
            applicationConfig.setOption("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceHessianClient");
        } else {
            applicationConfig.setOption("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceCajoClient");
        }

        WikittyProxy remoteWikittyService = new WikittyProxy(
                WikittyServiceFactory.buildWikittyService(applicationConfig));
        WikittyPublicationFileSystem localWikittyService = new WikittyPublicationFileSystem(
                dir, !noRecur, label);

        // Construct the criteria
        Criteria labelCriteria;
        Search mainRequest = Search.query();
        Search subRoqu = mainRequest.or();

        // must have the type of wikittypubtext/wikittypubdata
        subRoqu.exteq(WikittyPubText.EXT_WIKITTYPUBTEXT).exteq(
                WikittyPubData.EXT_WIKITTYPUBDATA);
        if (noRecur) {
            // and extension with the name strictly equals to the label (no
            // recursivity)
            labelCriteria = mainRequest.exteq(WikittyLabel.EXT_WIKITTYLABEL)
                    .eq(WikittyLabel.FQ_FIELD_WIKITTYLABEL_LABELS, label)
                    .criteria();

        } else {
            // and extension with the name that containt the label (recursivity)
            labelCriteria = mainRequest.exteq(WikittyLabel.EXT_WIKITTYLABEL)
                    .sw(WikittyLabel.FQ_FIELD_WIKITTYLABEL_LABELS, label)
                    .criteria();
        }

        // request to the proxy
        PagedResult<Wikitty> pageResult = remoteWikittyService
                .findAllByCriteria(labelCriteria);

        List<Wikitty> wikittiesToWrite = pageResult.getAll();

        // write the proper properties file!
        WikittyPublicationFileSystem.writeHomePropertyFile(dir);

        // write the wikities
        localWikittyService.store("", wikittiesToWrite, true);

        /*
         * obtain the list of wikittypub and write then in the file system with
         * the appropriate wikittyservice !
         */

        /*
         * on va commencer par vérifier les arguments ''wp checkout
         * [--norecursion] [url du WikittyService] [Label à extraire] [directory
         * local d'accueil]'' on doit avoir trois string dans le unparsed: url,
         * label, directory et potentiellement quelque chose dans le getoption
         * recursion
         *

    }*/

    static public List<File> listFile(File starts, boolean recursivly) {
        List<File> result = new ArrayList<File>();
        if (!starts.isDirectory()) {
            result.add(starts);
        }
        for (File child : starts.listFiles()) {
            if (child.isDirectory() && recursivly
                    && !child.getName().equals(WikittyPublicationFileSystem.PROPERTY_DIRECTORY)) {
                // Directory don't have to be harvest
                // result.add(child);
                result.addAll(listFile(child, recursivly));
            } else if (!child.isDirectory()) {
                result.add(child);
            }
        }
        return result;
    }

    


}

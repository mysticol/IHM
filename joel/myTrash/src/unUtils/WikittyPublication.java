/*
 * #%L
 * Wikitty :: publication
 * 
 * $Id: WikittyPublication.java 806 2011-04-14 08:23:15Z mfortun $
 * $HeadURL: http://svn.nuiton.org/svn/wikitty/trunk/wikitty-publication/src/main/java/org/nuiton/wikitty/publication/synchro/WikittyPublication.java $
 * %%
 * Copyright (C) 2010 - 2011 CodeLutin mfortun
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package unUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuiton.util.ApplicationConfig;
import org.nuiton.util.ArgumentsParserException;
import org.nuiton.util.CollectionUtil;
import org.nuiton.util.FileUtil;
import org.nuiton.wikitty.WikittyProxy;
import org.nuiton.wikitty.WikittyServiceFactory;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyLabel;
import org.nuiton.wikitty.entities.WikittyLabelHelper;
import org.nuiton.wikitty.publication.entities.WikittyPubData;
import org.nuiton.wikitty.publication.entities.WikittyPubText;
import org.nuiton.wikitty.search.Criteria;
import org.nuiton.wikitty.search.PagedResult;
import org.nuiton.wikitty.search.Search;

/**
 * Main class of the sync part of wikitty publication, this class is the entry
 * point for sync operation : import, checkout, commit, delete, relocate and
 * update.
 * 
 * 
 * 
 * @author mfortun
 * 
 */
public class WikittyPublication {

    /** to use log facility, just put in your code: log.info(\"...\"); */
    final static private Log log = LogFactory.getLog(WikittyPublication.class);

    static protected ApplicationConfig applicationConfig;

    /*
     * static string for allias, wrong named attribut TODO mfortun-2011-04-06
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
        applicationConfig.addAlias("--ws", "--option", WikittyPublication.WITTY_SERVICE_KEY);

        applicationConfig.addAlias("--dir", "--option", WikittyPublication.DIRECTORY_KEY);

        /*
         * TODO mfortun-2011-04-05 once application fixed setdefault value and
         * enumclass for initialisation
         */

        // allias for norecursion
        applicationConfig.addAlias("--norecursion", "--option",
                WikittyPublication.NO_RECURSION_KEY, "true");

        // allias for the protocole
        applicationConfig.addAlias("--hessian", "--option",
                WikittyPublication.HESSIAN_PROTOCOL_KEY, "true");

        // allias for all the action
        applicationConfig.addAlias("wp import", "--option", "import");
        applicationConfig.addAlias("wp checkout", "--option", "checkout");
        applicationConfig.addAlias("wp relocate", "--option", "relocate");
        applicationConfig.addAlias("wp commit", "--option", "commit");
        applicationConfig.addAlias("wp delete", "--option", "delete");
        applicationConfig.addAlias("wp update", "--option", "update");

        applicationConfig
                .addActionAlias("import",
                        "org.nuiton.wikitty.publication.WikittyPublication#importToWikitty");

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
                .getOptionAsBoolean(WikittyPublication.NO_RECURSION_KEY);
        File dir = applicationConfig.getOptionAsFile(WikittyPublication.DIRECTORY_KEY);
        String wikittyService = applicationConfig.getOption(WikittyPublication.WITTY_SERVICE_KEY);

        log.info("import : wikittyservice: " + wikittyService + " noresursion="
                + noRecur + " directory= " + dir.getAbsolutePath());
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
        List<File> toTransfert = WikittyPublicationFileSystem.listFile(dir, !noRecur);

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
     * TODO mfortun-2011-04-05 just prototyping, remove when really implements
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
     */
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
         */

    }

    /**
     * Relocate the default url of the wikitty service
     */
    static public void relocateWikitty() throws Exception {

        /*
         * log.info("checkout : wikittyservice: " + wikittyService +
         * " noresursion=" + noRecur + " directory= " + dir.getAbsolutePath()
         * +"Label a checkout " +label+ "HessianProtocol="+hessianProtocole);
         */

        File dir = applicationConfig.getOptionAsFile(WikittyPublication.DIRECTORY_KEY);
        String wikittyService = applicationConfig.getOption(WikittyPublication.WITTY_SERVICE_KEY);
        boolean hessianProtocole = applicationConfig
                .getOptionAsBoolean(WikittyPublication.HESSIAN_PROTOCOL_KEY);

        File wpHomeDir;

        System.out
                .println("search the directory of .wp file to write new properties file");
        // search for the home directory
        if (null == dir || !dir.exists()) {
            wpHomeDir = WikittyPublicationFileSystem.searchWikittyPublicationHomeDir(new File("."));
        } else {
            wpHomeDir = new File(dir.getCanonicalFile() + File.separator
                    + WikittyPublicationFileSystem.PROPERTY_DIRECTORY);
        }

        Properties oldProperties = new Properties();

        // TODO mfortun-2011-04-06 catch exception instead of throws
        File propertiesFile = new File(wpHomeDir + File.separator
                + WikittyPublicationFileSystem.WIKITTYPUBLICATION_PROPERTIES_FILE);
        // load the old file just for loggin
        oldProperties.load(new FileReader(propertiesFile));

        log.info("Try relocate :" + "wikitty service:"
                + oldProperties.getProperty(WikittyPublication.WITTY_SERVICE_KEY) + " by "
                + wikittyService + " HessianProtocol: " + hessianProtocole
                + " File : " + propertiesFile.getCanonicalPath());

        // Creation of the new properties file
        Properties props = new Properties();
        props.put(WikittyPublication.WITTY_SERVICE_KEY, wikittyService);
        if (hessianProtocole) {
            props.put("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceHessianClient");
        } else {
            props.put("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceCajoClient");
        }

        // save the new property file
        props.store(new FileWriter(propertiesFile), "");

        /*
         * on va commencer par vérifier les arguments ''wp relocate [nouvelle
         * url du WikittyService par defaut] [directory a relocaliser]'' on doit
         * avoir trois string dans le unparsed: url et directory
         */

    }

    /**
     * commit the current wikittyworkspace into a wikitty service
     */
    static public void commitToWikitty() throws Exception {

        File dir = applicationConfig.getOptionAsFile(WikittyPublication.DIRECTORY_KEY);

        File wpHomeDir;
        if (null == dir || !dir.exists()) {
            /*
             * si pas de dir on commit le dossier courant
             */
            dir = new File(".");

        }
        // on va chercher le home dir
        wpHomeDir = WikittyPublicationFileSystem.searchWikittyPublicationHomeDir(dir);

        Properties properties = new Properties();

        // TODO mfortun-2011-04-06 catch exception instead of throws
        File propertiesFile = new File(wpHomeDir.getCanonicalPath()
                + File.separator + WikittyPublicationFileSystem.WIKITTYPUBLICATION_PROPERTIES_FILE);
        // load the old file just for loggin
        properties.load(new FileReader(propertiesFile));

        // on va rajouter les propriété de notre file
        applicationConfig.setOptions(properties);

        boolean noRecur = applicationConfig
                .getOptionAsBoolean(WikittyPublication.NO_RECURSION_KEY);

        String wikittyService = applicationConfig.getOption(WikittyPublication.WITTY_SERVICE_KEY);
        boolean hessianProtocole = applicationConfig
                .getOptionAsBoolean(WikittyPublication.HESSIAN_PROTOCOL_KEY);

        if (hessianProtocole) {
            applicationConfig.setOption("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceHessianClient");
        } else {
            applicationConfig.setOption("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceCajoClient");
        }

        log.info("commit " + " wikitty-service" + wikittyService
                + "noRecurs : " + noRecur + " hessian Protocole :"
                + hessianProtocole + "directory : " + dir.getAbsolutePath());

        System.out.println("try to commit: ");
        printDirectory(dir, !noRecur);

        // faire la liste des fichiers à commit
        // avec récursivité si c'etransfert d'histoire entre svnst demandé.
        // et les transformer en wikitty, vérifier les versions et tout.

        /*
         * parcours l'arborescence des fichiers celon que on soit recursif ou
         * non plutot que faire la liste fichier, on va aller lire les ids dans
         * les fichiers de propriétés.
         * 
         * Ensuite on creer le wikittyserviceFileSysteme avec le répertoire de
         * travail et lui on lui donner les ids avec lesquels il va travailler
         * et aller chercher les fichiers de propriétés pour retrouver les files
         * et creer les wikittypub correspondant.
         * 
         * Pour faire un commit il ne suffira pas de faire un restore
         * finalement, il faudra faire un search aussi puisque on va se
         * retrouver à faire des new wikitty p'etre éclaircir ce point plus
         * tard. Pour le moment ne pas se poser de question trop, et faire sans
         * nouveau fichier.
         */

        // création du proxy

        /*
         * on va commencer par vérifier les arguments ''wp commit
         * [--norecursion] [--ws (url du WikittyService)] [répertoire à
         * pousser]'' on doit avoir deux string dans le unparsed et
         * potentiellement quelque chose dans le getoption recursion
         */

    }

    /**
     * delete a file or directory from the workspace it remove the label from
     * the wikitty
     * 
     * @throws Exception
     */
    static public void deleteFromWikitty(File toDelete) throws Exception {

        // check args
        if (null == toDelete || !toDelete.exists()) {
            // Exception

        } else {

            File currentDir = new File(FileUtil.getCurrentDirectory()
                    .getAbsolutePath());
            File wpHomeDir = WikittyPublicationFileSystem.searchWikittyPublicationHomeDir(currentDir);
            // search for the .wp home dir to load props

            // then load proxy

            // if toDelete is a directory do something different from usualy
            // recursivly remove labels

            File dir = applicationConfig.getOptionAsFile(WikittyPublication.DIRECTORY_KEY);

            Properties properties = new Properties();

            // TODO mfortun-2011-04-06 catch exception instead of throws
            File propertiesFile = new File(wpHomeDir.getCanonicalPath()
                    + File.separator + WikittyPublicationFileSystem.WIKITTYPUBLICATION_PROPERTIES_FILE);
            // load the old file just for loggin
            properties.load(new FileReader(propertiesFile));

            // on va rajouter les propriété de notre file
            applicationConfig.setOptions(properties);

            String wikittyService = applicationConfig
                    .getOption(WikittyPublication.WITTY_SERVICE_KEY);
            boolean hessianProtocole = applicationConfig
                    .getOptionAsBoolean(WikittyPublication.HESSIAN_PROTOCOL_KEY);

            if (hessianProtocole) {
                applicationConfig
                        .setOption("wikitty.WikittyService.components",
                                "org.nuiton.wikitty.services.WikittyServiceHessianClient");
            } else {
                applicationConfig.setOption(
                        "wikitty.WikittyService.components",
                        "org.nuiton.wikitty.services.WikittyServiceCajoClient");
            }

            log.info("Delete " + " wikitty-service" + wikittyService
                    + " hessian Protocole :" + hessianProtocole
                    + "file to delete : " + toDelete.getAbsolutePath());
            // on va chercher le home dir
            wpHomeDir = WikittyPublicationFileSystem.searchWikittyPublicationHomeDir(dir);

            WikittyProxy remoteWikittyService = new WikittyProxy(
                    WikittyServiceFactory
                            .buildWikittyService(applicationConfig));
            WikittyPublicationFileSystem localWikittyService = new WikittyPublicationFileSystem(
                    dir);

            if (toDelete.isDirectory()) {

                BidiMap deleteMap = WikittyPublicationFileSystem
                        .harvestLocalWikitties(toDelete, true);
                List<String> ids = new ArrayList<String>();
                ids = new ArrayList<String>();
                ids.addAll(CollectionUtil.toGenericCollection(
                        deleteMap.keySet(), String.class));

                for (String key : ids) {
                    // restore wikitty
                    Wikitty wd = remoteWikittyService.restore(key);

                    // searh for the label to delete from the wikitty
                    FileSystemWIkittyId location = (FileSystemWIkittyId) deleteMap
                            .get(key);

                    File wikittyFileParent = new File(location.getPath());
                    PropertiesExtended metaExtended = WikittyPublicationFileSystem
                            .getWikittyPublicationProperties(
                                    wikittyFileParent,
                                    WikittyPublicationFileSystem.WIKITTY_FILE_META_PROPERTIES_FILE);

                    String currentLabel = metaExtended
                            .getProperty(WikittyPublicationFileSystem.META_CURRENT_LABEL);
                    // remove label
                    WikittyLabelHelper.removeLabels(wd, currentLabel);
                    // save the wikitty
                    remoteWikittyService.store(wd);
                }

                localWikittyService.delete("", ids);
                // if it was a directory the wikitty file service does not have
                // delete it

                FileUtil.deleteRecursively(toDelete);
            } else {
                // infos about file about to delete
                String name = toDelete.getName();
                File parentFile = toDelete.getParentFile();
                // search of it's id
                PropertiesExtended metaProps = WikittyPublicationFileSystem
                        .getWikittyPublicationProperties(
                                parentFile,
                                WikittyPublicationFileSystem.WIKITTY_FILE_META_PROPERTIES_FILE);

                String id = metaProps.getProperty(name
                        + WikittyPublicationFileSystem.DEFAULT_PROPERTY_NAME_SEP + WikittyPublicationFileSystem.META_SUFFIX_KEY_ID);
                // his label
                String label = metaProps
                        .getProperty(WikittyPublicationFileSystem.META_CURRENT_LABEL);

                // restore wikitty remote

                Wikitty wd = remoteWikittyService.restore(id);

                // remove label

                WikittyLabelHelper.removeLabels(wd, label);
                // save the wikitty remote
                remoteWikittyService.store(wd);

                List<String> ids = new ArrayList<String>();
                ids.add(id);
                // delete localy
                localWikittyService.delete("", ids);
            }

        }

        /*
         * on va commencer par vérifier les arguments ''wp delete [--ws (url du
         * WikittyService)] [répertoire ou fichier à supprimer]'' on doit avoir
         * un string dans le unparsed et après pour pour le wikittyservice bah
         * on tape dedans en fonction de si elle a été précisé en ligne de
         * commande applicationConfig se débrouille
         */
    }

    /**
     * update the current workspace from a wikitty
     */
    static public void updateFromWikitty() throws Exception {

        File dir = applicationConfig.getOptionAsFile(WikittyPublication.DIRECTORY_KEY);

        File wpHomeDir;
        if (null == dir || !dir.exists()) {
            /*
             * si pas de dir on update le dossier courant
             */
            dir = new File(".");

        }
        // on va chercher le home dir
        wpHomeDir = WikittyPublicationFileSystem.searchWikittyPublicationHomeDir(dir);

        Properties properties = new Properties();

        // TODO mfortun-2011-04-06 catch exception instead of throws
        File propertiesFile = new File(wpHomeDir.getCanonicalPath()
                + File.separator + WikittyPublicationFileSystem.WIKITTYPUBLICATION_PROPERTIES_FILE);
        // load the old file just for loggin
        properties.load(new FileReader(propertiesFile));

        // on va rajouter les propriété de notre file
        applicationConfig.setOptions(properties);

        boolean noRecur = applicationConfig
                .getOptionAsBoolean(WikittyPublication.NO_RECURSION_KEY);

        String wikittyService = applicationConfig.getOption(WikittyPublication.WITTY_SERVICE_KEY);
        boolean hessianProtocole = applicationConfig
                .getOptionAsBoolean(WikittyPublication.HESSIAN_PROTOCOL_KEY);

        if (hessianProtocole) {
            applicationConfig.setOption("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceHessianClient");
        } else {
            applicationConfig.setOption("wikitty.WikittyService.components",
                    "org.nuiton.wikitty.services.WikittyServiceCajoClient");
        }

        log.info("update " + " wikitty-service" + wikittyService
                + "noRecurs : " + noRecur + " hessian Protocole :"
                + hessianProtocole + "directory : " + dir.getAbsolutePath());

        System.out.println("try to update: ");
        printDirectory(dir, !noRecur);

        /*
         * on va commencer par vérifier les arguments * ''wp update
         * [--norecursion] [--ws (url du WikittyService)] [répertoire à mettre à
         * jour]'' normalement un string dans unparsed et potentiellement
         * quelque chose dans le recursion
         */
    }

    
    
    

    static public void synchronisation ( String uriOrigin, String uriTarget){
        
    }


}

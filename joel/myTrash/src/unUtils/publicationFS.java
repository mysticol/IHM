package unUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.nuiton.util.FileUtil;
import org.nuiton.wikitty.entities.Wikitty;
import org.nuiton.wikitty.entities.WikittyImpl;
import org.nuiton.wikitty.entities.WikittyLabelHelper;
import org.nuiton.wikitty.entities.WikittyLabelImpl;
import org.nuiton.wikitty.publication.entities.WikittyPubDataHelper;
import org.nuiton.wikitty.publication.entities.WikittyPubDataImpl;
import org.nuiton.wikitty.publication.entities.WikittyPubTextHelper;
import org.nuiton.wikitty.publication.entities.WikittyPubTextImpl;

public class publicationFS {

    static public String WIKITTYPUBLICATION_PROPERTIES_FILE = "ws.properties";
    
    /**
     * 
     * Use to search in the file system the directory that containt the .wp dir
     * that containt the properties file for the wikitty service's adresse
     * 
     * @param start
     * @return File the directory container of the master property file
     * @throws Exception
     */
    static protected File searchWikittyPublicationHomeDir(File start)
            throws Exception {

        if (start != null && start.exists() && start.isDirectory()) {
            // on va retourner le dossier .wp home

            // method récursiv qui remonte dans les parents pour retrouver.

            File propertyDirectory = new File(start.getCanonicalPath()
                    + File.separator + PROPERTY_DIRECTORY);

            if (propertyDirectory.exists()) {
                File propertie = new File(propertyDirectory.getCanonicalPath()
                        + File.separator + WIKITTYPUBLICATION_PROPERTIES_FILE);
                if (propertie.exists()) {
                    return propertyDirectory;
                }
            }

            return searchWikittyPublicationHomeDir(start.getParentFile());
        } else {
            // Exception
            /*
             *  mfortun-2011-04-06 write/set the appropriate exception here
             */
            return null;
        }
    }
    
    
    /**
     * Method that create the list of file needed to commit, delete, update
     * import. It harvest file in order to transform them into wikitties
     * 
     * @param starts
     *            harvested directory
     * @param recursivly
     *            boolean id the directory have to be harvest
     * @return list of harvested file
     */
    static public List<File> listFile(File starts, boolean recursivly) {
        List<File> result = new ArrayList<File>();
        if (!starts.isDirectory()) {
            result.add(starts);
        }
        for (File child : starts.listFiles()) {
            if (child.isDirectory() && recursivly
                    && !child.getName().equals(PROPERTY_DIRECTORY)) {
                // Directory don't have to be harvest
                // result.add(child);
                result.addAll(listFile(child, recursivly));
            } else if (!child.isDirectory()) {
                result.add(child);
            }
        }
        return result;
    }
    
    
    
    /**
     * Transform an object into a wikitty object in this case a File into a
     * wikittyPubText/Data
     * 
     * @param fileToTransform
     *            the objet to transform
     * @param starts
     *            the home directory of the fileToTransform use to contruct the
     *            label
     * @return the wikitty
     * @throws Exception
     */
    /*
     *  mfortun-2011-04-07 correct the Exception's type
     */
    protected Wikitty fileToWikitty(File fileToTransform, File starts,
            boolean writeProperties) throws Exception {

        String completeName = fileToTransform.getName();
        // isolate extension and file name

        String extension = FileUtil.extension(fileToTransform);
        String name = FileUtil.basename(completeName, "." + extension);
        // search for the mimetype
        String mimeType = mimeTypeForExtension(extension);

        // prepare wikitty basics
        Wikitty result = new WikittyImpl();
        result.addExtension(WikittyLabelImpl.extensionWikittyLabel);

        // creation of the label
        /*
         *
         */
        String pathToFile = fileToTransform.getParent();
        String pathToStart = starts.getCanonicalPath();
        String startDirName = starts.getName();

        /*
         * remove path from root to start dir to have path from start to current
         * working dir e.g.: if current file=
         * /home/foo/bob/chaine.chaussette.tar.gz and starts dir = /home/foo/
         * then path will be = foo/bob
         */
        String path = startDirName + pathToFile.replaceAll(pathToStart, "");

        /*
         *  actually with a dot as a wikittylabel_separator, when
         * restauring label to directory it destroy directory that containt dot
         * in the name e.g.: /home/truc.machin/bob
         */

        String label = path.replaceAll(File.separator, WIKITTYLABEL_SEPARATOR);

        WikittyLabelHelper.addLabels(result, label);

        // complete with the correct extension with content
        if (isMimeWikittyPubText(mimeType)) {
            result.addExtension(WikittyPubTextImpl.extensionWikittyPubText);
            WikittyPubTextHelper.setName(result, name);
            WikittyPubTextHelper.setMimeType(result, mimeType);
            WikittyPubTextHelper.setContent(result,
                    FileUtil.readAsString(fileToTransform));
        } else {
            result.addExtension(WikittyPubDataImpl.extensionWikittyPubData);
            WikittyPubDataHelper.setName(result, name);
            WikittyPubDataHelper.setMimeType(result, mimeType);
            WikittyPubDataHelper.setContent(result,
                    FileUtil.fileToByte(fileToTransform));
        }

        return result;

    }
    
}

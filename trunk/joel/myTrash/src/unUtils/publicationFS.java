package unUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
             * TODO mfortun-2011-04-06 write/set the appropriate exception here
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
    
}

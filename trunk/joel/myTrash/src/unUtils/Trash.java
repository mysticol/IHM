package unUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Properties;

public class Trash {
/* from wikittypublication
    static protected HashMap<String, String> deleteFile(File toDelete)
    throws Exception {
HashMap<String, String> result = new HashMap<String, String>();

String pathToProperty = toDelete.getCanonicalFile() + File.separator
        + PROPERTY_DIRECTORY + File.separator;
// load properties files
File metaFile = new File(pathToProperty
        + WIKITTY_FILE_META_PROPERTIES_FILE);
Properties metaProperties = new Properties();
metaProperties.load(new FileReader(metaFile));

File idFile = new File(pathToProperty + WIKITTY_ID_PROPERTIES_FILE);
Properties idProperties = new Properties();
idProperties.load(new FileReader(idFile));

if (toDelete.isDirectory()) {
    for (File child : toDelete.listFiles()) {
        if (child.isDirectory()
                && !child.getName().equals(PROPERTY_DIRECTORY)) {
            result.putAll(deleteFile(child));
        }
    }

} else {

    String id = metaProperties.getProperty(toDelete.getName()
            + DEFAULT_PROPERTY_NAME_SEP + META_SUFFIX_KEY_ID);
    String label = metaProperties.getProperty(META_CURRENT_LABEL);

    result.put(id, label);
    // remove from properties
    idProperties.remove(id);

    metaProperties.remove(toDelete.getName()
            + DEFAULT_PROPERTY_NAME_SEP + META_SUFFIX_KEY_ID);
    metaProperties.remove(toDelete.getName()
            + DEFAULT_PROPERTY_NAME_SEP + META_SUFFIX_KEY_CHECKSUM);
    metaProperties.remove(toDelete.getName()
            + DEFAULT_PROPERTY_NAME_SEP + META_SUFFIX_KEY_VERSION);
}

metaProperties.store(new FileWriter(metaFile), "");
idProperties.store(new FileWriter(idFile), "");

toDelete.delete();
return result;

}
*/
    
}

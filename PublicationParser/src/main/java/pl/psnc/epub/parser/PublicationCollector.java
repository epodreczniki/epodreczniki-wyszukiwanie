package pl.psnc.epub.parser;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.psnc.epub.publicationparser.exceptions.PathNotFoundException;

/**
 *
 * @author spychala
 */
public class PublicationCollector {
    
    public final static String COLLECTION_FILE_NAME = "collection.xml";
    
    public static Map<String,List<String>> collectPublications(String path) {

        
        File directory = new File(path);
        
        if (!directory.exists())
            throw new PathNotFoundException(path);
        
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        if (directory.isFile()) {
            if (COLLECTION_FILE_NAME.equalsIgnoreCase(directory.getName())) {
                List<String> modules = ModuleCollector.collectModules(directory.getPath());
                map.put(directory.getPath(),modules);
            }
        }
        
        if (directory.isDirectory()) {
            
            PublicationFilter pubFilter = new PublicationFilter();
            File[] collections = directory.listFiles(pubFilter);
            if (collections != null && collections.length > 0) {
                List<String> modules = ModuleCollector.collectModules(directory.getPath());
                for (File collection : collections) {
                    map.put(collection.getPath(), modules);
                }
            }
            
            for (File subDirectory : directory.listFiles()) {
                collections = subDirectory.listFiles(pubFilter);
                if (collections != null && collections.length > 0) {
                    List<String> modules = ModuleCollector.collectModules(subDirectory.getPath());
                    for (File collection : collections)
                        map.put(collection.getPath(), modules);
                }
            }
        }        
        
        return map;
        
    }
    
}

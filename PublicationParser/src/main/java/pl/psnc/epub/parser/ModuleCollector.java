package pl.psnc.epub.parser;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import static pl.psnc.epub.parser.PublicationCollector.COLLECTION_FILE_NAME;
import pl.psnc.epub.parser.utils.FileUtils;
import pl.psnc.epub.publicationparser.exceptions.PathNotFoundException;

/**
 *
 * @author spychala
 */
public class ModuleCollector {
    
    public final static String MODULE_FILE_EXT = "cnxml";
    public final static String MODULE_FILE_NAME = "index.cnxml";
    public final static String XML_MODULE_FILE_NAME = "module.xml";
    
    public static List<String> collectModules(String path) {

        File directory = new File(path);
        
        if (!directory.exists())
            throw new PathNotFoundException(path);
        
        ArrayList<String> list = new ArrayList<String>();

        if (directory.isFile()) {
            if (MODULE_FILE_EXT.equalsIgnoreCase(FileUtils.stripFileExtension(directory)))
                list.add(directory.getPath());
            else if (XML_MODULE_FILE_NAME.equalsIgnoreCase(directory.getName()))
                list.add(directory.getPath());
        }
        
        if (directory.isDirectory()) {
            ModuleFilter moduleFilter = new ModuleFilter();
            File[] modules = directory.listFiles(moduleFilter);
            if (modules != null && modules.length > 0) {
                for (File module : modules)
                    list.add(module.getPath());
            }

            for (File subDirectory : directory.listFiles()) {
                modules = subDirectory.listFiles(moduleFilter);
                if (modules != null && modules.length > 0) {
                    for (File module : modules)
                        list.add(module.getPath());
                }
            }
        }
        
        return list;
        
    }

}

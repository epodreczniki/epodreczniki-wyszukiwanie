package pl.psnc.epub.parser;

import java.io.File;
import java.io.FilenameFilter;
import static pl.psnc.epub.parser.ModuleCollector.MODULE_FILE_NAME;
import static pl.psnc.epub.parser.ModuleCollector.XML_MODULE_FILE_NAME;

/**
 *
 * @author spychala
 */
public class ModuleFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        //return MODULE_FILE_EXT.equalsIgnoreCase(FileUtils.stripFileExtension(name));
        return (MODULE_FILE_NAME.equalsIgnoreCase(name) || XML_MODULE_FILE_NAME.equalsIgnoreCase(name));
    }
    
}

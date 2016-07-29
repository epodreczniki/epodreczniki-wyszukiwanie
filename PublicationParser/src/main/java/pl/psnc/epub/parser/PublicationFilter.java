package pl.psnc.epub.parser;

import java.io.File;
import java.io.FilenameFilter;
import static pl.psnc.epub.parser.PublicationCollector.COLLECTION_FILE_NAME;

/**
 *
 * @author spychala
 */
public class PublicationFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        return COLLECTION_FILE_NAME.equalsIgnoreCase(name);
    }
    
}

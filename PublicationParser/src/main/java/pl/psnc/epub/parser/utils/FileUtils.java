package pl.psnc.epub.parser.utils;

import java.io.File;

/**
 *
 * @author spychala
 */
public class FileUtils {
    
    public static String stripFileExtension(File file) {
        int dotPosition = file.getName().lastIndexOf('.');
        if (dotPosition > 0)
            return file.getName().substring(dotPosition + 1);
        else 
            return file.getName();
    }    
    public static String stripFileExtension(String fileName) {
        int dotPosition = fileName.lastIndexOf('.');
        if (dotPosition > 0)
            return fileName.substring(dotPosition + 1);
        else 
            return fileName;
    }    
}

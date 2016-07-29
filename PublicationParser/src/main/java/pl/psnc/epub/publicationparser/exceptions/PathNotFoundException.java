package pl.psnc.epub.publicationparser.exceptions;

/**
 *
 * @author spychala
 */
public class PathNotFoundException extends EPubParserBaseException {
    
    private final static String messagePlaceHldr = "Path %1$ not found.";

    public PathNotFoundException(String path) {
        super(String.format(messagePlaceHldr, path));
    }
    
}

package pl.psnc.epub.publicationparser.exceptions;

/**
 *
 * @author spychala
 */
public class NotAFileException extends EPubParserBaseException {

    private final static String messagePlaceHldr = "Path %1$ is not an ordinary file.";

    public NotAFileException(String path) {
        super(String.format(messagePlaceHldr, path));
    }
    
}

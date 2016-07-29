package pl.psnc.epub.publicationparser.exceptions;

/**
 *
 * @author spychala
 */
public class EPubParserBaseException extends RuntimeException {

    public EPubParserBaseException(String message) {
        super(message);
    }

    public EPubParserBaseException() {
        super();
    }
    
}

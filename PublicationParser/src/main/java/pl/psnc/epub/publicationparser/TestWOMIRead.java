package pl.psnc.epub.publicationparser;

import pl.psnc.epub.parser.EPubParser;

/**
 *
 * @author spychala
 */
public class TestWOMIRead {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if ((args.length != 1)) {
            printUsage();
            return;
        }
        
        if (args.length == 1) {
            EPubParser parser = new EPubParser();
            parser.testWomiRead(args[0]);
        }
        
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("   java -jar epubparser.jar [womiID]");
    }
    
}

package pl.psnc.epub.publicationparser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.JAXBException;
import org.apache.solr.client.solrj.SolrServerException;
import pl.psnc.epub.parser.EPubParser;

/**
 * Publication Parser
 *
 */
public class IndexContext {
    private static final String CLEAR_SWITCH = "-clear";

    public static void main(String[] args) throws UnsupportedEncodingException, JAXBException, SolrServerException, IOException {

        if ((args.length != 1) && (args.length != 2) && (args.length != 3) && (args.length != 4)) {
            printUsage();
            return;
        }

        //System.out.println(URLEncoder.encode("Strin#g","UTF-8"));
        //DOMePubParser parser = new DOMePubParser();
        //parser.parse(args[0]);
        if (args.length == 1) {
            EPubParser parser = new EPubParser();
            if (CLEAR_SWITCH.equals(args[0]))
                parser.clear();
            else
                parser.index(args[0]);
            //parser.parse(args[0]);
            //parser.collect(args[0]);
        } else if (args.length == 2) {
            EPubParser parser = new EPubParser(args[1]);
            if (CLEAR_SWITCH.equals(args[0]))
                parser.clear();
            else
                parser.index(args[0]);
        } else if (args.length == 3) {
            if (CLEAR_SWITCH.equals(args[0])) {
                EPubParser parser = new EPubParser(args[2]);
                parser.clear();
                parser.index(args[1]);
            } else {
                System.out.println("Bad usage!!!");
                printUsage();
                //parser.index(args[0], args[1]);
            }
        } else if (args.length == 4) {
            if (CLEAR_SWITCH.equals(args[0])) {
                EPubParser parser = new EPubParser(args[1], args[2], args[3]);
                parser.clearAll();
            } else {
                EPubParser parser = new EPubParser(args[1], args[2], args[3]);
                parser.index(args[0]);
            }
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("   java -jar epubparser.jar [path-to-epub-directory]");
        System.out.println("   java -jar epubparser.jar [path-to_epub-directory] [solr-base-url]");
        System.out.println("   java -jar epubparser.jar [path-to_epub-directory] [main-solr-collection-url] [autocomplete-solr-collection-url] [dynamic-search-solr-collection-url]");
        System.out.println("   Clearing data:");
        System.out.println("   java -jar epubparser.jar -clear");
        System.out.println("   java -jar epubparser.jar -clear [solr-base-url]");
        System.out.println("   java -jar epubparser.jar -clear [main-solr-collection-url] [autocomplete-solr-collection-url] [dynamic-search-solr-collection-url]");
        System.out.println("   Clearing and building data:");
        System.out.println("   java -jar epubparser.jar -clear [path-to_epub-directory] [solr-base-url]");
    }
}

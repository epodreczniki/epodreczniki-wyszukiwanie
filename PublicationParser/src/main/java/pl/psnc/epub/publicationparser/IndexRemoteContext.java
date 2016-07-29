package pl.psnc.epub.publicationparser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.solr.client.solrj.SolrServerException;
import pl.psnc.epub.parser.EPubParser;
import pl.psnc.epub.parser.jaxb.module.EPContent;

/**
 * Publication Parser
 *
 */
public class IndexRemoteContext {
    private static final String CLEAR_SWITCH = "-clear";

    public static void main(String[] args) throws UnsupportedEncodingException, JAXBException, SolrServerException, IOException {

        if ((args.length != 1) && (args.length != 2) && (args.length != 3) && (args.length != 4)) {
            printUsage();
            return;
        }

        //System.out.println(JAXBContext.newInstance(EPContent.class).getClass());
        //System.out.println(URLEncoder.encode("Strin#g","UTF-8"));
        //DOMePubParser parser = new DOMePubParser();
        //parser.parse(args[0]);
        if (args.length == 1) {
            EPubParser parser = new EPubParser();
            if (CLEAR_SWITCH.equals(args[0]))
                parser.clear();
            else {
                List<URL> urls = new ArrayList<URL>();
                URL url = new URL(args[0]);
                urls.add(url);
                parser.index(urls);
            }
        } else if (args.length == 2) {
            if (CLEAR_SWITCH.equals(args[0])) {
                EPubParser parser = new EPubParser();
                parser.clear();
            } else {
                EPubParser parser = new EPubParser(args[1]);
                List<URL> urls = new ArrayList<URL>();
                URL url = new URL(args[0]);
                urls.add(url);
                parser.index(urls);
            }
        } else if (args.length == 3) {
            if (CLEAR_SWITCH.equals(args[0])) {
                EPubParser parser = new EPubParser(args[2]);
                parser.clear();
                List<URL> urls = new ArrayList<URL>();
                URL url = new URL(args[1]);
                urls.add(url);
                parser.index(urls);
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
                List<URL> urls = new ArrayList<URL>();
                URL url = new URL(args[0]);
                urls.add(url);
                parser.index(urls);
            }
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("   java -jar epubparser.jar [url-to-epub]");
        System.out.println("   java -jar epubparser.jar [url-to-epub] [solr-base-url]");
        System.out.println("   java -jar epubparser.jar [url-to-epub] [main-solr-collection-url] [autocomplete-solr-collection-url] [dynamic-search-solr-collection-url]");
        System.out.println("   Clearing data:");
        System.out.println("   java -jar epubparser.jar -clear");
        System.out.println("   java -jar epubparser.jar -clear [solr-base-url]");
        System.out.println("   java -jar epubparser.jar -clear [main-solr-collection-url] [autocomplete-solr-collection-url] [dynamic-search-solr-collection-url]");
        System.out.println("   Clearing and building data:");
        System.out.println("   java -jar epubparser.jar -clear [url-to-epub] [solr-base-url]");
    }
}

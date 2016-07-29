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
public class ParseContext {

    public static void main(String[] args) throws UnsupportedEncodingException, JAXBException, SolrServerException, IOException {

        if (args.length != 1) {
            
            System.out.println("Usage:");
            System.out.println("   java -jar epubparser.jar [path-to epub-directory]");
        
        }
        
        EPubParser parser = new EPubParser();
        parser.parse(args[0]);
        
    }
}

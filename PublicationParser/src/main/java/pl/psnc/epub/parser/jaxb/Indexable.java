package pl.psnc.epub.parser.jaxb;

import java.util.List;
import org.w3c.dom.Node;
import pl.psnc.epub.solr.beans.context.IndexItem;

/**
 *
 * @author spychala
 */
public interface Indexable {
    
    public List<IndexItem> generateIndex(IndexItem parent);

}

package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPReferenceIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epreference-index-item";

    public EPReferenceIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPReferenceIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPReferenceIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

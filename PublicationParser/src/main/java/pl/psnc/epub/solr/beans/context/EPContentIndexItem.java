package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPContentIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epcontent-index-item";

    public EPContentIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPContentIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPContentIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

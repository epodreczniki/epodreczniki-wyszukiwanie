package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPCommandIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epcommand-index-item";

    public EPCommandIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPCommandIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPCommandIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

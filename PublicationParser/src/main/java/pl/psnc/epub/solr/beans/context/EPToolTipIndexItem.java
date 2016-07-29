package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPToolTipIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "eptooltip-index-item";

    public EPToolTipIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPToolTipIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPToolTipIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPLeadIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "eplead-index-item";

    public EPLeadIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPLeadIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPLeadIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

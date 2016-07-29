package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPInstructionsIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epinstructions-index-item";

    public EPInstructionsIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPInstructionsIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPInstructionsIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

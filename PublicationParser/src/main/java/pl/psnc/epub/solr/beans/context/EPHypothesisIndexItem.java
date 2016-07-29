package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPHypothesisIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "ephypothesis-index-item";

    public EPHypothesisIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPHypothesisIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPHypothesisIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

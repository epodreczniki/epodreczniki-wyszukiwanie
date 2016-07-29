package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class RuleIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "rule-index-item";

    public RuleIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public RuleIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public RuleIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

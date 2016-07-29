package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class StatementIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "statement-index-item";

    public StatementIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public StatementIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public StatementIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }

}

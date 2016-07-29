package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class EPMathElementIndexItem extends ModuleIndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "mathElement-index-item";

    public EPMathElementIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPMathElementIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPMathElementIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

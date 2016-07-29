package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class ExampleIndexItem extends ModuleIndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "example-index-item";

    public ExampleIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public ExampleIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public ExampleIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

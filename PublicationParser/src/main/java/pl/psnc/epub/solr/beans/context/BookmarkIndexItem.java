package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class BookmarkIndexItem extends ModuleIndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "bookmark-index-item";

    public BookmarkIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public BookmarkIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public BookmarkIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

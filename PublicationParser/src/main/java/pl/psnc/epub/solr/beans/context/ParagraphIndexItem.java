package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class ParagraphIndexItem extends ModuleIndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "paragraph-index-item";

    public ParagraphIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public ParagraphIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public ParagraphIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

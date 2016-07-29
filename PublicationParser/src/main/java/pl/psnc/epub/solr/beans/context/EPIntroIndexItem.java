package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPIntroIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epintro-index-item";

    public EPIntroIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public EPIntroIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPIntroIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

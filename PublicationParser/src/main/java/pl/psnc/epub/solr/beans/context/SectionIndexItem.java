package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class SectionIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "section-index-item";

    public SectionIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public SectionIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public SectionIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }

}

package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class EPTechnicalRemarksIndexItem extends IndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "eptechnical-remarks-index-item";

    public EPTechnicalRemarksIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public EPTechnicalRemarksIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPTechnicalRemarksIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

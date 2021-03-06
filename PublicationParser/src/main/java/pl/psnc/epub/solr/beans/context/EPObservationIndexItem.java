package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPObservationIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epobservation-index-item";

    public EPObservationIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPObservationIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPObservationIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

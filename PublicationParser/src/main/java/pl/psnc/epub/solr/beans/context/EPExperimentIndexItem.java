package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPExperimentIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epexperiment-index-item";

    public EPExperimentIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPExperimentIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPExperimentIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

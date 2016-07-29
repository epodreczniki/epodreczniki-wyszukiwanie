package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPInstrumentsIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epinstruments-index-item";

    public EPInstrumentsIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPInstrumentsIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPInstrumentsIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

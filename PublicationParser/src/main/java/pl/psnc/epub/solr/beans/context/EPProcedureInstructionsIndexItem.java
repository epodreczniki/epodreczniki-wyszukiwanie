package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class EPProcedureInstructionsIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "epprocedureinstructions-index-item";

    public EPProcedureInstructionsIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPProcedureInstructionsIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPProcedureInstructionsIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

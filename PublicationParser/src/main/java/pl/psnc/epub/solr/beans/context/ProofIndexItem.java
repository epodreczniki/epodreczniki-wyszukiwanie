package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class ProofIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "proof-index-item";

    public ProofIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public ProofIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public ProofIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

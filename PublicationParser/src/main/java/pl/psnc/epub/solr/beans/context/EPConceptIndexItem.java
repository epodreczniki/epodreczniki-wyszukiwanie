package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class EPConceptIndexItem extends IndexItem {

    protected static final String INDEX_ITEM_TYPE = "concept-index-item";

    public EPConceptIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPConceptIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPConceptIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }

}

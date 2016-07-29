package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class ProblemIndexItem extends IndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "problem-index-item";

    public ProblemIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public ProblemIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public ProblemIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }

}

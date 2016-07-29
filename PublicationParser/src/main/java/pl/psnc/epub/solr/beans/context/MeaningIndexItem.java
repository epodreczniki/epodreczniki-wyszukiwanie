package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class MeaningIndexItem extends IndexItem {

    protected static final String INDEX_ITEM_TYPE = "meaning-index-item";

    public MeaningIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public MeaningIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public MeaningIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

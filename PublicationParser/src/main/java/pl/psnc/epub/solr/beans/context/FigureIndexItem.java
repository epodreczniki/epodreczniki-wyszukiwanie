package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class FigureIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "figure-index-item";

    public FigureIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public FigureIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public FigureIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

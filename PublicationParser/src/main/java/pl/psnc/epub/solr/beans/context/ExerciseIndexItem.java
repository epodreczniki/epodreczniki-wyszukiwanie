package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class ExerciseIndexItem extends IndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "exercise-index-item";

    public ExerciseIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public ExerciseIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public ExerciseIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

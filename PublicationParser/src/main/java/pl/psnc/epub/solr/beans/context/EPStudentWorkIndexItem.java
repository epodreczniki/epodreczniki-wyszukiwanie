package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class EPStudentWorkIndexItem extends ModuleIndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "student-work-index-item";

    public EPStudentWorkIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public EPStudentWorkIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public EPStudentWorkIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

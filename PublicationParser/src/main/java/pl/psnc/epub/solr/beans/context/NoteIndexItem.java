package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;


/**
 *
 * @author spychala
 */
public class NoteIndexItem extends ModuleIndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "note-index-item";

    public NoteIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public NoteIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public NoteIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

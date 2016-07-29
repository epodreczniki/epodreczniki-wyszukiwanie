package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class SubcollectionIndexItem extends IndexItem{
    
    protected static final String INDEX_ITEM_TYPE = "subcollection-index-item";

    public SubcollectionIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public SubcollectionIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public SubcollectionIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setLink(String link) {
        if (parent != null) {
            if (parent.getId() != null) {
                if (link != null) {
                    this.link = String.format("%s/%s", ((IndexItem)parent).getCleanId(), link);
                } else {
                    this.link = ((IndexItem)parent).getCleanId();
                }
            }
        }
    }

    @Override
    public String getCleanId() {

        if (parent != null)
            return parent.getCleanId();
        
        return null;
        
    }

    
}

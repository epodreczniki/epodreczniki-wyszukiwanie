package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;

/**
 *
 * @author spychala
 */
public class CollectionModuleIndexItem extends IndexItem{
    
    //protected static final String INDEX_ITEM_TYPE = "collection-module-index-item";
    protected static final String INDEX_ITEM_TYPE = "module-index-item";

    public CollectionModuleIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public CollectionModuleIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
    @Override
    public void setId(String id) {
        super.setId(id);
        setLink(id);
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
    
}

package pl.psnc.epub.solr.beans.dynamicsearch;

/**
 *
 * @author spychala
 */
public class CollectionDynSearchIndexItem extends DynSearchIndexItem {

    protected static final String INDEX_ITEM_TYPE = "collection-index-item";

    public CollectionDynSearchIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
}

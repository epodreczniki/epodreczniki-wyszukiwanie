package pl.psnc.epub.solr.beans.dynamicsearch;

/**
 *
 * @author spychala
 */
public class ModuleDynSearchIndexItem extends DynSearchIndexItem {

    protected static final String INDEX_ITEM_TYPE = "module-index-item";

    public ModuleDynSearchIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
}

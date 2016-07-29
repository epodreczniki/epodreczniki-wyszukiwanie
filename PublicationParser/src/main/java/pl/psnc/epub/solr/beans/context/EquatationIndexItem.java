package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;

/**
 *
 * @author spychala
 */
public class EquatationIndexItem extends ModuleIndexItem {

    protected static final String INDEX_ITEM_TYPE = "equatation-index-item";

    public EquatationIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public EquatationIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }
    
}

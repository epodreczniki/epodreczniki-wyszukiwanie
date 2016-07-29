package pl.psnc.epub.solr.beans.context;

import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class DefinitionIndexItem extends IndexItem {

    protected static final String INDEX_ITEM_TYPE = "definition-index-item";

    public DefinitionIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public DefinitionIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public DefinitionIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
    }

}

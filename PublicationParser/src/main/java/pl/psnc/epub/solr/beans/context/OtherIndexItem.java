package pl.psnc.epub.solr.beans.context;

import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class OtherIndexItem extends IndexItem {

    protected static final String INDEX_ITEM_TYPE = "other-index-item";

    @Field(value = TYPE_FIELD)
    protected String type = "generic-index-item";

    @Field(value = CONTENT_FIELD)
    protected String content;

    public OtherIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public OtherIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public OtherIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
        if (solrDocument != null) {
            this.content = (String) solrDocument.getFieldValue(CONTENT_FIELD);
        }
    }
    
    
}

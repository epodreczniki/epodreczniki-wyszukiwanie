package pl.psnc.epub.solr.beans.context;

import pl.psnc.epub.solr.beans.BaseIndexItem;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class IndexItem extends BaseIndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "generic-index-item";
    
    public static final String TYPE_FIELD = "type";
    public static final String SUBTYPE_FIELD = "subtype";
    public static final String LINK_FIELD = "link";
    public static final String CONTENT_FIELD = "content";
    public static final String FULL_CONTENT_FIELD = "full_content";

    public static final String PUBLISHED_FIELD = "published";

    public static final String COLLECTION_TITLE_FIELD = "collection_title";
    public static final String COLLECTION_SUBJECT_FIELD = "collection_subject";
    public static final String COLLECTION_SCHOOL_TYPE_FIELD = "collection_school_type";
    public static final String COLLECTION_SCHOOL_TYPE_CODE_FIELD = "collection_school_type_code";
    public static final String COLLECTION_EP_CLASS_FIELD = "collection_ep_class";
    public static final String COLLECTION_VARIANT_NAME_FIELD = "collection_variant_name";
    public static final String COLLECTION_COVER_URL = "collection_cover_url";

    @Field(value = TYPE_FIELD)
    protected String type = INDEX_ITEM_TYPE;

    @Field(value = SUBTYPE_FIELD)
    protected String subtype;

    @Field(value = LINK_FIELD)
    protected String link;

    @Field(value = CONTENT_FIELD)
    protected String content;

    @Field(value = FULL_CONTENT_FIELD)
    protected String fullContent;

    @Field(value = PUBLISHED_FIELD)
    protected boolean published = true;

    @Field(value = COLLECTION_TITLE_FIELD)
    protected String collection_title;

    @Field(value = COLLECTION_SUBJECT_FIELD)
    protected String collection_subject;

    @Field(value = COLLECTION_SCHOOL_TYPE_FIELD)
    protected String collection_school_type;

    @Field(value = COLLECTION_SCHOOL_TYPE_CODE_FIELD)
    protected int collection_school_type_code;

    @Field(value = COLLECTION_EP_CLASS_FIELD)
    protected String collection_ep_class;

    @Field(value = COLLECTION_VARIANT_NAME_FIELD)
    protected String collection_variant_name;

    @Field(value = COLLECTION_COVER_URL)
    protected String collection_cover_url;

    public IndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public IndexItem(IndexContext context) {
        super();
        this.type = INDEX_ITEM_TYPE;
        if (context != null) {
            this.collection_title = context.getCollection_title();
            this.collection_subject =context.getCollection_subject();
            this.collection_school_type = context.getCollection_school_type();
            this.collection_school_type_code = context.getCollection_school_type_code();
            this.collection_ep_class = context.getCollection_ep_class();
            this.collection_variant_name = context.getCollectin_variant_name();
            this.collection_cover_url = context.getCollectin_cover_url();
        }
    }

    public IndexItem(SolrDocument solrDocument) {
        super(solrDocument);
        if (solrDocument != null) {
            this.type = (String) solrDocument.getFieldValue(TYPE_FIELD);
            this.subtype = (String) solrDocument.getFieldValue(SUBTYPE_FIELD);
            this.link = (String) solrDocument.getFieldValue(LINK_FIELD);
            this.content = (String) solrDocument.getFieldValue(CONTENT_FIELD);
            this.fullContent = (String) solrDocument.getFieldValue(FULL_CONTENT_FIELD);

            this.collection_title = (String) solrDocument.getFieldValue(COLLECTION_TITLE_FIELD);
            this.collection_subject = (String) solrDocument.getFieldValue(COLLECTION_SUBJECT_FIELD);
            this.collection_school_type = (String) solrDocument.getFieldValue(COLLECTION_SCHOOL_TYPE_FIELD);
            this.collection_school_type_code = (Integer) solrDocument.getFieldValue(COLLECTION_SCHOOL_TYPE_CODE_FIELD);
            this.collection_ep_class = (String) solrDocument.getFieldValue(COLLECTION_EP_CLASS_FIELD);
            this.collection_variant_name = (String) solrDocument.getFieldValue(COLLECTION_VARIANT_NAME_FIELD);
            this.collection_cover_url = (String) solrDocument.getFieldValue(COLLECTION_COVER_URL);
        }
    }

    public String getContent() {
        return content.replaceAll("\\<.*?\\>", "").trim();
    }

    public void setContent(String text) {
        if (text != null) {
            this.content = text.replaceAll("\\<.*?\\>", "").trim();
        }
    }

    public String getRawContent() {
        return content;
    }

    public void setRawContent(String text) {
        if (text != null) {
            this.content = text;
        }
    }

    public String getFullContent() {
        return fullContent.replaceAll("\\<.*?\\>", "").trim();
    }

    public void setFullContent(String text) {
        if (text != null) {
            this.fullContent = text.replaceAll("\\<.*?\\>", "").trim();
        }
    }

    public String getRawFullContent() {
        return fullContent;
    }

    public void setRawFullContent(String text) {
        if (text != null) {
            this.fullContent = text;
        }
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    @Override
    public void setId(String id) {
        super.setId(id);
        this.link = this.id;
    }

    public void setSubType(String subType) {
        this.subtype = subType;
        /*
        if (subType != null && !"".equals(subType))
            this.type = String.format("%s-%s", subType, INDEX_ITEM_TYPE);
        else
            this.type = INDEX_ITEM_TYPE;
        */
    }

    public boolean getPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getCollection_title() {
        return collection_title;
    }

    public void setCollection_title(String collection_title) {
        this.collection_title = collection_title;
    }

    public String getCollection_subject() {
        return collection_subject;
    }

    public void setCollection_subject(String collection_subject) {
        this.collection_subject = collection_subject;
    }

    public String getCollection_school_type() {
        return collection_school_type;
    }

    public void setCollection_school_type(String collection_school_type) {
        this.collection_school_type = collection_school_type;
    }

    public int getCollection_school_type_code() {
        return collection_school_type_code;
    }

    public void setCollection_school_type_code(int collection_school_type_code) {
        this.collection_school_type_code = collection_school_type_code;
    }

    public String getCollection_ep_class() {
        return collection_ep_class;
    }

    public void setCollection_ep_class(String collection_ep_class) {
        this.collection_ep_class = collection_ep_class;
    }

    public String getCollectin_variant_name() {
        return collection_variant_name;
    }

    public void setCollectin_variant_name(String collectin_variant_name) {
        this.collection_variant_name = collectin_variant_name;
    }

    public String getCollection_cover_url() {
        return collection_cover_url;
    }

    public void setCollection_cover_url(String collection_cover_url) {
        this.collection_cover_url = collection_cover_url;
    }

}

package pl.psnc.epub.solr.beans.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class WomiIndexItem extends IndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "womi-index-item";
    protected static final String INDEX_ITEM_SUBTYPE = "womi";

    private static final String KEYWORD_FIELD = "keyword";
    private static final String AUTHOR_FIELD = "author";
    private static final String VERSION_FIELD = "version";
    private static final String LICENSE_FIELD = "license";
    private static final String TITLE_FIELD = "title";
    private static final String ALTERNATIVE_TEXT_FIELD = "alttext";

    @Field(KEYWORD_FIELD)
    protected List<String> keywords = new ArrayList<String>();

    @Field(VERSION_FIELD)
    protected String version;

    @Field(LICENSE_FIELD)
    protected String license;

    @Field(AUTHOR_FIELD)
    protected List<String> authors = new ArrayList<String>();

    @Field(TITLE_FIELD)
    protected String title;

    @Field(ALTERNATIVE_TEXT_FIELD)
    protected String alttext;

    public WomiIndexItem() {
        this.type = INDEX_ITEM_TYPE;
        this.subtype = INDEX_ITEM_SUBTYPE;
    }

    public WomiIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
        this.subtype = INDEX_ITEM_SUBTYPE;
    }

    public WomiIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
        if (solrDocument != null) {
            if (solrDocument.containsKey(KEYWORD_FIELD))
                this.keywords = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(KEYWORD_FIELD).toArray(new String[0])));
            if (solrDocument.containsKey(AUTHOR_FIELD))
                this.authors = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(AUTHOR_FIELD).toArray(new String[0])));
            this.version = (String) solrDocument.getFieldValue(VERSION_FIELD);
            this.license = (String) solrDocument.getFieldValue(LICENSE_FIELD);
        }
    }
    
    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        if (keywords != null && keywords.size() > 0) {
            //System.out.println(keywords.toString());
            this.keywords = keywords;
        }
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void addAuthor(String author) {
        if (author != null)
            this.authors.add(author);
    }
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlttext() {
        return alttext;
    }

    public void setAlttext(String alttext) {
        this.alttext = alttext;
    }

    @Override
    public void setId(String id) {
        super.setId(id);
        //this.link = this.id;
    }

    public void setLink(String instanceId) {
        String linkId = instanceId;
        if (parent != null) {
            if (parent.getId() != null) {
                if (instanceId != null) {
                    linkId = String.format("%s/%s", parent.getId(), instanceId);
                } else {
                    linkId = parent.getId();
                }
            }
        }
        this.link = linkId;
    }
    /*
    public void setSubType(String subType) {
        if (subType != null && !"".equals(subType))
            this.type = String.format("%s-%s", subType, INDEX_ITEM_TYPE);
        else
            this.type = INDEX_ITEM_TYPE;
    }
    */
}

package pl.psnc.epub.solr.beans.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class CollectionIndexItem extends IndexItem {
    
    protected static final String INDEX_ITEM_TYPE = "collection-index-item";

    private static final String KEYWORD_FIELD = "keyword";
    private static final String SUBJECT_FIELD = "subject";
    private static final String TITLE_FIELD = "title";
    private static final String SUMMARY_FIELD = "summary";
    private static final String AUTHOR_FIELD = "author";
    private static final String MAINTAINER_FIELD = "maintainer";
    private static final String LICENSOR_FIELD = "licensor";
    private static final String RECIPIENT_FIELD = "recipient";
    private static final String CONTENT_STATUS_FIELD = "content-status";
    private static final String EDUCATIONAL_LEVEL_FIELD = "educationLevel";
    private static final String CLASS_FIELD = "class";
    private static final String SIGNATURE_FIELD = "signature";
    private static final String COVER_FIELD = "cover";
    
    private static final String CREATED_FIELD = "created";
    private static final String REVISED_FIELD = "revised";
    private static final String LANGUAGE_FIELD = "language";

    private static final String VERSION_FIELD = "version";

    private static final String LICENSE_FIELD = "license";

    @Field(KEYWORD_FIELD)
    protected List<String> keywords;

    @Field(SUBJECT_FIELD)
    protected List<String> subjects;

    @Field(TITLE_FIELD)
    protected String title;
    
    @Field(SUMMARY_FIELD)
    protected String summary;

    @Field(AUTHOR_FIELD)
    protected List<String> authors = new ArrayList<String>();

    @Field(MAINTAINER_FIELD)
    protected List<String> maintainers = new ArrayList<String>();

    @Field(LICENSOR_FIELD)
    protected List<String> licensors = new ArrayList<String>();

    @Field(RECIPIENT_FIELD)
    protected String recipient;

    @Field(CONTENT_STATUS_FIELD)
    protected String contentStatus;

    @Field(EDUCATIONAL_LEVEL_FIELD)
    protected List<String> educationLevels = new ArrayList<String>();

    @Field(CLASS_FIELD)
    protected String clazz;

    @Field(SIGNATURE_FIELD)
    protected String signature;

    @Field(COVER_FIELD)
    protected String cover;

    @Field(VERSION_FIELD)
    protected String version;

    @Field(LICENSE_FIELD)
    protected String license;

    @Field(LANGUAGE_FIELD)
    protected String language;
    @Field(REVISED_FIELD)
    protected Date revised;
    @Field(CREATED_FIELD)
    protected Date created;

    public CollectionIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public CollectionIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }

    public CollectionIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
        if (solrDocument != null) {
            if (solrDocument.containsKey(KEYWORD_FIELD))
                this.keywords = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(KEYWORD_FIELD).toArray(new String[0])));
            if (solrDocument.containsKey(SUBJECT_FIELD))
                this.subjects = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(SUBJECT_FIELD).toArray(new String[0])));
            this.title = (String) solrDocument.getFieldValue(TITLE_FIELD);
            this.summary = (String) solrDocument.getFieldValue(SUMMARY_FIELD);
            if (solrDocument.containsKey(AUTHOR_FIELD))
                this.authors = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(AUTHOR_FIELD).toArray(new String[0])));
            if (solrDocument.containsKey(MAINTAINER_FIELD))
                this.maintainers = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(MAINTAINER_FIELD).toArray(new String[0])));
            if (solrDocument.containsKey(LICENSOR_FIELD))
                this.licensors = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(LICENSOR_FIELD).toArray(new String[0])));
            this.recipient = (String) solrDocument.getFieldValue(RECIPIENT_FIELD);
            this.contentStatus = (String) solrDocument.getFieldValue(CONTENT_STATUS_FIELD);
            if (solrDocument.containsKey(EDUCATIONAL_LEVEL_FIELD))
                this.educationLevels = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(EDUCATIONAL_LEVEL_FIELD).toArray(new String[0])));
            this.clazz = (String) solrDocument.getFieldValue(CLASS_FIELD);
            this.signature = (String) solrDocument.getFieldValue(SIGNATURE_FIELD);
            this.cover = (String) solrDocument.getFieldValue(COVER_FIELD);

            this.version = (String) solrDocument.getFieldValue(VERSION_FIELD);
            this.license = (String) solrDocument.getFieldValue(LICENSE_FIELD);
            this.language = (String) solrDocument.getFieldValue(LANGUAGE_FIELD);
            this.created = (Date) solrDocument.getFieldValue(CREATED_FIELD);
            this.revised = (Date) solrDocument.getFieldValue(REVISED_FIELD);
        }
    }
    
    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        if (keywords != null)
            this.keywords = keywords;
    }
    
    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        if (subjects != null)
            this.subjects = subjects;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void addAuthor(String author) {
        if (author != null)
            this.authors.add(author);
    }

    public void addMaintainer(String maintainer) {
        if (maintainer != null)
            this.maintainers.add(maintainer);
    }

    public void addLicensors(String licensor) {
        if (licensor != null)
            this.licensors.add(licensor);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    public List<String> getEducationLevels() {
        return educationLevels;
    }

    public void setEducationLevels(List<String> educationLevels) {
        if (educationLevels != null)
            this.educationLevels = educationLevels;
    }

    public void addEducationLevel(String level) {
        if (level != null)
            this.educationLevels.add(level);
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    
    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        if (authors != null)
            this.authors = authors;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getRevised() {
        return revised;
    }

    public void setRevised(Date revised) {
        this.revised = revised;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
}

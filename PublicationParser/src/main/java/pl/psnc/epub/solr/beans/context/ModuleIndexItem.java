package pl.psnc.epub.solr.beans.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrDocument;
import pl.psnc.epub.parser.jaxb.module.Actor;
import pl.psnc.epub.solr.common.IndexContext;
        
/**
 *
 * @author spychala
 */
public class ModuleIndexItem extends IndexItem {

    protected static final String INDEX_ITEM_TYPE = "module-index-item";

    private static final String KEYWORD_FIELD = "keyword";
    private static final String SUBJECT_FIELD = "subject";
    private static final String AUTHOR_FIELD = "author";
    private static final String MAINTAINER_FIELD = "maintainer";
    private static final String LICENSOR_FIELD = "licensor";
    private static final String RECIPIENT_FIELD = "recipient";
    private static final String CONTENT_STATUS_FIELD = "content-status";
    private static final String DEFINITION_FIELD = "definition";

    private static final String CLASS_FIELD = "class";
    private static final String SIGNATURE_FIELD = "signature";
    private static final String COVER_FIELD = "cover";
    
    private static final String EDUCATION_LEVEL_FIELD = "educationLevel";

    private static final String CORE_CURRICULUM_ENTRY_FIELD = "corecurriculumentry";
    private static final String CORE_CURRICULUM_SUBJECT_FIELD = "corecurriculumsubject";
    private static final String CORE_CURRICULUM_CODE_FIELD = "corecurriculumcode";
    private static final String CORE_CURRICULUM_KEYWORD_FIELD = "corecurriculumkeyword";
    
    private static final String CREATED_FIELD = "created";
    private static final String REVISED_FIELD = "revised";
    private static final String LANGUAGE_FIELD = "language";

    private static final String VERSION_FIELD = "version";
    private static final String LICENSE_FIELD = "license";

    @Field(KEYWORD_FIELD)
    protected List<String> keywords = new ArrayList<String>();
    
    @Field(SUBJECT_FIELD)
    protected List<String> subjects = new ArrayList<String>();

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

    @Field(DEFINITION_FIELD)
    protected List<String> definitions = new ArrayList<String>();

    @Field(EDUCATION_LEVEL_FIELD)
    protected List<String> educationLevels = new ArrayList<String>();
    
    @Field(CORE_CURRICULUM_ENTRY_FIELD)
    protected List<String> coreCurriculumEntries = new ArrayList<String>();
    
    @Field(CORE_CURRICULUM_SUBJECT_FIELD)
    protected List<String> coreCurriculumSubjects = new ArrayList<String>();

    @Field(CORE_CURRICULUM_CODE_FIELD)
    protected List<String> coreCurriculumCodes = new ArrayList<String>();

    @Field(CORE_CURRICULUM_KEYWORD_FIELD)
    protected List<String> coreCurriculumKeywords = new ArrayList<String>();

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
    
    public ModuleIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }
    
    public ModuleIndexItem(IndexContext context) {
        super(context);
        this.type = INDEX_ITEM_TYPE;
    }
        
    public ModuleIndexItem(SolrDocument solrDocument) {
        super(solrDocument);
        if (solrDocument != null) {
            if (solrDocument.containsKey(KEYWORD_FIELD))
                this.keywords = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(KEYWORD_FIELD).toArray(new String[0])));
            if (solrDocument.containsKey(SUBJECT_FIELD))
                this.subjects = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(SUBJECT_FIELD).toArray(new String[0])));
            //this.authors = Arrays.asList((String[])solrDocument.getFieldValues(AUTHOR_FIELD).toArray());
            //this.maintainers = Arrays.asList((String[])solrDocument.getFieldValues(MAINTAINER_FIELD).toArray());
            //this.licensors = Arrays.asList((String[])solrDocument.getFieldValues(LICENSOR_FIELD).toArray());
            if (solrDocument.containsKey(AUTHOR_FIELD))
                this.authors = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(AUTHOR_FIELD).toArray(new String[0])));
            if (solrDocument.containsKey(MAINTAINER_FIELD))
                this.maintainers = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(MAINTAINER_FIELD).toArray(new String[0])));
            if (solrDocument.containsKey(LICENSOR_FIELD))
                this.licensors = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(LICENSOR_FIELD).toArray(new String[0])));
            this.recipient = (String) solrDocument.getFieldValue(RECIPIENT_FIELD);
            this.contentStatus = (String) solrDocument.getFieldValue(CONTENT_STATUS_FIELD);
            if (solrDocument.containsKey(DEFINITION_FIELD))
                this.definitions = new ArrayList<String>(Arrays.asList(solrDocument.getFieldValues(DEFINITION_FIELD).toArray(new String[0])));

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
        if (keywords != null && keywords.size() > 0) {
            System.out.println(keywords.toString());
            this.keywords = keywords;
        }
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(List<String> maintainers) {
        this.maintainers = maintainers;
    }

    public List<String> getLicensors() {
        return licensors;
    }

    public void setLicensors(List<String> licensors) {
        this.licensors = licensors;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
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

    public List<String> getEducationLevels() {
        return educationLevels;
    }

    public void setEducationLevels(List<String> educationLevels) {
        this.educationLevels = educationLevels;
    }
    
    public void addEducationLevel(String educationLevel) {
        if (educationLevels != null)
            this.educationLevels.add(educationLevel);
    }
    

    public List<String> getCoreCurriculumEntries() {
        return coreCurriculumEntries;
    }

    public void setCoreCurriculumEntries(List<String> coreCurriculumEntries) {
        this.coreCurriculumEntries = coreCurriculumEntries;
    }

    public void addCoreCurriculumEntry(String coreCurriculumEntry) {
        if (coreCurriculumEntries != null)
            this.coreCurriculumEntries.add(coreCurriculumEntry);
    }

    public List<String> getCoreCurriculumSubjects() {
        return coreCurriculumSubjects;
    }

    public void setCoreCurriculumSubjects(List<String> coreCurriculumSubjects) {
        this.coreCurriculumSubjects = coreCurriculumSubjects;
    }

    public void addCoreCurriculumSubject(String coreCurriculumSubject) {
        if (coreCurriculumSubjects != null)
           this.coreCurriculumSubjects.add(coreCurriculumSubject);
    }

    public List<String> getCoreCurriculumCodes() {
        return coreCurriculumCodes;
    }

    public void setCoreCurriculumCodes(List<String> coreCurriculumCodes) {
        this.coreCurriculumCodes = coreCurriculumCodes;
    }

    public void addCoreCurriculumCode(String coreCurriculumCode) {
        if (coreCurriculumCodes != null)
           this.coreCurriculumCodes.add(coreCurriculumCode);
    }

    public List<String> getCoreCurriculumKeywords() {
        return coreCurriculumKeywords;
    }

    public void setCoreCurriculumKeywords(List<String> coreCurriculumKeywords) {
        this.coreCurriculumKeywords = coreCurriculumKeywords;
    }
    
    public void addCoreCurriculumKeyword(String coreCurriculumKeyword) {
        if (coreCurriculumKeywords != null)
           this.coreCurriculumKeywords.add(coreCurriculumKeyword);
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

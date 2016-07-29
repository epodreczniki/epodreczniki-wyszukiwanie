package pl.psnc.epub.parser.jaxb.collection;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author spychala
 */
@XmlType(
    //propOrder={"actors", "roles"},
    factoryClass=CollectionFactory.class,
    factoryMethod="createMetadata")
public class Metadata {

    private String mdmlVersion;
    private String repository;
    private String contentUrl;
    private String contentId;
    private String title;
    private String version;
    private String created;
    private String revised;
    private List<Actor> actors;
    //private List<Role> roles;
    private Map<String, List<String>> roles;
    private License license;
    
    private List<String> keywords;
    private List<String> subjects;
    
    private String abstr;
    private String language;

    private Date createTime;

    // epo extensions
    private List<String> educationLevels;
    private String clazz;
    private String recipient;
    private String contentStatus;
    private String signature;
    //private String cover;
    
    private ETextBook eTextBook;

    Metadata(Date date) {
        createTime = date;
    }

    @XmlAttribute(name = "mdml-version", namespace="http://cnx.rice.edu/mdml")
    public String getMdmlVersion() {
        return mdmlVersion;
    }

    public void setMdmlVersion(String mdmlVersion) {
        this.mdmlVersion = mdmlVersion;
    }

    @XmlElement(name="repository", namespace="http://cnx.rice.edu/mdml")
    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    @XmlElement(name="content-url", namespace="http://cnx.rice.edu/mdml")
    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    @XmlElement(name="content-id", namespace="http://cnx.rice.edu/mdml")
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @XmlElement(name="title", namespace="http://cnx.rice.edu/mdml")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name="version", namespace="http://cnx.rice.edu/mdml")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name="created", namespace="http://cnx.rice.edu/mdml")
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @XmlElement(name="revised", namespace="http://cnx.rice.edu/mdml")
    public String getRevised() {
        return revised;
    }

    public void setRevised(String revised) {
        this.revised = revised;
    }

    @XmlElementWrapper(name="actors", namespace="http://cnx.rice.edu/mdml")
    @XmlElementRef()
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @XmlElement(name = "roles", namespace="http://cnx.rice.edu/mdml")
    @XmlJavaTypeAdapter(RolesAdapter.class)
    public Map<String, List<String>> getRoles() {
        return roles;
    }
    public void setRoles(Map<String, List<String>> roles) {
        this.roles = roles;
    }

    /*
    @XmlElementWrapper(name="roles", namespace="http://cnx.rice.edu/mdml")
    @XmlElementRef()
    public List<Role> getRoles() {
    return roles;
    }
    public void setRoles(List<Role> roles) {
    this.roles = roles;
    }
     */
    
    
    @XmlElement(name="license", namespace="http://cnx.rice.edu/mdml")
    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    @XmlElement(name="language", namespace="http://cnx.rice.edu/mdml")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlElement(name="abstract", namespace="http://cnx.rice.edu/mdml")
    public String getAbstr() {
        return abstr;
    }

    public void setAbstr(String abstr) {
        this.abstr = abstr;
    }

    @XmlElement(name = "keywordlist", namespace="http://cnx.rice.edu/mdml")
    @XmlJavaTypeAdapter(KeywordAdapter.class)
    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @XmlElement(name = "subjectlist", namespace="http://cnx.rice.edu/mdml")
    @XmlJavaTypeAdapter(SubjectAdapter.class)
    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "education-levellist", namespace="http://cnx.rice.edu/mdml")
    @XmlJavaTypeAdapter(EducationLevelAdapter.class)
    public List<String> getEducationLevels() {
        return educationLevels;
    }

    public void setEducationLevels(List<String> educationLevels) {
        this.educationLevels = educationLevels;
    }

    @XmlElement(name = "class", namespace="http://epodreczniki.pl/")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @XmlElement(name = "recipient", namespace="http://epodreczniki.pl/")
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @XmlElement(name = "content-status", namespace="http://epodreczniki.pl/")
    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    @XmlElement(name = "signature", namespace="http://epodreczniki.pl/")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    /*
    @XmlElement(name = "cover", namespace="http://epodreczniki.pl/")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    */

    @XmlElement(name="e-textbook", namespace="http://epodreczniki.pl/")
    public ETextBook geteTextBook() {
        return eTextBook;
    }

    public void seteTextBook(ETextBook eTextBook) {
        this.eTextBook = eTextBook;
    }
    
    public String getContextId() {
        
        String contextId = contentId;
        String variant = null;
        if (eTextBook != null) {
            if (eTextBook.getRecipient() != null)
                variant = eTextBook.getRecipient();

            if (variant != null)
                if (version != null)
                    contextId = String.format("%s/%s/%s", contentId, version, variant);
        }
        return contextId;
    }

}

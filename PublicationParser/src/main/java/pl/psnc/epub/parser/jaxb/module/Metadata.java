package pl.psnc.epub.parser.jaxb.module;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.parser.jaxb.collection.RolesAdapter;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlType(namespace="http://cnx.rice.edu/mdml")
public class Metadata extends ModuleEntity {

    private String contentId;
    private String title;
    private String version;
    private String created;
    private String revised;
    private List<Author> authors;
    private List<Actor> actors;
    //private Map<String, String> roles;
    private Map<String, List<String>> roles;
    
    private List<Maintainer> maintainers;
    private List<Licensor> licensors;

    private License license;
    
    private List<String> keywords;
    private List<String> subjects;
    
    private String abstr;
    private String language;

    // epo extensions
    private String recipient;
    private String contentStatus;
    
    private ETextBookModule eTextBookModule;

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

    @XmlElementWrapper(name="authors", namespace="http://cnx.rice.edu/mdml")
    @XmlElementRef()
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @XmlElementWrapper(name="licensors", namespace="http://cnx.rice.edu/mdml")
    @XmlElementRef()
    public List<Licensor> getLicensors() {
        return licensors;
    }

    public void setLicensors(List<Licensor> licensors) {
        this.licensors = licensors;
    }

    @XmlElementWrapper(name="maintainers", namespace="http://cnx.rice.edu/mdml")
    @XmlElementRef()
    public List<Maintainer> getMaintainers() {
        return maintainers;
    }

    public void setMiantainers(List<Maintainer> maintainers) {
        this.maintainers = maintainers;
    }

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

    @XmlElement(name="e-textbook-module", namespace="http://epodreczniki.pl/")
    public ETextBookModule geteTextBookModule() {
        return eTextBookModule;
    }

    public void seteTextBookModule(ETextBookModule eTextBookModule) {
        this.eTextBookModule = eTextBookModule;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        return null;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        return null;
    }

    @Override
    public AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent) {
        return null;
    }

    @Override
    public List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent) {
        return null;
    }
    
    @Override
    public void setCollectionId(String collectionId) {
        
        this.collectionId = collectionId;
        
        if (actors != null)
            for (Actor a : actors) {
                a.setCollectionId(collectionId);
            }
        if (authors != null)
            for (Author a : authors) {
                a.setCollectionId(collectionId);
            }
        if (maintainers != null)
            for (Maintainer m : maintainers) {
                m.setCollectionId(collectionId);
            }
        if (licensors != null)
            for (Licensor l : licensors) {
                l.setCollectionId(collectionId);
            }
        if (license != null)
            license.setCollectionId(collectionId);

    }    
    @Override
    public void setModuleId(String moduleId) {
        
        this.moduleId = moduleId;
        
        if (actors != null)
            for (Actor a : actors) {
                a.setModuleId(moduleId);
            }
        if (license != null)
            license.setModuleId(moduleId);
    }    

    public String getContextId() {
        
        String contextId = contentId;
        String variant = null;
        if (eTextBookModule != null) {
            if (eTextBookModule.getRecipient() != null)
                if (eTextBookModule.getContentStatus() != null)
                    variant = String.format("%s-%s", eTextBookModule.getRecipient(), eTextBookModule.getContentStatus());
                else
                    variant = eTextBookModule.getRecipient();
            else
                if (eTextBookModule.getContentStatus() != null)
                    variant = eTextBookModule.getContentStatus();

            if (variant != null)
                if (version != null)
                    contextId = String.format("%s/%s/%s", contentId, version, variant);
        }
        return contextId;
    }
}

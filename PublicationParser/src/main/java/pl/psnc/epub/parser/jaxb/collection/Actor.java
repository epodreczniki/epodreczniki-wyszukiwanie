package pl.psnc.epub.parser.jaxb.collection;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name = "actor")
@XmlSeeAlso({Person.class,Organization.class})
public class Actor extends BaseEntity {
    
    protected String id;
    protected String email;
    protected String fullName;
    protected String homePage;

    @XmlAttribute(name="userid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="email", namespace="http://cnx.rice.edu/mdml")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @XmlElement(name="fullname", namespace="http://cnx.rice.edu/mdml")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlElement(name="homepage", namespace="http://cnx.rice.edu/mdml")
    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
    
    @Override
    public String toString() {
        return String.format("%s [%s, %s]", id, fullName, email);
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
}

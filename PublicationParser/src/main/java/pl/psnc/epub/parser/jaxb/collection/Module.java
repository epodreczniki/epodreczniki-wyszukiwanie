package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.CollectionModuleIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="module")
public class Module extends CollectionElement {
    
    private String document;
    private String version;
    private String repository;
    private String url;
    private String versionAtThisCollection;
    
    private List<LinkGroup> featuredLinks;// = new ArrayList<LinkGroup>();

    //private String title;
    
    @XmlAttribute
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @XmlAttribute
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlAttribute
    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    @XmlAttribute
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlAttribute(name="version-at-this-collection-version", namespace="http://cnx.rice.edu/system-info")
    public String getVersionAtThisCollection() {
        return versionAtThisCollection;
    }

    public void setVersionAtThisCollection(String versionAtThisCollection) {
        this.versionAtThisCollection = versionAtThisCollection;
    }

    @XmlElementWrapper(name="featured-links")
    @XmlElementRef()
    public List<LinkGroup> getFeaturedLinks() {
        return featuredLinks;
    }

    public void setFeaturedLinks(List<LinkGroup> featuredLinks) {
        this.featuredLinks = featuredLinks;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {

        return null;
        /*
        IndexItem indexItem = null;
        if (document != null && !"".equals(document)) {
            indexItem = new CollectionModuleIndexItem();
            indexItem.setParent(parent);
            indexItem.setId(document);
            indexItem.setCollectionId(collectionId);
            indexItem.setContent(title);
            indexItem.setFullContent(this.toString());
        }
        return indexItem;
        */
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        return null;
    }
    
}

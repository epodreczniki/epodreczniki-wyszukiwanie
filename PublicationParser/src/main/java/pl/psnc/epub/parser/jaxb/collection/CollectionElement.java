package pl.psnc.epub.parser.jaxb.collection;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name = "element")
@XmlSeeAlso({Module.class,Subcollection.class})
public abstract class CollectionElement {

    String title;
    protected String collectionId;

    @XmlElement(name="title", namespace="http://cnx.rice.edu/mdml")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract IndexItem generateSelfIndex(IndexItem parent, IndexContext context);
    
    public abstract List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context);

    public List<IndexItem> generateIndex(IndexItem parent, IndexContext context) {
        
        IndexItem selfIndexItem = generateSelfIndex(parent, context);
        
        IndexItem currentItem = selfIndexItem;
        if (currentItem == null)
            currentItem = parent;
            
        List<IndexItem> childIndexItems = generateChildIndex(currentItem, context);
        
        ArrayList<IndexItem> resultIndexItems = new ArrayList<IndexItem>();
        if (selfIndexItem != null) 
            resultIndexItems.add(selfIndexItem);
        
        if (childIndexItems != null)
            resultIndexItems.addAll(childIndexItems);
        
        return resultIndexItems;
    }

    public List<IndexItem> generateIndex(IndexItem parent) {
        return generateIndex(parent, null);
    }

    public List<IndexItem> generateIndex(IndexContext context) {
        return generateIndex(null, context);
    }
    
    public List<IndexItem> generateIndex() {
        return generateIndex(null, null);
    }

    @XmlTransient
    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
    
    public String toString() {
        String result = null;
        try {
            JAXBContext moduleJaxbContext = JAXBContext.newInstance(Collection.class, CollectionElement.class);
            Marshaller moduleMarshaller = moduleJaxbContext.createMarshaller();
            moduleMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            moduleMarshaller.marshal(this, stringWriter);
            result = stringWriter.toString();
        } catch(JAXBException jaxbe) {
            
        }
        return result;
    }
    
}

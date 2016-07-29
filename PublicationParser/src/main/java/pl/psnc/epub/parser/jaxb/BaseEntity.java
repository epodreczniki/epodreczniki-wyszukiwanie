package pl.psnc.epub.parser.jaxb;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlTransient;
import pl.psnc.epub.parser.jaxb.module.Document;
import pl.psnc.epub.parser.jaxb.module.ModuleElement;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public abstract class BaseEntity implements Indexable {
 
    protected IndexItem itemIndex;
    protected AutocompleteIndexItem autocompleteItemIndex;
    protected String collectionId;
    protected String moduleId;

    public String toString() {
        String result = null;
        try {
            JAXBContext moduleJaxbContext = JAXBContext.newInstance(Document.class, ModuleElement.class);
            Marshaller moduleMarshaller = moduleJaxbContext.createMarshaller();
            moduleMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            moduleMarshaller.marshal(this, stringWriter);
            result = stringWriter.toString();
        } catch(JAXBException jaxbe) {
            
        }
        return (result==null?"":result);
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
            if(selfIndexItem.getCollectionId() != null)
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
    
    public abstract AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent);
    
    public abstract List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent);

    public List<AutocompleteIndexItem> generateAutocompleteIndex(AutocompleteIndexItem parent) {

        AutocompleteIndexItem selfIndexItem = generateSelfAutocompleteIndex(parent);
        
        AutocompleteIndexItem currentItem = selfIndexItem;
        if (currentItem == null)
            currentItem = parent;
            
        List<AutocompleteIndexItem> childIndexItems = generateChildAutocompleteIndex(currentItem);
        
        ArrayList<AutocompleteIndexItem> resultIndexItems = new ArrayList<AutocompleteIndexItem>();
        if (selfIndexItem != null) 
            resultIndexItems.add(selfIndexItem);
        
        if (childIndexItems != null)
            resultIndexItems.addAll(childIndexItems);
        
        return resultIndexItems;
    }
    
    public List<AutocompleteIndexItem> generateAutocompleteIndex() {
        return generateAutocompleteIndex(null);
    }
    

    @XmlTransient
    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
    
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    
}

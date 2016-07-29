package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.parser.jaxb.womi.WOMIList;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.EPReferenceIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="reference", namespace="http://epodreczniki.pl/")
public class EPReference extends EPGenericElement {

    protected String instanceId;

    protected String width;
    
    protected List<EPContent> content;

    //@XmlTransient
    @XmlElement(name="content", namespace="http://epodreczniki.pl/")
    public List<EPContent> getContent() {
        return content;
    }

    public void setContent(List<EPContent> content) {
        this.content = content;
    }

    //@XmlTransient
    @XmlElement(name="width", namespace="http://epodreczniki.pl/")
    public String getWidth() {
        return width;
    }
    
    public void setWidth(String width) {
        this.width = width;
    }
    
    //@XmlTransient
    @XmlAttribute(name="id", namespace="http://epodreczniki.pl/")
    public String getEpId() {
        return id;
    }

    public void setEpId(String id) {
        this.id = id;
    }

    //@XmlTransient
    @XmlAttribute(name="instance-id", namespace="http://epodreczniki.pl/")
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        EPReferenceIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new EPReferenceIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            //itemIndex.setContent(title);
            itemIndex.setFullContent(this.toString());
            
            //WOMIList womiList = (WOMIList)SingletonRegistry.getInstance(WOMIList.class.getName());
            WOMIList womiList = WOMIList.getInstance();
            womiList.addWOMI(parent, context, id, instanceId, moduleId, collectionId);
        }
        return itemIndex;
    }
    
    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        
        List<IndexItem> indexItems = new ArrayList<IndexItem>();
        if (content != null) {
            for (EPContent contentElem : content) {
                indexItems.addAll(contentElem.generateIndex(parent, context));
            }
        }
        return indexItems;
        
    }

    @Override
    public String toString() {
        return String.format("%s : %s", id, instanceId);
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
        if (content != null)
            for (EPContent contentElem : content)
                contentElem.setCollectionId(collectionId);
        
    }
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        if (content != null)
            for (EPContent contentElem : content)
                contentElem.setModuleId(moduleId);
        
    }

}

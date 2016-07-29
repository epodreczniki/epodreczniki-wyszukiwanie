package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.ModuleIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class GenericElement extends ModuleElement {
    
    protected List<Object> content;

    //@XmlMixed
    //@XmlAnyElement
    @XmlAnyElement(lax = true)
    @XmlMixed
    public List<Object> getText() {
        return content;
    }

    public void setText(List<Object> content) {
        this.content = content;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        List<IndexItem> indexItems =  new ArrayList<IndexItem>();
        if (content != null)
            for (Object o : content) {
                if (o instanceof ModuleElement)
                    indexItems.addAll(((ModuleElement)o).generateIndex(parent, context));
                else {
                    ModuleIndexItem moduleIndexItem = new ModuleIndexItem(context);
                    moduleIndexItem.setId(id);
                    moduleIndexItem.setCollectionId(collectionId);
                    moduleIndexItem.setModuleId(moduleId);
                    moduleIndexItem.setContent(o.toString());
                }
            }
        return indexItems;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (content != null)
            for (Object o : content) {
                if (o instanceof BaseEntity)
                    ((BaseEntity)o).setCollectionId(collectionId);
            }
    }    

    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        
        if (content != null)
            for (Object o : content) {
                if (o instanceof ModuleEntity)
                    ((ModuleEntity)o).setModuleId(moduleId);
            }
    }    
    
}

package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlAttribute;
import pl.psnc.epub.parser.jaxb.BaseEntity;

/**
 *
 * @author spychala
 */
public class EPGenericElement extends GenericElement {
    
    @XmlAttribute(name="id", namespace="http://epodreczniki.pl/")
    public String getEpId() {
        return id;
    }

    public void setEpId(String id) {
        this.id = id;
    }
    
    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (content != null) {
            for (Object o : content) {
                if (o instanceof BaseEntity)
                    ((BaseEntity)o).setCollectionId(collectionId);
            }
        }
    }    
    
    @Override
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
        if (content != null)
            for (Object o : content)
                if (o instanceof BaseEntity)
                    ((BaseEntity)o).setModuleId(moduleId);
    }

}

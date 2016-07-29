package pl.psnc.epub.parser.jaxb.module;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.ModuleIndexItem;
import pl.psnc.epub.solr.beans.context.SectionIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="section")
public class Section extends ContentElement {
    
    protected String title;

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new SectionIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            //itemIndex.setContent(this.toString());
            Pattern patternReference = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            Pattern patternParams = Pattern.compile("<ep:parameters.*?>.*?</ep:parameters.*?>|<ep:parameters.*?/>", Pattern.DOTALL);
            String str = patternReference.matcher(this.toString()).replaceAll("");
            str = patternParams.matcher(str).replaceAll("");
            itemIndex.setFullContent(str);
        }
        return itemIndex;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (content != null)
            for (Object o : content)
                if (o instanceof BaseEntity)
                    ((BaseEntity)o).setCollectionId(collectionId);

    }    
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        
        if (content != null)
            for (Object o : content)
                if (o instanceof ModuleEntity)
                    ((ModuleEntity)o).setModuleId(moduleId);
    }    

    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getContentAsString();
    }
    
}

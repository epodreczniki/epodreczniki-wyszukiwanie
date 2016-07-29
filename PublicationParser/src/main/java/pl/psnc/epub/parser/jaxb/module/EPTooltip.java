package pl.psnc.epub.parser.jaxb.module;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.EPToolTipIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="tooltip", namespace="http://epodreczniki.pl/")
public class EPTooltip  extends ContentElement {
    
    protected String title;
    
    protected EPContent content;

    @XmlElement(name="content", namespace="http://epodreczniki.pl/")
    public EPContent getContentElement() {
        return content;
    }

    public void setContentElement(EPContent content) {
        this.content = content;
    }

    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlAttribute(namespace="http://epodreczniki.pl/")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        EPToolTipIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new EPToolTipIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setContent(title);
            //itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }
    
    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        return content.generateIndex(parent, context);
    }

    @Override
    public String toString() {
        return String.format("%s : %s", title, (content == null ? "" :  content.toString()));
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
        content.setCollectionId(collectionId);
        
    }    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        content.setModuleId(moduleId);
        
    }    

}

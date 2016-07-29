package pl.psnc.epub.parser.jaxb.module;

import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPStepIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="step", namespace="http://epodreczniki.pl/")
public class EPStep extends ContentElement {
    
    protected String title;
    
    @XmlAttribute(name="id", namespace="http://epodreczniki.pl/")
    public String getEpId() {
        return id;
    }

    public void setEpId(String id) {
        this.id = id;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        
        if (id != null && !"".equals(id)) {
            itemIndex = new EPStepIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            itemIndex.setContent(str);
            itemIndex.setFullContent(str);
        }
        return itemIndex;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        return null;
    }

}

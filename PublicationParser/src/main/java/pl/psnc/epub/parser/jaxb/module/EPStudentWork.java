package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPBiographyIndexItem;
import pl.psnc.epub.solr.beans.context.EPProcedureInstructionsIndexItem;
import pl.psnc.epub.solr.beans.context.EPStudentWorkIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="student-work", namespace="http://epodreczniki.pl/")
public class EPStudentWork extends ContentElement {
    
    protected String type;
        
    @XmlAttribute(name="id", namespace="http://epodreczniki.pl/")
    public String getEpId() {
        return id;
    }

    public void setEpId(String id) {
        this.id = id;
    }

    @XmlElement(name="type", namespace="http://epodreczniki.pl/")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        EPStudentWorkIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new EPStudentWorkIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            itemIndex.setContent(str);
            //itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }
/*    
    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent) {

        List<IndexItem> indexItems = new ArrayList<IndexItem>();
        if (content != null) {
            indexItems.addAll(content.generateIndex(parent));
        }
        return indexItems;
    }
*/
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(type);
        if (content != null) {
            sb.append(" ");
            sb.append(content);
        }
        return sb.toString();
    }
/*
    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        if (content != null)
            content.setCollectionId(collectionId);

    }
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        if (content != null)
            content.setModuleId(collectionId);
        
    }
*/
}

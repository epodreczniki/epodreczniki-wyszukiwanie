package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPBiographyIndexItem;
import pl.psnc.epub.solr.beans.context.EPProcedureInstructionsIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="biography", namespace="http://epodreczniki.pl/")
public class EPBiography extends ModuleElement {
    
    protected String name;
    
    protected EPBirth birth;

    protected EPDeath death;
    
    protected EPReference reference;
    
    protected EPContent content;

    @XmlAttribute(name="id", namespace="http://epodreczniki.pl/")
    public String getEpId() {
        return id;
    }

    public void setEpId(String id) {
        this.id = id;
    }

    @XmlElement(name="name", namespace="http://epodreczniki.pl/")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name="birth", namespace="http://epodreczniki.pl/")
    public EPBirth getBirth() {
        return birth;
    }

    public void setBirth(EPBirth birth) {
        this.birth = birth;
    }

    @XmlElement(name="death", namespace="http://epodreczniki.pl/")
    public EPDeath getDeath() {
        return death;
    }

    public void setDeath(EPDeath death) {
        this.death = death;
    }

    @XmlElement(name="reference", namespace="http://epodreczniki.pl/")
    public EPReference getReference() {
        return reference;
    }

    public void setReference(EPReference reference) {
        this.reference = reference;
    }

    @XmlElement(name="content", namespace="http://epodreczniki.pl/")
    public EPContent getContent() {
        return content;
    }

    public void setContent(EPContent content) {
        this.content = content;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        EPBiographyIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new EPBiographyIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setContent(this.toString());
            //itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }
    
    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {

        List<IndexItem> indexItems = new ArrayList<IndexItem>();
        if (content != null) {
            indexItems.addAll(content.generateIndex(parent, context));
        }
        return indexItems;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(name);
        if (birth != null)
            sb.append(String.format(" (%s", birth));
        else
            sb.append("(");
        if (death != null)
            sb.append(String.format(", %s)", death));
        else
            sb.append(")");
        if (content != null) {
            sb.append(" ");
            sb.append(content);
        }
        return sb.toString();
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        if (content != null)
            content.setCollectionId(collectionId);
        if (birth != null)
            birth.setCollectionId(collectionId);
        if (death != null)
            death.setCollectionId(collectionId);
        if (reference != null)
            reference.setCollectionId(collectionId);

    }
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        if (content != null)
            content.setModuleId(collectionId);
        if (birth != null)
            birth.setModuleId(collectionId);
        if (death != null)
            death.setModuleId(collectionId);
        if (reference != null)
            reference.setModuleId(collectionId);
        
    }

}

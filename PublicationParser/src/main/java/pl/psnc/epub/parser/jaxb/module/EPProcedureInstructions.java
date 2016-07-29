package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPProcedureInstructionsIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="procedure-instructions", namespace="http://epodreczniki.pl/")
public class EPProcedureInstructions extends ModuleElement {
    
    protected String title;
    
    protected List<EPStep> steps;

    @XmlAttribute(name="id", namespace="http://epodreczniki.pl/")
    public String getEpId() {
        return id;
    }

    public void setEpId(String id) {
        this.id = id;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        EPProcedureInstructionsIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new EPProcedureInstructionsIndexItem(context);
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

        List<IndexItem> indexItems = new ArrayList<IndexItem>();
        if (steps != null) {
            for (EPStep step : steps) {
                indexItems.addAll(step.generateIndex(parent, context));
            }
        }
        return indexItems;
    }

    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @XmlElement(name="step", namespace="http://epodreczniki.pl/")
    public List<EPStep> getSteps() {
        return steps;
    }

    public void setSteps(List<EPStep> steps) {
        this.steps = steps;
    }

        @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        if (steps != null)
            for (EPStep step : steps)
                step.setCollectionId(collectionId);
        
    }
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        if (steps != null)
            for (EPStep step : steps)
                step.setModuleId(moduleId);
        
    }

}

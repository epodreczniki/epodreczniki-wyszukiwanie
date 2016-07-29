package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPObservationIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="observation", namespace="http://epodreczniki.pl/")
public class EPObservation extends EPGenericElement {

    protected String title;
    
    protected EPObjective objective;
    
    protected EPInstruments instruments;

    protected EPInstructions instructions;

    protected EPConclusions conclusions;
    
    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlElement(name="objective", namespace="http://epodreczniki.pl/")
    public EPObjective getObjective() {
        return objective;
    }

    public void setObjective(EPObjective objective) {
        this.objective = objective;
    }

    @XmlElement(name="instruments", namespace="http://epodreczniki.pl/")
    public EPInstruments getInstruments() {
        return instruments;
    }

    public void setInstruments(EPInstruments instruments) {
        this.instruments = instruments;
    }

    @XmlElement(name="instructions", namespace="http://epodreczniki.pl/")
    public EPInstructions getInstructions() {
        return instructions;
    }

    public void setInstructions(EPInstructions instructions) {
        this.instructions = instructions;
    }
    
    @XmlElement(name="conclusions", namespace="http://epodreczniki.pl/")
    public EPConclusions getConclusions() {
        return conclusions;
    }

    public void setConclusions(EPConclusions conclusions) {
        this.conclusions = conclusions;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new EPObservationIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            itemIndex.setContent(title);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            itemIndex.setFullContent(str);
        }
        return itemIndex;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        List<IndexItem> indexItems = new ArrayList<IndexItem>();
        if (objective != null)
            indexItems.addAll(objective.generateIndex(parent, context));
        if (instruments != null)
            indexItems.addAll(instruments.generateIndex(parent, context));
        if (instructions != null)
            indexItems.addAll(instructions.generateIndex(parent, context));
        if (conclusions != null)
            indexItems.addAll(conclusions.generateIndex(parent, context));
        return indexItems;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (collectionId != null) {
            if (objective != null)
                objective.setCollectionId(collectionId);
            if (instruments != null)
                instruments.setCollectionId(collectionId);
            if (instructions != null)
                instructions.setCollectionId(collectionId);
            if (conclusions != null)
                conclusions.setCollectionId(collectionId);
        }
        
    }

    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        
        if (moduleId != null) {
            if (objective != null)
                objective.setModuleId(moduleId);
            if (instruments != null)
                instruments.setModuleId(moduleId);
            if (instructions != null)
                instructions.setModuleId(moduleId);
            if (conclusions != null)
                conclusions.setModuleId(moduleId);
        }
        
    }
    
}

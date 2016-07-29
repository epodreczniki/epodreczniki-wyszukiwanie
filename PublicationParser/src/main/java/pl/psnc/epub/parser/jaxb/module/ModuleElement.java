package pl.psnc.epub.parser.jaxb.module;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.ModuleIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlSeeAlso({Paragraph.class, Note.class, Example.class, Section.class, 
    Figure.class, Equation.class, Exercise.class, Problem.class, Solution.class, 
    Rule.class, Code.class, Definition.class, Meaning.class, EPParameters.class, 
    EPEffect.class, EPRevisal.class, EPTooltip.class, EPReference.class, 
    EPProcedureInstructions.class, EPStep.class, EPBiography.class,
    EPIntro.class, EPCommand.class, EPObservation.class, EPInstruments.class, 
    EPInstructions.class, EPObjective.class, EPLead.class, EPConclusions.class, 
    EPStudentWork.class, EPBookmark.class, EPMathElement.class, EPConcept.class,
    EPStep.class, EPTechnicalRemarks.class, 
    EPExperiment.class,
    EPProblem.class,
    EPHypothesis.class,
    EPConclusions.class
})
public class ModuleElement extends ModuleEntity {

    protected String id;

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*
    public List<IndexItem> generateIndex(IndexItem parent) {
        List<IndexItem> indexItems =  new ArrayList<IndexItem>();
        if (id != null && !"".equals(id)) {
            ModuleIndexItem item = new ModuleIndexItem();
            item.setId(id);
            item.setContent(this.toString());
            item.setParent(parent);
            indexItems.add(item);
        }
        return indexItems;
    }
    */

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        //ModuleIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new ModuleIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            itemIndex.setContent(this.toString());
            itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        return null;
    }

    @Override
    public AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent) {
        return null;
    }

    @Override
    public List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent) {
        return null;
    }

}

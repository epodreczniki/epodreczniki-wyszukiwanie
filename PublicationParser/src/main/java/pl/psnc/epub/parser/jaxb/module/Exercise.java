package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.ExerciseIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="exercise")
public class Exercise extends ContentElement {

    protected String title;
    
    protected Problem problem;
    
    protected Solution solution;

    @XmlElement(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlElement(name="problem")
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @XmlElement(name="solution")
    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
    
    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new ExerciseIndexItem(context);
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
        if (problem != null)
            indexItems.addAll(problem.generateIndex(parent, context));
        if (solution != null)
            indexItems.addAll(solution.generateIndex(parent, context));
        return indexItems;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (collectionId != null) {
            if (problem != null)
                problem.setCollectionId(collectionId);
            if (solution != null)
                solution.setCollectionId(collectionId);
        }
        
    }

    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        
        if (moduleId != null) {
            if (problem != null)
                problem.setModuleId(moduleId);
            if (solution != null)
                solution.setModuleId(moduleId);
        }
        
    }
    
}

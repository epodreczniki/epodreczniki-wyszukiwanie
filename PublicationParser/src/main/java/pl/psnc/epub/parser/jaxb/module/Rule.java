package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.RuleIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="rule")
//@XmlType(name="note", namespace="http://cnx.rice.edu/cnxml")
public class Rule extends ModuleElement {

    protected String type;
    
    protected String title;

    protected Statement statement;

    protected Proof proof;

    protected Example example;
    
    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new RuleIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            itemIndex.setFullContent(str);
        }
        return itemIndex;
    }
    
    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        List<IndexItem> indexItems = new ArrayList<IndexItem>();
        if (statement != null)
            indexItems.addAll(statement.generateIndex(parent, context));
        if (proof != null)
            indexItems.addAll(proof.generateIndex(parent, context));
        return indexItems;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (collectionId != null) {
            if (statement != null)
                statement.setCollectionId(collectionId);
            if (proof != null)
                proof.setCollectionId(collectionId);
        }
        
    }
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        
        if (moduleId != null) {
            if (statement != null)
                statement.setModuleId(moduleId);
            if (proof != null)
                proof.setModuleId(moduleId);
        }
        
    }

    @XmlAttribute(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name="statement")
    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @XmlElement(name="proof")
    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
    }

    @XmlElement(name="example")
    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }
    
}

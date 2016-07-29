package pl.psnc.epub.parser.jaxb.module;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.DefinitionIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement
@XmlType(namespace="http://cnx.rice.edu/cnxml")
public class Definition extends ModuleEntity {

    protected String id;
    
    protected String term;
    
    protected Meaning meaning;

    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="term")
    public String getTerm() {
        return term;
    }
    
    public void setTerm(String term) {
        this.term = term;
    }

    @XmlElement(name="meaning")
    public Meaning getMeaning() {
        return meaning;
    }

    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        //DefinitionIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new DefinitionIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            itemIndex.setContent(term);
            //itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        return meaning.generateIndex(parent, context);
    }

    @Override
    public String toString() {
        return String.format("%s : %s", term, (meaning == null ? "" : meaning.toString()));
        //return String.format("%s", term);
    }

    @Override
    public AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent) {
        return null;
    }

    @Override
    public List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent) {
        return meaning.generateAutocompleteIndex(parent);
    }
    
    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
                
        if (meaning != null)
            meaning.setCollectionId(collectionId);

    }

    @Override
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
        if (meaning != null)
            meaning.setModuleId(moduleId);
    }
}

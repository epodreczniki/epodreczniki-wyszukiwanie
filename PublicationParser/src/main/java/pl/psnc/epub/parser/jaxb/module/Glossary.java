package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlType(namespace="http://cnx.rice.edu/cnxml")
public class Glossary extends ModuleEntity {
    
    private List<Definition> definitions;

    @XmlElement(name="definition")
    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> defs) {
        this.definitions = defs;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        return null;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        List<IndexItem> indexItems =  new ArrayList<IndexItem>();
        for (Object o : definitions) {
            if (o instanceof Definition)
                indexItems.addAll(((Definition)o).generateIndex(parent, context));
        }
        return indexItems;
    }

    public List<String> getDefinitionsStrings(){
        List<String> defs = new ArrayList<String>();
        for (Definition def : definitions) {
            defs.add(def.toString());
        }
        return defs;
    }

    @Override
    public AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent) {
        /*
        if (id != null && !"".equals(id)) {
            autocompleteItemIndex = new AutocompleteIndexItem();
            autocompleteItemIndex.setParent(parent);
            autocompleteItemIndex.setId(id);
            autocompleteItemIndex.addTags(getDefinitionsStrings());
        }
        
        return autocompleteItemIndex;
        */
        return null;
    }

    @Override
    public List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent) {
        List<AutocompleteIndexItem> indexItems =  new ArrayList<AutocompleteIndexItem>();
        for (Object o : definitions) {
            if (o instanceof Definition)
                indexItems.addAll(((Definition)o).generateAutocompleteIndex(parent));
        }
        return indexItems;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
                
        if (definitions != null)
            for (Definition definition : definitions)
                definition.setCollectionId(collectionId);
    }    

    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
                
        if (definitions != null)
            for (Definition definition : definitions)
                definition.setModuleId(moduleId);
    }    
    
}

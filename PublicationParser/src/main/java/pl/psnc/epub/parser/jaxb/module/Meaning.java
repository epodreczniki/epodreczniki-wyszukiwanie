package pl.psnc.epub.parser.jaxb.module;

import java.util.List;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.MeaningIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
//@XmlType(namespace="http://cnx.rice.edu/cnxml")
@XmlRootElement(name="meaning")
public class Meaning  extends GenericElement {
    /*
    protected String id;

    protected List<Object> content;

    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    */
    /*
    @XmlValue
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    */
    /*
    @XmlMixed
    @XmlAnyElement
    public List<Object> getMeaning() {
        return content;
    }

    public void setMeaning(List<Object> content) {
        this.content = content;
    }
    */
    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        //DefinitionIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new MeaningIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            itemIndex.setContent(str);
            //itemIndex.setFullContent(this.toString());
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

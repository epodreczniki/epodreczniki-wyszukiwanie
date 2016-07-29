package pl.psnc.epub.parser.jaxb.module;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement
@XmlType(namespace="http://cnx.rice.edu/cnxml")
public class Content extends ModuleEntity {

    private List<Object> elements;

    protected String text;

    @XmlAnyElement(lax = true)
    public List<Object> getElements() {
        return elements;
    }

    public void setElements(List<Object> elements) {
        this.elements = elements;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        return null;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        List<IndexItem> indexItems =  new ArrayList<IndexItem>();
        if (elements != null) {
            for (Object o : elements) {
                if (o instanceof ModuleElement)
                    indexItems.addAll(((ModuleElement)o).generateIndex(parent, context));
            }
        } else {
            // No child elements
        }
        return indexItems;
    }

    @Override
    public AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent) {
        return null;
    }

    @Override
    public List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent) {
        List<AutocompleteIndexItem> indexItems =  new ArrayList<AutocompleteIndexItem>();
        if (elements != null) {
            for (Object o : elements) {
                if (o instanceof ModuleElement)
                    indexItems.addAll(((ModuleElement)o).generateAutocompleteIndex(parent));
            }
        }else {
            // No child elements
        }
        return indexItems;
    }
    
    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (elements != null)
            for (Object o : elements)
                if (o instanceof BaseEntity)
                    ((BaseEntity)o).setCollectionId(collectionId);
    }    
    
    @Override
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
        if (elements != null)
            for (Object o : elements)
                if (o instanceof ModuleEntity)
                    ((ModuleEntity)o).setModuleId(moduleId);
    }
    
    /*
    @Override
    public String toString() {
        return getContentAsString();
    }

    protected String getContentAsString() {
        StringBuffer sb = new StringBuffer();
        if (elements != null) {
            for (Object elem : elements) {
                if (elem instanceof Node) {
                    sb.append(nodeToString((Node) elem));
                } else if (elem instanceof BaseEntity) {
                    sb.append(elem);
                }
            }
        }
        if (text != null) {
            sb.append(text);
        }
        return sb.toString();
    }

    protected String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            System.out.println("nodeToString Transformer Exception" + te);
        }
        return sw.toString();
    }
    */
    //@XmlValue
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

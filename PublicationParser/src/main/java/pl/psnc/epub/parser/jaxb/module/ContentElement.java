package pl.psnc.epub.parser.jaxb.module;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.ModuleEntity;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.ModuleIndexItem;
import pl.psnc.epub.solr.beans.context.OtherIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class ContentElement extends ModuleElement {
    
    private static Logger logger = Logger.getLogger(ContentElement.class.getName());

    protected List<Object> content;
    protected String text;

    @XmlAnyElement(lax = true)
    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }

    /*
    @Override
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
        ModuleIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new ModuleIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }
    
    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        List<IndexItem> indexItems = new ArrayList<IndexItem>();
        if (content != null) {
            for (Object o : content) {
                if (o instanceof Section)
                    indexItems.addAll(((Section)o).generateIndex(parent, context));
                else if (o instanceof Paragraph)
                    indexItems.addAll(((Paragraph)o).generateIndex(parent, context));
                else if (o instanceof Note)
                    indexItems.addAll(((Note)o).generateIndex(parent, context));
                else if (o instanceof Figure)
                    indexItems.addAll(((Figure)o).generateIndex(parent, context));
                else if (o instanceof Equation)
                    indexItems.addAll(((Equation)o).generateIndex(parent, context));
                else if (o instanceof Example)
                    indexItems.addAll(((Example)o).generateIndex(parent, context));
                else if (o instanceof Exercise)
                    indexItems.addAll(((Exercise)o).generateIndex(parent, context));
                else if (o instanceof Rule)
                    indexItems.addAll(((Rule)o).generateIndex(parent, context));
                else if (o instanceof Statement)
                    indexItems.addAll(((Example)o).generateIndex(parent, context));
                else if (o instanceof Definition)
                    indexItems.addAll(((Definition)o).generateIndex(parent, context));
                else if (o instanceof Code)
                    indexItems.addAll(((Code)o).generateIndex(parent, context));
                else if (o instanceof EPTooltip)
                    indexItems.addAll(((EPTooltip)o).generateIndex(parent, context));
                else if (o instanceof EPParameters)
                    indexItems.addAll(((EPParameters)o).generateIndex(parent, context));
                else if (o instanceof EPRevisal)
                    indexItems.addAll(((EPRevisal)o).generateIndex(parent, context));
                else if (o instanceof EPEffect)
                    indexItems.addAll(((EPEffect)o).generateIndex(parent, context));
                else if (o instanceof EPProcedureInstructions)
                    indexItems.addAll(((EPProcedureInstructions)o).generateIndex(parent, context));
                else if (o instanceof EPBiography)
                    indexItems.addAll(((EPBiography)o).generateIndex(parent, context));
                else if (o instanceof EPGenericElement)
                    indexItems.addAll(((EPGenericElement)o).generateIndex(parent, context));
                else if (o instanceof EPObservation)
                    indexItems.addAll(((EPObservation)o).generateIndex(parent, context));
                else if (o instanceof EPObjective)
                    indexItems.addAll(((EPObjective)o).generateIndex(parent, context));
                else if (o instanceof EPInstructions)
                    indexItems.addAll(((EPInstructions)o).generateIndex(parent, context));
                else if (o instanceof EPInstruments)
                    indexItems.addAll(((EPInstruments)o).generateIndex(parent, context));
                else if (o instanceof EPConclusions)
                    indexItems.addAll(((EPConclusions)o).generateIndex(parent, context));
                else if (o instanceof EPCommand)
                    indexItems.addAll(((EPCommand)o).generateIndex(parent, context));
                else if (o instanceof EPLead)
                    indexItems.addAll(((EPLead)o).generateIndex(parent, context));
                else if (o instanceof EPStudentWork)
                    indexItems.addAll(((EPStudentWork)o).generateIndex(parent, context));
                else if (o instanceof EPBookmark)
                    indexItems.addAll(((EPBookmark)o).generateIndex(parent, context));
                else if (o instanceof EPMathElement)
                    indexItems.addAll(((EPMathElement)o).generateIndex(parent, context));
                else if (o instanceof EPConcept)
                    indexItems.addAll(((EPConcept)o).generateIndex(parent, context));
                else if (o instanceof EPTechnicalRemarks)
                    indexItems.addAll(((EPTechnicalRemarks)o).generateIndex(parent, context));
                else if (o instanceof ElementNSImpl) {
                    String elementName = ((ElementNSImpl)o).getLocalName();
                    /*
                    if ("event".equals(elementName) ||
                            "biography".equals(elementName)||
                            "intro".equals(elementName)||
                            //"bookmark".equals(elementName)||
                            "lead".equals(elementName)) {
                        System.out.println(((ElementNSImpl)o).toString());
                    }
                    */
                    if ("title".equals(elementName) ||
                            "list".equals(elementName) ||
                            "table".equals(elementName) ||
                            "media".equals(elementName) ||
                            "math".equals(elementName) ||
                            "caption".equals(elementName) ||
                            "label".equals(elementName) ||
                            "subfigure".equals(elementName) ||
                            "quote".equals(elementName) ||
                            "emphasis".equals(elementName) ||
                            "event".equals(elementName)) {
                        IndexItem item = new OtherIndexItem(context);
                        item.setParent(parent);
                        item.setId(Integer.toString(o.hashCode()));
                        item.setLink(parent.getLink());
                        item.setCollectionId(collectionId);
                        item.setModuleId(moduleId);
                        item.setSubType(elementName);
                        item.setContent(((ElementNSImpl)o).toString());
                        if (item.getCollectionId() != null)
                            indexItems.add(item);
                        //System.err.println(String.format("WARINING!!! Generic index item created for element of section %s: %s", id, elementName));
                    } else if ("gallery".equals(elementName) ||
                            "zebra-point".equals(elementName)) {
                        logger.log(Level.INFO, String.format("Deliberately skiped known element of content element %s: %s", id, elementName));
                    } else {
                        //System.err.println(String.format("ERROR!!! Skiped unknown element of section %s: %s", id, elementName));
                        logger.log(Level.SEVERE, String.format("ERROR!!! Skiped unknown element of content element %s: %s", id, elementName));
                    }
                } else {
                    //System.err.println(String.format("ERROR!!! Skiped unknown element of section %s: %s", id, o.toString()));
                    logger.log(Level.SEVERE, String.format("ERROR!!! Skiped unknown element of content element %s: %s", id, o.toString()));
                }
            }
        }
        return indexItems;
    }

    @Override
    public void setCollectionId(String collectionId) {
        
        this.collectionId = collectionId;
                
        if (content != null)
            for (Object o : content)
                if (o instanceof BaseEntity)
                    ((BaseEntity)o).setCollectionId(collectionId);
    }    
    
    @Override
    public void setModuleId(String moduleId) {
        
        this.moduleId = moduleId;
                
        if (content != null)
            for (Object o : content)
                if (o instanceof ModuleEntity)
                    ((ModuleEntity)o).setModuleId(moduleId);
    }    

    protected String getContentAsString() {
        StringBuffer sb = new StringBuffer();
        if (content != null) {
            for (Object elem : content) {
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

    //@XmlValue
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

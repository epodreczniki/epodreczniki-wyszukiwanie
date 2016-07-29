package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.CollectionModuleIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.SubcollectionIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="subcollection")
public class Subcollection extends CollectionElement {
    
    protected static final String INDEX_SUFFIX = "subcollection";

    //Content content;
    private List<CollectionElement> content;

    /*
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
    */
    @XmlElementWrapper(name="content")
    @XmlElementRef()
    public List<CollectionElement> getContent() {
        return content;
    }

    public void setContent(List<CollectionElement> content) {
        this.content = content;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {

        List<IndexItem> indexList = new ArrayList<IndexItem>();
        for (CollectionElement colElem : content) {
            indexList.addAll(colElem.generateIndex(parent, context));
        }
        return indexList;

    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {

        //return null;

        SubcollectionIndexItem indexItem = null;
        //String id = generateSubcollectionId();
        //if ((id != null) && !"".equals(id))
        if (collectionId != null && !"".equals(collectionId)) {
            indexItem = new SubcollectionIndexItem(context);
            indexItem.setParent(parent);
            indexItem.setId(String.format("%s-%s", INDEX_SUFFIX, this.toString().hashCode()));
            indexItem.setLink(generateSubcollectionLink());
            indexItem.setCollectionId(collectionId);
            indexItem.setContent(title);
            indexItem.setFullContent(this.toString());
        }
        
        return indexItem;

    }

    @Override
    public void setCollectionId(String collectionId) {
        
        this.collectionId = collectionId;
                
        if (content != null)
            for (CollectionElement colElement : content)
                    colElement.setCollectionId(collectionId);
        
    }    
    /*
    private String generateSubcollectionId() {
        return generateSubcollectionId(null);
    }
    private String generateSubcollectionId(String id) {

        for (CollectionElement colElem : content) {
            if (colElem instanceof Subcollection) {
                String subId = generateSubcollectionId(id);
                id = String.format("%s/%s", INDEX_SUFFIX, subId);
            } else if (colElem instanceof Module) {
                String subId = ((Module)colElem).getDocument();
                id = String.format("%s/%s", INDEX_SUFFIX, subId);
            }
            if (id != null)
                return id;
        }
        return id;

    }
    */
    private String generateSubcollectionLink() {

        String link = null;
        for (CollectionElement colElem : content) {
            if (colElem instanceof Subcollection) {
                link = ((Subcollection)colElem).generateSubcollectionLink();
            } else if (colElem instanceof Module) {
                link = ((Module)colElem).getDocument();
            }
            if (link != null)
                return link;
        }
        return link;

    }
    
}

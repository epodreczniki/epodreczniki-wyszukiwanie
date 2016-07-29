package pl.psnc.epub.parser.jaxb.metadata;

import java.util.List;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author spychala
 */
class CollectionMetadata {
    
    private static Logger logger = Logger.getLogger(CollectionMetadata.class.getName());
    
    private String collectionId;
    
    private String collectionVersion;
    
    private List<String> variants;

    @XmlAttribute(name="id")
    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    @XmlAttribute(name="version")
    public String getCollectionVersion() {
        return collectionVersion;
    }

    public void setCollectionVersion(String collectionVersion) {
        this.collectionVersion = collectionVersion;
    }

    @XmlElement(name = "variants")
    @XmlJavaTypeAdapter(VariantAdapter.class)
    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }
    
}

package pl.psnc.epub.parser.jaxb.metadata;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author spychala
 */
@XmlRootElement
public class Metadata {
    
    private static Logger logger = Logger.getLogger(Metadata.class.getName());
    
    private CollectionMetadata metadata;

    @XmlElement(name = "collection-metadata")
    public CollectionMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(CollectionMetadata metadata) {
        this.metadata = metadata;
    }
    
}

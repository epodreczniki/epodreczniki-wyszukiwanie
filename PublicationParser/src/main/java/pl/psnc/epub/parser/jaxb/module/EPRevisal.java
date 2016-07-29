package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="revisal", namespace="http://epodreczniki.pl/")
public class EPRevisal  extends ContentElement {
    
    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        return null;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (content != null) {
            for (Object o : content) {
                if (o instanceof BaseEntity)
                    ((BaseEntity)o).setCollectionId(collectionId);
            }
        }
    }    

}

package pl.psnc.epub.parser.jaxb.module;

import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.solr.beans.context.EPObjectiveIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="objective", namespace="http://epodreczniki.pl/")
public class EPObjective  extends EPGenericElement {
    
    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new EPObjectiveIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            //String str = this.toString();
            //str = pattern.matcher(str).replaceAll("");
            itemIndex.setContent(str);
            itemIndex.setFullContent(str);
        }
        return itemIndex;
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        for (Object o : content) {
            if (o instanceof BaseEntity)
                ((BaseEntity)o).setCollectionId(collectionId);
        }
    }    

}

package pl.psnc.epub.parser.jaxb.module;

import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPTechnicalRemarksIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="technical-remarks", namespace="http://epodreczniki.pl/")

//@XmlType(name="para", namespace="http://cnx.rice.edu/cnxml")
//@XmlAccessorType(XmlAccessType.FIELD)
public class EPTechnicalRemarks extends GenericElement {

    /*
    @Override
    public String getId() {
        return String.format("para:%s", id);
    }
    */

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new EPTechnicalRemarksIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            //str = pattern.matcher(str).replaceAll("");
            itemIndex.setContent(str);
            itemIndex.setFullContent(str);
        }
        return itemIndex;
    }

    /*
    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent) {
        return null;
    }
    */
}

package pl.psnc.epub.parser.jaxb.module;

import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPExperimentIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="experiment", namespace="http://epodreczniki.pl/")
public class EPExperiment extends EPGenericElement {
    
    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new EPExperimentIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            //String str = this.toString();
            //str = pattern.matcher(str).replaceAll("");
            //itemIndex.setContent(str);
            itemIndex.setFullContent(str);
        }
        return itemIndex;
    }

}

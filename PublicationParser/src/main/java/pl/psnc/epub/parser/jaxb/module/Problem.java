package pl.psnc.epub.parser.jaxb.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlType;
import pl.psnc.epub.solr.beans.context.ProblemIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlType(name="problem")
public class Problem extends ContentElement {

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new ProblemIndexItem(context);
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
    
}

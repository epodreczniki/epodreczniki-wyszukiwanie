package pl.psnc.epub.parser.jaxb.module;

import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.NoteIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="note")
//@XmlType(name="note", namespace="http://cnx.rice.edu/cnxml")
public class Note extends GenericElement {

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new NoteIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            itemIndex.setContent(str);
            //itemIndex.setContent(this.toString());
            //itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }
    
}

package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPBiographyIndexItem;
import pl.psnc.epub.solr.beans.context.EPMathElementIndexItem;
import pl.psnc.epub.solr.beans.context.EPProcedureInstructionsIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="mathElement", namespace="http://epodreczniki.pl/")
public class EPMathElement extends ContentElement {
    
    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        EPMathElementIndexItem itemIndex = null;
        if (id != null && !"".equals(id)) {
            itemIndex = new EPMathElementIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setContent(this.toString());
            //itemIndex.setFullContent(this.toString());
        }
        return itemIndex;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {
        return null;
    }
}

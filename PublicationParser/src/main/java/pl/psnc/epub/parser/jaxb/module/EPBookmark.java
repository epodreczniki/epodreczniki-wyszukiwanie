package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.solr.beans.context.BookmarkIndexItem;
import pl.psnc.epub.solr.beans.context.EPLeadIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="bookmark", namespace="http://epodreczniki.pl/")
public class EPBookmark  extends EPGenericElement {
    
    protected String name;
    
    @XmlAttribute(name="name", namespace="http://epodreczniki.pl/")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        if (id != null && !"".equals(id)) {
            itemIndex = new BookmarkIndexItem(context);
            itemIndex.setParent(parent);
            itemIndex.setId(id);
            itemIndex.setCollectionId(collectionId);
            itemIndex.setModuleId(moduleId);
            //Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            //String str = pattern.matcher(this.toString()).replaceAll("");
            //String str = this.toString();
            //str = pattern.matcher(str).replaceAll("");
            itemIndex.setContent(name);
            itemIndex.setFullContent(name);
        }
        return itemIndex;
    }

}

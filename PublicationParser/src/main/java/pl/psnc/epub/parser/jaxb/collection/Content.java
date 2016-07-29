package pl.psnc.epub.parser.jaxb.collection;

import java.util.Date;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(
    name="content",
    factoryClass=CollectionFactory.class,
    factoryMethod="createContent")
public class Content {
    
    Date createTime;
    
    public Content(Date date) {
        createTime = date;
    }
    
}

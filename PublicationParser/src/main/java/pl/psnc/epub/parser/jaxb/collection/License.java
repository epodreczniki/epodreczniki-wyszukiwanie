package pl.psnc.epub.parser.jaxb.collection;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(
    factoryClass=CollectionFactory.class,
    factoryMethod="createLicense")
public class License {
    
    private String url;
    
    private Date createTime;
    
    public License(Date date) {
        createTime = date;
    }

    @XmlAttribute
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}

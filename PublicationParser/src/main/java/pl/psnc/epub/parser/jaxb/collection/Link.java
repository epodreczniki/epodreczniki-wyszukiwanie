package pl.psnc.epub.parser.jaxb.collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author spychala
 */
@XmlType(name="link", namespace="http://cnx.rice.edu/cnxml")
public class Link {
    
    private String url;
    private String text;

    @XmlAttribute(name="url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlValue
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}

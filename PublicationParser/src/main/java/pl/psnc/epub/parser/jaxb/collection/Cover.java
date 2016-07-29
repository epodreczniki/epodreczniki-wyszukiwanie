package pl.psnc.epub.parser.jaxb.collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="cover", namespace="http://epodreczniki.pl/")
public class Cover {
    
    private String coverType;
    private String cover;

    @XmlAttribute(name = "cover-type", namespace="http://epodreczniki.pl/")
    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    @XmlValue
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    
}

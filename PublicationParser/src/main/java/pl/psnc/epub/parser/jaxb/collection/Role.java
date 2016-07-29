package pl.psnc.epub.parser.jaxb.collection;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author spychala
 */
@XmlType
public class Role {

    private String type;
    private List<String> value;

    private final Date createTime;

    public Role() {
        this.createTime = new Date();
        this.type = null;
        this.value = null;
    }

    public Role(Date date) {
        this.createTime = date;
        this.type = null;
        this.value = null;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlValue
    @XmlList
    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
    
}

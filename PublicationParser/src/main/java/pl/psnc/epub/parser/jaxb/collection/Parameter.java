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
    factoryMethod="createParameter")
public class Parameter {
    
    private String name;
    private String value;

    private final Date createTime;

    public Parameter(Date date) {
        this.createTime = date;
        this.name = null;
        this.value = null;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

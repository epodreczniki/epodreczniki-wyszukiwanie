package pl.psnc.epub.parser.jaxb.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType
public class Variant {
    
    private String name;

    public Variant(String variantName) {
        name = variantName;
    }
    
    @XmlAttribute(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

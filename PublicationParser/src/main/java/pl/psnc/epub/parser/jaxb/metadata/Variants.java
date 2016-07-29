package pl.psnc.epub.parser.jaxb.metadata;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(name="variants")
public class Variants {
    
    private List<Variant> variants = new ArrayList<Variant>();

    @XmlElement(name="variant")
    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
    
    public void addVariant(Variant variant) {
        this.variants.add(variant);
    }

    public void addVariants(List<Variant> vars) {
        this.variants.addAll(vars);
    }
}

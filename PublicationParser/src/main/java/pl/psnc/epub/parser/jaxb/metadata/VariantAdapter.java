package pl.psnc.epub.parser.jaxb.metadata;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author spychala
 */
public class VariantAdapter extends XmlAdapter<Variants, List<String>> {

    @Override
    public List<String> unmarshal(Variants variants) throws Exception {
        List<String> list = new ArrayList<String>();
        if (variants != null) {
            for (Variant variant : variants.getVariants())
                list.add(variant.getName());
        }
        return list;
    }

    @Override
    public Variants marshal(List<String> strings) throws Exception {
        Variants variants = new Variants();
        if (strings != null) {
            for (String variantName : strings)
                variants.addVariant(new Variant(variantName));
        }
        return variants;
    }
    
}

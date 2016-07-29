package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author spychala
 */
public class KeywordAdapter extends XmlAdapter<Keywords, List<String>> {

    @Override
    public List<String> unmarshal(Keywords keywords) throws Exception {
        List<String> list = new ArrayList<String>();
        if (keywords != null) {
            list.addAll(keywords.getKeywords());
        }
        return list;
    }

    @Override
    public Keywords marshal(List<String> strings) throws Exception {
        Keywords keywords = new Keywords();
        if (strings != null) {
            keywords.addKeywords(strings);
        }
        return keywords;
    }
    
}

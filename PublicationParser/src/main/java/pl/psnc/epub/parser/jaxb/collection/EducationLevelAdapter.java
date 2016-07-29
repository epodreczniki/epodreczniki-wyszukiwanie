package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author spychala
 */
public class EducationLevelAdapter extends XmlAdapter<EducationLevels, List<String>>{
    
    @Override
    public List<String> unmarshal(EducationLevels educationLevels) throws Exception {
        List<String> list = new ArrayList<String>();
        if (educationLevels != null) {
            list.addAll(educationLevels.getEducationLevels());
        }
        return list;
    }

    @Override
    public EducationLevels marshal(List<String> strings) throws Exception {
        EducationLevels educationLevels = CollectionFactory.createEducationLevels();
        if (strings != null) {
            educationLevels.addEducationLevels(strings);
        }
        return educationLevels;
    }
    
}

package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author spychala
 */
public class SubjectAdapter extends XmlAdapter<Subjects, List<String>>{
    
    @Override
    public List<String> unmarshal(Subjects subjects) throws Exception {
        List<String> list = new ArrayList<String>();
        if (subjects != null) {
            list.addAll(subjects.getSubjects());
        }
        return list;
    }

    @Override
    public Subjects marshal(List<String> strings) throws Exception {
        Subjects subjects = CollectionFactory.createSubjects();
        if (strings != null) {
            subjects.addSubjects(strings);
        }
        return subjects;
    }
    
}

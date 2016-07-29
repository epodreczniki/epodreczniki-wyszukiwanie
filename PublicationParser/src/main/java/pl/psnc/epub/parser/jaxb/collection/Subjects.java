package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(name="subjectlist", namespace="http://cnx.rice.edu/mdml")
public class Subjects {
    
    private List<String> subjects = new ArrayList<String>();

    @XmlElement(name="subject", namespace="http://cnx.rice.edu/mdml")
    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> keywords) {
        this.subjects = keywords;
    }
    
    public void addSubject(String keyword) {
        this.subjects.add(keyword);
    }

    public void addSubjects(List<String> words) {
        this.subjects.addAll(words);
    }
}

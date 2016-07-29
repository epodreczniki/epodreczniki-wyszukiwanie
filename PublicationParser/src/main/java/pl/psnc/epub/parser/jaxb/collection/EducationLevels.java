package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(name="education-levellist", namespace="http://cnx.rice.edu/mdml")
public class EducationLevels {
    
    private List<String> educationLevels = new ArrayList<String>();

    @XmlElement(name="education-level", namespace="http://cnx.rice.edu/mdml")
    public List<String> getEducationLevels() {
        return educationLevels;
    }

    public void setEducationLevels(List<String> keywords) {
        this.educationLevels = keywords;
    }
    
    public void addEducationLevel(String keyword) {
        this.educationLevels.add(keyword);
    }

    public void addEducationLevels(List<String> words) {
        this.educationLevels.addAll(words);
    }
}

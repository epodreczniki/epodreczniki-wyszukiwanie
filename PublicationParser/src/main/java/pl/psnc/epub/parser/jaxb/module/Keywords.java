package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(name="keywordlist", namespace="http://cnx.rice.edu/mdml")
public class Keywords {
    
    private List<String> keywords = new ArrayList<String>();

    @XmlElement(name="keyword", namespace="http://cnx.rice.edu/mdml")
    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    
    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public void addKeywords(List<String> words) {
        this.keywords.addAll(words);
    }
}

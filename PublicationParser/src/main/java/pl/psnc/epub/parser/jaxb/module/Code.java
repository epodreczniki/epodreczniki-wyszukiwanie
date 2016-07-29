package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="code")
@XmlType(namespace="http://cnx.rice.edu/cnxml")
public class Code extends GenericElement {

    private String display;

    private String lang;

        
    @XmlAttribute(name="display")
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @XmlAttribute(name="lang")
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
        
        

}

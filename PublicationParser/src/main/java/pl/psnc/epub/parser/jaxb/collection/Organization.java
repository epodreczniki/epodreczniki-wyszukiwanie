package pl.psnc.epub.parser.jaxb.collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="organization", namespace="http://cnx.rice.edu/mdml")
public class Organization extends Actor {
    
    protected String shortName;

    @XmlElement(name="shortname", namespace="http://cnx.rice.edu/mdml")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    @Override
    public String toString() {
        if ((fullName != null) && (shortName != null)) {
            if (fullName.equals(shortName)) {
                if (email != null && !"".equals(email))
                    return String.format("%s [%s]", fullName, email);
                else
                    return String.format("%s", fullName);
            } else {
                if (email != null && !"".equals(email))
                    return String.format("%s [%s, %s]", fullName, shortName, email);
                else
                    return String.format("%s [%s]", fullName, shortName);
            }
        } else {
            if (fullName == null) {
                if (email != null && !"".equals(email))
                    return String.format("%s [%s]", shortName, email);
                else
                    return String.format("%s", shortName);
            }
            if (shortName == null) {
                if (email != null && !"".equals(email))
                    return String.format("%s [%s]", fullName, email);
                else
                    return String.format("%s", fullName);
            }
        }
        
        return String.format("%s [%s, %s]", fullName, shortName, email);
        
    }

}

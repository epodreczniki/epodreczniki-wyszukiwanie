package pl.psnc.epub.parser.jaxb.collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="person", namespace="http://cnx.rice.edu/mdml")
public class Person extends Actor {
    
    protected String firstName;
    protected String surname;
    protected String honorific;

    @XmlElement(name="firstname", namespace="http://cnx.rice.edu/mdml")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name="surname", namespace="http://cnx.rice.edu/mdml")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement(name="honorific", namespace="http://cnx.rice.edu/mdml")
    public String getHonorific() {
        return honorific;
    }

    public void setHonorific(String honorific) {
        this.honorific = honorific;
    }

    @Override
    public String toString() {
        String computedFullNameWithHonorific = null;
        if ((honorific != null) && (!"".equals(honorific)))
            computedFullNameWithHonorific = String.format("%s %s %s", honorific, firstName, surname);
        String computedFullNameWoHonorific = String.format("%s %s", firstName, surname);
        
        if ((computedFullNameWithHonorific == null) || ("".equals(computedFullNameWithHonorific))) {
            if (computedFullNameWoHonorific.equals(fullName))
                if (email != null && !"".equals(email))
                    return String.format("%s [%s]", fullName, email);
                else
                    return String.format("%s", fullName);
            else
                if (email != null && !"".equals(email))
                    return String.format("%s %s [%s, %s]", firstName, surname, fullName, email);
                else
                    return String.format("%s %s [%s]", firstName, surname, fullName);
        } else {
            if (computedFullNameWithHonorific.equals(fullName))
                if (email != null && !"".equals(email))
                    return String.format("%s [%s]", fullName, email);
                else
                    return String.format("%s", fullName);
            else
                if (email != null && !"".equals(email))
                    return String.format("%s %s %s [%s, %s]", honorific, firstName, surname, fullName, email);
                else
                    return String.format("%s %s %s [%s]", honorific, firstName, surname, fullName);
        }
    }

}

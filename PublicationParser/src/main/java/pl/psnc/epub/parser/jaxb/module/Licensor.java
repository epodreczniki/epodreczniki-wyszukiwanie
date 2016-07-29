package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.parser.jaxb.collection.Person;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="licencor", namespace="http://cnx.rice.edu/mdml")
public class Licensor extends Person {
    
}

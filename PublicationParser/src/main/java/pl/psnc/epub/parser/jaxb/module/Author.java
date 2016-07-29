package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.parser.jaxb.collection.Person;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="author", namespace="http://cnx.rice.edu/mdml")
public class Author extends Person {
    
}

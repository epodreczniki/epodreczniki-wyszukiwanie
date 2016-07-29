package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType(
    factoryClass=CollectionFactory.class,
    factoryMethod="createParameters")
public class Parameters {
    
    List<Parameter> parameters = new ArrayList<Parameter>();
    
    Date createTime;
    
    public Parameters(Date date) {
        createTime = date;
    }

    @XmlElement(name="param")
    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    
    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }

}

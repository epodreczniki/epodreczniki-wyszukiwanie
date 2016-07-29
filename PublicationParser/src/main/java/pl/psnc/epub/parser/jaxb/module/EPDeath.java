package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.psnc.epub.solr.beans.context.EPProcedureInstructionsIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="death", namespace="http://epodreczniki.pl/")
public class EPDeath extends ModuleElement {
    
    protected EPDate date;
    
    protected String location;

    @XmlElement(name="date", namespace="http://epodreczniki.pl/")
    public EPDate getDate() {
        return date;
    }

    public void setDate(EPDate date) {
        this.date = date;
    }

    @XmlAttribute(name="location", namespace="http://epodreczniki.pl/")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("zm. ");
        if ((date != null) && !"".equals(date)) {
            sb.append(date);
            if ((location != null) && !"".equals(location)) {
                sb.append(", ");
                sb.append(location);
            }
        } else {
            if (location != null) {
                sb.append(location);
            }
        }
        return sb.toString();
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        if (date != null)
            date.setCollectionId(collectionId);

    }
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        if (date != null)
            date.setModuleId(collectionId);
        
    }

}

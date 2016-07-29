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
@XmlRootElement(name="date", namespace="http://epodreczniki.pl/")
public class EPDate extends ModuleElement {
    
    protected EPDateElement dateStart;
    protected EPDateElement dateEnd;
    
    @XmlElement(name="date-start", namespace="http://epodreczniki.pl/")
    public EPDateElement getDateStart() {
        return dateStart;
    }

    public void setDateStart(EPDateElement date) {
        this.dateStart = date;
    }

    @XmlElement(name="date-end", namespace="http://epodreczniki.pl/")
    public EPDateElement getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(EPDateElement date) {
        this.dateEnd = date;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if ((dateStart != null) && !"".equals(dateStart)) {
            sb.append(dateStart);
            if ((dateEnd != null) && !"".equals(dateEnd)) {
                sb.append(" - ");
                sb.append(dateEnd);
            }
        } else {
            if ((dateEnd != null) && !"".equals(dateEnd)) {
                sb.append(dateEnd);
            }
        }
        return sb.toString();
    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        if (dateStart != null)
            dateStart.setCollectionId(collectionId);
        if (dateEnd != null)
            dateEnd.setCollectionId(collectionId);

    }
    
    @Override
    public void setModuleId(String moduleId) {

        this.moduleId = moduleId;
        if (dateStart != null)
            dateStart.setModuleId(collectionId);
        if (dateEnd != null)
            dateEnd.setModuleId(collectionId);

    }

}

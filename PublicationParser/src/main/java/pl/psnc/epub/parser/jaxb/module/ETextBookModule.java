package pl.psnc.epub.parser.jaxb.module;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="e-textbook-module", namespace="http://epodreczniki.pl/")
public class ETextBookModule {

    private String contentStatus;
    private String recipient;
    private String version;

    private List<CoreCurriculumEntry> coreCurriculumEntries;
    
    @XmlElementWrapper(name="core-curriculum-entries", namespace="http://epodreczniki.pl/")
    @XmlElementRef()
    public List<CoreCurriculumEntry> getCoreCurriculumEntries() {
        return coreCurriculumEntries;
    }

    public void setCoreCurriculumEntries(List<CoreCurriculumEntry> coreCurriculumEntries) {
        this.coreCurriculumEntries = coreCurriculumEntries;
    }
    
    @XmlAttribute(name = "content-status", namespace="http://epodreczniki.pl/")
    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    @XmlAttribute(name = "recipient", namespace="http://epodreczniki.pl/")
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @XmlAttribute(name = "version", namespace="http://epodreczniki.pl/")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
}

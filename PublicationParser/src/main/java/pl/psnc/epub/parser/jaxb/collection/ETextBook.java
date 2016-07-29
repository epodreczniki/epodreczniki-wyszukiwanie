package pl.psnc.epub.parser.jaxb.collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author spychala
 */
@XmlRootElement(name="e-textbook", namespace="http://epodreczniki.pl/")
public class ETextBook {
    
    private String contentStatus;
    private String recipient;
    private String version;
    private String clazz;
    private Cover cover;
    private String signature;

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

    @XmlElement(name = "class", namespace="http://epodreczniki.pl/")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @XmlElement(name = "cover", namespace="http://epodreczniki.pl/")
    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @XmlElement(name = "signature", namespace="http://epodreczniki.pl/")
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    
}

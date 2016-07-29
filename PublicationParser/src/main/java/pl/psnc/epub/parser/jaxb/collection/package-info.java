@XmlSchema(
    elementFormDefault = XmlNsForm.QUALIFIED,
    //attributeFormDefault = XmlNsForm.UNSET,
    namespace="http://cnx.rice.edu/collxml",
    xmlns={@XmlNs(prefix="", namespaceURI="http://cnx.rice.edu/collxml"),
           @XmlNs(prefix="cnx", namespaceURI="http://cnx.rice.edu/cnxml"),
           @XmlNs(prefix="cnxorg", namespaceURI="http://cnx.rice.edu/system-info"),
           @XmlNs(prefix="md", namespaceURI="http://cnx.rice.edu/mdml"),
           @XmlNs(prefix="col", namespaceURI="http://cnx.rice.edu/collxml"),
           @XmlNs(prefix="cnxml", namespaceURI="http://cnx.rice.edu/cnxml"),
           @XmlNs(prefix="m", namespaceURI="http://www.w3.org/1998/Math/MathML"),
           @XmlNs(prefix="q", namespaceURI="http://cnx.rice.edu/qml/1.0"),
           @XmlNs(prefix="xhtml", namespaceURI="http://www.w3.org/1999/xhtml"),
           @XmlNs(prefix="bib", namespaceURI="http://bibtexml.sf.net/"),
           @XmlNs(prefix="cc", namespaceURI="http://web.resource.org/cc/"),
           @XmlNs(prefix="rdf", namespaceURI="http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
           @XmlNs(prefix="ep", namespaceURI="http://epodreczniki.pl/")
    }
)
package pl.psnc.epub.parser.jaxb.collection;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

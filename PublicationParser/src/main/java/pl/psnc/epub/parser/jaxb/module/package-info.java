@XmlSchema(
    elementFormDefault = XmlNsForm.QUALIFIED,
    attributeFormDefault = XmlNsForm.UNSET,
    namespace="http://cnx.rice.edu/cnxml",
    xmlns={
           @XmlNs(prefix="", namespaceURI="http://cnx.rice.edu/cnxml"),
           @XmlNs(prefix="md04", namespaceURI="http://cnx.rice.edu/mdml/0.4"),
           @XmlNs(prefix="md", namespaceURI="http://cnx.rice.edu/mdml"),
           @XmlNs(prefix="m", namespaceURI="http://www.w3.org/1998/Math/MathML"),
           @XmlNs(prefix="bib", namespaceURI="http://bibtexml.sf.net/"),
           @XmlNs(prefix="q", namespaceURI="http://cnx.rice.edu/qml/1.0"),
           @XmlNs(prefix="ep", namespaceURI="http://epodreczniki.pl/")
           
    }
)
package pl.psnc.epub.parser.jaxb.module;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

package pl.psnc.spy.solr.indexer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 *
 * @author spychala
 */
//public class USMCommonWebException extends WebApplicationException {
public abstract class IndexerCommonWebException extends WebApplicationException {

    private static final String message = "Publication Indexer Webapplication exception.";
    private static final String messageElement = "message";
    private static final Status status = Response.Status.NOT_ACCEPTABLE;

    public IndexerCommonWebException(String message, Status status) {

        //GenericEntity ge = new GenericEntity<String>(message);
        super(Response
                .status(status)
                .entity(new JAXBElement<String>(new QName(messageElement), String.class, message))
                .build());
    }

    public IndexerCommonWebException(String message) {

        super(Response
                .status(status)
                .entity(new JAXBElement<String>(new QName(messageElement), String.class, message))
                .build());

    }

    public IndexerCommonWebException(Status status) {

        super(Response
                .status(status)
                .entity(new JAXBElement<String>(new QName(messageElement), String.class, message))
                .build());

    }

    public IndexerCommonWebException() {

        super(Response
                .status(status)
                .entity(new JAXBElement<String>(new QName(messageElement), String.class, message))
                .build());

    }
}

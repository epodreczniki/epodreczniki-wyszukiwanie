package pl.psnc.spy.solr.indexer;

import javax.ws.rs.core.Response;

/**
 *
 * @author spychala
 */
public class IndexerValidationException extends IndexerCommonWebException {

    //private static final String message = "Cannot validate.";
    private static final Response.Status status = Response.Status.NOT_ACCEPTABLE;

    public IndexerValidationException(String message) {
        super(message,status);
    }

}

package pl.psnc.spy.solr.indexer;

import javax.ws.rs.core.Response;

/**
 *
 * @author spychala
 */
public class IndexerNotImplementedException extends IndexerCommonWebException {

    private static final Response.Status status = Response.Status.NOT_ACCEPTABLE;

    public IndexerNotImplementedException() {
        super("Not yet implemeneted",status);
    }

}

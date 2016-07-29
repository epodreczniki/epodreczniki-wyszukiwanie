package pl.psnc.spy.solr.indexer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import pl.psnc.epub.parser.EPubParser;
import pl.psnc.epub.solr.common.SOLRResponse;

/**
 *
 * @author spychala
 */
@Path("publishing")
public class PublishingResource {
    
    @Context
    private ServletContext srvltContext;
    
    private static final String SORL_URL = "SOLR.url";
    private static final String SORL_MAIN = "SOLR.main";
    private static final String SORL_AUTOCOMPLETE = "SOLR.ac";
    private static final String SORL_DYNAMIC_SEARCH = "SOLR.ds";

    private static final String SORL_URL_DEFAULT = "http://localhost:8080/solr/";
    private static final String SORL_MAIN_DEFAULT = "epub";
    private static final String SORL_AUTOCOMPLETE_DEFAULT = "epub_ac";
    private static final String SORL_DYNAMIC_SEARCH_DEFAULT = "epub_ds";

    private final static Logger logger = Logger.getLogger(ClearResource.class.getName());

    private String solrMainURL = null;
    private String solrAutoURL = null;
    private String solrDynaURL = null;

    /**
     * Creates a new instance of PublishingResource
     */
    public PublishingResource() {
    }

    private void getServletContextParams() {

        //String urlFormatter = "%1$s%2$s";

        String solrURL = srvltContext.getInitParameter(SORL_URL);
        if ((solrURL == null) || ("".equals(solrURL)))
            solrURL = SORL_URL_DEFAULT;

        String solrMain = srvltContext.getInitParameter(SORL_MAIN);
        if ((solrMain == null) || ("".equals(solrMain)))
            solrMain = SORL_MAIN_DEFAULT;
        String solrAuto = srvltContext.getInitParameter(SORL_AUTOCOMPLETE);
        if ((solrAuto == null) || ("".equals(solrAuto)))
            solrAuto = SORL_AUTOCOMPLETE_DEFAULT;
        String solrDyna = srvltContext.getInitParameter(SORL_DYNAMIC_SEARCH);
        if ((solrDyna == null) || ("".equals(solrDyna)))
            solrDyna = SORL_DYNAMIC_SEARCH_DEFAULT;
        
        solrMainURL = solrURL.concat(solrMain);
        solrAutoURL = solrURL.concat(solrAuto);
        solrDynaURL = solrURL.concat(solrDyna);

    }

    /**
     * POST method for updating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Path("publish")
    @Produces("application/json")
    public List<SOLRResponse>  postPublishJson(@QueryParam("colURL") List<String> collectionsURLs) {

        getServletContextParams();
        
        return PublishCollections(collectionsURLs);
        
    }

    @POST
    @Path("unpublish")
    @Produces("application/json")
    public List<SOLRResponse>  postUnpublishJson(@QueryParam("colURL") List<String> collectionsURLs) {

        getServletContextParams();
        
        return UnpublishCollections(collectionsURLs);
        
    }
    
    private List<SOLRResponse> PublishCollections(List<String> collectionsIDs) {
        
            getServletContextParams();

            EPubParser parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
            if (parser == null) {
                logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
                throw new IndexerValidationException("Cannot create publication parser object!");
            }
                        
            List<SOLRResponse> responses;
            try {
                responses = parser.publish(collectionsIDs);
            } catch (IOException ioe) {
                logger.log(Level.SEVERE, null, ioe);
                throw new IndexerValidationException(ioe.getLocalizedMessage());
            } catch (JAXBException jaxbe) {
                logger.log(Level.SEVERE, null, jaxbe);
                throw new IndexerValidationException(jaxbe.toString());
            } catch (SolrServerException solre) {
                logger.log(Level.SEVERE, null, solre);
                throw new IndexerValidationException(solre.getLocalizedMessage());
            }
            
            return responses;
        
    }
    
    private List<SOLRResponse> UnpublishCollections(List<String> collectionsIDs) {
        
            getServletContextParams();

            EPubParser parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
            if (parser == null) {
                logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
                throw new IndexerValidationException("Cannot create publication parser object!");
            }
            
            String indexMsgPlaceholder = "Publishing collection: %s";
            List<URL> collections = new ArrayList<URL>();
            for (String url : collectionsIDs) {
                logger.log(Level.FINE, String.format(indexMsgPlaceholder, url));
                try {
                    URL collectionURL = new URL(url);
                    collections.add(collectionURL);
                } catch (MalformedURLException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
            
            List<SOLRResponse> responses;
            try {
                responses = parser.unpublish(collectionsIDs);
            } catch (IOException ioe) {
                logger.log(Level.SEVERE, null, ioe);
                throw new IndexerValidationException(ioe.getLocalizedMessage());
            } catch (JAXBException jaxbe) {
                logger.log(Level.SEVERE, null, jaxbe);
                throw new IndexerValidationException(jaxbe.toString());
            } catch (SolrServerException solre) {
                logger.log(Level.SEVERE, null, solre);
                throw new IndexerValidationException(solre.getLocalizedMessage());
            }
            
            return responses;
        
    }

}

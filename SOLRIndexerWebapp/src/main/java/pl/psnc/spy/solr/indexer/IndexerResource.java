package pl.psnc.spy.solr.indexer;

import com.sun.jersey.api.JResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import pl.psnc.epub.parser.EPubParser;
import pl.psnc.epub.solr.common.SOLRResponse;

/**
 * REST Web Service
 *
 * @author spychala
 */
@Path("index")
public class IndexerResource {

    //@Context
    //private UriInfo context;

    @Context
    private ServletContext srvltContext;
    
    private static final String SORL_URL = "SOLR.url";
    private static final String SORL_MAIN = "SOLR.main";
    private static final String SORL_AUTOCOMPLETE = "SOLR.ac";
    private static final String SORL_DYNAMIC_SEARCH = "SOLR.ds";
    private static final String URL_PLACEHOLDER = "Module.url";

    private static final String SORL_URL_DEFAULT = "http://localhost:8080/solr/";
    private static final String SORL_MAIN_DEFAULT = "epub";
    private static final String SORL_AUTOCOMPLETE_DEFAULT = "epub_ac";
    private static final String SORL_DYNAMIC_SEARCH_DEFAULT = "epub_ds";
    private static final String URL_PLACEHOLDER_DEFAULT = "http://localhost:8080/solr/";
    
    private final static Logger logger = Logger.getLogger(IndexerResource.class.getName());

    private String solrMainURL = null;
    private String solrAutoURL = null;
    private String solrDynaURL = null;
    
    private String baseURLPlaceholder = null;
    
    /**
     * Creates a new instance of GenericResource
     */
    public IndexerResource() {
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
        
        baseURLPlaceholder = srvltContext.getInitParameter(URL_PLACEHOLDER);
        //if ((baseURLPlaceholder == null) || ("".equals(baseURLPlaceholder)))
        //    baseURLPlaceholder = URL_PLACEHOLDER_DEFAULT;

        solrMainURL = solrURL.concat(solrMain);
        solrAutoURL = solrURL.concat(solrAuto);
        solrDynaURL = solrURL.concat(solrDyna);

    }

    /**
     * Retrieves representation of an instance of pl.psnc.spy.solr.indexer.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@QueryParam("colURL") List<String> collectionsURLs) {
        
        return "Index: Shuld be called with PUT instead of GET";
        
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    //@Consumes("application/json")
    @Produces("application/json")
    public List<SOLRResponse>  putJson(@QueryParam("colURL") List<String> collectionsURLs) {

        getServletContextParams();
        
        return PerformIndex(collectionsURLs);
        
    }

    private List<SOLRResponse> PerformIndex(List<String> collectionsURLs) throws IndexerValidationException {
        //public JSONArray getXml(@QueryParam("colURL") List<String> collectionsURLs) {

            EPubParser parser = null;
            if (baseURLPlaceholder == null || "".equals(URL_PLACEHOLDER_DEFAULT)) {
                parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
            } else {
                parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL, baseURLPlaceholder);
            }
            if (parser == null) {
                logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
                throw new IndexerValidationException("Cannot create publication parser object!");
            }
            
            String indexMsgPlaceholder = "Indexing collection: %s";
            List<URL> collections = new ArrayList<URL>();
            for (String url : collectionsURLs) {
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
                responses = parser.index(collections);
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

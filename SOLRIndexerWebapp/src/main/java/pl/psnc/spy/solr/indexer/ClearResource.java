package pl.psnc.spy.solr.indexer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import org.apache.solr.client.solrj.response.UpdateResponse;
import pl.psnc.epub.parser.EPubParser;

/**
 * REST Web Service
 *
 * @author spychala
 */
@Path("clear")
public class ClearResource {

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
     * Creates a new instance of ClearResource
     */
    public ClearResource() {
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
    
    @DELETE
    @Produces("application/json")
    public List<UpdateResponse> deleteJson() {
        
        return PerformClearAll();
        
    }

    @GET
    @Produces("application/json")
    public String getJson() {
        
        return "ClearAll: Shuld be called with DELETE instead of GET";
        
    }

    @DELETE
    @Path("/main/")
    @Produces("application/json")
    public List<UpdateResponse> deleteMainJson() {
        
        return PerformClearMain();
        
    }

    @GET
    @Path("/main/")
    @Produces("application/json")
    public String getMainJson() {
        
        return "ClearMain: Shuld be called with DELETE instead of GET";
        
    }

    @DELETE
    @Path("/autocomplete/")
    @Produces("application/json")
    public List<UpdateResponse> deleteAutocompleteJson() {
        
        return PerformClearAutocomplete();
        
    }

    @GET
    @Path("/autocomplete/")
    @Produces("application/json")
    public String getAutoCompleteJson() {
        
        return "ClearAutoComplete: Shuld be called with DELETE instead of GET";
        
    }

    @DELETE
    @Path("/dynamicsearch/")
    @Produces("application/json")
    public List<UpdateResponse> deleteDynamicSearchJson() {
        
        return PerformClearDynamicSearch();
        
    }

    @GET
    @Path("/dynamicsearch/")
    @Produces("application/json")
    public String getDynamicSearchJson() {
        
        return "ClearDynamicSearch: Shuld be called with DELETE instead of GET";
        
    }

    @DELETE
    @Path("/{collecionID}")
    @Produces("application/json")
    public List<UpdateResponse> deleteMainJson(@PathParam("collecionID") String collectionID) {
        
        return PerformClearCollection(String.format("%s*",collectionID));
        
    }

    @DELETE
    @Path("/{collecionID}/{version:([^/]+?)?}/{variant:([^/]+?)?}")
    @Produces("application/json")
    public List<UpdateResponse> deleteMainJson(
            @PathParam("collecionID") String collectionID,
            @PathParam("version") String version,
            @PathParam("variant") String variant
            ) {

        if ((version == null) || "".equals(version)) {
            version = "*";
        } else {
            if (version.equals("all"))
                version = "*";
        }
        if ((variant == null) || "".equals(variant)) {
            variant = "*";
        } else {
            if (variant.equals("all"))
                variant = "*";
        }
        return PerformClearCollection(String.format("%s/%s/%s",collectionID, version, variant));
        
    }

    @GET
    @Path("/{collecionID}")
    @Produces("application/json")
    public String getMainJson(@PathParam("collecionID") String collectionID) {
        
        return "ClearMain: Shuld be called with DELETE instead of GET";
        
    }

    private  List<UpdateResponse> PerformClearAll() {

            getServletContextParams();

            EPubParser parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
            if (parser == null) {
                logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
                throw new IndexerValidationException("Cannot create publication parser object!");
            }
            
            List<UpdateResponse> responses = parser.clearAll();
            
            return responses;
        
    }

    private List<UpdateResponse> PerformClearMain() {
        
            getServletContextParams();

            EPubParser parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
            if (parser == null) {
                logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
                throw new IndexerValidationException("Cannot create publication parser object!");
            }
            
            List<UpdateResponse> responses = parser.clearMain();
            
            return responses;
        
    }

    private  List<UpdateResponse> PerformClearAutocomplete() {

            getServletContextParams();

            EPubParser parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
            if (parser == null) {
                logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
                throw new IndexerValidationException("Cannot create publication parser object!");
            }
            
            List<UpdateResponse> responses = parser.clearAuto();
            
            return responses;
        
    }
    
    private  List<UpdateResponse> PerformClearDynamicSearch() {

            getServletContextParams();

            EPubParser parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
            if (parser == null) {
                logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
                throw new IndexerValidationException("Cannot create publication parser object!");
            }
            
            List<UpdateResponse> responses = parser.clearDynamic();
            
            return responses;
        
    }

    private List<UpdateResponse> PerformClearCollection(String collectionID) {

        getServletContextParams();

        EPubParser parser = new EPubParser(solrMainURL, solrAutoURL, solrDynaURL);
        if (parser == null) {
            logger.log(Level.SEVERE, null, "Cannot create publication parser object!");
            throw new IndexerValidationException("Cannot create publication parser object!");
        }

        //String clearQuery = String.format("collectionid:%s OR id:%s", collectionID, collectionID);
        
        List<UpdateResponse> responses = parser.clearCollection(collectionID);

        return responses;
    }
    
    
}

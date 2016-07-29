package pl.psnc.epub.solr.common;

import java.io.Serializable;
import java.net.URL;
import org.apache.solr.client.solrj.response.UpdateResponse;

/**
 *
 * @author spychala
 */
public class SOLRResponse implements Serializable {

    private UpdateResponse response;
    
    private URL url;
    
    private String id;

    private String message;

    public SOLRResponse(UpdateResponse response, URL url, String id) {
        this.response = response;
        this.url = url;
        this.id = id;
        this.message = "Element read successfully.";
    }

    public SOLRResponse(UpdateResponse response, URL url, String id, String message) {
        this.response = response;
        this.url = url;
        this.id = id;
        this.message = message;
    }
    
    
    public UpdateResponse getResponse() {
        return response;
    }

    public URL getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public void setResponse(UpdateResponse response) {
        this.response = response;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}

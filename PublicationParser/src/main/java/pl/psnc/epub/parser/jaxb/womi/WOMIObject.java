package pl.psnc.epub.parser.jaxb.womi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.WomiIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class WOMIObject {
    
    public static String PARAMETERS = "parameters";
    public static String CLASSIC = "classic";
    public static String RESOLUTION = "resolution";
    public static String MIMETYPE = "mimeType";
        
    private IndexItem parent = null;
    private String id = null;
    private String instanceId = null;
    private String collectionId = null;
    private String moduleId = null;
    private String manifestURL = null;
    private String metadataURL = null;
    private IndexContext context = null;
    
    private static Logger logger = Logger.getLogger(WOMIObject.class.getName());

    public static final Map<String, String> coverTypes;

    static {
        Map<String, String> m = new HashMap<String, String>();
        m.put("image/jpeg", "jpg"); 
        m.put("image/pjpeg", "jpg"); 
        m.put("image/png", "png"); 
        m.put("image/svg+xml", "svg"); 
        m.put("image/svg", "svg"); 
        m.put("svg", "svg");
        coverTypes = Collections.unmodifiableMap(m);
    }

    protected WOMIObject() {
    }
    
    public WOMIObject(IndexItem parent, IndexContext context, String id, String instanceId, String moduleId, String collectionId) {
        this.parent = parent;
        this.id = id;
        this.instanceId = instanceId;
        this.moduleId = moduleId;
        this.collectionId = collectionId;
        this.context = context;
    }

    public WOMIObject(IndexItem parent, IndexContext context, String id, String instanceId, String moduleId, String collectionId, String manifestURL, String metadataURL) {
        this.parent = parent;
        this.id = id;
        this.instanceId = instanceId;
        this.moduleId = moduleId;
        this.collectionId = collectionId;
        this.manifestURL = manifestURL;
        this.metadataURL = metadataURL;
        this.context = context;
    }
    
    public WOMIObject(String manifestURL, String metadataURL) {
        if (this.id == null)
            this.id = "-";
        this.manifestURL = manifestURL;
        this.metadataURL = metadataURL;
    }

    public IndexItem generateSelfIndex() {
        WomiIndexItem indexItem = null;
        if (id != null && !"".equals(id)) {
            indexItem = new WomiIndexItem(context);
            indexItem.setParent(parent);
            indexItem.setId(id);
            indexItem.setCollectionId(collectionId);
            indexItem.setModuleId(moduleId);
            indexItem.setLink(instanceId);
            if (parent != null)
                indexItem.setCollectionId(parent.getCollectionId());
            if (metadataURL != null && !"".equals(metadataURL)) {
                try {
                    MetadataObject metadata = readMetadata();
                    if (metadata != null) {
                        if (metadata.title != null) {
                            metadata.title = metadata.title.replaceAll("\\[\"", "");
                            metadata.title = metadata.title.replaceAll("\"\\]", "");
                            metadata.title = metadata.title.replaceAll("\"", " ");
                            //List<String> titles = Arrays.asList(metadata.keywords.split("\\s*,\\s*"));
                            indexItem.setTitle(metadata.title);
                        }
                        indexItem.setAlttext(metadata.alternativeText);
                        indexItem.addAuthor(metadata.author);
                        if (metadata.keywords != null) {
                            metadata.keywords = metadata.keywords.replaceAll("\\[\"", "");
                            metadata.keywords = metadata.keywords.replaceAll("\"\\]", "");
                            List<String> keywords = Arrays.asList(metadata.keywords.split("\\s*,\\s*"));
                            indexItem.setKeywords(keywords);
                        }
                        if (metadata.womiType != null && !"".equals(metadata.womiType))
                            indexItem.setSubType(metadata.womiType);
                    }
                } catch (ClassCastException cce) {
                    logger.log(Level.SEVERE,"WOMI: " + id + ", manifest URL: " + manifestURL + ", metadata URL: " + metadataURL, cce);
                }
            }
            if (manifestURL != null && !"".equals(manifestURL)) {
                try {
                    ManifestObject manifestdata = readManifest();
                    if (manifestdata != null)
                        indexItem.setVersion(manifestdata.version);
                } catch (ClassCastException cce) {
                    logger.log(Level.SEVERE,"WOMI: " + id + ", manifest URL: " + manifestURL + ", metadata URL: " + metadataURL, cce);
                }
            }
        }
        logger.log(Level.INFO,"WOMI: " + id + ", manifest URL: " + manifestURL + ", metadata URL: " + metadataURL + ", collectionid: " + collectionId);
        return indexItem;
        
    }
    
    private JSONObject readJSONObject(String jsonURL) {
        
	JSONParser parser = new JSONParser();
 
	try {
 
            URL url = new URL(jsonURL);
            URLConnection conn = url.openConnection();
            Object obj = parser.parse(new InputStreamReader(url.openStream()));
            return (JSONObject) obj;

	} catch (FileNotFoundException fnfe) {
            Logger.getLogger(WOMIObject.class.getName()).log(Level.SEVERE, null, fnfe);
            fnfe.printStackTrace();
	} catch (IOException ioe) {
            Logger.getLogger(WOMIObject.class.getName()).log(Level.SEVERE, null, ioe);
            ioe.printStackTrace();
	} catch (ParseException ex) {
            Logger.getLogger(WOMIObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private ManifestObject readManifest() {

        JSONObject manifestJSON = readJSONObject(manifestURL);
        
        ManifestObject manifest = null;
        if (manifestJSON != null)
            manifest = new ManifestObject(manifestJSON);
        
        return manifest;
    }
    
    private MetadataObject readMetadata() {
        
        JSONObject metadataJSON = readJSONObject(metadataURL);
        
        MetadataObject metadata = null;
        if (metadataJSON != null)
            metadata = new MetadataObject(metadataJSON);
        
        return metadata;
    }
    
    public String getCoverURL() {
        JSONObject manifestJSON = readJSONObject(manifestURL);
        String coverURL = manifestURL.toString().substring(0, manifestURL.toString().lastIndexOf("/") + 1) + CLASSIC;

        if (manifestJSON != null) {
            JSONObject parameters = (JSONObject) manifestJSON.get(PARAMETERS);
            if (parameters != null) {
                JSONObject classic = (JSONObject) parameters.get(CLASSIC);
                if (classic != null) {
                    String mimeType = (String) classic.get(MIMETYPE);
                    String extension = coverTypes.get(mimeType);
                        Long minRes = null;
                    if (!extension.equals("svg")) {
                        JSONArray resolutions = (JSONArray) classic.get(RESOLUTION);
                        Iterator<Long> iterator= resolutions.iterator();
                        while(iterator.hasNext())
                        {
                            Long resolution = iterator.next();
                            if (minRes != null) {
                                minRes = Math.min(minRes, resolution);
                            } else {
                                minRes = resolution;
                            }
                        }
                    }
                    if (minRes != null)
                        coverURL += "-" + minRes;
                    coverURL += "." + extension;
                }
            }
        }
        return coverURL;
    }
}

package pl.psnc.epub.parser.jaxb.womi;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author spychala
 */
public class MetadataObject {
    
    public static class MetadataConstans {
        public static String AUTHOR = "author";
        public static String TITLE = "title";
        public static String KEYWORDS = "keywords";
        public static String LICENSE = "license";
        public static String ALTERNATIVETEXT = "alternativeText";
        public static String WOMITYPE = "womiType";
    }
    String author = null;
    String title = null;
    String keywords = null;
    String license = null;
    String alternativeText = null;
    String womiType = null;
    
    private static Logger logger = Logger.getLogger(MetadataObject.class.getName());

    private MetadataObject() {
        
    }
    
    public MetadataObject(JSONObject jsonObject) {
        if (jsonObject == null)
            return;
        
        this.author = getObjectAsString(jsonObject.get(MetadataConstans.AUTHOR));
        this.title = getObjectAsString(jsonObject.get(MetadataConstans.TITLE));
        this.keywords = getObjectAsString(jsonObject.get(MetadataConstans.KEYWORDS));
        this.license = getObjectAsString(jsonObject.get(MetadataConstans.LICENSE));
        this.alternativeText = getObjectAsString(jsonObject.get(MetadataConstans.ALTERNATIVETEXT));
        this.womiType = (String) jsonObject.get(MetadataConstans.WOMITYPE);
    }
    
    private String getObjectAsString(Object jsonObj) {
        if (jsonObj == null)
            return null;
        
        if (jsonObj instanceof JSONArray)
            return ((JSONArray)jsonObj).toString();
        else
            return (String)jsonObj;
    }
    
    public MetadataObject(String author, String title, String keywords, String license, String alternativeText, String womiType) {
        this.author = author;
        this.title = title;
        this.keywords = keywords;
        this.license = license;
        this.alternativeText = alternativeText;
        this.womiType = womiType;
    }
    
}

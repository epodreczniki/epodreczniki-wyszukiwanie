package pl.psnc.epub.parser.jaxb.womi;

import org.json.simple.JSONObject;

/**
 *
 * @author spychala
 */
class ManifestObject {
    
    public static class ManifestConstans {
        public static String MAINFILE = "mainFile";
        public static String ENGINE = "engine";
        public static String VERSION = "version";
    }
    
    String mainFile = null;
    String engine = null;
    String version = null;
    
    private ManifestObject() {
        
    }
    
    public ManifestObject(JSONObject jsonObject) {
        if (jsonObject != null) {
            this.mainFile = (String) jsonObject.get(ManifestConstans.MAINFILE);
            this.engine = (String) jsonObject.get(ManifestConstans.ENGINE);
            this.version = (String) jsonObject.get(ManifestConstans.VERSION);
        }
    }
    
    public ManifestObject(String mainFile, String engine, String version){
        this.mainFile = mainFile;
        this.engine = engine;
        this.version = version;
    }
    
}

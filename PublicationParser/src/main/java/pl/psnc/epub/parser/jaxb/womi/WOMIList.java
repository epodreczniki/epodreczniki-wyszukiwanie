package pl.psnc.epub.parser.jaxb.womi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
public class WOMIList {
    
    private static List<WOMIObject> list = new ArrayList();
    private static Logger logger = Logger.getLogger(WOMIList.class.getName());
    
    private static String womiURLbase;

    protected WOMIList() {
       // Exists only to thwart instantiation.
    }
    
    public static WOMIList getInstance() {
        WOMIList instance = (WOMIList)SingletonRegistry.REGISTRY.getInstance(WOMIList.class.getName());
        if (SingletonRegistry.getInitialParameter() != null)
            instance.womiURLbase = (String)SingletonRegistry.getInitialParameter();
        return instance;
    }
    
    public void addWOMI(IndexItem parent, IndexContext context, String id, String instanceId, String moduleId, String collectionId) {
        
        String parsedId = id;
        if (parsedId != null)
            if (parsedId.startsWith("0"))
                try {
                    int intId = Integer.parseInt(parsedId);
                    if (intId != 0)
                        while (parsedId.startsWith("0"))
                            parsedId = parsedId.substring(1);
                } catch (NumberFormatException nfe) {
                    // OK, not a number
                }
        String womiURLPlaceholder = womiURLbase;
        if (!womiURLPlaceholder.endsWith("/"))
            womiURLPlaceholder = String.format("%s/%s", womiURLPlaceholder, parsedId);
        else
            womiURLPlaceholder = String.format("%s%s", womiURLPlaceholder, parsedId);
        String manifestURL = String.format("%s/manifest.json", womiURLPlaceholder);
        String metadataURL = String.format("%s/metadata.json", womiURLPlaceholder);
        //logger.log(Level.INFO,"WOMI: " + id + ", manifest URL: " + manifestURL + ", metadata URL: " + metadataURL);
        addWOMI(parent, context, id, instanceId, moduleId, collectionId, manifestURL, metadataURL);
        
    }

    public void addWOMI(IndexItem parent, IndexContext context, String id, String instanceId, String moduleId, String collectionId, String manifestURL, String metadataURL) {

        logger.log(Level.INFO,"Adding WOMI: " + id + ", instanceId: " + instanceId + ", moduleId: " + moduleId + ", collectionId: " + collectionId + ", metadata URL: " + metadataURL + ", metadataURL: " + metadataURL);
        WOMIObject womiObject = new WOMIObject(parent, context, id, instanceId, moduleId, collectionId, manifestURL, metadataURL);
        list.add(womiObject);
        
    }
    
    public void clear() {
        this.list.clear();
    }
    
    public List<IndexItem> generateIndex() {
    
        List<IndexItem> items = new ArrayList<IndexItem>();
        for(WOMIObject womiObj : list) {
            items.add(womiObj.generateSelfIndex());
        }
        return items;
        
    }
    
}

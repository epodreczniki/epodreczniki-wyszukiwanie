package pl.psnc.epub.parser;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.TreeWalker;
import pl.psnc.epub.parser.jaxb.collection.Collection;
import pl.psnc.epub.parser.jaxb.collection.CollectionElement;
import pl.psnc.epub.parser.jaxb.collection.Module;
import pl.psnc.epub.parser.jaxb.collection.Subcollection;
import pl.psnc.epub.parser.jaxb.metadata.Metadata;
import pl.psnc.epub.parser.jaxb.module.Document;
import pl.psnc.epub.parser.jaxb.module.ModuleElement;
import pl.psnc.epub.parser.jaxb.womi.SingletonRegistry;
import pl.psnc.epub.parser.jaxb.womi.WOMIList;
import pl.psnc.epub.parser.jaxb.womi.WOMIObject;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.dynamicsearch.CollectionDynSearchIndexItem;
import pl.psnc.epub.solr.beans.dynamicsearch.DynSearchIndexItem;
import pl.psnc.epub.solr.beans.dynamicsearch.ModuleDynSearchIndexItem;
import pl.psnc.epub.solr.common.IndexContext;
import pl.psnc.epub.solr.common.SOLRResponse;
//import pl.psnc.epub.parser.jaxb.collection.Module;

/**
 *
 * @author spychala
 */
public class EPubParser {
    
    private static final String BASE_URL = "http://localhost:8983/solr/";
    private static final String BASE_URL_PLACEHOLDER = "http://static.epodreczniki.pl/content/module/%s/module.xml";
    //private static final String BASE_URL_PLACEHOLDER = "http://static.test.epodreczniki.pl/content/module/%s/module.xml";
    //private static final String BASE_URL_PLACEHOLDER = "http://static.beta.epodreczniki.pl/content/module/%s/module.xml";
    private static final String MAIN_COLLECTION = "epub";
    private static final String AUTO_COLLECTION = "epub_ac";
    private static final String DYN_SEARCH_COLLECTION = "epub_ds";

    private String mainUrl = BASE_URL + MAIN_COLLECTION;
    private String autoUrl = BASE_URL + AUTO_COLLECTION;
    private String dynaUrl = BASE_URL + DYN_SEARCH_COLLECTION;
    private String urlPlaceholder = null;

    private static Logger logger = Logger.getLogger(EPubParser.class.getName());

    public EPubParser() {
        this.mainUrl = BASE_URL + MAIN_COLLECTION;
        this.autoUrl = BASE_URL + AUTO_COLLECTION;
        this.dynaUrl = BASE_URL + DYN_SEARCH_COLLECTION;
    }
    public EPubParser(String baseURLPlaceholder) {
        /*
        this.mainUrl = baseURL + MAIN_COLLECTION;
        this.autoUrl = baseURL + AUTO_COLLECTION;
        this.dynaUrl = baseURL + DYN_SEARCH_COLLECTION;
        */
        this.urlPlaceholder = baseURLPlaceholder;
    }
    
    public EPubParser(String solrMainURL, String solrAutoURL, String solrDynaURL) {        
        this(solrMainURL, solrAutoURL, solrDynaURL, null);
    }
    
    public EPubParser(String solrMainURL, String solrAutoURL, String solrDynaURL, String urlPlaceholder) {
        this.mainUrl = solrMainURL;
        this.autoUrl = solrAutoURL;
        this.dynaUrl = solrDynaURL;
        this.urlPlaceholder = urlPlaceholder;
    }
    
    public static void marshallCollection(Collection collection) throws JAXBException, IOException {
        
        JAXBContext collectionJaxbContext = JAXBContext.newInstance(Collection.class);
        Marshaller marshaller = collectionJaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        /*
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        marshaller.marshal(collection, writer);
        writer.close();
        */
        marshaller.marshal(collection, System.out);
        
    }

    public static void marshallModule(Document module) throws JAXBException {
        
        JAXBContext moduleJaxbContext = JAXBContext.newInstance(Document.class, ModuleElement.class);
        Marshaller marshaller = moduleJaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        /*
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        marshaller.marshal(collection, writer);
        writer.close();
        */
        marshaller.marshal(module, System.out);
        
    }
    
    public static Collection unmarshallCollection(File inXmlFile) throws JAXBException, IOException {
        
        JAXBContext collectionJaxbContext = JAXBContext.newInstance(Collection.class);
        Unmarshaller unmarshaller = collectionJaxbContext.createUnmarshaller();
        Collection epub = (Collection) unmarshaller.unmarshal(inXmlFile);
        
        return epub;
        
    }

    public static Collection unmarshallCollection(URL inURL) throws JAXBException, IOException {
        
        JAXBContext collectionJaxbContext = JAXBContext.newInstance(Collection.class);
        Unmarshaller unmarshaller = collectionJaxbContext.createUnmarshaller();
        Collection epub = (Collection) unmarshaller.unmarshal(inURL);

        String womiURL = inURL.toString().substring(0, inURL.toString().indexOf("collection/")) + "womi/";
        epub.setCoverBaseURL(womiURL);
        
        return epub;
        
    }

    public static Document unmarshallModule(File inXmlFile) throws JAXBException {
        
        JAXBContext moduleJaxbContext = JAXBContext.newInstance(Document.class, ModuleElement.class);
        Unmarshaller unmarshaller = moduleJaxbContext.createUnmarshaller();
        Document module = (Document) unmarshaller.unmarshal(inXmlFile);
        
        return module;
        
    }

    public static Document unmarshallModule(URL inURL) throws JAXBException {
        
        JAXBContext moduleJaxbContext = JAXBContext.newInstance(Document.class, ModuleElement.class);
        Unmarshaller unmarshaller = moduleJaxbContext.createUnmarshaller();
        Document module = (Document) unmarshaller.unmarshal(inURL);
        
        return module;
        
    }

    public void parse(String path) throws JAXBException, SolrServerException, IOException {
        
        Map<String, List<String>> collections = PublicationCollector.collectPublications(path);

        try {

            for (Map.Entry<String, List<String>> entry : collections.entrySet()) {

                System.out.print("Parsing collection: " + entry.getKey());
                Collection collection = unmarshallCollection(new File(entry.getKey()));
                System.out.println(".");
                
                //marshallCollection(collection);
                IndexItem colIndexItem = collection.generateIndex().get(0);
                int nOfModules = getNumberOfModules(collection);
                int nOfModuleFiles = ((entry.getValue() != null) ? entry.getValue().size() : 0);
                
                if (nOfModules != nOfModuleFiles)
                    System.out.println(String.format(" --> WARNING! Number of modules in collection: %d, number of files: %d",nOfModules, nOfModuleFiles));
                        
                for (String moduleFile : entry.getValue()) {
                    System.out.print("  Parsing module: " + moduleFile);
                    Document module = unmarshallModule(new File(moduleFile));
                    System.out.println(".");
                    
                    module.generateIndex(colIndexItem);
                    //marshallModule(module);
                }

            }

        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }
    
    private static final void traverseLevel(TreeWalker walker, String indent) {
        Node parend = walker.getCurrentNode();
        System.out.println(indent + "- " + parend.getLocalName());
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            traverseLevel(walker, indent+'\t');
        }
        walker.setCurrentNode(parend);
    }
    
    public static String xmlToString(Node node) {
        try {
            Source source = new DOMSource(node);
            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
            return stringWriter.getBuffer().toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }    
    
    public void collect(String path) {
        
        Map<String, List<String>> collections = PublicationCollector.collectPublications(path);

        for (Map.Entry<String, List<String>> entry : collections.entrySet()) {
            try {
                System.out.println("Collection: " + entry.getKey());
                Collection collection = unmarshallCollection(new File(entry.getKey()));
                
                int nOfModules = getNumberOfModules(collection);
                int nOfModuleFiles = ((entry.getValue() != null) ? entry.getValue().size() : 0);
                if (nOfModules != nOfModuleFiles)
                    System.out.println(String.format(" --> WARNING! Number of modules in collection: %d, number of files: %d",nOfModules, nOfModuleFiles));
                
                List<IndexItem> collIndexItems = collection.generateIndex();
                for (IndexItem colIndexItem : collIndexItems) {
                    System.out.println(" - " + colIndexItem.getId());
                }
                
                for (String moduleFile : entry.getValue()) {
                    System.out.println(" Module: " + moduleFile);
                    Document module = unmarshallModule(new File(moduleFile));
                    module.setCollectionId(collection.getMetadata().getContextId());
                    List<IndexItem> modIndexItems = module.generateIndex();
                    for (IndexItem modIndexItem : modIndexItems) {
                        System.out.println("   - " + modIndexItem.getId());
                    }
                }
            } catch (JAXBException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void collect(URL collectionURL) {
        
            try {
                System.out.println("Collection: " + collectionURL);
                Collection collection = unmarshallCollection(collectionURL);
                
                List<IndexItem> collIndexItems = collection.generateIndex();
                for (IndexItem colIndexItem : collIndexItems) {
                    System.out.println(" - " + colIndexItem.getId());
                }
                
                List<URL> modulesURLs = getCollectionsModulesURLs(collection);
                if (modulesURLs != null) {
                    for (URL moduleURL : modulesURLs) {
                        System.out.println(" Module URL: " + moduleURL);
                        Document module = unmarshallModule(moduleURL);
                        module.setCollectionId(collection.getMetadata().getContextId());
                        //module.setCollectionId(collection.getCollectionId());
                        List<IndexItem> modIndexItems = module.generateIndex();
                        for (IndexItem modIndexItem : modIndexItems) {
                            System.out.println("   - " + modIndexItem.getId());
                        }
                    }
                }
                
            } catch (JAXBException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }

    }
    
    public void index(String path) {
        index(path, mainUrl, autoUrl, dynaUrl);
    }

    public void index(String path, String baseURL) {
        String mainUrl = baseURL + MAIN_COLLECTION;
        //String mainUrl = null;
        String autoUrl = baseURL + AUTO_COLLECTION;
        //String autoUrl = null;
        String dynaUrl = baseURL + DYN_SEARCH_COLLECTION;
        //String dynaUrl = null;
        index(path, mainUrl, autoUrl, dynaUrl);
    }
    
    public void clear() {
        //clearAll(mainUrl, autoUrl, dynaUrl);
        clearAll();
    }

    /*
    public void clear(String baseURL) {
        String mainUrl = baseURL + MAIN_COLLECTION;
        //String mainUrl = null;
        String autoUrl = baseURL + AUTO_COLLECTION;
        //String autoUrl = null;
        String dynaUrl = baseURL + DYN_SEARCH_COLLECTION;
        //String dynaUrl = null;
        clearAll(mainUrl, autoUrl, dynaUrl);
    }
    */

    public List<UpdateResponse> clearCollection(String collectionID, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl) {
        
        String clearQuery = String.format("collectionid:%s", collectionID);
        return clear(clearQuery, mainSolrUrl, autocompSolrUrl, dynsrchSolrUrl);
        
    }
    public List<UpdateResponse> clearCollection(String collectionID) {
        
        String clearQuery = String.format("collectionid:%s", collectionID);
        return clear(clearQuery, mainUrl, autoUrl, dynaUrl);
        
    }
    
    public List<UpdateResponse> clearAll() {
        
        String deleteQuery = "*:*";
        return clear(deleteQuery, mainUrl, autoUrl, dynaUrl);
        
    }
    public List<UpdateResponse> clearMain() {
        
        String deleteQuery = "*:*";
        return clear(deleteQuery, mainUrl, null, null);
        
    }
    public List<UpdateResponse> clearAuto() {
        
        String deleteQuery = "*:*";
        return clear(deleteQuery, null, autoUrl, null);
        
    }
    public List<UpdateResponse> clearDynamic() {
        
        String deleteQuery = "*:*";
        return clear(deleteQuery, null, null, dynaUrl);
        
    }
    /*
    public List<UpdateResponse> clearAll(String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl) {
        
        String deleteQuery = "*:*";
        return clear(deleteQuery, mainSolrUrl, autocompSolrUrl, dynsrchSolrUrl);
        
    }
    */
    public List<UpdateResponse> clear(String deleteQuery, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl) {
        
        List<UpdateResponse> responses = new ArrayList<UpdateResponse>();

        try {
            HttpSolrServer mainSolr = null;
            HttpSolrServer autoSolr = null;
            HttpSolrServer dynaSolr = null;
            if ((mainSolrUrl != null) && (!"".equals(mainSolrUrl))) {
                mainSolr = new HttpSolrServer( mainSolrUrl );
                mainSolr.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
                mainSolr.setConnectionTimeout(5000); // 5 seconds to establish TCP
                // Setting the XML response parser is only required for cross
                // version compatibility and only when one side is 1.4.1 or
                // earlier and the other side is 3.1 or later.
                mainSolr.setParser(new XMLResponseParser()); // binary parser is used by default
                // The following settings are provided here for completeness.
                // They will not normally be required, and should only be used 
                // after consulting javadocs to know whether they are truly required.
                mainSolr.setSoTimeout(5000);  // socket read timeout
                mainSolr.setDefaultMaxConnectionsPerHost(100);
                mainSolr.setMaxTotalConnections(100);
                mainSolr.setFollowRedirects(false);  // defaults to false
                // allowCompression defaults to false.
                // Server side must support gzip or deflate for this to have any effect.
                mainSolr.setAllowCompression(true);        
            }
            if ((dynsrchSolrUrl != null) && (!"".equals(dynsrchSolrUrl))) {
                dynaSolr = new HttpSolrServer( dynsrchSolrUrl );
                dynaSolr.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
                dynaSolr.setConnectionTimeout(5000); // 5 seconds to establish TCP
                // Setting the XML response parser is only required for cross
                // version compatibility and only when one side is 1.4.1 or
                // earlier and the other side is 3.1 or later.
                dynaSolr.setParser(new XMLResponseParser()); // binary parser is used by default
                // The following settings are provided here for completeness.
                // They will not normally be required, and should only be used 
                // after consulting javadocs to know whether they are truly required.
                dynaSolr.setSoTimeout(5000);  // socket read timeout
                dynaSolr.setDefaultMaxConnectionsPerHost(100);
                dynaSolr.setMaxTotalConnections(100);
                dynaSolr.setFollowRedirects(false);  // defaults to false
                // allowCompression defaults to false.
                // Server side must support gzip or deflate for this to have any effect.
                dynaSolr.setAllowCompression(true);        
            }
            if ((autocompSolrUrl != null) && (!"".equals(autocompSolrUrl))) {
                autoSolr = new HttpSolrServer( autocompSolrUrl );
                autoSolr.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
                autoSolr.setConnectionTimeout(5000); // 5 seconds to establish TCP
                // Setting the XML response parser is only required for cross
                // version compatibility and only when one side is 1.4.1 or
                // earlier and the other side is 3.1 or later.
                autoSolr.setParser(new XMLResponseParser()); // binary parser is used by default
                // The following settings are provided here for completeness.
                // They will not normally be required, and should only be used 
                // after consulting javadocs to know whether they are truly required.
                autoSolr.setSoTimeout(5000);  // socket read timeout
                autoSolr.setDefaultMaxConnectionsPerHost(100);
                autoSolr.setMaxTotalConnections(100);
                autoSolr.setFollowRedirects(false);  // defaults to false
                // allowCompression defaults to false.
                // Server side must support gzip or deflate for this to have any effect.
                autoSolr.setAllowCompression(true);        
            }

            if (mainSolr != null) {
                System.out.print(String.format("Clearing main SOLR database under %s...",mainSolrUrl));
                mainSolr.deleteByQuery( deleteQuery );
                UpdateResponse commitResponse = mainSolr.commit();
                responses.add(commitResponse);
                System.out.println(commitResponse);
            }
            if (dynaSolr != null) {
                System.out.print(String.format("Clearing dynamic search SOLR database under %s...",dynsrchSolrUrl));
                dynaSolr.deleteByQuery( deleteQuery );
                UpdateResponse commitResponse = dynaSolr.commit();
                responses.add(commitResponse);
                System.out.println(commitResponse);
            }
            if (autoSolr != null) {
                System.out.print(String.format("Clearing autocomplete SOLR database under %s...",autocompSolrUrl));
                autoSolr.deleteByQuery( deleteQuery );
                UpdateResponse commitResponse = autoSolr.commit();
                responses.add(commitResponse);
                System.out.println(commitResponse);
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SolrServerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return responses;
        
    }
    
    public void index(String path, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl) {
        
        Map<String, List<String>> collections = PublicationCollector.collectPublications(path);

        try {
            HttpSolrServer mainSolr = initMainSOLRServer(mainSolrUrl);
            HttpSolrServer autoSolr = initAutoSOLRServer(autocompSolrUrl);
            HttpSolrServer dynaSolr =  initDynaSOLRServer(dynsrchSolrUrl);

            UpdateResponse updateResponse;
            for (Map.Entry<String, List<String>> entry : collections.entrySet()) {

                System.out.print("Indexing collection: " + entry.getKey());
                Collection collection = unmarshallCollection(new File(entry.getKey()));
                System.out.print(".");
                List<IndexItem> colItems = collection.generateIndex();
                if (mainSolr != null) {
                    if (colItems != null && colItems.size() > 0) {
                        updateResponse = mainSolr.addBeans(colItems);
                        System.out.println(updateResponse);
                    } else {
                        System.out.println(" - nothing to index");
                    }
                }
                List<AutocompleteIndexItem> autoColItems = collection.generateAutocompleteIndex();
                if (autoSolr != null) {
                    System.out.print("Indexing collection for autocomplete: " + entry.getKey());
                    if (autoColItems != null && autoColItems.size() > 0) {
                        updateResponse = autoSolr.addBeans(autoColItems);
                        System.out.println(updateResponse);
                    } else {
                        System.out.println(" - nothing to index");
                    }
                }
                if (dynaSolr != null) {
                    System.out.print("Indexing collection for dynamic search: " + entry.getKey());
                    CollectionDynSearchIndexItem dynaColItem = collection.generateDynSearchIndex();
                    if (dynaColItem != null) {
                        updateResponse = dynaSolr.addBean(dynaColItem);
                        System.out.println(updateResponse);
                    } else {
                        System.out.println(" - nothing to index");
                    }
                }

                int nOfModules = getNumberOfModules(collection);
                int nOfModuleFiles = ((entry.getValue() != null) ? entry.getValue().size() : 0);
                if (nOfModules != nOfModuleFiles)
                    System.out.println(String.format(" --> WARNING! Number of modules in collection: %d, number of files: %d",nOfModules, nOfModuleFiles));

                for (String moduleFile : entry.getValue()) {
                    System.out.println("  Indexing module: " + moduleFile);
                    Document module = unmarshallModule(new File(moduleFile));
                    module.setCollectionId(collection.getMetadata().getContextId());
                    //module.setCollectionId(collection.getCollectionId());
                    if (mainSolr != null) {
                        System.out.print("  ... main ...");
                        List<IndexItem> modItems = module.generateIndex(colItems.get(0));
                        if (modItems != null && modItems.size() > 0) {
                            updateResponse = mainSolr.addBeans(modItems);
                            System.out.println(updateResponse);
                        } else {
                            System.out.println("   - nothing to index");
                        }
                    }
                    if (autoSolr != null) {
                        System.out.print("  ... autocomplete ...");
                        List<AutocompleteIndexItem> autoItems = module.generateAutocompleteIndex(autoColItems.get(0));
                        if (autoItems != null && autoItems.size() > 0) {
                            updateResponse = autoSolr.addBeans(autoItems);
                            System.out.println(updateResponse);
                        } else {
                            System.out.println("   - nothing to index");
                        }
                    }
                    if (dynaSolr != null) {
                        System.out.print("  ... dynamic search ...");
                        ModuleDynSearchIndexItem dynaItem = module.generateDynSearchIndex();
                        if (dynaItem != null) {
                            if ((dynaItem.getId() != null) && (!"".equals(dynaItem.getId()))) {
                                updateResponse = dynaSolr.addBean(dynaItem);
                                System.out.println(updateResponse);
                            } else {
                                System.err.println(String.format("ERROR!!! Module %s has generated index item with empty id %s!!!",module.getId(), dynaItem.getId()));
                            }
                        } else {
                            System.out.println("   - nothing to index");
                        }
                    }
                }

            }

            if (mainSolr != null) {
                UpdateResponse commitResponse = mainSolr.commit();
                System.out.println(commitResponse);
            }
            if (dynaSolr != null) {
                UpdateResponse commitResponse = dynaSolr.commit();
                System.out.println(commitResponse);
            }
            if (autoSolr != null) {
                UpdateResponse commitResponse = autoSolr.commit();
                System.out.println(commitResponse);
            }
            
        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SolrServerException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void index(List<URL> collectionURLs, String urlPlaceholder) throws IOException, JAXBException, SolrServerException {
        index(collectionURLs, mainUrl, autoUrl, dynaUrl, urlPlaceholder);
    }

    public List<SOLRResponse> index(List<URL> collectionURLs) throws IOException, JAXBException, SolrServerException {
        return index(collectionURLs, mainUrl, autoUrl, dynaUrl, urlPlaceholder);
    }

    public List<SOLRResponse> index(List<URL> collectionURLs, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl) throws IOException, JAXBException, SolrServerException {
        return index(collectionURLs, mainSolrUrl, autocompSolrUrl, dynsrchSolrUrl, null);
    }
    
    public List<SOLRResponse> index(List<URL> collectionURLs, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl, String urlPlaceholder) throws IOException, JAXBException, SolrServerException {
    
        this.urlPlaceholder = urlPlaceholder;
        //if ((urlPlaceholder == null) || "".equals(urlPlaceholder))
        //    urlPlaceholder = BASE_URL_PLACEHOLDER;

        List<SOLRResponse> responses = new ArrayList<SOLRResponse>();
        
        if (collectionURLs == null) 
            return responses;
        
        if (collectionURLs != null && collectionURLs.size() == 0)
            return responses;

        try {
            HttpSolrServer mainSolr = initMainSOLRServer(mainSolrUrl);
            HttpSolrServer autoSolr = initAutoSOLRServer(autocompSolrUrl);
            HttpSolrServer dynaSolr =  initDynaSOLRServer(dynsrchSolrUrl);

            UpdateResponse updateResponse;

            for (URL collectionURL : collectionURLs) {

                System.out.print("Indexing collection: " + collectionURL);
                logger.log(Level.INFO, "Indexing collection: " + collectionURL);
                Collection collection = null;
                Metadata metadata = null;
                IndexItem colItem = null;
                AutocompleteIndexItem autoItem = null;
                CollectionDynSearchIndexItem dynaColItem = null;
                String womiURL = collectionURL.toString().substring(0, collectionURL.toString().indexOf("collection/")) + "womi/";
                logger.log(Level.INFO, "WOMI url for collection: " + womiURL);
                SingletonRegistry.setInitialParameter(womiURL);
                IndexContext context = null;
                try {
                    collection = unmarshallCollection(collectionURL);
                    System.out.print(".");
                    context = new IndexContext(collection);
                    List<IndexItem> colItems = collection.generateIndex(null, context);
                    if ((colItems != null) && (colItems.size() > 0))
                        colItem = colItems.get(0);
                    if (mainSolr != null) {
                        if (colItems != null && colItems.size() > 0) {
                            updateResponse = mainSolr.addBeans(colItems);
                            responses.add(new SOLRResponse(updateResponse, collectionURL, collection.getCollectionId()));
                            System.out.println(updateResponse);
                            logger.log(Level.INFO, "Collection: " + collectionURL + updateResponse.toString());
                        } else {
                            System.out.println(" - nothing to index");
                            logger.log(Level.INFO, " - nothing to index");
                        }
                    }
                    List<AutocompleteIndexItem> autoColItems = collection.generateAutocompleteIndex();
                    if ((autoColItems != null) && (autoColItems.size() > 0))
                        autoItem = autoColItems.get(0);
                    if (autoSolr != null) {
                        System.out.print("Indexing collection for autocomplete: " + collectionURL);
                        logger.log(Level.INFO, "Indexing collection for autocomplete: " + collectionURL);
                        if (autoColItems != null && autoColItems.size() > 0) {
                            updateResponse = autoSolr.addBeans(autoColItems);
                            responses.add(new SOLRResponse(updateResponse, collectionURL, collection.getCollectionId()));
                            System.out.println(updateResponse);
                            logger.log(Level.INFO, updateResponse.toString());
                        } else {
                            System.out.println(" - nothing to index");
                            logger.log(Level.INFO, " - nothing to index");
                        }
                    }
                    if (dynaSolr != null) {
                        System.out.print("Indexing collection for dynamic search: " + collectionURL);
                        logger.log(Level.INFO, "Indexing collection for dynamic search: " + collectionURL);
                        dynaColItem = collection.generateDynSearchIndex();
                        if (dynaColItem != null) {
                            updateResponse = dynaSolr.addBean(dynaColItem);
                            responses.add(new SOLRResponse(updateResponse, collectionURL, collection.getCollectionId()));
                            System.out.println(updateResponse);
                            logger.log(Level.INFO, updateResponse.toString());
                        } else {
                            System.out.println(" - nothing to index");
                            logger.log(Level.INFO, " - nothing to index");
                        }
                    }
                } catch (IOException ioe) {
                    System.out.println(" - ERROR !!!");
                    System.out.println("IOException: " + ioe.getLocalizedMessage());
                    ioe.printStackTrace();
                    logger.log(Level.INFO, "IOException: " + ioe.getLocalizedMessage());
                    responses.add(new SOLRResponse(null, collectionURL, null, ioe.getLocalizedMessage()));
                } catch (JAXBException jaxbe) {
                    System.out.println(" - ERROR !!!");
                    System.out.println("JAXBException: " + jaxbe.getLocalizedMessage());
                    System.out.println("JAXBException.getLinkedException(): " + jaxbe.getLinkedException());
                    jaxbe.printStackTrace();
                    logger.log(Level.INFO, "JAXBException: " + jaxbe.getLocalizedMessage());
                    logger.log(Level.INFO, "JAXBException.getLinkedException: " + jaxbe.getLinkedException().getLocalizedMessage());
                    String message = jaxbe.getLocalizedMessage();
                    if ((jaxbe.getLinkedException() != null) && (jaxbe.getLinkedException().getCause() != null))
                        message = jaxbe.getLinkedException().getCause().getLocalizedMessage();
                    responses.add(new SOLRResponse(null, collectionURL, null, message));
                } catch (SolrServerException solre) {
                    System.out.println(" - ERROR !!!");
                    System.out.println("SOLRException: " + solre.getLocalizedMessage());
                    solre.printStackTrace();
                    logger.log(Level.INFO, "SOLRException: " + solre.getLocalizedMessage());
                    responses.add(new SOLRResponse(null, collectionURL, null, solre.getLocalizedMessage()));
                }

                List<URL> modulesURLs = new ArrayList<URL>();
                if (this.urlPlaceholder == null)
                    modulesURLs = getCollectionsModulesURLs(collection);
                else
                    modulesURLs = getCollectionsModulesURLs(collection, urlPlaceholder, collectionURL);
                
                for (URL moduleURL : modulesURLs) {
                    System.out.println("  Indexing module: " + moduleURL);
                    logger.log(Level.INFO, "Indexing module: " + moduleURL);
                    try {
                        Document module = unmarshallModule(moduleURL);
                        //module.setCollectionId(collection.getCollectionId());
                        module.setCollectionId(collection.getMetadata().getContextId());
                        if (mainSolr != null) {
                            System.out.print("  ... main ...");
                            List<IndexItem> modItems = module.generateIndex(colItem, context);
                            if (modItems != null && modItems.size() > 0) {
                                updateResponse = mainSolr.addBeans(modItems);
                                responses.add(new SOLRResponse(updateResponse, moduleURL, module.getId()));
                                System.out.println(updateResponse);
                                logger.log(Level.INFO, updateResponse.toString());
                            } else {
                                System.out.println("   - nothing to index");
                            }
                        }
                        if (autoSolr != null) {
                            System.out.print("  ... autocomplete ...");
                            List<AutocompleteIndexItem> autoItems = module.generateAutocompleteIndex(autoItem);
                            if (autoItems != null && autoItems.size() > 0) {
                                updateResponse = autoSolr.addBeans(autoItems);
                                responses.add(new SOLRResponse(updateResponse, moduleURL, module.getId()));
                                System.out.println(updateResponse);
                            } else {
                                System.out.println("   - nothing to index");
                                logger.log(Level.INFO, " - nothing to index");
                            }
                        }
                        if (dynaSolr != null) {
                            System.out.print("  ... dynamic search ...");
                            ModuleDynSearchIndexItem dynaItem = module.generateDynSearchIndex();
                            if (dynaItem != null) {
                                if ((dynaItem.getId() != null) && (!"".equals(dynaItem.getId()))) {
                                    updateResponse = dynaSolr.addBean(dynaItem);
                                    responses.add(new SOLRResponse(updateResponse, moduleURL, module.getId()));
                                    System.out.println(updateResponse);
                                } else {
                                    System.err.println(String.format("ERROR!!! Module %s has generated index item with empty id %s!!!",module.getId(), dynaItem.getId()));
                                }
                            } else {
                                System.out.println("   - nothing to index");
                            }
                        }
                    } catch (IOException ioe) {
                        responses.add(new SOLRResponse(null, moduleURL, null, ioe.getLocalizedMessage()));
                        logger.log(Level.SEVERE, null, String.format("ERROR! Could not index module %s: %s", moduleURL, ioe.getLocalizedMessage()));
                        logger.log(Level.SEVERE, null, ioe);
                    } catch (JAXBException jaxbe) {
                        String message = null;
                        if ((jaxbe.getLinkedException() != null) && (jaxbe.getLinkedException().getCause() != null))
                            message = jaxbe.getLinkedException().getCause().getLocalizedMessage();
                        responses.add(new SOLRResponse(null, moduleURL, null, message));
                        logger.log(Level.SEVERE, null, String.format("ERROR! Could not index module %s: %s", moduleURL, message));
                        logger.log(Level.SEVERE, null, jaxbe);
                    } catch (SolrServerException solre) {
                        responses.add(new SOLRResponse(null, moduleURL, null, solre.getLocalizedMessage()));
                        logger.log(Level.SEVERE, null, String.format("ERROR! Could not index module %s: %s", moduleURL, solre.getLocalizedMessage()));
                        logger.log(Level.SEVERE, null, solre);
                    }

                    WOMIList womiList = WOMIList.getInstance();
                    if (womiList != null) {
                        System.out.println("  Indexing WOMIs for module: " + moduleURL + " using: " + womiURL);
                        logger.log(Level.INFO, "Indexing WOMIs for module: " + moduleURL + " using: " + womiURL);
                        List<IndexItem> womiIndexItems = womiList.generateIndex();
                        if (womiIndexItems != null && womiIndexItems.size() > 0) {
                            updateResponse = mainSolr.addBeans(womiIndexItems);
                            responses.add(new SOLRResponse(updateResponse, new URL(womiURL), collection.getCollectionId()));
                            System.out.println(updateResponse);
                            logger.log(Level.INFO, updateResponse.toString());
                        } else {
                            System.out.println("   - nothing to index");
                        }
                    }
                    womiList.clear();
                }                
            }
            

            if (mainSolr != null) {
                UpdateResponse commitResponse = mainSolr.commit();
                responses.add(new SOLRResponse(commitResponse, new URL(mainSolrUrl), null, "commit - main"));
                System.out.println(commitResponse);
                logger.log(Level.INFO, commitResponse.toString());
            }
            if (dynaSolr != null) {
                UpdateResponse commitResponse = dynaSolr.commit();
                responses.add(new SOLRResponse(commitResponse, new URL(dynsrchSolrUrl), null, "commit - dynamicsearch"));
                System.out.println(commitResponse);
                logger.log(Level.INFO, commitResponse.toString());
            }
            if (autoSolr != null) {
                UpdateResponse commitResponse = autoSolr.commit();
                responses.add(new SOLRResponse(commitResponse, new URL(autocompSolrUrl), null, "commit - autocomplete"));
                System.out.println(commitResponse);
                logger.log(Level.INFO, commitResponse.toString());
            }
            
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw ex;
        } catch (SolrServerException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw ex;
        }

        return responses;
           
    }

    private int getNumberOfModules(Collection collection) {
        int nOfModules = 0;
        if (collection.getContent() != null)
            for (CollectionElement element : collection.getContent()) {
                if (element instanceof Module)
                    nOfModules++;
                else if (element instanceof Subcollection)
                    if (((Subcollection)element).getContent() != null)
                        nOfModules  += ((Subcollection)element).getContent().size();
            }
        return nOfModules;
    }
    
    private List<URL> getCollectionsModulesURLs(Collection collection) {

        List<URL> modulesURLs = new ArrayList<URL>();
        if (collection != null)
            if (collection.getContent() != null)
                for (CollectionElement element : collection.getContent())
                    modulesURLs.addAll(getCollectionsModulesURLs(element));

        return modulesURLs;
    }

    private List<URL> getCollectionsModulesURLs(CollectionElement element) {

        List<URL> modulesURLs = new ArrayList<URL>();
        if (element instanceof Module) {
            if (((Module)element).getUrl() != null)
                try {
                    URL moduelURL = new URL(((Module)element).getUrl());
                    modulesURLs.add(moduelURL);
                } catch (MalformedURLException mue) {

                }
        } else if (element instanceof Subcollection) {
            if (((Subcollection)element).getContent() != null) {
                for (CollectionElement subElement : ((Subcollection)element).getContent()) {
                    modulesURLs.addAll(getCollectionsModulesURLs(subElement));
                }
            }
        }
        return modulesURLs;
    }

    private List<URL> getCollectionsModulesURLs(Collection collection, String urlPlaceholder) {
        return getCollectionsModulesURLs(collection, urlPlaceholder, null);
    }
    /*
    private List<URL> getCollectionsModulesURLs(Collection collection, String urlPlaceholder, String contextId) {

        List<URL> modulesURLs = new ArrayList<URL>();
        if (collection != null)
            if (collection.getContent() != null) {
                for (CollectionElement element : collection.getContent())
                    modulesURLs.addAll(getCollectionsModulesURLs(element, urlPlaceholder, contextId));
            }

        return modulesURLs;
    }
    */
    private List<URL> getCollectionsModulesURLs(Collection collection, String urlPlaceholder, URL collectionURL) {

        //System.out.println("  --> Retrieving modules for: " + collectionURL);
        logger.log(Level.INFO, "  --> Retrieving modules for: " + collectionURL);
        String collectionDirectoryUrl = collectionURL.toString().substring(0, collectionURL.toString().lastIndexOf("/collection.xml"));
        //System.out.println("  --> Collection directory: " + collectionDirectoryUrl);
        logger.log(Level.INFO, "  --> Collection directory: " + collectionDirectoryUrl);
        List<URL> modulesURLs = new ArrayList<URL>();
        if (collection != null)
            if (collection.getContent() != null) {
                for (CollectionElement element : collection.getContent())
                    modulesURLs.addAll(getCollectionsModulesURLs(element, urlPlaceholder, collectionDirectoryUrl));
            }

        return modulesURLs;
    }

    private List<URL> getCollectionsModulesURLs(CollectionElement element, String urlPlaceholder, String contextId) {

        //System.out.println("  --> getCollectionsModulesURLs: " + urlPlaceholder + " for " + contextId);
        logger.log(Level.INFO, "  --> getCollectionsModulesURLs: " + urlPlaceholder + " for " + contextId);
        List<URL> modulesURLs = new ArrayList<URL>();
        if (element instanceof Module) {
            //if (((Module)element).getUrl() != null)
                try {
                    String formatedURL = null;
                    if (contextId != null)
                        formatedURL = String.format(urlPlaceholder, contextId, ((Module)element).getDocument());
                    else
                        formatedURL = String.format(urlPlaceholder,((Module)element).getDocument());
                    logger.log(Level.INFO, "  --> getCollectionsModulesURLs: formatedURL=" + formatedURL);
                    URL moduelURL = new URL(formatedURL);
                    //URL moduelURL = new URL(((Module)element).getUrl());
                    modulesURLs.add(moduelURL);
                } catch (MalformedURLException mue) {
                    logger.log(Level.INFO, "  !!! malformedURL !!!" + mue);
                }
        } else if (element instanceof Subcollection) {
            if (((Subcollection)element).getContent() != null) {
                for (CollectionElement subElement : ((Subcollection)element).getContent()) {
                    modulesURLs.addAll(getCollectionsModulesURLs(subElement, urlPlaceholder, contextId));
                }
            }
        }
        return modulesURLs;
    }

    private HttpSolrServer initMainSOLRServer(String mainSolrUrl) {

        HttpSolrServer mainSolr = null;
        System.out.println(String.format("Initializing main solr connection on %s", mainSolrUrl));
        logger.log(Level.INFO, String.format("Initializing main solr connection on %s", mainSolrUrl));
        if ((mainSolrUrl != null) && (!"".equals(mainSolrUrl)) && (!"null".equals(mainSolrUrl))) {
            mainSolr = new HttpSolrServer( mainSolrUrl );
            mainSolr.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
            mainSolr.setConnectionTimeout(10000); // 5 seconds to establish TCP
            // Setting the XML response parser is only required for cross
            // version compatibility and only when one side is 1.4.1 or
            // earlier and the other side is 3.1 or later.
            mainSolr.setParser(new XMLResponseParser()); // binary parser is used by default
            // The following settings are provided here for completeness.
            // They will not normally be required, and should only be used 
            // after consulting javadocs to know whether they are truly required.
            mainSolr.setSoTimeout(10000);  // socket read timeout
            mainSolr.setDefaultMaxConnectionsPerHost(100);
            mainSolr.setMaxTotalConnections(100);
            mainSolr.setFollowRedirects(false);  // defaults to false
            // allowCompression defaults to false.
            // Server side must support gzip or deflate for this to have any effect.
            mainSolr.setAllowCompression(true);        
        }
        return mainSolr;
    }

    private HttpSolrServer initDynaSOLRServer(String dynsrchSolrUrl) {
        HttpSolrServer dynaSolr = null;
        System.out.println(String.format("Initializing dynamic search solr connection on %s", dynsrchSolrUrl));
        logger.log(Level.INFO, String.format("Initializing dynamic search solr connection on %s", dynsrchSolrUrl));
        if ((dynsrchSolrUrl != null) && (!"".equals(dynsrchSolrUrl)) && (!"null".equals(dynsrchSolrUrl))) {
            dynaSolr = new HttpSolrServer( dynsrchSolrUrl );
            dynaSolr.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
            dynaSolr.setConnectionTimeout(10000); // 5 seconds to establish TCP
            // Setting the XML response parser is only required for cross
            // version compatibility and only when one side is 1.4.1 or
            // earlier and the other side is 3.1 or later.
            dynaSolr.setParser(new XMLResponseParser()); // binary parser is used by default
            // The following settings are provided here for completeness.
            // They will not normally be required, and should only be used 
            // after consulting javadocs to know whether they are truly required.
            dynaSolr.setSoTimeout(10000);  // socket read timeout
            dynaSolr.setDefaultMaxConnectionsPerHost(100);
            dynaSolr.setMaxTotalConnections(100);
            dynaSolr.setFollowRedirects(false);  // defaults to false
            // allowCompression defaults to false.
            // Server side must support gzip or deflate for this to have any effect.
            dynaSolr.setAllowCompression(true);        
        }
        return dynaSolr;
    }

    private HttpSolrServer initAutoSOLRServer(String autocompSolrUrl) {
        HttpSolrServer autoSolr = null;
        System.out.println(String.format("Initializing autocomplete solr connection on %s", autocompSolrUrl));
        logger.log(Level.INFO, String.format("Initializing autocomplete solr connection on %s", autocompSolrUrl));
        if ((autocompSolrUrl != null) && (!"".equals(autocompSolrUrl)) && (!"".equals(autocompSolrUrl))) {
            autoSolr = new HttpSolrServer( autocompSolrUrl );
            autoSolr.setMaxRetries(1); // defaults to 0.  > 1 not recommended.
            autoSolr.setConnectionTimeout(10000); // 5 seconds to establish TCP
            // Setting the XML response parser is only required for cross
            // version compatibility and only when one side is 1.4.1 or
            // earlier and the other side is 3.1 or later.
            autoSolr.setParser(new XMLResponseParser()); // binary parser is used by default
            // The following settings are provided here for completeness.
            // They will not normally be required, and should only be used 
            // after consulting javadocs to know whether they are truly required.
            autoSolr.setSoTimeout(10000);  // socket read timeout
            autoSolr.setDefaultMaxConnectionsPerHost(100);
            autoSolr.setMaxTotalConnections(100);
            autoSolr.setFollowRedirects(false);  // defaults to false
            // allowCompression defaults to false.
            // Server side must support gzip or deflate for this to have any effect.
            autoSolr.setAllowCompression(true);        
        }
        return autoSolr;
    }

    public List<SOLRResponse> publish(List<String> collectionIDs) throws IOException, JAXBException, SolrServerException {
        return publish(collectionIDs, mainUrl, autoUrl, dynaUrl, urlPlaceholder);
    }
    
    public List<SOLRResponse> publish(List<String> collectionIDs, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl) throws IOException, JAXBException, SolrServerException {
        return publish(collectionIDs, mainSolrUrl, autocompSolrUrl, dynsrchSolrUrl, null);
    }
    
    public List<SOLRResponse> publish(List<String> collectionIDs, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl, String urlPlaceholder) throws IOException, JAXBException, SolrServerException {
        return changePublicationState(collectionIDs, mainSolrUrl, autocompSolrUrl, dynsrchSolrUrl, urlPlaceholder, true);
    }
    
    public List<SOLRResponse> unpublish(List<String> collectionIDs) throws IOException, JAXBException, SolrServerException {
        return publish(collectionIDs, mainUrl, autoUrl, dynaUrl, urlPlaceholder);
    }
    
    public List<SOLRResponse> unpublish(List<String> collectionIDs, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl) throws IOException, JAXBException, SolrServerException {
        return publish(collectionIDs, mainSolrUrl, autocompSolrUrl, dynsrchSolrUrl, null);
    }
    
    public List<SOLRResponse> unpublish(List<String> collectionIDs, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl, String urlPlaceholder) throws IOException, JAXBException, SolrServerException {
        return changePublicationState(collectionIDs, mainSolrUrl, autocompSolrUrl, dynsrchSolrUrl, urlPlaceholder, false);
    }

    public List<SOLRResponse> changePublicationState(List<String> collectionIDs, String mainSolrUrl, String autocompSolrUrl, String dynsrchSolrUrl, String urlPlaceholder, boolean publishedState) throws IOException, JAXBException, SolrServerException {
        
        String collectionQuery = "collectionid:%s";
        
        for (String collectionID : collectionIDs) {
            SolrQuery query = new SolrQuery();
            query.setQuery(String.format(collectionQuery,collectionID));
            query.setFields("id");
            query.setStart(0);    
            
            HttpSolrServer mainSolr = initMainSOLRServer(mainSolrUrl);
            QueryResponse response = mainSolr.query(query);
            SolrDocumentList results = response.getResults();
            for (int i = 0; i < results.size(); ++i) {
                SolrInputDocument doc = new SolrInputDocument();
                Map<String, String> partialUpdate = new HashMap<String, String>();
                partialUpdate.put("set", Boolean.toString(publishedState));
                doc.addField("id", results.get(i));
                doc.addField("published", partialUpdate);
                System.out.println(doc.toString());
            }
        }

        return null;
    }

    public void testWomiRead(String womiID) {
        String commonURL = "http://content.epodreczniki.pl/content/womi/%s";
        String manifestURL = String.format(commonURL + "/manifest.json", womiID);
        String metadataURL = String.format(commonURL + "/metadata.json", womiID);
        WOMIObject obj = new WOMIObject(manifestURL, metadataURL);
        IndexItem idx = obj.generateSelfIndex();
        System.out.println(idx);
    }
}

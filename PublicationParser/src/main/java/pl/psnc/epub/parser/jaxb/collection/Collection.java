package pl.psnc.epub.parser.jaxb.collection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import pl.psnc.epub.parser.EPubParser;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.womi.WOMIObject;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.CollectionIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.dynamicsearch.CollectionDynSearchIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement
@XmlType(
    propOrder={"metadata", "parameters", "content"},
    factoryClass=CollectionFactory.class,
    factoryMethod="createCollection")
public class Collection extends BaseEntity {
    
    private static Logger logger = Logger.getLogger(Collection.class.getName());
    
    private Metadata metadata;
    private Map<String, String> parameters;
    //private Content content;
    private List<CollectionElement> content;

    private Date createTime;

    private String collectionCoverURL;

    public Collection(Date date) {
        createTime = date;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /*
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
    */

    @XmlElementWrapper(name="content")
    @XmlElementRef()
    public List<CollectionElement> getContent() {
        return content;
    }

    public void setContent(List<CollectionElement> content) {
        this.content = content;
    }

    @XmlElement(name = "parameters")
    @XmlJavaTypeAdapter(ParameterAdapter.class)
    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        
        CollectionIndexItem index = null;
        
        if (metadata != null && metadata.getContentId() != null && !"".equals(metadata.getContentId())) {
            index = new CollectionIndexItem(context);

            index.setId(metadata.getContextId());
            //index.setId(contextId);
            index.setCollectionId(metadata.getContextId());
            //index.setCollectionId(metadata.getContentId());
            index.setContent(this.toString());
            index.setParent(parent);
            index.setKeywords(metadata.getKeywords());
            index.setSubjects(metadata.getSubjects());
            index.setRecipient(metadata.getRecipient());
            index.setContentStatus(metadata.getContentStatus());
            if (metadata.getEducationLevels() != null) {
                for (String eduLevel : metadata.getEducationLevels()) {
                    index.addEducationLevel(eduLevel);
                }
            }
            index.setClazz(metadata.getClazz());
            index.setSignature(metadata.getSignature());
            Map<String, Actor> actors = new HashMap<String, Actor>();
            if (metadata.getActors() != null) {
                for (Actor actor : metadata.getActors())
                    actors.put(actor.id, actor);
            }
            if (metadata.getRoles() != null) {
                for (Map.Entry<String, List<String>> role : metadata.getRoles().entrySet()) {
                    if (role != null && role.getValue() != null) {
                        if ("author".equals(role.getKey())) {
                            for (String actorId : role.getValue()) {
                                Actor actor = actors.get(actorId);
                                if (actor != null)
                                    index.addAuthor(actor.toString());
                            }
                        } else if ("maintainer".equals(role.getKey())) {
                            for (String actorId : role.getValue()) {
                                Actor actor = actors.get(actorId);
                                if (actor != null)
                                    index.addMaintainer(actor.toString());
                            }
                        } else if ("licensor".equals(role.getKey())) {
                            for (String actorId : role.getValue()) {
                                Actor actor = actors.get(actorId);
                                if (actor != null)
                                    index.addLicensors(actor.toString());
                            }
                        } else {
                            //System.out.println(String.format("Unknown role: %s for %s", role.getKey(), role.getValue()));
                            logger.log(Level.SEVERE, null, String.format("Unknown role: %s for %s", role.getKey(), role.getValue()));
                        }
                    }
                }
            }
            
            index.setVersion(metadata.getVersion());
            if (metadata.getLicense() != null) {
                index.setLicense(metadata.getLicense().getUrl());
            }
            index.setLanguage(metadata.getLanguage());

            Locale pl = new Locale("pl_PL");
            Date dateParsed = null;
            try {
                dateParsed = new SimpleDateFormat("yyyy-MM-dd HH:mm z", pl).parse(metadata.getCreated());
            } catch (java.text.ParseException jtpe) {
                try {
                    dateParsed = new SimpleDateFormat("yyyy-MM-dd HH:mm", pl).parse(metadata.getCreated());
                } catch (java.text.ParseException jtpeint) {
                    logger.log(Level.SEVERE, "Collection creation date is not parsable: " + metadata.getCreated(), jtpeint);
                }
            }
            if (dateParsed != null)
                index.setCreated(dateParsed);
            dateParsed = null;
            try {
                dateParsed = new SimpleDateFormat("yyyy-MM-dd HH:mm z", pl).parse(metadata.getRevised());
            } catch (java.text.ParseException jtpe) {
                try {
                    dateParsed = new SimpleDateFormat("yyyy-MM-dd HH:mm", pl).parse(metadata.getRevised());
                } catch (java.text.ParseException jtpeint) {
                    logger.log(Level.SEVERE, "Collection creation date is not parsable: " + metadata.getRevised(), jtpeint);
                }
            }
            if (dateParsed != null)
                index.setRevised(dateParsed);
            
            ETextBook eTextBook = metadata.geteTextBook();
            if (eTextBook != null) {
                //index.setVersion(eTextBook.getVersion());
                index.setContentStatus(eTextBook.getContentStatus());
                index.setRecipient(eTextBook.getRecipient());
                index.setClazz(eTextBook.getClazz());
                index.setSignature(eTextBook.getSignature());
                if (eTextBook.getCover() != null)
                    index.setCover(eTextBook.getCover().getCover());
            }
            
            index.setTitle(metadata.getTitle());
            index.setSummary(metadata.getAbstr());
            index.setFullContent(this.toString());
        }
        
        return index;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {

        List<IndexItem> indexList = new ArrayList<IndexItem>();
        for (CollectionElement colElem : content) {
            indexList.addAll(colElem.generateIndex(parent, context));
        }
        return indexList;

    }

    @Override
    public AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent) {

        AutocompleteIndexItem index = null;
        
        if (metadata.getContentId() != null && !"".equals(metadata.getContentId())) {
            
            index = new AutocompleteIndexItem();
            index.setId(metadata.getContextId());
            //index.setId(metadata.getContentId());
            index.setCollectionId(metadata.getContextId());
            //index.setCollectionId(metadata.getContentId());
            index.setParent(parent);
            index.addTags(metadata.getKeywords());
            index.addTags(metadata.getSubjects());
        }
        
        return index;
        
    }

    @Override
    public List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent) {
        return null;
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        
        if (metadata != null)
            collectionId =  metadata.getContextId();
            //collectionId =  metadata.getContentId();
        
        for (CollectionElement colElem : content) {
            colElem.setCollectionId(collectionId);
        }
        
    }
    
    public CollectionDynSearchIndexItem generateDynSearchIndex() {

        CollectionDynSearchIndexItem selfIndexItem = null;
        
        if (metadata.getContentId() != null && !"".equals(metadata.getContentId())) {

            selfIndexItem = new CollectionDynSearchIndexItem();
            
            //selfIndexItem.setId(metadata.getContentId());
            selfIndexItem.setId(metadata.getContextId());
            //selfIndexItem.setCollectionId(metadata.getContentId());
            selfIndexItem.setCollectionId(metadata.getContextId());
            selfIndexItem.addContent(this.toString());
            selfIndexItem.addContents(metadata.getKeywords());
            selfIndexItem.addContents(metadata.getSubjects());
            Map<String, Actor> actors = new HashMap<String, Actor>();
            if (metadata.getActors() != null) {
                for (Actor actor : metadata.getActors())
                    selfIndexItem.addContent(actor.toString());
            }

            selfIndexItem.setTitle(metadata.getTitle());
            selfIndexItem.setAbstract(metadata.getAbstr());
        }
        
        return selfIndexItem;
        
    }    

    public String rgetCoverURL() {
        return collectionCoverURL;
    }

    public void rsetCoverURL(String coverURL) {
        this.collectionCoverURL = coverURL;
    }

    public void setCoverBaseURL(String coverBaseURL) {
        if (metadata != null) {
            if ((metadata.geteTextBook() != null) &&
                    (metadata.geteTextBook().getCover() != null) && 
                    (metadata.geteTextBook().getCover().getCover() != null)) {
                String coverWomiID = metadata.geteTextBook().getCover().getCover();
                if (!"".equals(coverWomiID)) {
                    String womiURLPlaceholder = coverBaseURL;
                    if (!womiURLPlaceholder.endsWith("/"))
                        womiURLPlaceholder = String.format("%s/%s", womiURLPlaceholder, coverWomiID);
                    else
                        womiURLPlaceholder = String.format("%s%s", womiURLPlaceholder, coverWomiID);
                    String manifestURL = String.format("%s/manifest.json", womiURLPlaceholder);
                    String metadataURL = String.format("%s/metadata.json", womiURLPlaceholder);
                    WOMIObject womi = new WOMIObject(manifestURL, metadataURL);
                    this.collectionCoverURL = womi.getCoverURL();
                }
            }
        }
    }
    
}

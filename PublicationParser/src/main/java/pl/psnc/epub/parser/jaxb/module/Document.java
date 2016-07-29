package pl.psnc.epub.parser.jaxb.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import pl.psnc.epub.parser.jaxb.BaseEntity;
import pl.psnc.epub.parser.jaxb.collection.LinkGroup;
import pl.psnc.epub.solr.beans.autocomplete.AutocompleteIndexItem;
import pl.psnc.epub.solr.beans.context.IndexItem;
import pl.psnc.epub.solr.beans.context.ModuleIndexItem;
import pl.psnc.epub.solr.beans.dynamicsearch.ModuleDynSearchIndexItem;
import pl.psnc.epub.solr.common.IndexContext;

/**
 *
 * @author spychala
 */
@XmlRootElement
@XmlType(
    propOrder={"id","moduleId","name","title","metadata", "featuredLinks", "content", "glossary"}
)
public class Document extends BaseEntity {

    private static Logger logger = Logger.getLogger(Document.class.getName());

    private String id;
    private String moduleId;
    
    private String name;
    private String title;
    private Metadata metadata;
    private List<LinkGroup> featuredLinks;// = new ArrayList<LinkGroup>();
    //private String content;
    
    //protected List<Object> content;
    private Content content;
    private Glossary glossary;


    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name="module-id")
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlElementWrapper(name="featured-links")
    @XmlElementRef()
    public List<LinkGroup> getFeaturedLinks() {
        return featuredLinks;
    }

    public void setFeaturedLinks(List<LinkGroup> featuredLinks) {
        this.featuredLinks = featuredLinks;
    }

    /*
    @XmlAnyElement(lax = true)
    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }
    */
    
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
    
    public Glossary getGlossary() {
        return glossary;
    }

    public void setGlossary(Glossary glossary) {
        this.glossary = glossary;
    }

    /*
    public List<IndexItem> generateIndex(IndexItem parent) {
        
        List<IndexItem> indexList = new ArrayList<IndexItem>();
    
        if (id != null && !"".equals(id)) {
            ModuleIndexItem index = new ModuleIndexItem();
            index.setId(id);
            index.setContent(this.toString());
            index.setParent(parent);
            indexList.add(index);

            indexList.addAll(content.generateIndex(index));
        }
        
        
        return indexList;
    }
    */

    @Override
    public IndexItem generateSelfIndex(IndexItem parent, IndexContext context) {
        ModuleIndexItem indexItem = null;

        String indexId = null;
        if (metadata != null)
            indexId = metadata.getContentId();
        if ((indexId == null) || "".equals(indexId) || "?".equals(indexId))
            indexId = id;

        if (indexId != null && !"".equals(indexId)) {
            indexItem = new ModuleIndexItem(context);
            indexItem.setParent(parent);
            indexItem.setId(indexId);
            indexItem.setCollectionId(collectionId);
            indexItem.setModuleId(moduleId);
            Pattern pattern = Pattern.compile("<ep:reference.*?>.*?</ep:reference.*?>|<ep:reference.*?/>", Pattern.DOTALL);
            String str = pattern.matcher(this.toString()).replaceAll("");
            indexItem.setFullContent(str);
            indexItem.setContent(title);
            if (metadata != null) {
                indexItem.setKeywords(metadata.getKeywords());
                indexItem.setSubjects(metadata.getSubjects());
                indexItem.setRecipient(metadata.getRecipient());
                indexItem.setContentStatus(metadata.getContentStatus());
                
                Map<String, Actor> actors = new HashMap<String, Actor>();
                if (metadata.getActors() != null)
                    for (Actor actor : metadata.getActors()) {
                        if (actor.actorid == null) {
                            logger.log(Level.INFO, String.format("ERROR: Actor id is null for: %s",actor));
                        } else 
                            actors.put(actor.actorid, actor);
                    }
                if (metadata.getRoles() != null) {
                    for (Map.Entry<String, List<String>> role : metadata.getRoles().entrySet()) {
                        if (role != null && role.getValue() != null) {
                            if ("author".equals(role.getKey())) {
                                for (String actorId : role.getValue()) {
                                    Actor actor = actors.get(actorId);
                                    if (actor != null)
                                        indexItem.addAuthor(actor.toString());
                                    else
                                        logger.log(Level.SEVERE, String.format("Cannot find Actor: %s for %s", actorId, actors));
                                }
                            } else if ("maintainer".equals(role.getKey())) {
                                for (String actorId : role.getValue()) {
                                    Actor actor = actors.get(actorId);
                                    if (actor != null)
                                        indexItem.addMaintainer(actor.toString());
                                    else
                                        logger.log(Level.SEVERE, String.format("Cannot find Actor: %s for %s", actorId, actors));
                                }
                            } else if ("licensor".equals(role.getKey())) {
                                for (String actorId : role.getValue()) {
                                    Actor actor = actors.get(actorId);
                                    if (actor != null)
                                        indexItem.addLicensors(actor.toString());
                                    else
                                        logger.log(Level.SEVERE, String.format("Cannot find Actor: %s for %s", actorId, actors));
                                }
                            } else {
                                //System.out.println(String.format("Unknown role: %s for %s", role.getKey(), role.getValue()));
                                logger.log(Level.SEVERE, String.format("Unknown role: %s for %s", role.getKey(), role.getValue()));
                            }
                        }
                    }
                }
                
                indexItem.setVersion(metadata.getVersion());
                if (metadata.getLicense() != null) {
                    indexItem.setLicense(metadata.getLicense().getUrl());
                }
                indexItem.setLanguage(metadata.getLanguage());
                try {
                    Locale pl = new Locale("pl_PL");
                    String created = metadata.getCreated();
                    if ((created != null) && (!"".equals(created))) {
                        created = created.trim();
                        if (created.indexOf(" ") == created.lastIndexOf(" "))
                            indexItem.setCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm", pl).parse(created));
                        else
                            indexItem.setCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm zzzz", pl).parse(created));
                    }
                    String revised = metadata.getRevised();
                    if ((revised != null) && (!"".equals(revised))) {
                        revised = revised.trim();
                        if (revised.indexOf(" ") == revised.lastIndexOf(" "))
                            indexItem.setRevised(new SimpleDateFormat("yyyy-MM-dd HH:mm", pl).parse(revised));
                        else
                            indexItem.setRevised(new SimpleDateFormat("yyyy-MM-dd HH:mm zzzz", pl).parse(revised));
                    }
                } catch (ParseException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
                
                ETextBookModule eTextBook = metadata.geteTextBookModule();
                if (eTextBook != null) {
                    indexItem.setVersion(eTextBook.getVersion());
                    indexItem.setContentStatus(eTextBook.getContentStatus());
                    indexItem.setRecipient(eTextBook.getRecipient());
                    List<CoreCurriculumEntry> ccEntries = eTextBook.getCoreCurriculumEntries();
                    if (ccEntries != null)
                        for (CoreCurriculumEntry ccEntry : ccEntries) {
                            indexItem.addEducationLevel(ccEntry.getEducationLevel());
                            indexItem.addCoreCurriculumEntry(ccEntry.toString());
                            indexItem.addCoreCurriculumCode(ccEntry.getCoreCurriculumCode());
                            indexItem.addCoreCurriculumKeyword(ccEntry.getCoreCurriculumKeyword());
                            indexItem.addCoreCurriculumSubject(ccEntry.getCoreCurrirulumSubject());
                        }
                }

            }
            if (glossary != null)
                indexItem.setDefinitions(glossary.getDefinitionsStrings());
            //if (glossary != null)
            //    if (glossary.getDefinitions() != null)
            //        for (Object def : glossary.getDefinitions())
            //indexItem.setDefinitions(glossary.getDefinitions());
        }
        return indexItem;
    }

    @Override
    public List<IndexItem> generateChildIndex(IndexItem parent, IndexContext context) {

        List<IndexItem> indexList = new ArrayList<IndexItem>();
        indexList.addAll(content.generateIndex(parent, context));
        if (glossary != null)
            indexList.addAll(glossary.generateIndex(parent, context));
        return indexList;
        
    }

    @Override
    public AutocompleteIndexItem generateSelfAutocompleteIndex(AutocompleteIndexItem parent) {
        //ModuleIndexItem indexItem = null;

        String indexId = null;
        if (metadata != null)
            indexId = metadata.getContentId();
        
        if ((indexId == null) || "".equals(indexId) || "?".equals(indexId))
            indexId = id;

        if (indexId != null && !"".equals(indexId)) {
            autocompleteItemIndex = new AutocompleteIndexItem();
            autocompleteItemIndex.setParent(parent);
            autocompleteItemIndex.setId(indexId);
            autocompleteItemIndex.setCollectionId(collectionId);
            if (metadata != null) {
                autocompleteItemIndex.addTags(metadata.getKeywords());
                autocompleteItemIndex.addTags(metadata.getSubjects());
            }
            if (glossary != null)
                autocompleteItemIndex.addTags(glossary.getDefinitionsStrings());
        }
        return autocompleteItemIndex;
    }

    @Override
    public List<AutocompleteIndexItem> generateChildAutocompleteIndex(AutocompleteIndexItem parent) {

        List<AutocompleteIndexItem> indexList = new ArrayList<AutocompleteIndexItem>();
        indexList.addAll(content.generateAutocompleteIndex(parent));
        if (glossary != null)
            indexList.addAll(glossary.generateAutocompleteIndex(parent));
        return indexList;

    }

    @Override
    public void setCollectionId(String collectionId) {

        this.collectionId = collectionId;
        
        if (collectionId != null) {
            this.moduleId = String.format("%s/%s", collectionId, moduleId);
            if (metadata != null) {
                metadata.setCollectionId(collectionId);
                metadata.setModuleId(moduleId);
            }
            if (content != null) {
                content.setCollectionId(collectionId);
                content.setModuleId(moduleId);
            }
            if (glossary != null) {
                glossary.setCollectionId(collectionId);
                glossary.setModuleId(moduleId);
            }
            
            if (featuredLinks != null)
                for (LinkGroup linkGrp : featuredLinks) {
                    linkGrp.setCollectionId(collectionId);
                    linkGrp.setModuleId(moduleId);
                }
        }
        
    }

    public ModuleDynSearchIndexItem generateDynSearchIndex() {

        ModuleDynSearchIndexItem selfIndexItem = null;
        
        if (metadata.getContentId() != null && !"".equals(metadata.getContentId())) {
            
            selfIndexItem = new ModuleDynSearchIndexItem();
            
            selfIndexItem.setId(metadata.getContentId());
            selfIndexItem.setCollectionId(collectionId);
            selfIndexItem.addContent(this.toString());
            selfIndexItem.addContents(metadata.getKeywords());
            selfIndexItem.addContents(metadata.getSubjects());
            if (metadata.getActors() != null)
                for (Actor actor : metadata.getActors())
                    selfIndexItem.addContent(actor.toString());

            if (content != null)
                if (content.getElements() != null) {
                    for (Object o : content.getElements()) {
                        if (o instanceof ModuleElement)
                            selfIndexItem.addContent(((ModuleElement)o).toString());
                    }
                } else {
                    logger.log(Level.WARNING, String.format("No content for module: %s", metadata.getContentId()));
                }
            
            selfIndexItem.setTitle(metadata.getTitle());
            selfIndexItem.setAbstract(metadata.getAbstr());
            
        }
        
        return selfIndexItem;
        
    }    
    
    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {

        if (moduleId != null) {
            if (content != null)
                content.setModuleId(moduleId);
            if (glossary != null)
                glossary.setModuleId(moduleId);
            /*
            for (LinkGroup linkGroupElem : featuredLinks) {
                linkGroupElem.setModuleId(moduleId);
            }
            */
        }
        
    }
    
}

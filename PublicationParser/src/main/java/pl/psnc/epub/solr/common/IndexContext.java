package pl.psnc.epub.solr.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import pl.psnc.epub.parser.jaxb.collection.Collection;

/**
 *
 * @author spychala
 */
public class IndexContext {

    private String collectionId = null;
    private String collection_title;
    private String collection_subject;
    private String collection_school_type;
    private int collection_school_type_code;
    private String collection_ep_class;
    private String collectin_variant_name;

    private String collectin_cover_url;

    public static final Map<String, String> schoolTypes;

    static {
        Map<String, String> m = new HashMap<String, String>();
        m.put("I","Wczesnoszkolna");  
        m.put("II", "Podstawowa");
        m.put("III", "Gimnazjum");
        m.put("IV", "Ponadgimnazjalna");
        schoolTypes = Collections.unmodifiableMap(m);
    }

    public static final Map<String, Integer> educationCodes;

    static {
        Map<String, Integer> m = new HashMap<String, Integer>();
        m.put("I",1);  
        m.put("II", 2);
        m.put("III", 3);
        m.put("IV", 4);
        educationCodes = Collections.unmodifiableMap(m);
    }

    public static final Map<String, String> variantNames;

    static {
        Map<String, String> m = new HashMap<String, String>();
        m.put("student","Podręcznik dla ucznia");
        m.put("student-canon","Podręcznik dla ucznia");
        m.put("student-expanding","Podręcznik z treściami rozszerzającymi dla ucznia");
        m.put("student-supplemental","Podręcznik z treściami uzupełniającymi dla ucznia");
        m.put("teacher","Podręcznik dla nauczyciela");
        m.put("teacher-canon","Podręcznik dla nauczyciela");
        m.put("teacher-expanding","Podręcznik z treściami rozszerzającymi dla nauczyciela");
        m.put("teacher-supplemental","Podręcznik z treściami uzupełniającymi dla nauczyciela");
        variantNames = Collections.unmodifiableMap(m);
    }

    public IndexContext(Collection collection) {
        String variantNiceName = "Podręcznik w nieznanym wariancie";
        try {
            this.collectionId = collection.getMetadata().getContextId();
            this.collection_title = collection.getMetadata().getTitle();
            if ((collection.getMetadata().getSubjects() != null) && (collection.getMetadata().getSubjects().size() > 0))
                this.collection_subject = collection.getMetadata().getSubjects().get(0);
            if ((collection.getMetadata().getEducationLevels() != null) && (collection.getMetadata().getEducationLevels().size() > 0)) {
                String educationLevel = collection.getMetadata().getEducationLevels().get(0);
                if ((educationLevel != null) && !"".equals(educationLevel)) {
                    this.collection_school_type = schoolTypes.get(educationLevel);
                    this.collection_school_type_code = educationCodes.get(educationLevel);
                }
            }
            this.collection_ep_class = collection.getMetadata().geteTextBook().getClazz();
            String variant = String.format(
                    "%s-%s",
                    collection.getMetadata().geteTextBook().getRecipient(),
                    collection.getMetadata().geteTextBook().getContentStatus());
            variantNiceName = variantNames.get(variant);
        } catch (Exception e) {
            
        }
        this.collectin_variant_name = variantNiceName;
        this.collectin_cover_url = collection.rgetCoverURL();
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollection_title() {
        return collection_title;
    }

    public void setCollection_title(String collection_title) {
        this.collection_title = collection_title;
    }

    public String getCollection_subject() {
        return collection_subject;
    }

    public void setCollection_subject(String collection_subject) {
        this.collection_subject = collection_subject;
    }

    public String getCollection_school_type() {
        return collection_school_type;
    }

    public void setCollection_school_type(String collection_school_type) {
        this.collection_school_type = collection_school_type;
    }

    public int getCollection_school_type_code() {
        return collection_school_type_code;
    }

    public void setCollection_school_type_code(int collection_school_type_code) {
        this.collection_school_type_code = collection_school_type_code;
    }

    public String getCollection_ep_class() {
        return collection_ep_class;
    }

    public void setCollection_ep_class(String collection_ep_class) {
        this.collection_ep_class = collection_ep_class;
    }

    public String getCollectin_variant_name() {
        return collectin_variant_name;
    }

    public void setCollectin_variant_name(String collectin_variant_name) {
        this.collectin_variant_name = collectin_variant_name;
    }

    public String getCollectin_cover_url() {
        return collectin_cover_url;
    }

    public void setCollectin_cover_url(String collectin_cover_url) {
        this.collectin_cover_url = collectin_cover_url;
    }
    
}

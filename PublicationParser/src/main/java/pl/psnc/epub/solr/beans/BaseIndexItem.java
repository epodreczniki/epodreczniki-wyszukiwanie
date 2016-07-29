package pl.psnc.epub.solr.beans;

import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.common.SolrDocument;

/**
 *
 * @author spychala
 */
public abstract class BaseIndexItem {
    
    protected BaseIndexItem parent;
    
    public static final String ID_FIELD = "id";
    public static final String COLLECTION_ID_FIELD = "collectionid";
    public static final String MODULE_ID_FIELD = "moduleid";

    @Field(value = ID_FIELD)
    protected String id;

    @Field(value = COLLECTION_ID_FIELD)
    protected String collectionId;

    @Field(value = MODULE_ID_FIELD)
    protected String moduleId;

    public BaseIndexItem() {
    }
    
    public BaseIndexItem(SolrDocument solrDocument) {
        if (solrDocument != null) {
            this.id = (String) solrDocument.getFieldValue(ID_FIELD);
            this.collectionId = (String) solrDocument.getFieldValue(COLLECTION_ID_FIELD);
            this.moduleId = (String) solrDocument.getFieldValue(MODULE_ID_FIELD);
        }
    }
    
    public String getId() {
        return id;
    }

    public BaseIndexItem getParent() {
        return parent;
    }

    public void setId(String id) {
        this.id = id;
        String indexId = id;
        if (parent != null) {
            if (parent.id != null) {
                if (id != null) {
                    indexId = String.format("%s/%s", parent.id, id);
                } else {
                    indexId = parent.id;
                }
            }
        }
        this.id = indexId;
    }

    public void setParent(BaseIndexItem parent) {
        this.parent = parent;
        if (parent != null) {
            if (parent.id != null) {
                if (id != null) {
                    this.id = String.format("%s/%s", parent.id, id);
                } else {
                    this.id = parent.id;
                }
            }
        }
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    
    public String getCleanId() {
        return getId();
    }
    
    
}

package pl.psnc.epub.solr.beans.dynamicsearch;

import java.util.ArrayList;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;
import pl.psnc.epub.solr.beans.BaseIndexItem;

/**
 *
 * @author spychala
 */
public class DynSearchIndexItem extends BaseIndexItem {

    protected static final String INDEX_ITEM_TYPE = "generic-dynamic-search-index-item";

    @Field(value = "type")
    protected String type = "generic-index-item";

    @Field("title")
    protected String title;

    @Field("abstract")
    protected String abstrac;

    @Field("content_s")
    protected List<String> contents;

    public DynSearchIndexItem() {
        this.type = INDEX_ITEM_TYPE;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }
    
    public void addContents(List<String> contents) {
        
        if (contents == null)
            return;
        
        if (this.contents == null)
            this.contents = new ArrayList<String>();
        
        this.contents.addAll(contents);
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return abstrac;
    }

    public void setAbstract(String abstrac) {
        this.abstrac = abstrac;
    }

    public void addContent(String content) {

        if (this.contents == null)
            this.contents = new ArrayList<String>();
        
        this.contents.add(content);
        
    }
    
    
}

package pl.psnc.epub.solr.beans.autocomplete;

import pl.psnc.epub.solr.beans.context.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.solr.client.solrj.beans.Field;
import pl.psnc.epub.solr.beans.BaseIndexItem;

/**
 *
 * @author spychala
 */
public class AutocompleteIndexItem extends BaseIndexItem {

    @Field("tag")
    protected List<String> tags;

    public AutocompleteIndexItem() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    public void addTags(List<String> tags) {
        
        if (tags == null)
            return;
        
        if (this.tags == null)
            this.tags = new ArrayList<String>();
        
        this.tags.addAll(tags);
        
    }
}

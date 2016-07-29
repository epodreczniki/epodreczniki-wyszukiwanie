package pl.psnc.epub.parser.jaxb;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author spychala
 */
public abstract class ModuleEntity  extends BaseEntity {
    
    protected String moduleId;

    @XmlTransient
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
    
    
}

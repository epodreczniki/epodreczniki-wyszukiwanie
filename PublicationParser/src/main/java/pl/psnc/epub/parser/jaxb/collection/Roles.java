package pl.psnc.epub.parser.jaxb.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author spychala
 */
@XmlType
public class Roles {

    private List<Role> roles = new ArrayList<Role>();

    private Date createTime;
    
    public Roles() {
        createTime = new Date();
    }
    
    public Roles(Date date) {
        createTime = date;
    }

    @XmlElement(name="role", namespace="http://cnx.rice.edu/mdml")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}

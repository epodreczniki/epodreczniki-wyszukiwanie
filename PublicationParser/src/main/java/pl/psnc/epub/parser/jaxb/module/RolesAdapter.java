package pl.psnc.epub.parser.jaxb.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author spychala
 */
public class RolesAdapter extends XmlAdapter<Roles, Map<String, List<String>>> {

    @Override
    public Map<String, List<String>> unmarshal(Roles roles) throws Exception {
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        if (roles != null)
            for(Role role : roles.getRoles()) {
                List<String> personsInRole;
                if (hashMap.containsKey(role.getType()))
                    personsInRole = hashMap.get(role.getType());
                else
                    personsInRole = new ArrayList();
                for (String val: role.getValue())
                    personsInRole.add(val);
                hashMap.put(role.getType(), personsInRole);
            }
        return hashMap;
    }

    @Override
    public Roles marshal(Map<String, List<String>> map) throws Exception {
        Roles roles = new Roles();
        if (map != null) {
            for(Map.Entry<String, List<String>> entry : map.entrySet()) {
                Role role = new Role();
                role.setType(entry.getKey());
                role.setValue(entry.getValue());
                roles.addRole(role);
            }
        }
        return roles;
    }
    
}

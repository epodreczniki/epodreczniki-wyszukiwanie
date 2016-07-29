package pl.psnc.epub.parser.jaxb.collection;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author spychala
 */
public class ParameterAdapter extends XmlAdapter<Parameters, Map<String, String>>{

    @Override
    public Map<String, String> unmarshal(Parameters parameters) throws Exception {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        if (parameters != null)
            for(Parameter parameter : parameters.getParameters()) {
                hashMap.put(parameter.getName(), parameter.getValue());
            }
        return hashMap;
    }

    @Override
    public Parameters marshal(Map<String, String> map) throws Exception {
        Parameters parameters = CollectionFactory.createParameters();
        if (map != null) {
            for(Map.Entry<String, String> entry : map.entrySet()) {
                Parameter parameter = CollectionFactory.createParameter();
                parameter.setName(entry.getKey());
                parameter.setValue(entry.getValue());
                parameters.addParameter(parameter);
            }
        }
        return parameters;
    }
    
}

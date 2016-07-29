package pl.psnc.epub.parser.jaxb.womi;

/**
 *
 * @author spychala
 */

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonRegistry {
    
    public static SingletonRegistry REGISTRY = new SingletonRegistry();
    
    private static HashMap map = new HashMap();
    private static Logger logger = Logger.getLogger(SingletonRegistry.class.getName());
    
    private static Object param;
    
    protected SingletonRegistry() {
       // Exists to defeat instantiation
    }
    
    public static synchronized void setInitialParameter(Object paramValue) {
        param = paramValue;
    }
    
    public static Object getInitialParameter() {
        return param;
    }
    
    public static synchronized Object getInstance(String classname) {
        Object singleton = map.get(classname);
        if(singleton != null) {
            return singleton;
        }
        try {
            singleton = Class.forName(classname).newInstance();
            logger.log(Level.FINE, "created singleton: " + singleton);
        }
        catch(ClassNotFoundException cnf) {
            logger.log(Level.SEVERE, "Couldn't find class " + classname, cnf);
        }
        catch(InstantiationException ie) {
            logger.log(Level.SEVERE, "Couldn't instantiate an object of type " + classname, ie);
        }
        catch(IllegalAccessException ia) {
            logger.log(Level.SEVERE, "Couldn't access class " + classname, ia);
        }
        map.put(classname, singleton);
        return singleton;
    }
}
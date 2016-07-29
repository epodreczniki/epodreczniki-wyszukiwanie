package pl.psnc.epub.publicationparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import pl.psnc.epub.publicationparser.exceptions.NotAFileException;
import pl.psnc.epub.publicationparser.exceptions.PathNotFoundException;

/**
 *
 * @author spychala
 */
public class DOMePubParser {

    public void parse(String path) {
        
        if (path == null || "".equals(path)) {
            throw new PathNotFoundException(path);
        }
        
        File xmlFile = new File(path);
        if (xmlFile.exists())
            if (!xmlFile.isFile())
                throw new NotAFileException(path);
        
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.normalizeDocument();
            
            /*
            DOMParser dParser = new DOMParser();
            dParser.parse(path);
            Document doc = dParser.getDocument();
            
            NodeList root = doc.getChildNodes();
            */
            Element root = doc.getDocumentElement();
            /*
            System.out.println("["+ root.getNodeName() +"]");
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                System.out.println("["+ node.getNodeName() +"]");
            }
            */
            // Retrieving collection (s?)
            //System.out.println();
            //System.out.println();
            //System.out.println();
            
            /*
            NodeList parameters = root.getElementsByTagName("col:parameters");
            Element params = (Element) parameters.item(0);
            NodeList collection = params.getElementsByTagName("col:param");
            //NodeList collection = root.getChildNodes();
            //NodeList collection = root.getElementsByTagName("*");
            System.out.println("Found " + collection.getLength() + " content nodes: ");
            for (int i = 0; i < collection.getLength(); i++) {
                Element elem = (Element) collection.item(i);
                //System.out.println("["+ elem.getNodeName() +"] <text>" + elem.getTextContent() +"</text>");
                //System.out.println("["+ elem.getNodeName() +"] " + elem.getAttribute("name") + "=" + elem.getAttribute("value"));
                System.out.println("["+ elem.getNodeName() +"] ");
            }
            */
            
            List<Node> collection = getChildElementsByTagName("col:content", root);
            System.out.println("Found " + collection.size() + " nodes 'col:content' in '" + root.getNodeName() + "': ");
            for (Node content : collection) {
                
                System.out.println("Found " + content.getChildNodes().getLength() + " children of ["+ content.getNodeName() +"] ");
                for (int i=0; i<content.getChildNodes().getLength(); i++) {
                    Node elem = content.getChildNodes().item(i);
                    System.out.println("["+ elem.getNodeName() +"] ");
                }
            }
                
        } catch (Exception ex) {
            // TODO!!!
            System.err.println(ex);
        }
       
    }
    
    protected List<Node> getChildElementsByTagName(String tagName, Node root) {

        ArrayList<Node> list = new ArrayList<Node>();
        for ( int x = 0; x < root.getChildNodes().getLength(); x++ ) {
            Node child = root.getChildNodes().item(x);
            if (child.getNodeName().equalsIgnoreCase(tagName)) {
                list.add(child);
            }
        }
        return list;
    }    
}

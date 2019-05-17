import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class SAXExample {

    public static void main(String[] args) throws Exception{
        ReadXML("src/deti.xml");
    }

    public static void ReadXML (String fileName) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(fileName);
        org.w3c.dom.Element root = doc.getDocumentElement();
        Elements elements = new Elements(root.getNodeName());
        ParsXML(root, elements);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writer().withDefaultPrettyPrinter().writeValue(new File("src/main/java/test.json"), elements);
    }

    private static void ParsXML (Node node, Elements elements){
        if (node.hasChildNodes() && (node.getNodeType()== Node.ELEMENT_NODE)){
            NodeList list = node.getChildNodes();
            for (int i=0; i<list.getLength(); i++){
                if (list.item(i).getNodeType()== Node.ELEMENT_NODE){
                    Elements child = elements.addChild();
                    ParsXML(list.item(i), child);
                }
                if (list.item(i).getNodeType()== Node.TEXT_NODE){
                    if (!node.getTextContent().contains("\n")){
                        String text = elements.getText(node.getTextContent());
                    }
                }
            }
        }
    }
}

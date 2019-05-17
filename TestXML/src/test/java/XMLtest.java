import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLtest {

    private Document document;
    private Document document2;
    private Document document3;
    private Document document4;

    @Before
    public void setUp() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        System.out.println("TESTING START");
        document = builder.parse(new File("src/deti.xml"));
        document2 = builder.parse(new File("src/odinelement.xml"));
        document3 = builder.parse(new File("src/mnogoelementov.xml"));
        document4 = builder.parse(new File("src/wrong.xml"));
    }

    @After
    public void tearDown() {
        document = null;
        System.out.println("TESTING END");
    }

    @Test
    public void testDocument() {
        assertNotSame(document, document2);
    }
    @Test
    public void test2Document () {
        assertNotSame(document, document3);
    }
    @Test
    public void test3Document () {
        assertNotSame(document, document4);
    }
}



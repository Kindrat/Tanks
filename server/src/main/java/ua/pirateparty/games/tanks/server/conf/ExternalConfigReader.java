package ua.pirateparty.games.tanks.server.conf;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.InvalidXPathException;
import org.dom4j.io.SAXReader;

import static ua.pirateparty.games.tanks.server.conf.Constants.EXTERNAL_CONFIG_FILE;
import static ua.pirateparty.games.tanks.util.log.Loggers.globalLogger;

public class ExternalConfigReader {

    protected static int maxDBConnections;
    protected static String user;
    protected static String pass;
    protected static String host;
    protected static String base;

    static {
        parseXml();
    }

    private static void parseXml() {
        try {
            Document document = (new SAXReader()).read(EXTERNAL_CONFIG_FILE);
            Element elementToParse = document.getRootElement().element("database");
            maxDBConnections = Integer.valueOf(elementToParse.elementText("maxConnections"));
            user = elementToParse.elementText("user");
            pass = elementToParse.elementText("pass");
            host = elementToParse.elementText("host");
            base = elementToParse.elementText("base");
        } catch (InvalidXPathException | DocumentException e){
            globalLogger.error("ExternalConfigReader.parseXml\t" + e);
        }
    }
}

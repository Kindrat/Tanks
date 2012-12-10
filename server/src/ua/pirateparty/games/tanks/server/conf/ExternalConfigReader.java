package ua.pirateparty.games.tanks.server.conf;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.InvalidXPathException;
import org.dom4j.io.SAXReader;

import static ua.pirateparty.games.tanks.server.conf.Constants.EXTERNAL_CONFIG_FILE;
import static ua.pirateparty.games.tanks.util.log.Loggers.globalLogger;

/**
 * Created with IntelliJ IDEA.
 * User: legioner
 * Date: 09.12.12
 * Time: 16:10
 */
public class ExternalConfigReader {
    protected static int serverPort;
    protected static int connectTimeoutMillis;
    protected static int backlog;
    protected static int resourceUsageTimerPeriod;

    protected static int maxDBConnections;
    protected static String user;
    protected static String pass;
    protected static String host;
    protected static String base;

    public ExternalConfigReader(){
    }

    static {
        parseXml();
    }

    private static void parseXml() {
        try {
            Document document = (new SAXReader()).read(EXTERNAL_CONFIG_FILE);

            Element elementToParse = document.getRootElement().element("server");

                serverPort = Integer.valueOf(elementToParse.elementText("port"));
                connectTimeoutMillis = Integer.valueOf(elementToParse.elementText("connectTimeoutMillis"));
                backlog = Integer.valueOf(elementToParse.elementText("backlog"));
                resourceUsageTimerPeriod = Integer.valueOf(elementToParse.elementText("resourceUsageTimerPeriod"));


            elementToParse = document.getRootElement().element("database");

                maxDBConnections = Integer.valueOf(elementToParse.elementText("maxConnections"));
                user = elementToParse.elementText("user");
                pass = elementToParse.elementText("pass");
                host = elementToParse.elementText("host");
                base = elementToParse.elementText("base");

        } catch (InvalidXPathException e){
            globalLogger.error(e);
        } catch (DocumentException e) {
            globalLogger.error("XML config file parsing error\n" + e);
        }
    }
}

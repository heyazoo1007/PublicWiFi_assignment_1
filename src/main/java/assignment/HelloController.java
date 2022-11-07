package assignment;

import com.squareup.okhttp.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;

public class HelloController {

    public static void saveWifi() {
        String key = "";

        try {
            OkHttpClient client = new OkHttpClient();
            String url = "http://openapi.seoul.go.kr:8088/" + key + "/xml/TbPublicWifiInfo/1/5/";
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("row");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);

                Element eElement = (Element) nNode;
                String distance = "";
                String manageNo = getTagValue("X_SWIFI_MGR_NO", eElement);
                String borough = getTagValue("X_SWIFI_WRDOFC", eElement);
                String wifiName = getTagValue("X_SWIFI_MAIN_NM", eElement);
                String streetAddress = getTagValue("X_SWIFI_ADRES1", eElement);
                String detailAddress = getTagValue("X_SWIFI_ADRES2", eElement);
                String typeOfInstall = getTagValue("X_SWIFI_INSTL_TY", eElement);
                String companyOfInstall = getTagValue("X_SWIFI_INSTL_MBY", eElement);
                String service = getTagValue("X_SWIFI_SVC_SE", eElement);
                String typeOfNet = getTagValue("X_SWIFI_CMCWR", eElement);
                Integer year = Integer.parseInt(getTagValue("X_SWIFI_CNSTC_YEAR", eElement));
                String inOrOut = getTagValue("X_SWIFI_INOUT_DOOR", eElement);
                Long LAT = Long.parseLong(getTagValue("LAT", eElement));
                Long LNT = Long.parseLong(getTagValue("LNT", eElement));
                LocalDateTime workTime = LocalDateTime.parse(getTagValue("WORK_DTTM", eElement));

                PublicWifiDto publicWifiDto = new PublicWifiDto(distance, manageNo, borough, wifiName, streetAddress, detailAddress, typeOfInstall, companyOfInstall, service, typeOfNet, year, inOrOut, LAT, LNT, workTime);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getTagValue(String tag, Element eElement) {
        String result = "";

        NodeList nodeList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

        result = nodeList.item(0).getTextContent();

        return result;
    }
}

package assignment.web;

import assignment.service.WifiService;
import assignment.web.dto.PublicWifiDto;
import com.google.gson.*;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {

        public static void main(String[] args) throws IOException {

            WifiService wifiService = new WifiService();

            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" +  URLEncoder.encode("sample","UTF-8") );
            urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode("1","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode("5","UTF-8"));

            urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");
            //System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // api 응답에서 wifi 객체 정보만 추출
            String[] result = sb.toString().split("row");
            String jsonStr = result[1].substring(2, result[1].length() - 2);

            JsonArray jsonArray = (JsonArray) JsonParser.parseString(jsonStr);
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject object = (JsonObject) jsonArray.get(i);
                PublicWifiDto publicWifiDto = PublicWifiDto.builder()
                        .manageNo(object.get("X_SWIFI_MGR_NO").getAsString())
                        .borough(object.get("X_SWIFI_WRDOFC").getAsString())
                        .wifiName(object.get("X_SWIFI_MAIN_NM").getAsString())
                        .streetAddress(object.get("X_SWIFI_ADRES1").getAsString())
                        .detailAddress(object.get("X_SWIFI_ADRES2").getAsString())
                        .floor(object.get("X_SWIFI_INSTL_FLOOR").getAsString())
                        .typeOfInstall(object.get("X_SWIFI_INSTL_TY").getAsString())
                        .companyOfInstall(object.get("X_SWIFI_INSTL_MBY").getAsString())
                        .whichService(object.get("X_SWIFI_SVC_SE").getAsString())
                        .typeOfNet(object.get("X_SWIFI_CMCWR").getAsString())
                        .yearOfInstall(object.get("X_SWIFI_CNSTC_YEAR").getAsInt())
                        .inOrOut(object.get("X_SWIFI_INOUT_DOOR").getAsString())
                        .wifiCondition(object.get("X_SWIFI_REMARS3").getAsString())
                        .LAT(object.get("LAT").getAsFloat())
                        .LNT(object.get("LNT").getAsFloat())
                        .workTime(object.get("WORK_DTTM").getAsString())
                        .build();

                // 파싱한 데이터 DTO에 남아서 DB로 저장
                wifiService.wifiSave(publicWifiDto);
            }
        }
}

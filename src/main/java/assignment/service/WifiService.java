package assignment.service;

import assignment.web.dto.PublicWifiDto;

import java.sql.*;

public class WifiService {

    public void wifiSave(PublicWifiDto publicWifiDto) {

        String url = "jdbc:mariadb://localhost:3306/testdb1";
        String dbUserId = "User";
        String dbPassword = "password";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        float distance = 0.0F;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "insert into public_wifi(distance, manage_no, borough, wifi_name, street_address, detail_address, floor, type_of_install, company_of_install, which_service, type_of_net, year_of_install, in_or_out, wifi_condition, lat, lnt, work_time) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, distance);
            preparedStatement.setString(2, publicWifiDto.getManageNo());
            preparedStatement.setString(3, publicWifiDto.getBorough());
            preparedStatement.setString(4, publicWifiDto.getWifiName());
            preparedStatement.setString(5, publicWifiDto.getStreetAddress());
            preparedStatement.setString(6, publicWifiDto.getDetailAddress());
            preparedStatement.setString(7, publicWifiDto.getFloor());
            preparedStatement.setString(8, publicWifiDto.getTypeOfInstall());
            preparedStatement.setString(9, publicWifiDto.getCompanyOfInstall());
            preparedStatement.setString(10, publicWifiDto.getWhichService());
            preparedStatement.setString(11, publicWifiDto.getTypeOfNet());
            preparedStatement.setInt(12, publicWifiDto.getYearOfInstall());
            preparedStatement.setString(13, publicWifiDto.getInOrOut());
            preparedStatement.setString(14, publicWifiDto.getWifiCondition());
            preparedStatement.setFloat(15, publicWifiDto.getLAT());
            preparedStatement.setFloat(16, publicWifiDto.getLNT());
            preparedStatement.setString(17, publicWifiDto.getWorkTime());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally { // 아래는 무조건 실행되어야 하므로
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

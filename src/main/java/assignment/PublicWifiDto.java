package assignment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PublicWifiDto {

    String manageNo; // 관리번호
    String borough; // 자치구
    String wifiName; // 와이파이 명
    String streetAddress; //도로명 주소
    String detailAddress; // 상세 주소
    String floor; // 설치 위치(층)
    String typeOfInstall; // 설치 유형
    String companyOfInstall; // 설치기관
    String whichService; // 서비스 구분
    String typeOfNet; // 망종류
    Integer year; // 설치 년도
    String inOrOut; // 실내외 구분
    String condition; // wifi 접속환경
    Long LAT; // y좌표
    Long LNT; // x좌표
    String workTime; // 작업 일자

    public PublicWifiDto(String manageNo, String borough, String wifiName, String streetAddress, String detailAddress, String floor, String typeOfInstall, String companyOfInstall, String whichService, String typeOfNet, Integer year, String inOrOut, String condition, Long LAT, Long LNT, String workTime) {
        this.manageNo = manageNo;
        this.borough = borough;
        this.wifiName = wifiName;
        this.streetAddress = streetAddress;
        this.detailAddress = detailAddress;
        this.floor = floor;
        this.typeOfInstall = typeOfInstall;
        this.companyOfInstall = companyOfInstall;
        this.whichService = whichService;
        this.typeOfNet = typeOfNet;
        this.year = year;
        this.inOrOut = inOrOut;
        this.condition = condition;
        this.LAT = LAT;
        this.LNT = LNT;
        this.workTime = workTime;
    }
}

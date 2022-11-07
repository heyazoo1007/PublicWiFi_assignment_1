package assignment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PublicWifiDto {

    String distance; // 거리
    String manageNo; // 관리번호
    String borough; // 자치구
    String wifiName; // 와이파이 명
    String streetAddress; //도로명 주소
    String detailAddress; // 상세 주소
    String typeOfInstall; // 설치 유형
    String companyOfInstall; // 설치기관
    String service; // 서비스 구분
    String typeOfNet; // 망종류
    Integer year; // 설치 년도
    String inOrOut; // 실내외 구분
    Long LAT; // x좌표
    Long LNT; // y좌표
    LocalDateTime workTime; // 작업 일자

    public PublicWifiDto(String distance, String manageNo, String borough, String wifiName, String streetAddress, String detailAddress, String typeOfInstall, String companyOfInstall, String service, String typeOfNet, Integer year, String inOrOut, Long LAT, Long LNT, LocalDateTime workTime) {
        this.distance = distance;
        this.manageNo = manageNo;
        this.borough = borough;
        this.wifiName = wifiName;
        this.streetAddress = streetAddress;
        this.detailAddress = detailAddress;
        this.typeOfInstall = typeOfInstall;
        this.companyOfInstall = companyOfInstall;
        this.service = service;
        this.typeOfNet = typeOfNet;
        this.year = year;
        this.inOrOut = inOrOut;
        this.LAT = LAT;
        this.LNT = LNT;
        this.workTime = workTime;
    }
}

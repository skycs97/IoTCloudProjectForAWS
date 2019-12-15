# IoTCloudProjectForAWS
iot 클라우드 플랫폼 기말프로젝트 aws
aws 백엔드 구축을 위한 Lambda함수 모음입니다.

## DeviceControlDataLambda
디바이스 제어이력 조회 (api에 사용)
DynamoDB에 있는 제어이력 테이블에서 정보를 가져옴

입력 예시:
rooturl/devices/{device}/controldata?from="날짜 시간"&to="날짜 시간"
  

## DeviceControlLambda
디바이스 제어 (api에 사용)
디바이스 섀도우의 update를 이용

입력예시:
rooturl/devices/{device}

 body에 다음과 같은 json 추가

 {  
  "tag":{  
    "tagName":"value1",  
    "tagValue":"value2"  
    }  
  }

## DeviceDataGetLambda
디바이스 센서 데이터 조회(api에 사용)
DynamoDB에 있는 센서 테이블에서 정보를 가져옴

입력 예시:
rooturl/devices/{device}/data?from="날짜 시간"&to="날짜 시간"

## DeviceDataUpdate
디바이스 섀도우의 update/documents 를 확인하여 데이터를 테이블에 저장

## DeviceSTateLambda
디바이스 섀도우의 현재 상태를 읽어 리턴해줌

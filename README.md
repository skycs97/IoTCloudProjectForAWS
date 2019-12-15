# IoTCloudProjectForAWS
iot 클라우드 플랫폼 기말프로젝트 aws

# DeviceControlDataLambda
디바이스 제어이력 조회 (api에 사용)
DynamoDB에 있는 제어이력 테이블에서 정보를 가져옴

입력 예시:
 body에 다음과 같은 json 추가
 '''
 {
  "tag":{
    "tagName":"value1",
    "tagValue":"value2"
    }
  }
  '''

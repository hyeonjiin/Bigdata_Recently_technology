# MAP reduce를 활용하여 서울 공기질 분석하기
https://www.kaggle.com/bappekim/air-pollution-in-seoul

위의 링크는 kaggle에서 서울 공기질 데이터셋입니다.

맵리듀스와 분산 클러스터 환경 (google cloud platform의 dataproc) 을 활용하여 서울 공기질을 분석 해보세요.

* 문제 1) 각 지역(station code)별로 평균, 최대, 최소 PM10 측정치 구하기

* 문제 2) PM10, PM2.5 기준으로 공기의 질이 ‘좋음’ 수준이 가장 많이 측정된 지역은 어디인지 찾기

(공기질 좋음 = PM10 기준: 30 이하, PM2.5 기준 15 이하)

* 문제 3) 데이터 변환하기 → 각 <시간, 지역>별로 모든 종류의 측정치 모아서 저장하기

* 문제 4) 시간대를 기준으로 평균 공기질 구하기 (SO2, NO2, CO, O3, PM10, PM2.5 한꺼번에 구하기)

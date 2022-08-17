http://snap.stanford.edu/data/wiki-topcats.html

위의 링크는 위키백과의 하이퍼링크 네트워크를 그래프로 표현한 데이터셋입니다.

wiki-topcats.txt.gz 을 다운받아 압축을 풀면 edge list 형태로 저장된 그래프를 받을 수 있습니다.



본 그래프를 입력으로, 맵리듀스와 분산 클러스터 환경 (google cloud platform의 dataproc) 을 활용하여 다음 task 를 수행해보세요.



Task 1. 그래프에서 중복된 간선과 loop를 모두 제거하는 기능을 MapReduce로 구현하기

    - 간선 (u, v)와 (v, u)는 동일한 간선으로 봅니다.

    - loop는 (u, u)처럼 동일한 정점을 연결하는 간선입니다.

    - 모든 간선 (u, v) 가 u < v 를 만족하도록 저장하세요.

Task 2. 간선 (u, v)에 대해서 다음 조건에 따라 u와 v의 순서를 변경

    - degree(u) < degree(v) 이거나, degree(u) == degree(v) 이면서 id(u) < id(v) 인 경우 → 그대로 둠 (즉, (u, v)를 출력)

	- 그 밖의 경우에는 u와 v의 순서를 바꿈 (즉, (v, u)를 출력)

Task 3. 삼각형 MapReduce 알고리즘에 1) Task 1의 결과 그래프와 2) Task 2의 결과 그래프를 각각 입력했을 때, 성능 비교하기

	- 두 경우에 대해서, 중간에 발생하는 wedge의 수 세보고 비교하기

	- 두 경우에 대해서, 실행시간 비교하기


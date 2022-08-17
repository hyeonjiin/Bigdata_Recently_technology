import random
from collections import Counter
import matplotlib.pyplot as plt
import seaborn as sns
class Reservoir:
    def __init__(self, k):
        self.sampled = []
        self.k = k
        self.cnt = 0

    def put(self, item):
        if self.cnt < self.k:
            self.sampled.append(item)
        else:
            r = random.randint(0,self.cnt)
            if r < self.k:
                self.sampled[r] = item
        self.cnt += 1

L = []
L1=[]
for i in range(10000):
    reservoir = Reservoir(100)  #크기가 100짜리인 reservoir sample 생성
    reservoir3 = []
    for j in range(1000):      #비복원 추출
        reservoir.put(j)
    for j in range(100):       #복원추출
        reservoir2 = Reservoir(1)  #크기가 1짜리인 reservoir sample 생성
        for l in range(1000):
            reservoir2.put(l)
        reservoir3.append(reservoir2.sampled[0])
    L += reservoir.sampled # 비복원 추출하여 뽑은 숫자들 합치기
    L1+=reservoir3         # 복원추출하여 뽑은 숫자들 합치기

#빈도수 그래프 그리기
sns.distplot(x=L,bins=1000,hist=False)
sns.distplot(x=L1,bins=1000,hist=False)
plt.xlabel("(0~999)num")
plt.legend(["sol_1","sol_2"])
plt.show()
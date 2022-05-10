import random
import math
import matplotlib.pyplot as plt
class Bucket:
    def __init__(self, start, end):
        self.start = start
        self.end = end

    def __repr__(self):
        return f"({self.start},{self.end})"


class DGIM:

    def __init__(self):
        self.bucket_tower = [[]]
        self.ts = 0
        self.hap = []

    def put(self, bit):

        if bit == 1:
            b= Bucket(self.ts,self.ts)
            self.bucket_tower[0].insert(0, b)
            layer = 0

            while len(self.bucket_tower[layer]) > 2:
                if len(self.bucket_tower) <= layer+1:
                    self.bucket_tower.append([])

                b1 = self.bucket_tower[layer].pop()
                b2 = self.bucket_tower[layer].pop()
                b1.end = b2.end
                self.bucket_tower[layer+1].insert(0,b1)
                layer += 1
        self.ts+=1

    def put2(self, bit):

        b= Bucket(self.ts,self.ts)
        self.bucket_tower[0].insert(0, b)
        self.hap.append(bit)
        layer = 0

        #print(len(self.bucket_tower),self.bucket_tower,self.hap)

        while len(self.bucket_tower[layer]) > 2:
            if len(self.bucket_tower) <= layer + 1:
                self.bucket_tower.append([])

            b1 = self.bucket_tower[layer].pop()
            b2 = self.bucket_tower[layer].pop()
            #print('sum = ',sum(self.hap[b1.start:b2.end+1]))
            if(sum(self.hap[b1.start:b2.end+1])<=2**(layer+1)):
                b1.end = b2.end
                self.bucket_tower[layer + 1].insert(0, b1)
                layer+=1

            else:
                self.bucket_tower[layer].append(b2)
                self.bucket_tower[layer+1].insert(0, b1)
                layer += 1

        self.ts += 1

    def count(self,k):
        s = self.ts - k

        cnt = 0

        for layer, buckets in enumerate(self.bucket_tower):
            for bucket in buckets:
                if s <= bucket.start:
                    cnt += (1 << layer) #2 ** layer
                elif s <= bucket.end:
                    cnt += (1 << layer) * (bucket.end - s + 1) // (bucket.end - bucket.start + 1)
                    return cnt
                else:
                    return cnt
        return cnt

    def count2(self,k):
        s = self.ts - k
        cnt = 0
        hap = 0
        hap2 = 0
        ssum = 0
        for layer, buckets in enumerate(self.bucket_tower):
            for bucket in buckets:
                hap += bucket.end-bucket.start+1
                if hap == k:
                    ssum += sum(self.hap[bucket.start:bucket.end + 1])
                    return ssum
                elif hap>k:
                    size = hap-hap2
                    choice = size-(hap-k)
                    ssum+=int(sum(self.hap[bucket.start:bucket.end + 1])*(choice/size))
                    return ssum

                hap2= hap
                ssum+=sum(self.hap[bucket.start:bucket.end+1])
        return cnt
#첫번째 방법 :m개 비트를 별도의 스트림으로 보고 각 각에 1,2,4,8 등을 곱하는 방법
dgim1 = DGIM() #첫번째 stream (곱하기 1)
dgim2 = DGIM() #두번째 stream (곱하기 2)
dgim3 = DGIM() #세번째 stream (곱하기 4)
dgim4 = DGIM() #네번째 stream (곱하기 8)
#두번째 방법 : 부분합을 이용하여 구한 합
dgim = DGIM() #stream

sol_1=0 #첫번째 방법으로 구한 k개의 합
bitstream = []
X=[]  #그래프 x축
realnum=[] # 실제로 구합 k개의 합
solution1=[] #그래프 y축(첫번째 방법)
solution2=[] #그래프 y축(두번째 방법)

#0~15사의의 수를 랜덤하게 10000개 dgim에 넣기
for i in range(10000):
    num = random.randint(0 ,15)
    bitstream.append(num)
    dgim.put2(num)
    dgim1.put(int("{0:b}".format(num).zfill(4)[3]))
    dgim2.put(int("{0:b}".format(num).zfill(4)[2]))
    dgim3.put(int("{0:b}".format(num).zfill(4)[1]))
    dgim4.put(int("{0:b}".format(num).zfill(4)[0]))

# 최근 k개의 합 구하기
for k in range(1,2000):
    X.append(k)
    sol_1=dgim1.count(k) + dgim2.count(k)*2 + dgim3.count(k)*4 + dgim4.count(k)*8
    realnum.append(sum(bitstream[-k:]))
    solution1.append(sol_1)
    solution2.append(dgim.count2(k))

plt.plot(X,realnum)
plt.plot(X,solution1)
plt.plot(X,solution2)
plt.xlabel("recently - k")
plt.ylabel("sum")
plt.legend(["real num","first solution","second solution"])
plt.show()
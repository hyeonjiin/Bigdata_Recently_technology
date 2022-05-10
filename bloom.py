import math
import mmh3
import random
class BloomFilter:
    def __init__(self,capacity,fp_prob):
        self.capacity = capacity
        self.fp_prob = fp_prob
        self.bitarray = 0
        self.n_bits = math.ceil(-math.log(fp_prob,math.e) * capacity / (math.log(2,math.e)**2 ))
        self.n_hashs = int(self.n_bits / capacity * math.log(2, math.e))
        #print(self.n_hashs, self.n_bits)
        self.seeds = [random.randint(0,999999) for i in range(self.n_hashs)]

    def put(self,item):
        for i in range(self.n_hashs):
            pos = mmh3.hash(item,self.seeds[i])%self.n_bits
            self.bitarray |= (1 << pos)

    def test(self,item):
        for i in range(self.n_hashs):
            pos = mmh3.hash(item,self.seeds[i])%self.n_bits
            if self.bitarray & (1 << pos) == 0:
                return False
        return True

bloom = BloomFilter(10, 0.5)
p = ['lion','rabbit','elephant','cameleon','giraffe','crocodile','dog','frog','turtle','bee','penguin','gull','parrot','cat','tiger','pig','chicken','spider','fox','monkey']
test = ['lion','act','elephant','actually','crocodile','bean','doea','brown','show','pretty','inform','animal','parrot','zoo','october','almo','clean','again','cat','aaa','bbb','ccc','program','dirty','bread',
        'become','oioi','asbbeio','zest','aiang','dirbe','best','being','book','dexk','bee','tiger','element','paoct','april','january','march','june','more','chicken','fox','money','monkey']
f = 0
for i in p:
    bloom.put(i)
for i in test:
    if i not in p:
        if bloom.test(i) == True:
            f+=1
    elif i in p:
        if bloom.test(i) == False:
            f+=1
print('false positive = ',0.5)
print('오판한 경우 :',f,' 총 데이터의 수 :',len(test),' 데이터를 바탕으로 계산한 false positive :',f/len(test))


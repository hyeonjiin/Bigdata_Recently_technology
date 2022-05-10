import mmh3
import random
import math
import statistics
import matplotlib.pyplot as plt
class FM:
    def __init__(self,domain_size):
        self.bitarray = 0
        self.bitarray=[0,0,0,0,0,0,0,0]
        self.seed=[]
        self.domain_size = domain_size
        self.n_bits = math.ceil(math.log2(domain_size))
        self.mask = (1<<self.n_bits) - 1
        self.seed.append(random.randint(0,9999999))
        self.seed.append(random.randint(0,9999999))
        self.seed.append(random.randint(0, 9999999))
        self.seed.append(random.randint(0, 9999999))
        self.seed.append(random.randint(0, 9999999))
        self.seed.append(random.randint(0, 9999999))
        self.seed.append(random.randint(0, 9999999))
        self.seed.append(random.randint(0, 9999999))
        #self.seed.append(random.randint(0, 9999999))
        self.max=0

    #version1
    def put1(self,item):
        h = mmh3.hash(item,self.seed[0]) & self.mask
        r = 0

        if h==0 : return
        while (h & (1<<r)) == 0: r+=1

        if self.max < r:
            self.max = r

    #version2
    def put2(self,item):
        h = mmh3.hash(item,self.seed[0]) & self.mask
        r = 0

        if h==0 : return
        while (h & (1<<r)) == 0: r+=1

        self.bitarray[0] |= (1<<r)

    def put3(self,item,s):
        h = mmh3.hash(item,self.seed[s]) & self.mask
        r = 0

        if h==0 : return
        while (h & (1<<r)) == 0: r+=1

        self.bitarray[s] |= (1<<r)

    def size_ver1(self):
        R = self.max
        return 2**R

    def size2(self):
        R = 0
        while(self.bitarray[0] & (1<<R) != 0): R+=1

        return 2**R / 0.77351

    def size3(self,s):
        R = 0
        while(self.bitarray[s] & (1<<R) != 0): R+=1

        return 2**R / 0.77351
fm1 = FM(100000)
fm2 = FM(100000)
fm3 = FM(100000)
tset = set()
x=[]
y1=[]
y2=[]
y3=[]

for i in range(100000):

    m1,m2,m3,m4,m5,m6,m7,m8=[],[],[],[],[],[],[],[]
    item = str(random.randint(0,100000))
    fm1.put1(item)
    fm2.put2(item)

    for j in range(0,8):
        fm3.put3(item,j)
        if j%2 == 0:
            m1.append(fm3.size3(j))
        elif j%2==1:
            m2.append(fm3.size3(j))

    avr = (statistics.median(m1)+statistics.median(m2))/2
    tset.add(item)

    x.append(len(tset))
    y1.append(fm1.size_ver1())
    y2.append(fm2.size2())
    y3.append(avr)

plt.scatter(x,y1,color='purple')
plt.scatter(x,y2,color='blue')
plt.scatter(x,y3,color='orange')
plt.plot(x,x,color='r')
plt.show()





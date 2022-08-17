from itertools import combinations
#C2
def make_candidate(freq_itemsets,k):
    candidates = set()
    for itemset1 in freq_itemsets:
        for itemset2 in freq_itemsets:
            union = itemset1 | itemset2
            if len(union) == k:
                for item in union:
                    if union - {item} not in freq_itemsets:
                        break
                else:
                    candidates.add(union)
    return candidates

def filter(candidates, k, s):
    itemset_cnt_k = {}
    with open("groceries.csv","r") as f:
        for line in f:
            basket = line.strip().split(",")
            for comb in combinations(basket, k):
                comb = frozenset(comb)
                if comb in candidates:
                    if comb not in itemset_cnt_k:
                        itemset_cnt_k[comb] = 0
                    itemset_cnt_k[comb] += 1

    freq_items = set(itemset for itemset, cnt in itemset_cnt_k.items() if cnt >= s)
    return freq_items
#Lis : 실제 구매 item셋   fi : Apriori알고리즘을 바탕으로 구한 아이템 set  confidence : 확률값
def association_rule(Lis, fi, confidence):

    #Apriori알고리즘을 바탕으로 구한 아이템들의 부분집합 구하기
    fi=list(fi)
    result=[]
    for i in range(0, len(fi) + 1):
        c = combinations(fi, i)
        result.extend(c)
    fi=set(fi)

    #실제 구매 item셋에 I={i1,i2,….,ik}가 있을 때, 어떠한 아이템 j도 존재하는지 확인
    for i in range(1,len(result)-1):
        A = 0
        I = 0
        diff = fi.difference(result[i])
        for L in Lis:
            if L.issuperset(result[i]) == True:
                A+=1
                if L.issuperset(diff):
                    I+=1

        #confidence값과 비교
        if A==0:
            A=1
        if I/A>confidence:
            print(result[i], "->", diff,": c=",I/A)

#item 개수 구하기
s=100
confidence = 0.5
item_cnt={}
Lis = []
with open("groceries.csv","r") as f:
    for line in f:
        basket = line.strip().split(",")
        Lis.append(set(basket))
        for item in basket:
            if item not in item_cnt:
                item_cnt[item] = 0
            item_cnt[item] += 1
#L1
freq_itemsets = set(frozenset([item]) for item, cnt in item_cnt.items() if cnt >= s)

freq_itemsets_all = freq_itemsets.copy()
k=2

while len(freq_itemsets) > 0:
    candidates = make_candidate(freq_itemsets, k)
    freq_itemsets = filter(candidates,k,s)
    freq_itemsets_all |= freq_itemsets
    print(k,len(candidates),len(freq_itemsets))
    k=k+1

freq_itemsets_all = sorted(freq_itemsets_all,key=len)
for fi in freq_itemsets_all:
    association_rule(Lis, fi, confidence)

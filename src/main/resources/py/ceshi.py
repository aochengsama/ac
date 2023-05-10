import random
import sys


jizun = 6
allp= 3300
singlep = []
singlelow = []
singleup = []


xall = []
def func(jizun,allp,singlep,singlelow,singleup):
    for i in range(jizun):
        x = random.uniform(singlelow[i], singleup[i])
        x2 = singlep[i] - x
        xall.append(x2)

if __name__ == '__main__':
    jizun=int(sys.argv[1])
    allp=int(sys.argv[2])
    a = sys.argv[3].split(",")
    b = sys.argv[4].split(",")
    c = sys.argv[5].split(",")
    for i in range(6):
        singlep.append(int(a[i]))
        singlelow.append(int(b[i]))
        singleup.append(int(c[i]))
    func(jizun,allp,singlep,singlelow,singleup)
    print(xall)


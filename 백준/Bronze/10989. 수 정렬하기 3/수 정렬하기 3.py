import sys

ipt = sys.stdin.readline

n = int(ipt())
li = [0] * 10001

for i in range(n):
    li[int(ipt())] += 1

for i in range(10001):
    if li[i] == 0:
        continue
    for j in range(li[i]):
        print(i)
import sys

ipt = sys.stdin.readline

n = int(ipt())
li = list()

for i in range(n):
    li.append(int(ipt()))

li.sort()

for i in li:
    print(i)
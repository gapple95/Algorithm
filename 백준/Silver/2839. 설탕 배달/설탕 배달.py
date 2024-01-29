n = int(input())

ans = 0

while n>=0:
    if(n % 5 == 0):
        ans += n // 5
        print(ans)
        exit()
    n -= 3
    ans += 1
print(-1)
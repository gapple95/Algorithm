import sys

class Queue:
    def __init__(self):
        self.queue = [0] * 2000001
        self.front = 0
        self.back = 0

    def push(self, num):
        self.queue[self.back] = num
        self.back += 1

    def pop(self):
        if self.front == self.back:
            return -1
        else:
            p = self.queue[self.front]
            self.front += 1
            return p

    def size(self):
        return self.back - self.front

    def empty(self):
        if self.front == self.back:
            return 1
        else:
            return 0

    def Front(self):
        if self.front == self.back:
            return -1
        else:
            return self.queue[self.front]

    def show(self):
        return self.queue

    def Back(self):
        if self.front == self.back:
            return -1
        else:
            return self.queue[self.back-1]

queue = Queue()

n = int(sys.stdin.readline())

for i in range(1, n+1):
    queue.push(i)

count = 0
while not queue.size() == 1:
    if count % 2 == 0:
        queue.pop()
    else:
        p = queue.pop()
        queue.push(p)
    # print(queue.show())
    count += 1

print(queue.pop())
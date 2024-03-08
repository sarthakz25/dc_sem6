from heapq import heapify, heappop, heappush

numOfProcesses = int(input("enter the no. of processes: "))
numCS = int(input("enter no. of process which want to enter the critical section: "))

lists = []
heapify(lists)
ts = [0] * numOfProcesses

dictt = {}

for i in range(numCS):
    timestamp = int(input("\nenter the timestamp: "))
    process = int(input("enter process no.: "))
    ts[process - 1] = timestamp
    dictt[timestamp] = process
    heappush(
        lists,
        (
            timestamp,
            process,
        ),
    )

sorted_ts = sorted(dictt.items(), key=lambda x: x[0])

print("\nprocess and timestamp:")
for i, t in enumerate(ts):
    print(f"{i + 1} {t}")

for SenderTs, SenderId in sorted_ts:
    print(f"\nrequest from process: {SenderId}")
    for RecvProcessIndex, RecvTimestamp in enumerate(ts):
        if SenderId != (RecvProcessIndex + 1):
            if RecvTimestamp > ts[SenderId - 1] or RecvTimestamp == 0:
                print(f"{RecvProcessIndex + 1} replied.")
            else:
                print(f"{RecvProcessIndex + 1} deferred.")
print()
while lists:
    t, s = heappop(lists)
    print(f"process {s} has entered critical section.")

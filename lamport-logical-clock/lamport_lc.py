class Process:
    def __init__(self, id, drift_time) -> None:
        self.process_id = id
        self.drift_time = drift_time
        self.timestamps = [i * drift_time for i in range(0, 11)]
        self.events = [i * drift_time for i in range(0, 11)]
        print(f"process {id} increments its clock by {drift_time}")

    def receiveMessage(self, index, time_stamp) -> None:
        self.timestamps[index] = max(self.timestamps[index], time_stamp)


print(f"each process starts at time 0")
p1 = Process(1, 6)
p2 = Process(2, 8)
p3 = Process(3, 10)
num_events = 10

m1 = [[0] * 10 for i in range(0, 10)]
m1[1][2] = 1
m1[9][8] = -1

m2 = [[0] * 10 for i in range(0, 10)]
m2[3][4] = 1
m2[7][6] = -1

clock_adjustments = []

for i in range(0, num_events):
    for j in range(0, num_events):
        if m1[i][j] == 1:
            if (
                f"Clock Adjustments (P1, P2): {(p1.timestamps[i], p2.timestamps[j])}"
                not in clock_adjustments
            ):
                p2.receiveMessage(j, p1.timestamps[i + 1])
                clock_adjustments.append(
                    f"Clock Adjustments (P1, P2): {(p1.timestamps[i], p2.timestamps[j])}"
                )

        if m1[i][j] == -1:
            if (
                f"Clock Adjustments (P1, P2): {(p1.timestamps[i], p2.timestamps[j])}"
                not in clock_adjustments
            ):
                p1.receiveMessage(i, p2.timestamps[j + 1])
                clock_adjustments.append(
                    f"Clock Adjustments (P1, P2): {(p1.timestamps[i], p2.timestamps[j])}"
                )

        for k in range(0, num_events):
            if m2[j][k] == 1:
                if (
                    f"Clock Adjustments (P2, P3): {(p2.timestamps[j], p3.timestamps[k])}"
                    not in clock_adjustments
                ):
                    p3.receiveMessage(k, p2.timestamps[j + 1])
                    clock_adjustments.append(
                        f"Clock Adjustments (P2, P3): {(p2.timestamps[j], p3.timestamps[k])}"
                    )

            if m2[j][k] == -1:
                if (
                    f"Clock Adjustments (P2, P3): {(p2.timestamps[j], p3.timestamps[k])}"
                    not in clock_adjustments
                ):
                    p2.receiveMessage(j, p3.timestamps[k + 1])
                    clock_adjustments.append(
                        f"Clock Adjustments (P2, P3): {(p2.timestamps[j], p3.timestamps[k])}"
                    )

for i in clock_adjustments:
    print(i)

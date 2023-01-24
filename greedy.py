def partition(v, w, low, high):

    pivot = v[high]/w[high]
 
    i = low - 1
 
    for j in range(low, high):
        if v[j]/w[j] <= pivot:
            i = i + 1
            v[i], v[j] = v[j], v[i]
            w[i], w[j] = w[j], w[i]
 
    v[i + 1], v[high] = v[high], v[i + 1]
    w[i + 1], w[high] = w[high], w[i + 1]
    return i + 1


def quickSort(v, w, low, high):
    if low < high:
        pi = partition(v, w, low, high)
        quickSort(v, w, low, pi - 1)
        quickSort(v, w, pi + 1, high)

def greedy(v, w, b):
    quickSort(v, w, 0, len(v)-1)
    total_value = 0
    total_weight = 0

    for i in range(len(v)-1, -1, -1):
        if total_weight + w[i] <= b:
            total_weight += w[i]
            total_value += v[i]
    return total_value

v = [30, 40, 50, 60, 70]
w = [50, 10, 20, 30, 60]
print(greedy(v,w,100))
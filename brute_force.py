def brute_force(w, v, n, b):
    if n == 0:
        return 0
    else:
        A = brute_force(w, v, n-1, b)

        if w[n] > b:
            return A
        else:
            B = v[n] + brute_force(w, v, n-1, b - w[n])
            return max(A, B)


b = 100
n = 4
v = [30, 40, 50, 60, 70]
w = [50, 10, 20, 30, 60]
print(brute_force(w, v, n, b))
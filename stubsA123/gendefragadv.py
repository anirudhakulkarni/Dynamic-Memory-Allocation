'''
@anirudha
12:45:28 23-11-2020
'''
import numpy as np
f = open("test51.txt", "w")
f.write("100\n")
for tests in range(1,101):
    arr = np.array(np.random.choice(tests*10, tests*100)) #Max Limit, Test cases
    f.write(str(tests*500) + '\n') #Memory
    f.write(str(tests*100)+'\n') #test cases
    for i in range(tests*100):
        k = np.array(np.random.choice(3,1, p=[0.49, 0.49, 0.02]))[0]#0.2 probability for defrag
        if k==0:
            f.write("Allocate "+str(arr[i]) +"\n")
        elif k==1:
            f.write("Free "+str(arr[i]) + "\n")
        else:
            f.write("Defragment 0\n")
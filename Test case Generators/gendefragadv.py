'''
@anirudha
12:45:28 23-11-2020
'''
import numpy as np
f = open("test101.txt", "w")
f.write("200\n")
for tests in range(1,201):
    arr = np.array(np.random.choice(tests*10, tests*300)) #Max Limit, Test cases
    f.write(str(tests*50000) + '\n') #Memory
    f.write(str(tests*300)+'\n') #test cases
    for i in range(tests*300):
        k = np.array(np.random.choice(3,1, p=[0.7, 0.28, 0.02]))[0]#0.2 probability for defrag
        if k==0:
            f.write("Allocate "+str(arr[i]) +"\n")
        elif k==1:
            f.write("Free "+str(arr[i]) + "\n")
        else:
            f.write("Defragment 0\n")
    print("test no "+str(tests) +" is over.")
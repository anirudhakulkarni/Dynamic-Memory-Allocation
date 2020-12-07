'''
@anirudha
16:34:45 17-11-2020
'''
import numpy as np
f = open("test17.txt", "w")
f.write("200\n")
for tests in range(1,201):
    arr = np.array(np.random.choice(tests*10, tests*100)) #Max Limit, Test cases
    f.write(str(tests*200) + '\n') #Memory
    f.write(str(tests*100)+'\n') #test cases
    for i in range(tests*100):
        k = np.random.randint(2, size=1)[0]
        if k==1:
            f.write("Allocate "+str(arr[i]) +"\n")
        else:
            f.write("Free "+str(arr[i]) + "\n")

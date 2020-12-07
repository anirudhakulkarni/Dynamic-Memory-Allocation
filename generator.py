'''
@anirudha
12:24:45 17-11-2020
'''
import numpy as np
upperlimit=2000
commands=500000
memory_size=1000000
arr = np.array(np.random.choice(upperlimit, commands))
f = open("test17.txt", "w")
f.write("1\n")
f.write(str(memory_size)+"\n")
f.write(str(commands)+"\n")
for i in range(commands):
    k = np.random.randint(2, size=1)[0]
    if k==1:
        f.write("Allocate "+str(arr[i]) +"\n")
    else:
        f.write("Free "+str(arr[i]) + "\n")
import numpy as np
f = open("test21.txt", "w")
f.write("150\n")
for tests in range(1,151):
    arr = np.array(np.random.choice(tests*10, tests*120)) #Max Limit, Test cases
    f.write(str(tests*500) + '\n') #Memory
    f.write(str(tests*120)+'\n') #test cases
    for i in range(tests*120):
        k = np.random.randint(5, size=1)[0]
        if k==0 or k==1:
            f.write("Allocate "+str(arr[i]) +"\n")
        elif k==2 or k==3:
            f.write("Free "+str(arr[i]) + "\n")
        else:
            f.write("Defragment\n")
    print(tests)
import numpy as np
f = open("test58.txt", "w")
f.write("150\n")
count1 = 0
count2=0
count3=0
count4=0
for tests in range(1,151):
    arr = np.array(np.random.choice(tests*500, tests*100)) #Max Limit, Test cases
    f.write(str(tests*500) + '\n') #Memory
    f.write(str(tests*100)+'\n') #test cases
    for i in range(tests*100):
        count2+=1
        k = np.array(np.random.choice(3,1, p=[0.80, 0.18, 0.02]))[0]
        
        if k==0:
            count3+=1
            f.write("Allocate "+str(arr[i] // 50) +"\n")
            count4+=1
        elif k==1:
            count3+=1
            f.write("Free "+str(arr[i] // 5) + "\n")
            count4+=1
        else:
            count3+=1
            f.write("Defragment 0\n")
            count4+=1
        count1 = count1+1
print(count1)
print(count2)
print(count3)
print(count4)
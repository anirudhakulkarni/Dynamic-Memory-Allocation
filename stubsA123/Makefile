all:
	javac *.java
clean:
	rm *.class
test51:
	javac *.java
	./runAVL.sh test51.txt aans51_ani.txt
	rm *.class
	python compare.py aans51_ani.txt bans51_ani.txt
test58:
	javac *.java
	./runAVL.sh test58.txt aans58_ani.txt
	rm *.class
	python compare.py aans58_ani.txt bans58_ani.txt
test59:
	javac *.java
	./runAVL.sh test59.txt aans59_ani.txt
	rm *.class
	python compare.py aans59_ani.txt bans59_ani.txt
test60:
	javac *.java
	./runAVL.sh test60.txt aans60_ani.txt
	rm *.class
	python compare.py aans60_ani.txt bans60_ani.txt
test101:
	javac *.java
	./runAVL.sh test101.txt aans101_ani.txt
	rm *.class
	python compare.py aans101_ani.txt bans101_ani.txt
gen51:
	javac *.java
	./run.sh test51.txt bans51_ani.txt
gen58:
	javac *.java
	./run.sh test58.txt bans58_ani.txt
gen59:
	javac *.java
	./run.sh test59.txt bans59_ani.txt
gen60:
	javac *.java
	./run.sh test60.txt bans60_ani.txt
gen101:
	javac *.java
	./run.sh test101.txt bans101_ani.txt
testall:
	make gen60
	make gen59
	make gen58
	make test60
	make test58
	make test59
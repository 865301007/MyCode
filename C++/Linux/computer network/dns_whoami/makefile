objects=main.o checker.o responser.o 
dns_whoami:$(objects)
	g++ -o dns_whoami $(objects)  -lpthread
main.o:main.cpp checker.h responser.h
	g++ -O2 -c main.cpp
checker.o:checker.cpp checker.h
	g++ -O2 -c checker.cpp
responser.o:responser.cpp responser.h
	g++ -O2 -c responser.cpp
clean:
	rm -r dns_whoami $(objects)
OBJECTS=log.o main.o command_resolver.o file_handler.o shrinker.o
C_OPTION=-c
LIB=-L/usr/lib/ -lboost_program_options-gcc41-mt
dns_shrink:$(OBJECTS)
	g++ -o $@  $(OBJECTS) $(LIB)
main.o:main.cpp command_resolver.h
	g++ $(C_OPTION) main.cpp
command_resolver.o:command_resolver.cpp log.h
	g++ $(C_OPTION) command_resolver.cpp
log.o:log.cpp
	g++ $(C_OPTION) log.cpp
file_handler.o:log.h view_node.h file_handler.cpp
	g++ $(C_OPTION) file_handler.cpp
shrinker.o:shrinker.cpp view_node.h
	g++ $(C_OPTION) shrinker.cpp
clean:
	rm -f $(OBJECTS) dns_shrink
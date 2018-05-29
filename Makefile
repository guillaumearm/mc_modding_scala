GRADLEW=./gradlew
RIMRAF=rm -rf
ECHO=@echo

help:
	${ECHO} "Usage: make [command]\n\
	 \n\
	Available commands:\n\
	    help: \t\tdisplay this help\n\
	    build: \t\tbuild the mod\n\
	    check: \t\tcheck the mod\n\
	    clean: \t\tclean built app\n\
	    fclean: \t\tclean all project\n\
	    dev: \t\tprepare dev environment\n\
	    redev: \t\tfull clean then prepare dev environment\n\
	    runClient: \t\trun minecraft client\n\
	    runServer: \t\trun minecraft server"

build:
	${GRADLEW} build

check:
	${GRADLEW} check

cleanBuild:
	${RIMRAF} build out

clean: cleanBuild
	${GRADLEW} clean

fclean: clean
	${RIMRAF} ${WILDCARD}.iml ${WILDCARD}.ipr ${WILDCARD}.iws run

dev:
	${GRADLEW} --refresh-dependencies clean setupDecompWorkspace setupDevWorkspace idea ideaModule genIntellijRuns check

runClient:
	${GRADLEW} runClient

runServer:
	${GRADLEW} runServer

run: runClient

rerun: clean build run

re: clean build

redev: fclean dev

.PHONY: help build cleanBuild check clean fclean dev runClient runServer re redev run rerun



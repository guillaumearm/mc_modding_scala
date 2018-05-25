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
	    dev: \t\tprepare dev environment\n\
	    runClient: \t\trun minecraft client\n\
	    runServer: \t\trun minecraft server"

build: 
	${GRADLEW} build

check:
	${GRADLEW} check

clean:
	${GRADLEW} clean && ${RIMRAF} build out

dev:
	${GRADLEW} --refresh-dependencies clean setupDecompWorkspace setupDevWorkspace idea ideaModule genIntellijRuns check

runClient:
	${GRADLEW} runClient

runServer:
	${GRADLEW} runServer

.PHONY: help build check dev runClient runServer



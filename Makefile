GRADLEW=./gradlew


dev:
	${GRADLEW} --refresh-dependencies clean setupDecompWorkspace setupDevWorkspace idea ideaModule genIntellijRuns check

runClient:
	${GRADLEW} runClient

runServer:
	${GRADLEW} runServer

.PHONY: dev runClient runServer



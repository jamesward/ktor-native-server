# Ktor Native Server

> Currently only contains a Linux target

Run locally:
```
./gradlew runDebugExecutable
```

Create & run native executable:
```
./gradlew linkReleaseExecutable
build/bin/linuxX64/releaseExecutable/ktor-native-server.kexe
```

Build a container:
```
./gradlew jibDockerBuild --image=ktor-native-server
```

Run the container:
```
docker run -it -p8080:8080 ktor-native-server
```
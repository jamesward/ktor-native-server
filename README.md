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


```
ab -c 8 -n 1000000 http://localhost:8080/
```
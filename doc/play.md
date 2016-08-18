

```bash
activator new hello-play

activator run

# Use ~run TO COMPILE CHANGED FILES IMMEDIATELY
~run

sbt> clean
sbt> compile
sbt> run
```

A **Play application** is a directory on the filesystem that contains a certain structure that Play uses to find configuration, code, and any other resources it needs.
* app—Application source code
* conf—Configuration files and data
* project—Project build scripts
* public—Publicly accessible static files
* test—Automated tests


```bash
activator new hello-play
activator new hello-play2 play-scala

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



There is a major implications for being able to test your web application without running a server.
```bash
scala> views.html.hello.render("Play!")

```
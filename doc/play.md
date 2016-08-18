

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

Test post 

```bash
curl 'http://localhost:9000/subscribe/text' -H 'Content-Type:
text/plain;charset=UTF-8' --data-binary 'userId@gmail.com'


curl 'http://localhost:9000/subscribe/json' -H 'Content-Type: text/json' --data-binary '{"emailId": "userId@gmail.com", "interval": "month"}'

curl 'http://localhost:9000/subscribe/jsonparser' -H 'Content-Type: text/json' --data-binary '{"emailId": "userId@gmail.com", "interval": "month"}'

curl 'http://localhost:9000/test/echo' -H 'Content-Type: text/plain' --data-binary '{"emailId": "userId@gmail.com", "interval": "month"}'
```
This app can be used for testing various issues in runtime environments.

# Installation

. Download JAR from bin/ directory
. Run it with Java 8 (Tomcat is integrated)

# Usage

The following endpoints can be used:

* `GET /command?cmd=COMMAND` where `COMMAND` is a command line script to be executed (e.g. `ls`)
* `GET/POST/PUT/DELETE/OPTIONS/PATCH/HEAD /input` logs request headers, parameters and body to STDOUT
* `GET /log?message=MESSAGE` where `MESSAGE` is any text message that will be logged to STDOUT
* `GET /request?url=URL` where `URL` is an URL including http or https protocol (e.g. https://github.com). To this URL a GET request will be done. The response body of this test request is also returned to the original request.
* `POST /upload` can be used to test a file upload. The file is written to a configurable path. Params:
** `file`: The multipart file
** `path`: A relative or absolute path to the directory where the file shall be written
** `name`: The file name
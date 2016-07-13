# tl;dr

google login plugin preinstall jenkins image.


# Prerequisite

google oAuth2 is already set.
ref:https://wiki.jenkins-ci.org/display/JENKINS/Google+Login+Plugin


# Build your image

This onbuild image require `plugins.txt` and `custom.groovy`
Create an empty file, even if you do not need additional plugins.(custom.grooby too)

## Dockerfile

~~~Dockerfile
FROM sho2010/jenkins-google-login:onbuild

MAINTANER YOUR_NAME

# do_something
~~~

## preinstall plugins & init script

`plugins.txt`

~~~
# preinstall git plugin
git
~~~

## Build

~~~sh
$ docker build -t jenkins-gl .
~~~

# run & require ENV variables

~~~sh
docker run -d \
           -p 8080:8080 -p 50000:50000 \
           -e GOOGLE_APP_CLIENT_ID="YOUR_CLIENT_ID" \
           -e GOOGLE_APP_SERCRET="YOUR_SECRET" \
           -e GOOGLE_ACCOUNT_DOMAIN="YOUR_DOMAIN" \
           jenkins-gl
~~~

# Quick guide

1. download latest [release](https://github.com/kalmikko/ot-harjoitustyo/releases)

2. download the jar file

3. run the jar file

## commands

to run the jar file, go to the file path in terminal and type "java -jar FILENAME"

to generate jacoco test report, download the whole project, go to Digilog folder in terminal and type "mvn jacoco:report"

for checkstyle do the same, but use command "mvn jxr:jxr checkstyle:checkstyle"

for javadoc "mvn javadoc:javadoc"

to generate the jar file "mvn package"

to run tests "mvn test"

## functionality
When first time opening the jar file, the program will make the sql database for you. Then you can choose between some pre-built profiles, or a blank profile after which you will have to build a profiole for yourself.

Basic commands can be listed after running the jar file with a terminal. The basic commands are:

  show media all - list all additions

  info all - get all profile info

  add mediatype MEDIATYPE_NAME - add a mediatype
  add genre GENRE_NAME - add a genre
  
After adding some mediatypes and genres (or if you use one of the pre-built profiles) the commands will update as
  
  show media all - list all additions
  show media book - list book additions

  info all - get all profile info

  add book BOOK_NAME - add book addition
  add mediatype MEDIATYPE_NAME - add a mediatype
  add genre GENRE_NAME - add a genre

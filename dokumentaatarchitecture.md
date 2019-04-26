# Architecture

## sql

Digilog is based around a SQL database. It has additions, to which are connected medias and to which are connected genres and types. All of the tables are normalized, so that the data is easier to handle.

![sqltables](https://github.com/kalmikko/ot-harjoitustyo/blob/master/dokumentaatio/sqltablesHD.png)

## application logic

The application has a class that initializes the program, using springframework. It then runs the user interface class, which uses the sql class to save, modify and show different data and the user logic class for more complicated processes, like filtering data.

![packagediagram](https://github.com/kalmikko/ot-harjoitustyo/blob/master/dokumentaatio/packagediagram.png)

## weaknesses

The class structure does not follow OOP principles very well. There is only one class to interact with the sql database, and the role of the OOP class structure is taken over by the SQL database tables.

## Test coverage

using jacoco to generate test coverage reports, excluding the startup class and the text interface class, the program gets the following row coverage.

![testcoverage](https://github.com/kalmikko/digilog/blob/master/dokumentaatio/testcoverage.png)

# running tests

running the tests expects that the SQL database has no empty tables, so running the program once and adding at least one addition should be done before running the tests.

# problems

the digilog.user package test coverage is low, because mostly it is used for the text interface logic which is hard and unnecessary to test, if I were to expand the logic class more I could do better tests to the whole package.

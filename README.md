# doc-stats
Generate some statistics about text documents.

## Build
This is a Maven project, so simply run:

mvn install

in the main project directory.

## Usage
These methods can be used by an external project by passing in a filepath to the constructor.
To generate all the statistics for a file in the command line, run:

java -jar DocumentStats-1.0.jar [file]

## Assumptions

* Case is insensitive, count upper and lower case letters together
* Average character count returns as a string - while you could return a rounded double it's not precise
* Trailing lines without a line break aren't included - so an empty file has no lines
* Most common *letter*, so we can ignore numbers and other symbols (including extended Unicode points)

## Todo

* Tag

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

## Design decisions and assumptions

* The file isn't read until the statistic methods run, as it may have changed since the constructor was run
* The requirements ask for code that is easy to extend to other statistics, so the getCharacterCount has been split into a separate method. This is a useful building block for creating other statistics, and worth the performance impact.
* Case is insensitive, so count upper and lower case letters together
* The average character count returns as a string - while you could return a rounded double it's not precise
* Trailing lines without a line break aren't included - so an empty file has no lines
* The requirement is for the most common *letter*, so we can ignore numbers and other symbols (including extended Unicode points)

# doc-stats
Generate some statistics about text documents.

## Build
tbd

## Usage
java -jar DocumentStats-1.0.jar [file]

## Assumptions

* ASCII only (for now?)
* Case is insensitive, count upper and lower case letters together
* Average character count returns as a string - while you could return a rounded double it's not precise
* Trailing lines without a line break aren't included - so an empty file has no lines
* Most common *letter*, so we can ignore numbers and other symbols (including extended Unicode points)

## Todo

* Write build instructions
* Really sort out exceptions
* What happens if you run some Chinese text or emojis or something
* Tag

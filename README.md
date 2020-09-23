# doc-stats
Generate some statistics about text documents.

## Build
tbd

## Assumptions

* ASCII only (for now?)
* Case is insensitive, count upper and lower case letters together
* Average character count returns as a string - while you could return a rounded double it's not precise
* Trailing lines without a line break aren't included - so an empty file has no lines
* Most common *letter*, so we can ignore numbers and other symbols

** Todo

* Write build instructions
* Decide what types to return

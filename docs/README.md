# Nelson User Guide

Nelson is a chess-themed task assistant that stores ToDos, deadlines, and events. It remembers your tasks between
runs and provides both a command-line interface and a JavaFX graphical interface.

## Quick start

1. Ensure Java 25 is installed.
2. Run `java -jar nelson-all.jar` from the folder containing the JAR file.
3. Enter one command at a time using the formats below.

Dates must use the `yyyy-MM-dd` format, such as `2026-09-10`.

## Adding tasks

- `todo DESCRIPTION` adds a task without a date.
- `deadline DESCRIPTION /by DATE` adds a task due on a date.
- `event DESCRIPTION /from START_DATE /to END_DATE` adds an event spanning two dates.

Examples:

```text
todo read book
deadline submit report /by 2026-09-10
event project meeting /from 2026-09-08 /to 2026-09-08
```

## Viewing and searching

- `list` displays every task.
- `find KEYWORD` displays tasks whose descriptions contain the keyword. Matching ignores letter case.

Example: `find book`

## Sorting tasks

Use `sort` to arrange all tasks alphabetically by description. Sorting ignores letter case, keeps tasks with equal
descriptions in their previous order, displays the new order, and saves it for the next run.

Example:

```text
sort
```

## Updating task status

- `mark NUMBER` marks the numbered task as done.
- `unmark NUMBER` marks the numbered task as not done.

Task numbers are shown by `list`, `find`, and `sort`.

## Deleting tasks

Use `delete NUMBER` to remove the numbered task from the list.

Example: `delete 2`

## Exiting

Use `bye` to close Nelson. Task changes are saved automatically as they are made.

# Nelson UI Test Plan

Run:

```powershell
python skills/test-ui/scripts/run_ui_tests.py
```

## Test case: Welcome and resignation
**Aim:** Verify that Nelson greets the user and exits after bye.
**Input:**
```text
bye
```
**Expected output:**
```text
System booting...
       _   __     __
       / | / /___ / /________  ____
      /  |/ / __ \/ / ___/ __ \/ __ \
     / /|  /  __/ / (__  ) /_/ / / / /
    /_/ |_/\___/_/_/____/\____/_/ /_/

    ____________________________________________________________
    Molo! I have a surprise for you. Your move!
    Type your move, or are you just going to let your time run out?
    ____________________________________________________________
    ____________________________________________________________
    Resigning already? Pathetic. I win.
    ____________________________________________________________
```

## Test case: Mark and unmark a task
**Aim:** Verify task status changes and numbered list formatting.
**Input:**
```text
read book
return book
mark 1
mark 2
unmark 2
list
bye
```
**Expected output:**
```text
System booting...
       _   __     __
       / | / /___ / /________  ____
      /  |/ / __ \/ / ___/ __ \/ __ \
     / /|  /  __/ / (__  ) /_/ / / / /
    /_/ |_/\___/_/_/____/\____/_/ /_/

    ____________________________________________________________
    Molo! I have a surprise for you. Your move!
    Type your move, or are you just going to let your time run out?
    ____________________________________________________________
    ____________________________________________________________
    A weak opening, but I have added: read book. Defend yourself.
    ____________________________________________________________
    ____________________________________________________________
    A developing move. I have added: return book. Defend yourself.
    ____________________________________________________________
    ____________________________________________________________
    You completed a task? Do not celebrate. I am already calculating 15 moves ahead.
    [X] read book
    ____________________________________________________________
    ____________________________________________________________
    You completed a task? Do not celebrate. I am already calculating 15 moves ahead.
    [X] return book
    ____________________________________________________________
    ____________________________________________________________
    A blunder. Taking back your move? Pathetic. I have marked this as not done yet:
    [ ] return book
    ____________________________________________________________
    ____________________________________________________________
    Evaluate your board state. Here are your tasks:
    1.[T][X] read book
    2.[T][ ] return book
    ____________________________________________________________
    ____________________________________________________________
    Resigning already? Pathetic. I win.
    ____________________________________________________________
```

## Test case: Add typed tasks
**Aim:** Verify ToDo, Deadline, and Event parsing, confirmation, and list formatting.
**Input:**
```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
```
**Expected output:**
```text
System booting...
       _   __     __
       / | / /___ / /________  ____
      /  |/ / __ \/ / ___/ __ \/ __ \
     / /|  /  __/ / (__  ) /_/ / / / /
    /_/ |_/\___/_/_/____/\____/_/ /_/

    ____________________________________________________________
    Molo! I have a surprise for you. Your move!
    Type your move, or are you just going to let your time run out?
    ____________________________________________________________
    ____________________________________________________________
    A weak move. I have added this trivial task to your board:
    [T][ ] borrow book
    Now you have 1 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
    A weak move. I have added this trivial task to your board:
    [D][ ] return book (by: Sunday)
    Now you have 2 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
    A weak move. I have added this trivial task to your board:
    [E][ ] project meeting (from: Mon 2pm to: 4pm)
    Now you have 3 tasks in the list.
    ____________________________________________________________
    ____________________________________________________________
    Evaluate your board state. Here are your tasks:
    1.[T][ ] borrow book
    2.[D][ ] return book (by: Sunday)
    3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
    ____________________________________________________________
    ____________________________________________________________
    Resigning already? Pathetic. I win.
    ____________________________________________________________
```

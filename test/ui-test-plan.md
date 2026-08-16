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
    1.[X] read book
    2.[ ] return book
    ____________________________________________________________
    ____________________________________________________________
    Resigning already? Pathetic. I win.
    ____________________________________________________________
```

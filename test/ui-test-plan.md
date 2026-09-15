# UI Test Plan

This file records the command-line UI test cases for the project. Run the cases from top to bottom. A test session stops at the first failure.

## Test cases

### TC-001: Save added and updated tasks

- **Aim:** Verify that adding each task type and marking a task produces the expected UI responses and saves the latest task state to the data file.
- **Precondition:** `data/mimimeow.txt` does not exist.
- **Allowed differences:** Platform-specific line endings and trailing whitespace in the decorative banner or blank output lines may be ignored.
- **Inputs:**
  ```text
  todo compare A | B \ C
  deadline return book /by June 6th
  event project meeting /from Aug 6th 2pm /to Aug 6th 4pm
  mark 1
  bye
  ```
- **Expected console output:**
  ```text
        ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )
   ███╗   ███╗██╗███╗   ███╗██╗███╗   ███╗███████╗ ██████╗ ██╗    ██╗
   ████╗ ████║██║████╗ ████║██║████╗ ████║██╔════╝██╔═══██╗██║    ██║
   ██╔████╔██║██║██╔████╔██║██║██╔████╔██║█████╗  ██║   ██║██║ █╗ ██║
   ██║╚██╔╝██║██║██║╚██╔╝██║██║██║╚██╔╝██║██║╚══╝  ██║   ██║██║███╗██║
   ██║ ╚═╝ ██║██║██║ ╚═╝ ██║██║██║ ╚═╝ ██║███████╗╚██████╔╝╚███╔███╔╝
   ╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚══════╝ ╚═════╝  ╚══╝╚══╝
        ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )

      Hello! I'm MimiMeow.
      What can I do for you?
      ────────────────────────────────────────────────────────────

      ────────────────────────────────────────────────────────────
      (^._.^) meows: Got it! Meow'hv added this task:
      [T][ ] compare A | B \ C
      NOW you have 1 task in the list.
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      (^._.^) meows: Got it! Meow'hv added this task:
      [D][ ] return book (by: June 6th)
      NOW you have 2 tasks in the list.
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      (^._.^) meows: Got it! Meow'hv added this task:
      [E][ ] project meeting (from: Aug 6th 2pm to: Aug 6th 4pm)
      NOW you have 3 tasks in the list.
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      Nice! Meow've marked this task as done:
      [T][X] compare A | B \ C
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      Bye. Hope to see you again soon!
      ────────────────────────────────────────────────────────────
  ```

- **Expected `data/mimimeow.txt` contents:**
  ```text
  T | 1 | compare A \| B \\ C
  D | 0 | return book | June 6th
  E | 0 | project meeting | Aug 6th 2pm | Aug 6th 4pm
  ```

### TC-002: Load saved tasks after restart

- **Aim:** Verify that a new MimiMeow session loads every saved task and restores its completion status.
- **Precondition:** Run TC-001 first and leave its generated `data/mimimeow.txt` unchanged.
- **Allowed differences:** Platform-specific line endings and trailing whitespace in the decorative banner or blank output lines may be ignored.
- **Inputs:**
  ```text
  list
  bye
  ```
- **Expected console output:**
  ```text
        ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )
   ███╗   ███╗██╗███╗   ███╗██╗███╗   ███╗███████╗ ██████╗ ██╗    ██╗
   ████╗ ████║██║████╗ ████║██║████╗ ████║██╔════╝██╔═══██╗██║    ██║
   ██╔████╔██║██║██╔████╔██║██║██╔████╔██║█████╗  ██║   ██║██║ █╗ ██║
   ██║╚██╔╝██║██║██║╚██╔╝██║██║██║╚██╔╝██║██╔══╝  ██║   ██║██║███╗██║
   ██║ ╚═╝ ██║██║██║ ╚═╝ ██║██║██║ ╚═╝ ██║███████╗╚██████╔╝╚███╔███╔╝
   ╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚══════╝ ╚═════╝  ╚══╝╚══╝
        ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )

      Hello! I'm MimiMeow.
      What can I do for you?
      ────────────────────────────────────────────────────────────

      ────────────────────────────────────────────────────────────
      Here are the tasks in your list:
      1. [T][X] compare A | B \ C
      2. [D][ ] return book (by: June 6th)
      3. [E][ ] project meeting (from: Aug 6th 2pm to: Aug 6th 4pm)
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      Bye. Hope to see you again soon!
      ────────────────────────────────────────────────────────────
  ```

### TC-003: Reject corrupted saved data safely

- **Aim:** Verify that malformed saved data produces a clear error with its line number and does not partially load tasks.
- **Precondition:** Copy `test/fixtures/corrupted-tasks.txt` to `data/mimimeow.txt`.
- **Allowed differences:** Platform-specific line endings and trailing whitespace in the decorative banner or blank output lines may be ignored.
- **Inputs:**
  ```text
  todo do not overwrite
  list
  bye
  ```
- **Expected console output:**
  ```text
        ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )
   ███╗   ███╗██╗███╗   ███╗██╗███╗   ███╗███████╗ ██████╗ ██╗    ██╗
   ████╗ ████║██║████╗ ████║██║████╗ ████║██╔════╝██╔═══██╗██║    ██║
   ██╔████╔██║██║██╔████╔██║██║██╔████╔██║█████╗  ██║   ██║██║ █╗ ██║
   ██║╚██╔╝██║██║██║╚██╔╝██║██║██║╚██╔╝██║██╔══╝  ██║   ██║██║███╗██║
   ██║ ╚═╝ ██║██║██║ ╚═╝ ██║██║██║ ╚═╝ ██║███████╗╚██████╔╝╚███╔███╔╝
   ╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚══════╝ ╚═════╝  ╚══╝╚══╝
        ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )

      Hello! I'm MimiMeow.
      What can I do for you?
      ────────────────────────────────────────────────────────────

      Miiiision impossible! Mimi could not understand saved task on line 3: status must be 0 or 1.
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      Miiiision impossible! Mimi cannot save changes because the existing data could not be loaded. Fix the data file and restart MimiMeow first.
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      There are no tasks in your list yet!
      ────────────────────────────────────────────────────────────
      ────────────────────────────────────────────────────────────
      Bye. Hope to see you again soon!
      ────────────────────────────────────────────────────────────
  ```
- **Expected `data/mimimeow.txt` contents:** The file remains identical to `test/fixtures/corrupted-tasks.txt`.

# UI Test Plan

This file records the command-line UI test cases for the project. Run the cases from top to bottom. A test session stops at the first failure.

## Test case format

For each test case, record:

- **Aim:** What behaviour is being verified.
- **Inputs:** The exact commands or console input, in order.
- **Expected output:** The output that should be produced, including relevant prompts and messages.

## Test cases

### TC-001: Handle invalid commands and empty todos

- **Aim:** Verify that MimiMeow reports invalid input using persona-appropriate messages and continues accepting commands.
- **Inputs:**
  ```text
  todo
  blah
  bye
  ```
- **Expected output:**
  ```text
  Miiiision impossible! This todo is as empty as Mimi's food bowl. Add a description, meow.
  Miiiision impossible! Mimi does not recognise that command. Try todo, list, mark, or bye.
  Bye. Hope to see you again soon!
  ```

### TC-002: Add a todo and verify the task list

- **Aim:** Verify that a valid todo is added and appears in the list.
- **Inputs:**
  ```text
  todo Feed Mimi
  list
  bye
  ```
- **Expected output:**
  ```text
  Got it! Meow'hv added this task:
  [T][ ] Feed Mimi
  Feed Mimi
  Bye. Hope to see you again soon!
  ```

### TC-003: Reject malformed task commands without changing state

- **Aim:** Verify that missing descriptions and missing deadline/event fields are rejected without creating tasks.
- **Inputs:**
  ```text
  todo
  deadline Submit report
  event Team meeting /from 10am
  list
  bye
  ```
- **Expected output:**
  ```text
  Miiiision impossible! This todo is as empty as Mimi's food bowl. Add a description, meow.
  Miiiision impossible! Mimi needs a deadline description and date. Try: deadline description /by date.
  Miiiision impossible! Mimi needs both /from and /to times for this event.
  There are no tasks in your list yet!
  Bye. Hope to see you again soon!
  ```

### TC-004: Add deadline and event tasks

- **Aim:** Verify that valid deadline and event commands create the correct task types and preserve their details.
- **Inputs:**
  ```text
  deadline Submit report /by Friday
  event Team meeting /from 10am /to 11am
  list
  bye
  ```
- **Expected output:**
  ```text
  [D][ ] Submit report (by: Friday)
  [E][ ] Team meeting (from: 10am to 11am)
  1. [D][ ] Submit report (by: Friday)
  2. [E][ ] Team meeting (from: 10am to 11am)
  Bye. Hope to see you again soon!
  ```

### TC-005: Reject invalid task numbers

- **Aim:** Verify that invalid, missing, and out-of-range task numbers do not change task completion state.
- **Inputs:**
  ```text
  todo Check Mimi's food bowl
  mark abc
  mark 0
  mark 2
  list
  unmark 1
  list
  bye
  ```
- **Expected output:**
  ```text
  Miiiision impossible! The task number must be a positive whole number.
  Miiiision impossible! Mimi cannot find task 0. Check the task number
  Miiiision impossible! Mimi cannot find task 2. Check the task number
  1. [T][ ] Check Mimi's food bowl
  1. [T][ ] Check Mimi's food bowl
  Bye. Hope to see you again soon!
  ```

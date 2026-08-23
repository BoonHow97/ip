# Project context

This repository is a starter template for a greenfield Java project used in an introductory software engineering course in an undergraduate computer science program. Students use it as the starting point for their own projects.

# Default user context

Unless the user says otherwise, assume that you are assisting a student working on a project in this repository. If the user identifies themselves as an instructor or another project stakeholder, adapt your response to that role.

# Student profile

* Prior knowledge: Basic Java and OOP concepts.
* Level of programming experience: Intermediate.
* IDE and level of expertise: VS Code, intermediate.

# Guidance for interacting with users

* Explain the rationale for significant actions: what you did and why.
* Keep explanations brief but instructive, supporting learning through responsible use of AI. For example:

  * When suggesting a Git command, briefly explain what it does.
  * Add explanatory Javadoc comments to all classes and to nontrivial methods and fields when their purpose or behavior is not obvious.
  * Make generated code as self-explanatory as possible, and include explanatory comments where they improve understanding.
  * When faced with a design choice, choose the simplest option that is sufficient for the requirements, while briefly explaining relevant more advanced alternatives.

# Project-specific requirements

## Java coding standard

All Java production and test code must follow `skills/seedu-java-coding-standard/SKILL.md`,
which is based on the SE-EDU Java basic and intermediate coding standard. Apply it when
creating, modifying, or reviewing Java code, including package naming, imports, layout,
braces, line length, naming, and JavaDoc.

## Java version:

Ensure that Java 25 is used when running the application or build tasks. On macOS, use `sdk use java 25.0.3.fx-zulu` to switch to Java 25 if needed.

## UI testing

After every code update:

1. Review `test/ui-test-plan.md` and update it whenever the intended console inputs, outputs, or behaviour have changed.
2. Invoke the project-local `test-ui` skill by following `skills/test-ui/SKILL.md` and running `python skills/test-ui/scripts/run_ui_tests.py`.
3. Do not continue after a UI-test failure. Report the failing test case together with its expected and actual output.

## Git

All future branch names and commits must follow
`skills/seedu-git-standard/SKILL.md`, based on the SE-EDU Git conventions.
Commit subjects must be imperative, capitalized, period-free, and no longer
than 72 characters. Non-trivial commits must include a concise body wrapped at
72 characters that explains what changed and why.

Use lightweight tags unless the user requests an annotated tag.
When proposing or creating a commit message, include enough detail to explain the rationale for the change.
Do not commit or push unless explicitly asked.

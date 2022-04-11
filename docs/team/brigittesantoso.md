---
layout: page
title: Brigitte Santoso's Project Portfolio Page
---

## Project: HRConnect

HRConnect is an application for managing job applicants, interviews and miscellaneous tasks. <br>

Given below are my contributions to the project.

### Enhancements implemented
* **Job and Stage fields**: Added `Job` and `Stage` attributes to `Person` class
    * What it does: Clearly indicate which job position an applicant is applying to and which stage progress they are currently at
    * Justification: This feature improves the product significantly as the main aim of our application for HR recruiters to manage the job applicants and their stage progress easily.
    * Highlights: This enhancements affects some existing code as `Person` now has more attributes.
* **Interview Feature**: Added a tab to showcase list of interviews along with its various commands.
    * What it does: Allows the user to add, delete, edit, list and clear previous/upcoming interviews.
    * Justification: This feature improves the product significantly because it allows HR recruiters to easily keep track of the interviews they have with each applicant. The add, delete, edit, list and clear commands help fulfill the possible changes that the user might make when using the list.
    * Highlights: This enhancement affects existing commands as the `Person` is an attribute in the `Interview` class thus edits have to be made with some of the `Person` commands.
* **Type Feature**: Introduced types into code to distinguish between applicant, interview and task.
    * What it does: Allows the user to differentiate between which list they are updating by adding either `[p]`, `[i]` or `[t]` behind each command.
    * Justification: This feature improves the product significantly as it helps to standardise command line conventions and make it less confusing and easier to differentiate between different types of commands.
    * Highlights: This enhancement was difficult as it requires abstraction of code such as creating an abstract class `AddCommand` which `AddPersonCommand`, `AddInterviewCommand` and `AddTaskCommand` extends from to reduce the amount of duplicate code and ensure that OOP principles are adhered to.


### Code Contribution
Link: <a href="https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=brigittesantoso&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other">Code Contributed</a>

### Contributions to the UG and DG
**UG** <br>
* Add all task commands to the UG with clear formatting
* Add constraint pointers to some commands 
  * Clearing applicant list and Adding interview
* Add notes for Interview and Task features
  * Listed in chronological date time order
* Solve some UG bugs highlighted from PED
* Helped to format the UG by adding appropriate page breaks for PDF submission <br> <br>
**DG** <br>
* Edited Model and Storage sections of DG along with their elaboration
* Edited UML diagrams for Model and Storage section 
  * BetterModelClassDiagram, ModelClassDiagram and StorageClassDiagram

## Contributions to team-based tasks

* Created a few milestones and issues for team
* Ensure team completes all team-based tasks on time

## Review/mentoring contributions

* Reviewed and approved some team members PRs with some comments and suggestions
* Helped to solve bugs and issues in other team members features

## Contributions beyond team-projects

* Bugs reported in other team products during PED <br>
  * Link: <a href="https://github.com/brigittesantoso/ped/issues">PED Issues</a>

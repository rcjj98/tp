---
layout: page
title: User Guide
---

# HRConnect

HRConnect is a desktop application for managing the contacts of job applicants. It can also be used to keep track of 
the progress of each applicant during the application process.


* Table of Contents
  {:toc}

## Quick Start
1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `HRConnect.jar` from <a href="https://github.com/AY2122S2-CS2103T-W11-2/tp">here</a>
3. Copy the file to the folder you want to use as the *home folder* for your HRConnect.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   
  ![Ui](images/Ui.png)

5. Type your command into the command box and press `Enter` to execute it. 

    Some sample commands to try:
   * `add [p] n/Bob Tan p/98765876 e/bot@gmail.com a/262 Serangoon Central Drive 1-125 j/1 INPROGRESS`: Adds a new contact name Bob Tan to the address book
   * `list [p]`: Lists all contacts
   * `delete [p] Alex Lee`: Deletes job applicant named **Alex Lee** from the address book
   * `clear`: Deletes all contacts.
   * `exit`: Exits the app.

6. Refer to the [Features](#Features) below for details of each command.

## Features
### :information_source: Notes about the command format:
* Words in `UPPER_CASE` are the parameters to be supplied by the user. 
  
  e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.


* Items in square brackets are optional. 
  
  e.g n/NAME [j/APPLICATION] can be used as n/John Doe j/1 INPROGRESS or as n/John Doe.


* Items with ... after them can be used multiple times including zero times.

  e.g. [j/APPLICATION]... can be used as   (i.e. 0 times), j/1 INPROGRESS, j/2 ACCEPTED etc.


* Parameters can be in any order.
    
  e.g. if the command specifies n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME is also acceptable.


* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.

  e.g. if you specify p/12341234 p/56785678, only p/56785678 will be taken.


* Extraneous parameters for commands that do not take in parameters (such as help, list, exit and clear) will be ignored.

  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

&nbsp;
### Viewing Help: `help`
Shows a message explaining how to access the help page and the basic commands.

Format: `help`  

&nbsp;
### Listing all job applicants/interviews: `list`
Shows a list of all job applicants/interviews in the address book.

Format: 
* `list [i]`: Show all interviews.
* `list [p]`: Show all job applicants.

&nbsp;
### Adding a new job applicant/interview: `add` 
Adds a new job applicant/interview to the address book.

* #### Adding a new job applicant 
  Format: `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [j/APPLICATION]...`

  Example: `add [p] n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 j/1 INPROGRESS`

![add-applicant](images/add-applicant.png)

* #### Adding a new interview 
  Format: `add [i] INDEX d/DATE t/TIME`

  Example: `add [i] 1 d/9-May-2020 t/17:30`

![add-interview](images/add-interview.png)

&nbsp;
### Editing a job applicant/interview: `edit`
Edits an existing job applicant/interview in the address book

* #### Editing a job applicant
  Format: `edit INDEX n/NAME j/APPLICATION` <br>
  Example: `edit 2 n/Amanda Tan j/3 REJECTED` <br><br>

Before edit command executed.<br>
![edit](images/before-edit-contact-2.png) <br><br>

After edit command is executed. <br>
![edit](images/after-edit-contact-2.png) <br><br>

* #### Editing interview details 
  {more to add}

&nbsp;
### Deleting job applicant/interview: `delete` 
Deletes an existing job applicant/interview timing in the address book.

* #### Deleting a job applicant 
  Format: `delete [p] INDEX` or `delete [p] NAME`

  Example: `delete [p] 1` or `delete [p] Alex Jones`

* #### Deleting an interview 
  Format: `delete [i] INDEX`

  Example: `delete [i] 1`

&nbsp;
### Finding a job applicant or interviews by keywords: `find` 
Finds job applicants or interviews whose data contain any of the given keywords.

Use `g/` flags to find job applicants or interviews whose data contain **all** the keywords.

:bulb: Hint: Use multiple `g/` flags to simulate an **OR** command (e.g. `find g/n/alex g/j/software developer g/s:INPROGRESS`)

Notes:
* Finding persons `[p]` **only** accepts `g/`, `n/`, `p/`, `e/`, `a/`, `j/`, and `s/` flags
* Finding interviews `[i]` **only** accepts `g/`, `n/`, `d/`, `t/`, and `j/`, flags

Format: `find g/KEYWORD [KEYWORDS...] [g/KEYWORD [KEYWORDS...]]...`

Examples:
* `find g/n/alex g/s/ACCEPTED g/p/1111` is logically equivalent to `find n/alex OR s/ACCEPTED OR p/1111`
* `find g/n/alex s/ACCEPTED p/1111` is logically equivalent to `find n/alex AND s/ACCEPTED AND p/1111`
* `find g/n/alex g/s/ACCEPTED g/p/1111` is logically equivalent to `find (n/alex AND g/s/ACCEPTED) OR p/1111`


![find](images/find.png)

&nbsp;
### Clearing all entries: `clear` 
Clears all entries from the address book.  

Format: `clear`

&nbsp;
### Exiting the program: `exit` 
Exits the program.  

Format: `exit`

&nbsp;
## Storage
### Saving the data
AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

&nbsp;
### Editing the data file
AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

:exclamation: **Caution:** If your changes to the data file makes its format invalid, the address book will discard all data and start with an empty data file at the next run.

&nbsp;
### Archiving the current data: `export` [coming in v1.3]

&nbsp;
### Importing the data file: `import`

Imports all persons data from a *csv* or *json* save file generated from this address book.

File Format:
* CSV (tab delimited): name    phone_number    email_address    address    job_description    application_stage
* JSON: Follows the existing file data structure. 

Notes:
1. Filepath can be relative or absolute.
2. No duplicates are allowed to be imported into the address book.
   1. Data in the address book has a higher priority than data in the save file.
3. If any error is found, **none** of the data in the save file will be imported into the address book. 

Format: `import FILEPATH`

Example: `import C:\Users\<your username>\Desktop\data.csv` or `import ..\test\data.json`


## Command Summary

|               Action | Format                                                                       |
|---------------------:|:-----------------------------------------------------------------------------|
|        Add Interview | `add [i] 1 d/DATE t/TIME`                                                    |
|    Add Job Applicant | `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [j/APPLICATION]...`         |
|                Clear | `clear`                                                                      |
|     Delete Interview | `delete [i] INDEX`                                                           |
| Delete Job Applicant | `delete [p] INDEX`<br/>`delete [p] NAME`                                     |
|                 Edit | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [j/APPLICATION]` |
|                 Exit | `exit`                                                                       |
|                 Find | `find g/KEYWORD [KEYWORDS...] [g/KEYWORD [KEYWORDS...]]...`                  |
|                 Help | `help`                                                                       |
|               Import | `import FILEPATH`                                                            |
|      List Interviews | `list [i]`                                                                   |
|  List Job Applicants | `list [p]`                                                                   |

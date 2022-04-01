---
layout: page
title: User Guide
---

# HRConnect

HRConnect is a desktop application for managing the contacts of job applicants. It can also be used to keep track of 
the progress of each applicant during the application process.


## Table of Contents
  {:toc}


## Quick Start
1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `HRConnect.jar` from <a href="https://github.com/AY2122S2-CS2103T-W11-2/tp">here</a>
3. Copy the file to the folder you want to use as the *home folder* for your HRConnect.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   
  ![Ui](images/Ui.png)

5. Type your command into the command box and press `Enter` to execute it. 

    Some sample commands to try:
   * `add [p] n/Bob Tan p/98765876 e/bot@gmail.com a/262 Serangoon Central Drive 1-125 
   j/Software Developer s/INPROGRESS`: Adds a new contact name Bob Tan to the address book
   * `list [p]`: Lists all contacts
   * `delete [p] Alex Lee`: Deletes job applicant named **Alex Lee** from the address book
   * `clear`: Deletes all contacts.
   * `exit`: Exits the app.

6. Refer to the [Features](#Features) below for details of each command.

## Applicant Features
### :information_source: Notes about the command format regarding Applicants:
* Words in `UPPER_CASE` are the parameters to be supplied by the user. 
  
  e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.


* Items in square brackets are optional. 
  
  e.g n/NAME [p/PHONE_NUMBER] can be used as n/John Doe p/PHONE_NUMBER or as n/John Doe.


* Items with ... after them can be used multiple times including zero times.

  e.g. [a/ADDRESS]... can be used as   (i.e. 0 times), a/ADDRESS, a/ADDRESS etc.


* Parameters can be supplied in any order.
    
  e.g. if the command requires n/NAME p/PHONE_NUMBER, <br> p/PHONE_NUMBER n/NAME is also acceptable.


* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.

  e.g. if you specify p/12341234 p/56785678, only p/56785678 will be taken.


* Extraneous parameters for commands that do not take in parameters (such as help, list, exit and clear) will be ignored.

  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

&nbsp;
### Viewing Help: `help`
Shows a message explaining how to access the help page and the basic commands.

Format: `help`  

&nbsp;
### Listing all job applicants: `list [p]`
Shows a list of all job applicants/interviews in the address book.

Format: 
* `list [p]`: Show all job applicants.

&nbsp;
### Adding a new job applicant: `add [p]` 
Adds a new job applicant to the address book.

* #### Adding a new job applicant 
  Format: `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/JOB_POSITION s/STAGE`

  Example: `add [p] n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 j/Software Developer s/INPROGRESS`

![add-applicant](images/add-applicant.png)


&nbsp;
### Editing a job applicant:`edit [p]`
Edits an existing job applicant/interview in the address book

* #### Editing a job applicant
  Format: `edit [p] INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [j/JOB_POSITION] [s/STAGE]` <br>
  Example: `edit [p] 2 n/Amanda Tan j/Software Developer s/REJECTED` <br><br>

Before edit command executed.<br>
![edit](images/before-edit-contact-2.png) <br><br>

After edit command is executed. <br>
![edit](images/after-edit-contact-2.png) <br><br>

:exclamation: **Take note:** Cannot edit a person if he/she has an interview scheduled.


&nbsp;
### Deleting job applicant: `delete [p]` 
Deletes an existing job applicant from the address book.

* #### Deleting a job applicant 
  Format: `delete [p] INDEX` or `delete [p] NAME`

  Example: `delete [p] 1` or `delete [p] Alex Jones` <br><br>
  :exclamation: **Take note:** Cannot delete a person if he/she has an interview scheduled.

&nbsp;
### Finding job applicant(s) by keywords: `find [p]` 
Finds job applicants whose data contain the given keywords.

Use `g/` flags to find job applicants whose data contain **all** the keywords.

:bulb: Tip: Use multiple `g/` flags to simulate an **OR** command (e.g. `find g/n/alex g/j/software developer g/s:INPROGRESS`)

Notes:
* Finding persons `[p]` **only** accepts `g/`, `n/`, `p/`, `e/`, `a/`, `j/`, and `s/` flags

Format: `find g/KEYWORD [KEYWORDS...] [g/KEYWORD [KEYWORDS...]]...`

Examples:
* `find [p] g/s/ACCEPTED g/n/John Doe` is logically equivalent to <br>
`find s/ACCEPTED OR n/John Doe`  <br> <br>
![find](images/find-applicant-OR-example.png) <br> <br>

* `find [p] g/j/Software Developer s/ACCEPTED` is logically equivalent to <br> 
`find j/Software Developer AND s/ACCEPTED` <br> <br>
![find](images/find-applicant-AND-example.png)

* `find [p] g/j/Software Developer s/REJECTED g/n/John Doe` is logically equivalent to <br>
`find (j/Software Developer AND s/REJECTED) OR n/John Doe` <br> <br>
![find](images/find-applicant-AND-OR-example.png)


&nbsp;
### Clearing all entries: `clear` 
Clears all entries from the address book.  

Format: `clear`

&nbsp;
### Exiting the program: `exit` 
Exits the program.  

Format: `exit`


## Interview Features
### :information_source: Notes about the command format regarding Interviews:

&nbsp;
### Listing all scheduled interviews: `list [i]`
Shows a list of all scheduled interviews in the address book.

Format:
* `list [i]`: Show all scheduled interviews.

&nbsp;
### Adding a new interview slot for a job applicant: `add [i]`
Adds a new job interview slot to the address book.

* #### Adding a new interview
  Format: `add [i] INDEX d/DATE t/TIME` <br><br>

  Example: `add [i] 1 d/2021-06-25 t/17:30`

![add-interview](images/add-interview.png)

&nbsp;
### Editing an existing interview slot: `edit [i]`
Edits an existing interview slot in the address book

* #### Editing interview details
  Format: `edit [i] INDEX d/DATE [t/TIME]` <br> 
  or `edit [i] INDEX t/TIME [d/DATE]` <br><br>

  Examples: <br> `edit [i] 1 d/2021-12-30` <br>
  `edit [i] 1 t/10:30` <br>
  `edit [i] 1 d/2021-12-30 t/10:30` <br><br>

![edit-interview](images/edit-interview.png)

&nbsp;
### Deleting an interview slot: `delete [i]`
Deletes an existing interview slot in the address book.

* #### Deleting an interview slot
  Format: `delete [i] INDEX`

  Example: `delete [i] 1`

&nbsp;
### Finding scheduled interview slot(s) by keywords: `find [i]`
Finds interview slots with data containing any of the specified keywords.

Use `g/` flags to find interview slot(s) with data containing **all** the keywords.

:bulb: Hint: Use multiple `g/` flags to simulate an **OR** command (e.g. `find g/n/alex g/j/software developer g/s:INPROGRESS`)

Notes:
* Finding interviews `[i]` **only** accepts `g/`, `n/`, `d/`, `t/`, and `j/`, flags

Format: `find g/KEYWORD [KEYWORDS...] [g/KEYWORD [KEYWORDS...]]...`

Examples:
* `find [i] g/n/Amanda Tan g/j/Software Developer g/t/10:10` is logically equivalent to `find n/Amanda Tan OR j/Software Developer OR t/10:10` <br><br>
* `find [i] g/n/Amanda Tan j/Software Developer t/10:10` is logically equivalent to `find n/Amanda Tan AND j/Software Developer AND t/10:10` <br><br>
* `find [i] g/n/Amanda Tan g/j/Software Developer g/t/10:10` is logically equivalent to `find (n/Amanda Tan AND g/j/Software Developer) OR t/10:10` <br><br>


![find](images/find-interview.png)




&nbsp;
## Storage
### Saving the data
AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

&nbsp;
### Editing the data file
AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

:exclamation: **Caution:** If your changes to the data file makes its format invalid, the address book will discard all data and start with an empty data file at the next run.


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

&nbsp;
### Exporting to a csv data file: `export`

Exports all applicant data from the address book to a *csv* save file.

File Format:
* CSV (tab delimited): name    phone_number    email_address    address    job_description    application_stage <br> 

Notes:
1. Filepath of specified CSV file can be either relative or absolute.
2. File name of csv file **cannot** contain any front or back slashes.
   1. Invalid csv file name with front slash: myCSVfile\\.csv
   2. Invalid csv file name with back slash: myCSVfile/.csv
3. Specifying the same csv file name and path will overwrite the data inside the specified csv file. 
4. If any error is found while executing the command, 
**none** of the data from the addressbook will be exported into the specified csv file.

Format: `export FILEPATH`

Absolute filepath example for WindowsOS: `export C:\Users\<YOUR_USERNAME>\Desktop\myData.csv` <br>
Relative filepath example for WindowsOS: `export ./myData.csv` <br><br>
Absolute filepath example for MacOS: `export  /Users/<YOUR_USERNAME>/Downloads/myDataFile.csv` <br>
Relative filepath example for MacOS: `export  ./myDataFile.csv
`

## Command Summary

|               Action | Format                                                                           |
|---------------------:|:---------------------------------------------------------------------------------|
|        Add Interview | `add [i] 1 d/DATE t/TIME`                                                        |
|    Add Job Applicant | `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [j/APPLICATION]...`             |
|                Clear | `clear`                                                                          |
|     Delete Interview | `delete [i] INDEX`                                                               |
| Delete Job Applicant | `delete [p] INDEX`<br/>`delete [p] NAME`                                         |
|       Edit Applicant | `edit [p] INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [j/APPLICATION]` |
|       Edit Interview | `edit [i] INDEX [d/DATE] [t/TIME]`                                               |
|                 Exit | `exit`                                                                           |
|       Find Applicant | `find g/KEYWORD [KEYWORDS...] [g/KEYWORD [KEYWORDS...]]...`                      |
|                 Help | `help`                                                                           |
|               Import | `import FILEPATH`                                                                |
|               Export | `export FILEPATH`                                                                |
|      List Interviews | `list [i]`                                                                       |
|  List Job Applicants | `list [p]`                                                                       |

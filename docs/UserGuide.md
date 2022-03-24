# HRConnect

HRConnect is a desktop application for managing the contacts of job applicants. It can also be used to keep track of 
the progress of each applicant during the application process.

### Table of Contents
* [Quick Start](#Quick-Start)
* [Features](#Features)
* [FAQ](#FAQ)
* [Command Summary](#Command-Summary)

## Quick Start
1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `addressbook.jar` from 
3. Copy the file to the folder you want to use as the *home folder* for your AddressBook.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
5. Type your command into the command box and press `Enter` to execute it. 

    Some sample commands to try:
     * `add [p] n/Bob Tan p/98765876 e/bot@gmail.com a/262 Serangoon Central Drive 1-125 j/1 j/2`: Adds a new contact name Bob Tan to the address book
     * `list [p]`: Lists all contacts
     * `delete [p] Alex Lee`: Deletes job applicant named **Alex Lee** from the address book
     * `clear`: Deletes all contacts.
     * `exit`: Exits the app.
7. Refer to the [Features](#Features) below for details of each command.

## Features
### Viewing Help: `help`
Shows a message explaining how to access the help page and the basic commands.

Format: `help`  

&nbsp;
### Listing all job applicants: `list`
Shows a list of all persons in the address book.

Format: `list`

---

### Adding a new job applicant/interview: `add` 
Adds a new job applicant/interview to the address book.

* #### Adding a new job applicant 
  Format: `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [j/JOB_ID]...`

  Example: `add [p] n/Tom Tan p/98764312 e/tt@outlook.com a/Blk 3 Queens Road #12-155 j/2 j/5`


* #### Adding a new interview 
  Format: `add [i] INDEX d/DATE t/TIME`

  Example: `add [i] 2 d/9-May-2020 t/17:30`


---

### Editing a job applicant/interview: `edit` 
Edits an existing job applicant/interview in the address book

* #### Editing a job applicant 
    Format:

* #### Editing an interview 
    Format: 

---

### Deleting job applicant/interview: `delete` 
Deletes an existing job applicant/interview in the address book.

* #### Deleting a job applicant 
  Format: `delete [p] INDEX` or `delete [p] NAME`

  Example: `delete [p] 2` or `delete [p] Alex Jones`

* #### Deleting an interview 
  Format: `delete [i] INDEX`

  Example: `delete [1] 2`

### Finding a job applicant by keywords: `find` 
Finds job applicants whose data contain any of the given keywords.

Format: `find g/[KEYWORDS...] ...`

Example: `find g/john bukit jobid:1 g/thomas 119 progress:inprogress`

&nbsp;
### Clearing all entries: `clear` 
Clears all entries from the address book.  

Format: `clear`

&nbsp;
### Exiting the program: `exit` 
Exits the program.  

Format: `exit`


### Saving the data
### Editing the data file
### Archiving the current data [coming in v1.3]
### Importing the data file [coming in v1.3] 

## FAQ 
## Command Summary 

| Action | Format, Examples |
|:------:|:----------------:|
|  Help  |      `help`      |
|  List  |      `list`      |
|  Add   |      `add`       |
|  Edit  |      `edit`      |
|  Find  |      `find`      |
| Delete |     `delete`     |
| Clear  |     `clear`      |
|  Exit  |      `exit`      |


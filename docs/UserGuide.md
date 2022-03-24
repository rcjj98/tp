#HRConnect

HRConnect is a desktop application for managing the contacts of job applicants. It can also be used to keep track of 
the progress of each applicant during the application process.

### Table of Contents


--------------------------------------------------------------------------------------------------------------------

## Quick Start
1. Ensure you have `Java 11` or above installed in your computer.
2. Download the latest `addressbook.jar` from <a href="www.google.com">here</a>
3. Copy the file to the folder you want to use as the *home folder* for your AddressBook.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
5. Type your command into the command box and press `Enter` to execute it. 
   * Some sample commands to try:
     * `add [p] n/Bob Tan p/98765876 e/bobt@gmail.com a/262 Serangoon Central Drive 1-125 j/1 j/2`: Adds a new contact name Bob Tan to the address book
     * `list [p]`: Lists all contacts
     * `delete [p] Alex Lee`: Deletes job applicant named **Alex Lee** from the address book
     * `clear`: Deletes all contacts.
     * `exit`: Exits the app.
6. Refer to the [Features](name="features") below for details of each command.




## Features<a name="features"></a>
### Viewing Help: `help`
Shows a message explaining how to access the help page and the basic commands.

Format: `help`

### Listing all job applicants: `list` <a name="list"></a>
Shows a list of all persons in the address book.

Format: `list`

### Adding a new job applicant/interview: `add` <a name="add"></a>
Adds a new job applicant/interview to the address book.

* #### Adding a new job applicant <a name="add-applicant"></a>
  Format: `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [j/JOB_ID]...`


* #### Adding a new interview <a name="add-interview"></a>
  Format: `add [p] INDEX d/DATE t/TIME`


### Editing a job applicant/interview: `edit` <a name="edit"></a>
Edits an existing job applicant/interview in the address book

* #### Editing a job applicant <a name="edit-applicant"></a>
    Format:

* #### Editing an interview <a name="edit-interview"></a>
    Format: 

### Deleting job applicant/interview: `delete` <a name="delete"></a>
Deletes an existing job applicant/interview in the address book.

* #### Deleting a job applicant <a name="delete-applicant"></a>
  Format: `delete [p] INDEX` or `delete [p] NAME`

* #### Deleting an interview <a name="delete-interview"></a>
  Format: `delete [i] INDEX`

### Finding a job applicant by keywords: `find` <a name="find"><a/>
Finds job applicants whose data contain any of the given keywords.

Format: `find g/[KEYWORDS...] ...`

### Clearing all entries: `clear` <a name="clear"><a/>
Clears all entries from the address book.

Format: `clear`

### Exiting the program: `exit` <a name="exit"><a/>
Exits the program.

Format: `exit`

### Saving the data <a name="save"></a>
### Editing the data file <a name="modify"></a>
### Archiving the current data [coming in v1.3] <a name="archive"></a>
### Importing the data file [coming in v1.3] <a name="import"></a>

## FAQ <a name="faq"></a>
## Command Summary <a name="summary"></a>

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


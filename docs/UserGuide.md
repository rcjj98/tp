---
layout: page
title: User Guide
---

HRConnect is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HRConnect can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `HRConnect.jar` from [here](https://github.com/AY2122S2-CS2103T-W11-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add [p]`**`n/John Doe p/84352609 e/johndoe@email.com a/244 Ang Mo Kio Street 32 j/1` : Adds a person named `John Doe` who is applying for the job with job ID `1` and his current application status is `INPROGRESS` to the person list .

   * **`delete [p]`**`3` : Deletes the 3rd contact shown in the person list.

   * **`clear`** : Deletes all contacts in person list.
   
   * **`find`**`j/1 n/alex` : Finds all entries with `jobid 1` and the name containing `Alex`.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add [p] n/NAME`, `NAME` is a parameter which can be used as `add [p] n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [j/APPLICATION]` can be used as `n/John Doe j/1` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[j/APPLICATION]…​` can be used as ` ` (i.e. 0 times), `j/1`, `j/1 j/2` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the person list.

Format: `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/APPLICATION`

Adds an interview to the interview list. 

Format: `add [i] INDEX d/DATE t/TIME`

* Adds interview for person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Each applicant can only apply for 1 job in the company and each employer can only be recruiting for 1 job.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When first added into the address book, each applicant's stage of application is `Resume Screened`.
</div>

Examples:
* Add Person: `add [p] n/John Doe p/84352609 e/johndoe@email.com a/244 Ang Mo Kio Street 32 j/1`
* Add Interview: `add [i] 1 d/25-Dec-2021 t/04:05`

### Listing all persons : `list`

Shows a list of all persons in the person list.

Format: `list [p]`

Shows a list of all interviews in the interview list.

Format: `list [i]`

### Editing a person : `edit`

Edits an existing person in the address book.

Applicant Format: `edit INDEX c/CATEGORY [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [j/JOBPOSITION] [s/STAGE]`

Employer Format: `edit INDEX c/CATEGORY [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [j/JOBPOSITION]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The `CATEGORY` field must be provided.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* Editing applicant: `edit 1 c/A p/91234567 j/2`

  Edits the phone number and job ID of the 1st **applicant** to be `91234567` and `2` respectively.


* Editing applicant: `edit 3 c/A n/Betsy Crower s/Interview`

  Edits the name and stage of application of the 2nd **applicant** to be `Betsy Crower` and `Interview` respectively.


* Editing employer: `edit 1 c/E e/johndoe@gmail.com j/3`

  Edits the email and job ID of the 1st **employer** to be `johndoe@gmail.com` and `3` respectively.

### Locating persons by keywords: `find`

Finds all contacts from the address book whose data contains the keywords. The keywords are case-insensitive.

Format: `find g/[keywords] g/[keywords]...`

To search for job id, use `jobid:`.
* For example, `find g/jobid:2`

To search for progress of application, use `progress:`.
* For example, `find g/progress:inprogress`

In the event that either `jobid:` or `progress:` is misspelled, the command will treat the 
search term as a normal keyword search instead of a special keyword.

The `g/` flag, which stands for *group*, simulates the AND operator and the keywords are separated by a space like so:
`find g/alex jobid:1 jia`.

In order to simulate an OR operator, multiple `g/` flags can be chained together like so: `find g/alex g/jobid:1`.

Therefore, combining the `g/` flags will result in a series of AND statements ORed together as shown below.

* `find g/alex 123 progress:inprogress g/lee bukit tan` is logically equivalent to 
`find (alex AND 123 AND progress:inprogress) OR (lee AND bukit AND tan)`

Examples:
* `find g/alex g/steven` returns all entries with the word `steven` **or** `alex` in their data.
* `find g/alex 123 @gmail.com` returns all entries containing `alex` **and** `123` **and** `@gmail.com` in their data.

### Deleting a person : `delete`

Deletes the specified person from the person list.

Format: `delete [p] INDEX`

Deletes the specified interview from the interview list.

Format: `delete [i] INDEX`

* Deletes the person/interview at the specified `INDEX`. 
* For delete person, can only delete if person does not have any interview.
* The index refers to the index number shown in the displayed person/interview list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list [p]` followed by `delete [p] 2` deletes the 2nd person in the person list assuming he does not have any interview.
* `list [i]` followed by `delete [i] 3` deletes the 3rd interview in the interview list.

### Clearing all entries : `clear`

Clears all entries from the person list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                  | Format, Examples                                                                                                                                                                                                                                                                               |
|-------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Person**          | `add [p] n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/1` <br> e.g., `add [p] n/John Doe p/84352609 e/johndoe@email.com a/244 Ang Mo Kio Street 32 j/1`                                                                                                                                            |
| **Add Interview**       | `add [i] INDEX d/DATE t/TIME` <br> e.g., `add [i] 1 d/24-Dec-2021 t/05:06`                                                                                                                                                                                                                     |
| **Clear**               | `clear`                                                                                                                                                                                                                                                                                        |
| **Delete Person**       | `delete [p] INDEX`<br> e.g., `delete [p] 3`                                                                                                                                                                                                                                                    |
| **Delete Interview**    | `delete [i] INDEX`<br> e.g., `delete [i] 3`                                                                                                                                                                                                                                                    |
| **Edit**                | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [j/JOBPOSITION STAGE]`<br> e.g.,`edit 2 n/Jane Doe j/1 INPROGRESS`                                                                                                                                                                 |
| **Find**                | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                                     |
| **List Person List**    | `list [p]`                                                                                                                                                                                                                                                                                     |
| **List Interview List** | `list [i]`                                                                                                                                                                                                                                                                                     |
| **Help**                | `help`                                                                                                                                                                                                                                                                                         |


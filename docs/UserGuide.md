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

   * **`add`**`n/John Doe p/84352609 e/johndoe@email.com a/244 Ang Mo Kio Street 32 j/1 REJECTED` : Adds a job applicant named `John Doe` to the Address Book who is applying for the job with job ID `1` and his current application status is `REJECTED`.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

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

Adds a contact to the address book.

Format:

Applicant Format: `add c/CATEGORY n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/JOBPOSITION s/STAGE`

Employer Format: `add c/CATEGORY n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/JOBPOSITION`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Each applicant can only apply for 1 job in the company and each employer can only be recruiting for 1 job.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
When first added into the address book, each applicant's stage of application is `Resume Screened`.
</div>

Examples:
* Add Applicant: `add c/A n/John Doe p/84352609 e/johndoe@email.com a/244 Ang Mo Kio Street 32 j/1 s/Resume Screened`
* Add Employer: `add c/E n/Jane Doe p/88541245 e/janedoe@email.com a/222 Bishan Street 22 j/1`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing contact in the address book.

Format: `edit INDEX c/CATEGORY [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [j/JOBPOSITION STAGE]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `edit 1 p/91234567 j/2 INPROGRESS`

  Edits the phone number and job application of the 1st applicant to be `91234567` and `2 INPROGRESS` respectively.


* `edit 3 n/Betsy Crower s/1 ACCEPTED`

  Edits the name and job application of the 2nd applicant to be `Betsy Crower` and `1 ACCEPTED` respectively.


* `edit 1 c/E e/johndoe@gmail.com a/Tanah Merah`

  Edits the email and address of the 1st applicant to be `johndoe@gmail.com` and `Tanah Merah` respectively.

### Locating persons by name: `find`

Finds a contact from the address book.

Format: `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] etc…`


Examples:
* `find n/john` returns `John`, `John Lee`, `John Yeo`
* `find n/john p/123456 e/johnlee@gmail.com` returns `John Lee`<br>

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

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


| Action     | Format, Examples                                                                                                                                                                                                                                                                                                                                                                                              |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | Applicant: `add c/CATEGORY n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/JOB_APPLIED s/STAGE_OF_APPLICATION` <br> e.g., `add c/A n/John Doe p/84352609 e/johndoe@email.com a/244 Ang Mo Kio Street 32 j/1 s/Resume Screened`<br><br>Employer: `add c/CATEGORY n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS j/JOB_RECRUITING` <br> e.g., `add c/E n/Jane Doe p/88541245 e/janedoe@email.com a/222 Bishan Street 22 j/1` |
| **Clear**  | `clear`                                                                                                                                                                                                                                                                                                                                                                                                       |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                                                                                                                                           |
| **Edit**   | `edit INDEX c/CATEGORY [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [j/JOBPOSITION] [s/STAGE]`<br> e.g.,`edit 2 c/A n/Jane Doe s/Interview` <br> <br> `edit INDEX c/CATEGORY [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [j/JOBPOSITION]`<br> e.g.,`edit 2 c/E n/James Lee p/9891 3445`                                                                                                                |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                                                                                                                                                    |
| **List**   | `list`                                                                                                                                                                                                                                                                                                                                                                                                        |
| **Help**   | `help`                                                                                                                                                                                                                                                                                                                                                                                                        |


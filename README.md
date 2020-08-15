# About
This is my take on the CS-230 Software Engineering (2018) software product specifically, the library system with a single person login system. This was originally a group assignment, but it was incomplete at the time.

# Update
Tawe-Lib Reborn has all the functionality that satisfies the specification (with some unnecessary stuff like edit users) minus the 'extra' feature and user statistics. Any pushes to this repository from this day onwards will be refactoring of the code (and unseen bugs).

# How to Run
- **On Eclipse:** Import this an existing project and run Main.java.   
- **On Command Line:** You **MUST** change the file path to the data files by adding "../DataFiles/" (to go back a directory from src folder). This is present in:
  - CreateProfilePictureController.java
  - DisplayUserController.java
  - EditResourceController.java
  - EditUserController.java
  - FileHandling.java 
  - NewResourceController.java
  - NewUserController.java
  - ResourceSettingsController.java
  - ViewResourceController.java
  
  Then compile the code in the same directory as Main.java. 

# Notes about the Implementation
- Everything is coded in Eclipse IDE.
- Indents are 4 spaces.
- The request 'queue' works by a first come first serve basis (for a copy). 
- Due to the 'no due date set' requirement, it's possible to borrow a resource copy indefinitely as long as no one else requests for the copy.
- Copies are generated as a new resource is created and the loan duration is randomly picked from four options.
- Users can edit their details, even though this feature is not mentioned in the specification.
- Current Balance is shown in the user's profile (rather than a stand alone page).
- File handling is done by using text files.

# Data Structures
- Each data class are stored in Array Lists, with the exception of both User classes.
- Both Users and Librarians are stored in Linked Hashmaps, with the key being their username.

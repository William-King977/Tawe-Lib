# About
This is my take on the CS-230 Software Engineering (2018) software product specifically, the library system with a single person login system. This was originally a group assignment, but it was incomplete at the time.

# Update
Tawe-Lib Reborn has all the functionality that satisfies the specification (with some unnecessary stuff like edit users) minus the 'extra' feature and user statistics. Any pushes to this repository from this day onwards will be refactoring of the code and possibly the removal of the Property class.

# Notes about the Implementation
- The Property class isn't necessary. It was there because we (my group) thought books and DVDs had both genre and language as attributes, but DVD doesn't have a genre (as shown in the specification). It was only kept for this initially because it makes sense for DVDs to have a genre. 
- The request 'queue' works by a first come first serve basis (for a copy). 
- Due to the 'no due date set' requirement, it's possible to borrow a resource copy indefinitely as long as no one else requests for the copy.
- Copies are generated as a new resource is created and the loan duration is randomly picked from four options.
- Users can edit their details, even though this feature is not mentioned in the specification.
- Current Balance is shown in the user's profile (rather than a stand alone page).
- File handling is done by using text files.

# Data Structures
- Each data class are stored in Array Lists, with the exception of both User classes.
- Both Users and Librarians are stored in Linked Hashmaps, with the key being their username.

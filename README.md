# About
A recreation of the CS-230 Software Engineering (2018) software product specifically, the library system with a single person login system. Hopefully with better functionality than the original.

# Update
Tawe-Lib Reborn has all the functionality that satisfies the specification (with some unnecessary stuff like edit users) minus the 'extra' feature and user statistics. Any pushes to this repository from this day onwards will be refactoring of code and possibly the removal of the Property class.

# Notes about the Implementation
- The Property class isn't necessary. It was there because we (my group) thought books and DVDs had both genre and language as attributes, but DVD doesn't have a genre (as shown in the specification). It was only kept for this initially because it makes sense for DVDs to have a genre. 
- Copies are generated as a new resource is created and the loan duration is randomly picked from four options.
- Users can edit their details, even though this feature is not mentioned in the specification.
- Current Balance is shown in the user's profile (rather than a stand alone page).
- File handling is done by using text files.

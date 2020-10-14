# About
A library management system with a single person login system. This was originally a university group assignment, but it was incomplete at the time.

# How To Run
- **On Eclipse:** Import this as an existing project and run Main.java in the data package (directory).   

# Notes About The Implementation
- Everything is coded in Eclipse IDE.
- The request 'queue' works by a first come first serve basis (for a copy). 
- Due to the 'no due date set' requirement, it's possible to borrow a resource copy indefinitely as long as no one else requests for the copy.
- Copies are generated as a new resource is created (set to 5 copies) and the loan duration is randomly picked from 4 options.
- Users can edit their details, even though this feature is not mentioned in the specification.
- Current Balance is shown in the user's profile (rather than a stand alone page).
- File handling is done by using text files.

# Data Structures
- Each data class is stored in Array Lists, with the exception of both User classes.
- Both Users and Librarians are stored in Linked Hashmaps, using their username as the key.

# Video Demonstration
A demo video that demonstrates all of the features: https://youtu.be/3TyYubX-mis

# Credits
Even though this project was built from the beginning (on this repository), components from the original project were used. So I'd like to thank the following people who were in my group for the assignment:

- Aaron Lewis
- Adam Moore
- Chris Wong
- Ellis Thompson: https://github.com/Ellislee1
- Kelvin Ho

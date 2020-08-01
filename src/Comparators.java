import java.util.Comparator;

/**
 * A class to hold all the comparator classes which are all used
 * to sort Array Lists holding elements of the data classes. 
 * @author William King
 */
public class Comparators {
	// This class is purely used to keep all the different comparators
	// in one place.
}

/**
 * A Comparator class used to sort an array list of resources 
 * in ascending order.
 * @author William King
 */
class SortResources implements Comparator<Resource> {
    public int compare(Resource a, Resource b) { 
        return a.getResourceID() - b.getResourceID(); 
    } 
}

/**
 * A Comparator class used to sort an array list of copies 
 * in ascending order.
 * @author William King
 */
class SortCopies implements Comparator<Copy> {
    public int compare(Copy a, Copy b) { 
        return a.getCopyID() - b.getCopyID(); 
    } 
}

/**
 * A Comparator class used to sort an array list of loans 
 * in ascending order.
 * @author William King
 */
class SortLoansAsc implements Comparator<Loan> {
    public int compare(Loan a, Loan b) { 
        return a.getLoanID() - b.getLoanID(); 
    } 
}

/**
 * A Comparator class used to sort an array list of loans 
 * in descending order.
 * @author William King
 */
class SortLoansDesc implements Comparator<Loan> {
    public int compare(Loan a, Loan b) { 
        return b.getLoanID() - a.getLoanID(); 
    } 
}

/**
 * A Comparator class used to sort an array list of loans 
 * in ascending order (based on return date and time).
 * @author William King
 */
class SortLoansReturnDesc implements Comparator<Loan> {
    public int compare(Loan a, Loan b) {
    	// Compare by ID if both loans are not returned.
    	if (!b.isReturned() && !a.isReturned()) {
    		return b.getLoanID() - a.getLoanID();
    	// Favour the loans that have no return date.
    	} else if (!b.isReturned()) {
    		return 1;
    	} else if (!a.isReturned()) {
    		return -1;
    	// If the dates are different, calculate days between them.
    	} else if (!b.getReturnDate().equals(a.getReturnDate())) {
    		String firstDate = a.getReturnDate();
    		String secondDate = b.getReturnDate();
    		return Utility.daysBetweenDates(firstDate, secondDate);
    	// Otherwise, calculate the time between them.
    	} else {
    		String firstTime = a.getReturnTime();
    		String secondTime = b.getReturnTime();
    		return Utility.secondsBetweenTimes(firstTime, secondTime);
    	}
    } 
}

/**
 * A Comparator class used to sort an array list of requests
 * in ascending order.
 * @author William King
 */
class SortRequests implements Comparator<Request> {
    public int compare(Request a, Request b) { 
        return a.getRequestID() - b.getRequestID(); 
    } 
}

/**
 * A Comparator class used to sort an array list of transactions
 * in ascending order.
 * @author William King
 */
class SortTransactionsAsc implements Comparator<Transaction> {
    public int compare(Transaction a, Transaction b) { 
        return a.getTransactionID() - b.getTransactionID(); 
    } 
}

/**
 * A Comparator class used to sort an array list of transactions
 * in descending order.
 * @author William King
 */
class SortTransactionsDesc implements Comparator<Transaction> {
    public int compare(Transaction a, Transaction b) { 
        return b.getTransactionID() - a.getTransactionID(); 
    } 
}
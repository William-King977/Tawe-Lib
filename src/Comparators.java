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
class SortLoans implements Comparator<Loan> {
    public int compare(Loan a, Loan b) { 
        return a.getLoanID() - b.getLoanID(); 
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
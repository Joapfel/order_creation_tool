package dao;

/**
 * This class provides the methods needed for data storage.
 * The concrete storage method is decided by the implementation class:
 *      e.g. there could be a class which uses a data base
 *           there could be a class which uses serialization
 *           there could be a class which writes to a .txt file
 *
 * The client who uses the Storage interface does not care about the concrete implementation
 * as long as it works. This way the concrete storage method stays exchangeable at any time
 * without the need for refactoring.
 */
public interface Storage {

    /*
    uncomment this only after the user dao class was created.
    after uncommenting make sure the correct user class is used (not from another java package).
     */

    User findUserByUsername(String username);
    void saveUser(User user);
	void saveCustomer(Customer customer);
	Customer findCustomersByCompanyName(String companyName);
	Customer findCustomerById(int companyID);	
}

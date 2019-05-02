package authentication;

/**
 * 
 * @author johannes
 *
 */
public interface Authentication {

    /**
     * Checks whether the given credentials exist.
     * The concrete implementation might be more or less complex:
     *      e.g. the password can be store literally
     *           the password can be Base64 encoded
     *           the password can be encrypted
     * This decision is made by the Class which implements this interface.
     *
     * @param username
     * @param password
     *
     * @return returns true if credentials exist, false otherwise
     */
    boolean login(String username, String password);
}

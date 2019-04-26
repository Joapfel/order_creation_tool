package dao;

/**
 * This class is ought to be composed with the advanced Authentication implementation
 */
public interface Encryption {

    /**
     * Encrypts the password.
     * The type of encryption is decided by the implementation class.
     *
     * @param password the literal password
     *
     * @return returns the encrypted password as Base64 string
     */
    String encrypt(String password);

    /**
     * Decrypts the password.
     * The type of encryption is decided by the implementation class.
     *
     * @param passwordBase64 the Base64 encoded and decrypted password
     *
     * @return returns the literal password
     */
    String decrypt(String passwordBase64);
}

package de.cutl.diguna.networkwarden.business.login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use this class to change the passwords stored in the database fro one that
 * is visible as plain text (a security threat) to one that is "hashed".
 * Hashing is a one-way encryption system. Hashes can be generated but cannot
 * be reserve engineered, which is why they are called one-way hashes. Use
 * this class to generate a hashed password, based on the original plain text
 * version, using SHA-256, which is a superior hashing algorithm. Then copy
 * the output from the console and use it to replace what you have in your
 * database.
 * 
 * @author chris
 */
public class HashedPasswordGenerator {

    /**
     * generate SHA-256 Hash from input.
     * 
     * http://stackoverflow.com/questions/5531455/how-to-encode-some-string-with-sha256-in-java/11009612#11009612
     * 
     * @param password
     * @return 
     */
    public static String generateHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashedPasswordGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(generateHash("password1"));
        // 0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e
        System.out.println(generateHash("password2"));
        // 6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4
        System.out.println(generateHash("password3"));
        // 5906ac361a137e2d286465cd6588ebb5ac3f5ae955001100bc41577c3d751764
    }

}

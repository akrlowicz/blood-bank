package edu.ib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Simple class for password security
 * Using the MD5 Message-Digest Algorithm
 */
public class SimpleMD5E {

    /**
     * Hashing given password
     * @param passwordToHash password entered by user
     * @return hashed String
     */
     public static String hashPassword(String passwordToHash) {

            String generatedPassword = null;

            try {

                MessageDigest md = MessageDigest.getInstance("MD5");// Create MessageDigest instance for MD5

                md.update(passwordToHash.getBytes());  //Add password bytes to digest

                byte[] bytes = md.digest(); //Get the hash's bytes

                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format

                StringBuilder sb = new StringBuilder();

                for(int i=0; i< bytes.length ;i++) {

                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }


                generatedPassword = sb.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            return generatedPassword;
        }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {

        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

        byte[] salt = new byte[16];

        sr.nextBytes(salt);//Get a random salt

        return salt;
    }

}

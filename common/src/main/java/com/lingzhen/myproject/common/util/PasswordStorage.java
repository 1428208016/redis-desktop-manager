package com.lingzhen.myproject.common.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * 密码加密工具类-PBKDF2算法
 * @date 2019-11-01
 * 源 https://github.com/defuse/password-hashing
 */
public class PasswordStorage {

    @SuppressWarnings("serial")
    static private class InvalidHashException extends Exception {
        public InvalidHashException(String message) {
            super(message);
        }
        public InvalidHashException(String message, Throwable source) {
            super(message, source);
        }
    }

    @SuppressWarnings("serial")
    static private class CannotPerformOperationException extends Exception {
        public CannotPerformOperationException(String message) {
            super(message);
        }
        public CannotPerformOperationException(String message, Throwable source) {
            super(message, source);
        }
    }
    //使用PKCS #5 v2.0.中提供的基于密码的密钥派生功能构造秘密密钥。
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // These constants may be changed without breaking existing hashes.
    // 这些常数可以在不破坏现有散列的情况下进行更改。
    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 18;
    private static final int PBKDF2_ITERATIONS = 64000; //PBKDF2迭代

    // These constants define the encoding and may not be changed.
    // 这些常量定义编码，不能更改。
    private static final int SALT_INDEX = 0;
    private static final int PBKDF2_INDEX = 1;

    public static String createHash(String password) {
        return createHash(password.toCharArray());
    }

    private static String createHash(char[] password) {
        String parts = "";
        try{
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_BYTE_SIZE];
            random.nextBytes(salt);

            // Hash the password
            byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
            //int hashSize = hash.length;

            // format: algorithm:iterations:hashSize:salt:hash
            parts = toBase64(salt) + ":" + toBase64(hash);
        } catch (CannotPerformOperationException cpoe) {

        }
        return parts;
    }

    public static boolean verifyPassword(String password, String correctHash) {
        return verifyPassword(password.toCharArray(), correctHash);
    }

    //throws CannotPerformOperationException, InvalidHashException
    private static boolean verifyPassword(char[] password, String correctHash) {
        // Decode the hash into its parameters
        String[] params = correctHash.split(":");

        byte[] salt = null;
        try {
            salt = fromBase64(params[0]);   //盐
        } catch (Exception ex) {
            return false;
        }

        byte[] hash = null;
        try {
            hash = fromBase64(params[1]);   //hash
        } catch (Exception e) {
            return false;
        }
        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = null;
        try{
            testHash = pbkdf2(password, salt, PBKDF2_ITERATIONS, hash.length);
        }catch (Exception e){
            return false;
        }
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b){
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws CannotPerformOperationException {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            throw new CannotPerformOperationException("Hash algorithm not supported.", ex);
        } catch (InvalidKeySpecException ex) {
            throw new CannotPerformOperationException("Invalid key spec.",ex);
        }
    }

    private static byte[] fromBase64(String hex) throws Exception{
        return Base64.getDecoder().decode(hex);
    }

    private static String toBase64(byte[] array){
        return Base64.getEncoder().encodeToString(array);
    }

}
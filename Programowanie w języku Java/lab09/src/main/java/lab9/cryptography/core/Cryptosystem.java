package lab9.cryptography.core;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>
 * Helpful link:
 * 
 * {@link https://dzone.com/articles/java-cryptography-simplified-part-1)}
 * 
 * @author PSzynal
 * @version 1.0
 * 
 */
public class Cryptosystem {

	/**
	 * 
	 * <p>
	 * DiffieHellman - Diffie–Hellman key exchange
	 * <p>
	 * DSA - Digital Signature Algorithm
	 * <p>
	 * RSA - Rivest–Shamir–Adleman
	 * 
	 * @author PSzynal
	 */
	public enum EncryptionMethod {
		RSA, DSA, CBC;
	}

	/*
	 * Cryptographic Extension (JCE) framework.
	 */
	private Cipher cipher;

	public Cryptosystem(String method) throws NoSuchAlgorithmException, NoSuchPaddingException {
		// this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		this.cipher = Cipher.getInstance(method);
	}

	/**
	 * TEST
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String encrypted = "test/text_encrypted.txt";
		String decrypted = "test/text_decrypted.txt";
		EncryptionMethod method = EncryptionMethod.RSA;
		Cryptosystem ac = new Cryptosystem(method.name());
		PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey", method.name());
		PublicKey publicKey = ac.getPublic("KeyPair/publicKey", method.name());
		if (new File("test/text.txt").exists()) {
			ac.encryptFile(ac.getFileInBytes(new File("test/text.txt")), new File(encrypted), privateKey);
			ac.decryptFile(ac.getFileInBytes(new File(encrypted)), new File(decrypted), publicKey);
		} else {
			System.out.println("Create a file text.txt under folder KeyPair");
		}
	}

	public PrivateKey getPrivate(String filename, String method) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(method);
		return keyFactory.generatePrivate(encodedKeySpec);
	}

	public PublicKey getPublic(String filename, String method) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(method);
		return keyFactory.generatePublic(spec);
	}

	public void encryptFile(byte[] input, File output, PrivateKey key) throws IOException, GeneralSecurityException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		writeToFile(output, this.cipher.doFinal(input));
	}

	public void decryptFile(byte[] input, File output, PublicKey key) throws IOException, GeneralSecurityException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		writeToFile(output, this.cipher.doFinal(input));
	}

	private void writeToFile(File output, byte[] toWrite)
			throws IllegalBlockSizeException, BadPaddingException, IOException {
		FileOutputStream outputStream = new FileOutputStream(output);
		outputStream.write(toWrite);
		outputStream.flush();
		outputStream.close();
	}

	public String encryptText(String msg, PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}

	public String decryptText(String msg, PublicKey key)
			throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
	}

	public byte[] getFileInBytes(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		byte[] fbytes = new byte[(int) f.length()];
		fis.read(fbytes);
		fis.close();
		return fbytes;
	}

}

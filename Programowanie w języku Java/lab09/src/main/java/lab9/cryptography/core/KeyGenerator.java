package lab9.cryptography.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class KeyGenerator {

	private KeyPairGenerator keyPairGenerator;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public KeyGenerator(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
		this.keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		this.keyPairGenerator.initialize(keylength);
	}

	/**
	 * This app crfeate key pair (a public key and a private key). It does not
	 * enforce any security, and, when initialized, should be treated like a
	 * PrivateKey.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = new KeyGenerator(1024);
			keyGenerator.createKeys();
			System.out.println(keyGenerator);
			keyGenerator.writeToFile("KeyPair/publicKey", keyGenerator.getPublicKey().getEncoded());
			System.out.println("A PublicKey has been created in 'KeyPair/publicKey'");
			keyGenerator.writeToFile("KeyPair/privateKey", keyGenerator.getPrivateKey().getEncoded());
			System.out.println("A PrivateKey key has been created in 'KeyPair/publicKey'");
		} catch (NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void createKeys() {
		this.pair = this.keyPairGenerator.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
		try {
			writeToFile("KeyPair/publicKey", getPublicKey().getEncoded());
			writeToFile("KeyPair/privateKey", getPrivateKey().getEncoded());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}

}
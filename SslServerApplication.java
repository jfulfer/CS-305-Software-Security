package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";


@RestController
class SecureController {
	
	// Function used to return a byte array hash of the given string
	public byte[] generateHash(String string) throws NoSuchAlgorithmException {
		
		// Initialize MD object
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		// Return hash of given string using MD object
		byte[] hash = md.digest(string.getBytes());
		
		// Return byte array hash
		return hash;
	}
	
	// Function used to convert byte array to hash string
	public String bytesToString(byte[] byteArr) {
		
		// Initialize String Builder object
		StringBuilder hexString = new StringBuilder();
		
		// Iterate through array and use predefined toHexString() function of int type
		for (byte i : byteArr) {
			int decimal = (int)i & 0XFF;
			String hex = Integer.toHexString(decimal);
			
			if (hex.length() % 2 == 1) {
				hex = "0" + hex;
			}
			hexString.append(hex);
		}
		
		// Convert String Builder object to String and return
		return hexString.toString();
	}
	
	@RequestMapping("/hash")
	public String displayHash() throws NoSuchAlgorithmException {
		
		// Example string data
		String data = "Hello World Check Sum!";
		
		// Perform hash function and return byte array
		byte[] hash = generateHash(data);
		
		// Convert byte array to string
		String hashString = bytesToString(hash);
		
		// Display hash string and hashing algorithm used
		return "<p>Given String: " + data + "\n <p>SHA-256: " + hashString + "\n <p>Jeff Fulfer";
	}
}


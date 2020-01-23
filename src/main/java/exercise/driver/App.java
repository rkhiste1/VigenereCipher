package exercise.driver;

import exercise.vigenere.VigenereCipher;

public class App {

    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Exact 3 parameters required - [action] [key] [target]");
            System.exit(1);
        }

        VigenereCipher vigenereCipher = new VigenereCipher();
        String action, key, target;
        action = args[0];
        key = args[1];
        target = args[2];

        if ("encrypt".equalsIgnoreCase(action)) {
        	System.out.println(vigenereCipher.getEncryptedText(target, key));
        } else if ("decrypt".equalsIgnoreCase(action)) {
        	System.out.println(vigenereCipher.getDecryptedText(target,key));
        } else if ("encryptDir".equalsIgnoreCase(action)) {
        	vigenereCipher.encryptDecryptDir(target, key,action);
        } else if ("decryptDir".equalsIgnoreCase(action)) {
        	vigenereCipher.encryptDecryptDir(target, key,action);
        } else {
            System.out.println("action [" + action + "] not implemented");
        }

    }
}

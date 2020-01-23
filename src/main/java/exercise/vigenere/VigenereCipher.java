package exercise.vigenere;

import java.io.File;
import java.io.IOException;

import exercise.util.FileProcessor;

public class VigenereCipher {
	

	public static final String CIPHER_CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \t\n\r~!@#$%^&*()_+-=[]\\{}|;':\",./<>?";
	
	private Character getEncryptedCipher(Character target, Character keyChar) {
		
		int resultIndex = CIPHER_CHAR_SET.indexOf(target) + CIPHER_CHAR_SET.indexOf(keyChar);
		if(resultIndex > CIPHER_CHAR_SET.length() - 1)
			resultIndex = resultIndex - CIPHER_CHAR_SET.length();
		return CIPHER_CHAR_SET.charAt(resultIndex);
		
	} 
	
	public String getEncryptedText(String target, String key) {
		
		StringBuilder result = new StringBuilder();
		int j = 0;
		for(int i = 0; i < target.length(); i++) {
			int targetIndex = CIPHER_CHAR_SET.indexOf(target.charAt(i));
		    if(targetIndex >= 0) {	//if input character is present in source set
		    	result.append(getEncryptedCipher(target.charAt(i), key.charAt(j)));
		      	j++;
		    	if(j > key.length() - 1)
		    		j = 0;
		    } else {				//else copy as-it-is
		    	result.append(target.charAt(i));
		    }
		}
		    
		return result.toString();
	}
	
	private Character getDecryptedCipher(Character target, Character keyChar) {
		int resultIndex = CIPHER_CHAR_SET.indexOf(target) - CIPHER_CHAR_SET.indexOf(keyChar);
		if(resultIndex < 0)
			resultIndex = resultIndex + CIPHER_CHAR_SET.length(); 
		return CIPHER_CHAR_SET.charAt(resultIndex);
		
	} 

	public String getDecryptedText(String target, String key) {
		
		StringBuilder result = new StringBuilder();
		int j = 0;
		for(int i = 0; i < target.length(); i++) {
			int targetIndex = CIPHER_CHAR_SET.indexOf(target.charAt(i));
		    if(targetIndex >= 0) {	//if input character is present in source set
		    	
		    	result.append(getDecryptedCipher(target.charAt(i), key.charAt(j)));
		      	j++;
		    	if(j > key.length() - 1)
		    		j = 0;
		    	} else {			//else copy as-it-is
		    		result.append(target.charAt(i));
		    	}
		    }
		    
		    return result.toString();
	}
	

	
	public void encryptDecryptDir(String dirPath, String key, String action) {
		
		File files = new File(dirPath);
		File[] listOfFiles = files.listFiles();
		if(listOfFiles.length > 0) {
			String outputDir ="";
			if("encryptDir".equalsIgnoreCase(action))
				outputDir = dirPath + ".encrypted";
			else if("decryptDir".equalsIgnoreCase(action))
				outputDir = dirPath + ".decrypted";
			File output = new File(outputDir);
			output.mkdir();
			encryptDecryptFiles(listOfFiles, key,outputDir,action);
		}	
	}

	private void encryptDecryptFiles(File[] listOfFiles, String key, String outputDir, String action) {

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	File outputfile = new File(outputDir + "/" + file.getName());
		        System.out.println(file.getName());
		        System.out.println(outputfile.getName());

		    	FileProcessor fileProcessor = new FileProcessor(file.getAbsolutePath(), outputfile.getAbsolutePath());
				
					if("encryptDir".equalsIgnoreCase(action))
						encryptFile(fileProcessor,key);
					else if("decryptDir".equalsIgnoreCase(action))
						decryptFile(fileProcessor,key);

		    } else if(file.isDirectory()) {
		    	String folderpath = outputDir + "/" + file.getName();
				File folder = new File(folderpath);
				folder.mkdir();
		    	encryptDecryptFiles(file.listFiles(), key, folderpath,action);
		    }
		}

	}

	private void decryptFile(FileProcessor fileProcessor, String key) {
		int j =0;
        int target;
		try {
			while((target=fileProcessor.readCharFromFile())!= -1 )
			{
				int targetIndex = CIPHER_CHAR_SET.indexOf((char)target);
			    if(targetIndex >= 0) {
			    	fileProcessor.writeCharToFile((getDecryptedCipher((char)target, key.charAt(j))));
			      	j++;
			    	if(j > key.length() - 1)
			    		j = 0;
			    } else {
			    	fileProcessor.writeCharToFile(target);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			fileProcessor.closeStream();
		}
				
	}

	private void encryptFile(FileProcessor fileProcessor, String key) {
		// TODO Auto-generated method stub
		int j =0;
        int target;
		try {
			while((target=fileProcessor.readCharFromFile())!= -1 )
			{
				int targetIndex = CIPHER_CHAR_SET.indexOf((char)target);
			    if(targetIndex >= 0) {
			    	fileProcessor.writeCharToFile((getEncryptedCipher((char)target, key.charAt(j))));
			      	j++;
			    	if(j > key.length() - 1)
			    		j = 0;
			    } else {
			    	fileProcessor.writeCharToFile(target);
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			fileProcessor.closeStream();
		}
		
	}

	


}

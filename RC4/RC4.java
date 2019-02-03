public class RC4 {
  
   public static void main(String[] plainTextString){
    
    byte[] plainText = new byte[plainTextString.length];
    byte[] cipherText = new byte[plainTextString.length];
    byte[] key = {4,0,1,1,5,3,1,9}; 
    int keySize = key.length;

    byte[] K = new byte[32]; 
    byte[] S = new byte[32];     
    
    //change plainText from string
    convertToBytes(plainText, plainTextString);
    
    //set up the byte arrays
    initialize(S,K,key,keySize);

    System.out.print("Ciphertext is: ");
    
    //encrypt message
    encrypt(S,K, plainText,cipherText);
    
  }
  
  public static void convertToBytes(byte[] plainText,String[] plainTextString){
    for (int i = 0; i < plainTextString.length; i++){
      plainText[i] = (byte) (Integer.parseInt(plainTextString[i], 16));;
    }
  }
  
  public static void initialize(byte[] S, byte[] K, byte[] key, int keySize){
    for (int i = 0; i < 32; i++){
      S[i] = (byte) i;
      K[i] = key[i % keySize];
    }
    
    int j = 0;
    
    for (int i = 0; i < 32; i++){
      j = (j + S[i] + K[i]) % 32;
      byte temp = S[i];
      S[i] = S[j];
      S[j] = S[i];
    }
    
    int i = 0;
    j = 0;
    
    //get rid of first 256 items in stream for extra security
    for (int k = 0; k < 256; k++){
      i = (i + 1) % 32;
      j = (j + S[i]) % 32;
      byte temp = S[i];
      S[i] = S[j];
      S[j] = S[i];
    }
  }
  
  public static void encrypt(byte[] S, byte[] K, byte[] plainText,byte[] cipherText){
    int i= 0;
    int j = 0;
    int t, nextByteInStream;
    
    //generate stream for each item in plaintext
    for (int k = 0; k < plainText.length; k++){
      i = (i + 1) % 32;
      j = (j + S[i]) % 32;
      byte temp = S[i];
      S[i] = S[j];
      S[j] = S[i];
      
      t = (S[i] + S[j]) % 32;
      //get next item in stream
      nextByteInStream = S[t];     
      
      //XOR plaintext with stream
      cipherText[k] = (byte) (plainText[k] ^ nextByteInStream);
      System.out.print(String.format("%x", cipherText[k]) + " ");
    }
    System.out.println();    
  }
}
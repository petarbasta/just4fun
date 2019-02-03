import java.util.ArrayList;

public class Message {
  
  public String message;
  public int lengthOfMessage;
  public ArrayList<Integer> array = new ArrayList<Integer>();
  public ArrayList<Integer> gcdArray = new ArrayList<Integer>();
  public ArrayList<Integer> key = new ArrayList<Integer>();
  public ArrayList<Integer> finalKey = new ArrayList<Integer>();
  public String finalCharKey = "";
  
  
  public Message (String m){
    message = m;
    lengthOfMessage = m.length();
    this.makeValid();

  }
  
  public Message (String m, boolean b){
    message = m;
    lengthOfMessage = m.length();
  }
  
  /**
   * makeValid modifies message to remove any character that is not a letter and turn Upper Case into Lower Case
   */
  public void makeValid(){
    String tempMessage = "";
    for (int i = 0; i < lengthOfMessage; i++) {
      if (message.charAt(i) <= 'z' && message.charAt(i) >= 'a') {
        tempMessage += message.charAt(i);
      }
      else if (message.charAt(i) <= 'Z' && message.charAt(i) >= 'A') {
        tempMessage += (char)(message.charAt(i) + 32);
      }
    }
    
    message = "";
    
    for (int i = 0; i < tempMessage.length(); i++) {
      message += tempMessage.charAt(i);
    }
    lengthOfMessage = message.length();
  }
  
  /**
   * prints the string message
   */
  public void print(){
    System.out.println(message);
  }
  
  /**
   * caesarCipher implements the Caesar cipher : it shifts all letter by the number 'key' given as a parameter.
   */
  public void caesarCipher(int key){
    String tempMessage = "";
    for (int i = 0; i < lengthOfMessage; i++) {
      int myChar = message.charAt(i) - 97 + key;
      while (myChar < 0) {
        myChar += 26;
      }
      tempMessage += (char)((myChar %26) +97);
    }
    
    message = "";
    
    for (int i = 0; i < tempMessage.length(); i++) {
      message += tempMessage.charAt(i);
    }  
  }
  
  public void caesarDecipher(int key){
    this.caesarCipher(- key);
  }
  
  /**
   * caesarAnalysis breaks the Caesar cipher
   */
  public void caesarAnalysis(){
    char maxChar = ' ';
    int maxCount = 0;
    for (int i = 97 ; i < 123; i++) {
      int currCount = 0;
      for (int j = 0; j < message.length(); j++) {
        if (message.charAt(j) == ((char)(i))){
          currCount++;
        }
      }
      if (currCount > maxCount) {
        maxCount = currCount;
        maxChar = ((char)(i));
      }
    }
    int tempKey = maxChar - 'e';
    while (tempKey < 0) {
      tempKey += 26;
    }
    finalCharKey += ((char)(tempKey + 97));
    finalKey.add(tempKey);
    
  }
  
  /**
   * vigenereCipher implements the Vigenere Cipher : it shifts all letter from message by the corresponding shift in the 'key'
   */
  public void vigenereCipher (ArrayList<Integer> key){
    String tempMessage = "";
    for (int i = 0; i < message.length(); i++) {
      int myChar = message.charAt(i) - 97 + key.get(i%key.size());
      while (myChar < 0) {
        myChar += 26;
      }
      tempMessage += (char)((myChar % 26) +97);
    }
    
    message = "";
    
    for (int i = 0; i < tempMessage.length(); i++) {
      message += tempMessage.charAt(i);
    }  
  }
  
  /**
   * vigenereDecipher deciphers the message given the 'key' according to the Vigenere Cipher
   */
  public void vigenereDecipher (ArrayList<Integer> key){
    for(int i = 0; i < key.size(); i++){
      key.set(i, - key.get(i));
    }
    vigenereCipher(key);
  }
  
  public void vigenereAnalysis() {
    
    //make array
    for (int i = 0; i < message.length()-2; i++) {
      for (int j = i+3 ; j < message.length()-2; j++) {
        if (message.charAt(i) == message.charAt(j)){
          if (message.charAt(i+1) == message.charAt(j+1)){
            if (message.charAt(i+2) == message.charAt(j+2)){
              array.add(j-i);
            }
          }
        }
      }
    }
    
    //make gcdArray
    for (int i = 0; i < array.size(); i++) {
      for (int j = i+1; j <array.size()-1; j++) {
        gcdArray.add(GCD(array.get(i),array.get(j)));
      }
    }
    
    
    int keyLength = maxInt();
    ArrayList[][] nArrays = new ArrayList[keyLength][];
    
    String tempMessage = message;
    
    for (int i = 0; i < keyLength; i++) {
      message = "";
      for (int j = i; j < tempMessage.length(); j += keyLength) {
        message += tempMessage.charAt(j);
      }
      caesarAnalysis();
    }
    
    message = tempMessage;
    System.out.println("Key is: " + finalCharKey);
    vigenereDecipher(finalKey);
    System.out.println("Decrypted message is: " + message);
  }
  
  public int maxInt() {
    int maxInt = -1;
    int maxCount = 0;
    for (int i = 0 ; i < gcdArray.size(); i++) {
      int currCount = 0;
      for (int j = 0; j < gcdArray.size(); j++) {
        if (gcdArray.get(j) == i) {
          currCount++;
        }
      }
      if (currCount > maxCount) {
        maxCount = currCount;
        maxInt = i;
      }
    }
    return maxInt;
  }
  
  public int GCD(int a, int b) {
    if (b==0) return a;
    return GCD(b,a%b);
  }
}
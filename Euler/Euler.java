public class Euler {
  public static void main(String[] args) {
    
    double total = 0.0;
    double each = 0.0; 
    int counter = 1;
    
    for (int i = 0 ; i < 10000000; i++) {
      each = Math.random();
      counter = 1;
      
      while (each < 1.0){
        each += Math.random();
        counter += 1;
      }
      total += counter;
    }
    
    System.out.println(total/10000000);
  }
}
    
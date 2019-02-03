public class ExpectedWaitingTime {
 
  public static void main (String args[]) {
   
    int rand = -1;
    int count = 0;
    
    for (int i=0;i<1000000;i++){
      while (rand != 4){
        rand = (int) (Math.random()*100);
        count++;
      }    
      rand = -1;
    }
    System.out.println(count/1000000);
  }
}

//probability of rolling a 4 is 1/100, expected value is 100 tries to roll a 4
import java.util.Scanner;

public class ThreadTest01 {

    static int i=200;
    public static void main(String[] args) {
 

        Thread  t1=new Thread(()->{
            synchronized("mai"){
                while(i>0){
                    System.out.println(Thread.currentThread().getName()+"购票:"+i--);
    
                }
            } 
           
        });

        Thread t2 = new Thread(()->{
            synchronized("mai"){
                while(i>0){
                    System.out.println(Thread.currentThread().getName()+"购票:"+i--);
                }
            }
          
        });

            t1.start();
            t2.start();
  


    }
    
}

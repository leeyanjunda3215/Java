import java.util.Scanner;

public class ThreadTest02 {

    public static void main(String[] args) {
        Acount acount = new Acount();
        acount.setacount(200);

        Thread  t1=new Thread(()->{
            synchronized("mai"){
                acount.chun(200);
            } 
        });

        Thread t2 = new Thread(()->{
            synchronized("mai"){
                acount.qu(50);
            }
        });

            t1.start();
            t2.start();
    }
    
}

class Acount{
    int acountnum;
    public Acount(){
        
    }

    public setacount(int num){
        this.acountnum = num;
    }

    public int chun(int num){
      return  this.acountnum += num;
    }
    public int qu(int num){
        return this.acountnum -= num;
    }

}

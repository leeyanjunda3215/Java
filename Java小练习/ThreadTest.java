import java.util.Scanner;

public class ThreadTest {


    public static void main(String[] args) {


        System.out.println("输入要进行阶乘的值：");
        Scanner s = new Scanner(System.in);
        int j = s.nextInt();
        int x = s.nextInt();
        System.out.println(Thread.currentThread());

        new Thread(()->{
            
            int num=1;
            for(int i=1;i<=j;i++){
                num=i*num;
            }
            System.out.println(Thread.currentThread()+":"+j+"的阶乘是："+num);
        }).start();


        new Thread(()->{
            int num=1;
            for(int i=1;i<=x;i++){
                num=i*num;
            }
            System.out.println(Thread.currentThread()+":"+x+"的阶乘是："+num);
        }).start();


    }
    
}

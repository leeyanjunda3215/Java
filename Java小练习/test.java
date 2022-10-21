import javax.swing.*;
import java.awt.*;
public class test extends JFrame{
   
    public test(){
        initFrame();
    }
    public void initFrame(){
        setSize(300,150);
        setTitle("对话");

        GridLayout g = new GridLayout(3,1);
        setLayout(g);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        add(p1);
        add(p2);
        add(p3);

        FlowLayout f = new FlowLayout(FlowLayout.RIGHT,10,5);
        p3.setLayout(f);
        JLabel l_name = new JLabel("姓 名：");
        JTextField t_name = new JTextField(20);
        p1.add(l_name);
        p1.add(t_name);

        JLabel l_pwd = new JLabel("密 码：");
        JTextField t_pwd = new JTextField(20);
        p2.add(l_pwd);
        p2.add(t_pwd);


        JButton j_ok = new JButton("确 认");
        JButton j_quit = new JButton("退 出");
        p3.add(j_ok);
        p3.add(j_quit);

         j_ok.addActionListener(actionEvent -> {
             String name = t_name.getText();
             String pwd = t_pwd.getText();
             System.out.println(name);
             System.out.println(pwd);
             if (name.equals("小明") && pwd.equals("123@ED")){
                   JOptionPane.showMessageDialog(null,"用户认证完毕！");
              }else {
                   JOptionPane.showMessageDialog(null,"用户认证失败！");
                }
            });
         j_quit.addActionListener(actionEvent -> {
             System.exit(0);
         });






        setVisible(true);
    }

    public static void main(String[] args) {
         new test();
    }
}

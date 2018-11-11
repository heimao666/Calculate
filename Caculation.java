package Caculate;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class Caculation extends JFrame {
    private JTextArea Text=new JTextArea();
   // private JTextField text = new JTextField();
    private String[] lab={"7", "8", "9", "*","4", "5", "6",
            "/", "1", "2", "3", "+","C", "0", "=","-"};
    private double Num=0;//计算的第一个数
    private double Num2=0;//计算的第二个数
    private JButton button[]=new JButton[lab.length];//按钮
    private boolean k1=false;//判断按键前输入是否是数字
    private double result;//中间计算结果
    private char operator;//运算符

    Caculation(){
        this.setBounds(300, 200, 300, 250);	//窗体大小
        this.setTitle("计算器v1.0");	//窗体名称this.add(text,BorderLayout.NORTH);
        this.setLayout(new BorderLayout());
        this.add(Text, BorderLayout.NORTH);
        Text.setFont(new Font("微雅软黑", Font.BOLD + Font.ITALIC, 15));
        JPanel jp=new JPanel();
        // 用网格布局器，4行，4列的网格
        GridLayout gl=new GridLayout(4,4);
        jp.setLayout(gl);

        for (int i = 0; i < lab.length; i++) {
            button[i] = new JButton(lab[i]); //创建按钮
            jp.add(button[i]);
            button[i].setBorder(BorderFactory.createRaisedBevelBorder());
            button[i].setFont(new Font("粗体", Font.PLAIN, 15));
        }
        this.add(jp);
        for (int i = 0; i < lab.length; i++) {
            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String buttonName=e.getActionCommand();

                    if(buttonName.equals(lab[3])&&!k1) {//lab[0]="*";
                        k1=true;
                        operator='*';
                    }
                    else if(buttonName.equals(lab[7])&&!k1){//lab[1]="/";
                        k1=true;
                        operator='/';

                    }
                    else if(buttonName.equals(lab[11])&&!k1){//lab[11]="+";
                        k1=true;
                        operator='+';
                    }
                    else if(buttonName.equals(lab[12])){
                        Num=0;
                        Num2=0;
                        result=0;
                        k1=false;
                        Text.setText("");
                    }
                    else if(buttonName.equals(lab[14])&&!k1){//lab[14]="=";
                        k1=true;
                        switch(operator) {
                            case '+':
                                result = Num + Num2;
                                break;
                            case '-':
                                result = Num - Num2;
                                break;
                            case '*':
                                result = Num * Num2;
                                break;
                            case '/':
                                result = Num / Num2;
                                break;
                        }
                        if(Num2==0){
                            Text.setText("除数不能为0");

                        }
                        else {
                            Text.setText(String.valueOf(result));
                        }

                        Num=result;
                        k1=false;
                    }
                    else if(buttonName.equals(lab[15])&&!k1){//lab[15]="-";
                        k1=true;
                        operator='-';
                    }
                    else{
                        if(!k1) {
                            Text.setText(Text.getText() + buttonName);
                            Num = getnumber();
                        }else{
                            Text.setText(buttonName);
                            Num2=getnumber();
                            k1=false;
                        }
                    }
                }
            });
        }
        //this.pack();//固定窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private double getnumber(){
        double num=0;
        try {
            num= Double.valueOf(Text.getText()).doubleValue();
        } catch (NumberFormatException e) {
        }
        return num;
    }
    public static void main(String[] args){
        new Caculation();
    }
}

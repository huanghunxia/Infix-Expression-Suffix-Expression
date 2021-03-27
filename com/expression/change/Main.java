package com.expression.change;

import java.util.Scanner;

public class Main {
    //主方法类
    public static void main(String[] args){
        boolean flag1=false;
        boolean flag2=false;
        String choose="";
        menu();
        while(true){
            if(!flag2)
                choose=input();
            switch (choose){
                case "1":
                    flag2 = true;
                    System.out.print("请输入中缀表达式：");
                    String infix=input();
                    Infix_suffix infix_suffix=new Infix_suffix(infix);
                    flag1 = infix_suffix.change().equals("") ? false : true;
                    break;
                case "2":
                    flag2 = true;
                    System.out.print("请输入后缀表达式：");
                    String suffix=input();
                    Suffix_infix suffix_infix=new Suffix_infix(suffix);
                    flag1 = suffix_infix.change().equals("") ? false : true;
                    break;
                default:
                    System.out.print("选项输入错误,请重新输入选择：");
                    break;
            }
            if(flag1)
                break;
            else
                continue;
        }


    }
    public static void menu(){
        System.out.println("—————————————————欢迎来到表达式转换程序—————————————————");
        System.out.println("1.中缀表达式转后缀表达式(不需要结尾符号)");
        System.out.println("2.后缀表达式转中缀表达式(分隔符使用&)");
        System.out.print("请输入你的选择：");
    }
    public static String input(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}

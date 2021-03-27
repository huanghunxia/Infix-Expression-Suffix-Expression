package com.expression.change;

import java.util.HashMap;

public class Suffix_infix {
    //后缀转中缀类
    private String suffix;
    private HashMap<Character,Integer> map;
    public Suffix_infix(String suffix){
        this.suffix=suffix;
        this.map=new HashMap<Character,Integer>();
        this.map.put('*',1);
        this.map.put('/',2);
        this.map.put('+',3);
        this.map.put('-',4);
        //利用map设置运算符优先级
    }
    public String change(){
        //把后缀表达式变成中缀表达式
        String infix="";
        String[] datas = suffix.split("&");
        if(!check(datas)){
            System.out.println("后缀表达式输入错误，请重新输入正确表达式(分隔符号为“&”)：");
            return "";
        }
        MyStack stack = new MyStack();
        for(int i=0;i<datas.length;i++){
            if(datas[i].charAt(0)>='0'&&datas[i].charAt(0)<='9'){
                //判断是数字还是运算符
                stack.push(datas[i]);
            }else{
                String s2=(String)stack.pop();
                String s1=(String)stack.pop();
                infix="("+s1+datas[i]+s2+")";
                stack.push(infix);
                //拼接表达式后重新入栈
            }
        }
        System.out.println("\n原后缀表达式为："+suffix+"\n转为中缀表达式为："+infix);
        System.out.printf("该表达式计算结果为：%.1f",calc(suffix));
        return infix;
    }
    public double calc(String suffix){
        //利用后缀表达式计算出结果
        double result=0;
        String[] numbers = suffix.split("&");
        MyStack stack = new MyStack();
        for(int i = 0;i<numbers.length;i++){
            char ch=numbers[i].charAt(0);
            if(map.get(ch)==null) {
                //利用map判断是否为数字,是的话直接入栈
                stack.push(numbers[i]);
            } else{
                double num2=Double.parseDouble((String)stack.pop());
                double num1=Double.parseDouble((String)stack.pop());
                switch(ch){
                    case '*':
                        result=num1*num2;
                        stack.push(String.valueOf(result));
                        break;
                    case '/':
                        result=num1/num2;
                        stack.push(String.valueOf(result));
                        break;
                    case '+':
                        result=num1+num2;
                        stack.push(String.valueOf(result));
                        break;
                    case '-':
                        result=num1-num2;
                        stack.push(String.valueOf(result));
                        break;
                }
            }
        }
        result = Double.parseDouble((String)stack.pop());
        return result;
        //输出最后计算结果
    }
    public boolean check(String[] datas){
        //检查表达式是否正确
        if(datas.length<3)
            return false;
        String s="*/+-0123456789.";
        int par_count=0;
        int num=0;
        for(int i=0;i<datas.length;i++){
            if(!datas[i].equals("")){
                String ch=String.valueOf(datas[i].charAt(0));
                if(s.indexOf(ch)==-1)
                    return false;
                if(datas[i].charAt(0)>='0'&&datas[i].charAt(0)<='9'){
                    num++;
                } else{
                    if(datas[i].length()!=1)
                        return false;
                    par_count++;
                }

            }else{
                return false;
            }

        }
        return num==(par_count+1);
    }
}

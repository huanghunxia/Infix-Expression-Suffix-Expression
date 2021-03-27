package com.expression.change;

import java.util.HashMap;
public class Infix_suffix {
    //中缀转后缀类
    private String infix;
    private HashMap<Character,Integer> map;
    public Infix_suffix(String infix){
        this.infix=infix;
        this.map=new HashMap<Character,Integer>();
        this.map.put('*',1);
        this.map.put('/',2);
        this.map.put('+',3);
        this.map.put('-',4);
        //利用map设置运算符优先级
    }
    public String change(){
        //把中缀表达式变成后缀表达式
        int count=0;
        //记录栈的出现次数
        char ch;
        String suffix="";
        MyStack stack = new MyStack();
        char[] datas=infix.toCharArray();
        //字符串转char数组
        if(!check(datas)){
            System.out.println("中缀表达式输入错误，请重新输入正确表达式(括号使用英文，无需结尾符号)");
            return "";
        }
        for(int i=0;i<datas.length;i++){
            if((datas[i]>='0'&&datas[i]<='9')||datas[i]=='.'){
                //判断是否为数字
                suffix+=String.valueOf(datas[i]);
            } else if(datas[i]=='(') {
                //遇到括号直接入栈
                stack.push(datas[i]);
                count++;
            } else if(datas[i]==')'){
                //检测是否为)括号，是的话直接读栈，直到遇到（括号
                while(!stack.isEmpty()&&(char)stack.peek()!='('){
                    suffix+="&";
                    ch=(char)stack.pop();
                    suffix+=String.valueOf(ch);
                }
                stack.pop();
                count--;
            } else{
                suffix+="&";
                if(stack.isEmpty()||count!=0){
                    //如果栈为空或者出现了括号就直接入栈
                    stack.push(datas[i]);
                }else{
                    while(!stack.isEmpty()&&map.get((char)stack.peek())<map.get(datas[i])){
                        //如果栈顶的运算符优先级大于读取的运算符优先级就将其出栈
                        ch=(char)stack.pop();
                        suffix+=String.valueOf(ch);
                        suffix+="&";
                    }
                    stack.push(datas[i]);
                }
            }
        }
        while(!stack.isEmpty()){
            //将栈内剩余的运算符出栈
            suffix+="&";
            ch=(char)stack.pop();
            suffix+=String.valueOf(ch);
        }
        //suffix=suffix.substring(0,suffix.length()-1);
        //时间复杂度（On2）
        System.out.println("\n原中缀表达式为："+infix+"\n转为后缀表达式为："+suffix);
        System.out.printf("该表达式计算结果为：%.1f",calc(suffix));
        return suffix;
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
                //使用double计算小数
                //出栈两个数据，计算后重新入栈
//                int num2=Integer.parseInt((String)stack.pop());
//                int num1=Integer.parseInt((String)stack.pop());
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
    public boolean check(char[] datas){
        //利用字符串判断输入表达式是否正确
        if(datas.length<3)
            return false;
        String s="()*/+-0123456789.";
        char ch=' ';
        for(int i=0;i<datas.length;i++){
            String c=String.valueOf(datas[i]);
            if(s.indexOf(c)==-1)
                return false;
            if(ch==datas[i]&&map.get(datas[i])!=null)
                return false;
            //判断同一运算符是否依次出现两次以上，是的话表达式错误
            ch=datas[i];
        }
        return true;
    }


}

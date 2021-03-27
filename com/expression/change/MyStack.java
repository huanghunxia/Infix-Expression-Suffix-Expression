package com.expression.change;

public class MyStack {
    //手写栈
    private ListNode topnode;
    private int len;
    public void push(Object data){
        if(topnode == null){
            topnode = new ListNode(data);
        }else{
            ListNode node=new ListNode(data);
            node.next=topnode;
            topnode=node;
        }
        len++;
    }
    public Object pop(){
        if(!isEmpty()){
            Object data=topnode.data;
            topnode=topnode.next;
            len--;
            return data;
        }
        return null;
    }
    public Object peek(){
        if(!isEmpty())
            return topnode.data;
        return null;
    }
    public boolean isEmpty(){
        return len==0;
    }
}

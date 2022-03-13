package Model.adt;

import java.util.ArrayList;
import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    Stack<T> myStack;


    public MyStack(){
        myStack = new Stack<T>();
    }

    @Override
    public T pop() {
        return myStack.pop();
    }

    @Override
    public void push(T v) {
        myStack.push(v);
    }

    @Override
    public String toString(){
        return myStack.toString();
    }

    @Override
    public ArrayList<T> getList() {
        return new ArrayList<T>(myStack);
    }

    @Override
    public boolean isEmpty() {
        return myStack.isEmpty();
    }
}

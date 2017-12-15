/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import java.util.Stack;

/**
 *
 * @author Shreyas
 */
public class LimitedStack<T> extends Stack<T> {
    private final int maxSize;
    public LimitedStack(int size) {
        super();
        this.maxSize=size;
    }
    @Override
    public T push(T object) {
        while (this.size()>=maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}
package com.Utils;


public interface Observer<E> {
    void update(Observable<E> observable);
}
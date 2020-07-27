package com.sunil.core;

import java.io.*;

enum Singleton {
    INSTANCE;

    int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

public class SingletonDemo {

    /**
     * Different ways to create singleton
     * 1. Using Enum
     * 2. Using static method
     *
     */

    public static <T> T getSerDe(T object) {
        T instance =  null;
        try {
            // serialize
            FileOutputStream fileOutputStream = new FileOutputStream("out.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(object);
            outputStream.close();
            fileOutputStream.close();

            // deserialize
            FileInputStream fileInputStream = new FileInputStream("out.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            instance = (T) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static void createSingletonUsingEnum() {
        Singleton singleton = Singleton.INSTANCE;
        singleton.setValue(8979); // some random int, just to check whether this
        // value is preserved during SerDe operation.

        // case 1: should respect SerDe operation
        Singleton singletonClone = getSerDe(singleton);
        if (singleton == singletonClone) {
            System.out.println("original singleton value: " + singleton.getValue() + "  hashcode :" + singleton.hashCode());
            System.out.println("clone singleton value: " + singletonClone.getValue() + "  hashcode :" + singleton.hashCode());
            System.out.println("ENUM singleton serialize/deserialize check passed :) ");
        }

    }

    public static void main(String[] args) {
        // Method 1
        createSingletonUsingEnum();

    }


}

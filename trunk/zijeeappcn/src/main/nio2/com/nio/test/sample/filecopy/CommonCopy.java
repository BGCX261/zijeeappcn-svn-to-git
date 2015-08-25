package com.nio.test.sample.filecopy;

public class CommonCopy implements Runnable {

    String         fromFile;
    String         toFile;
    public boolean flag = false;

    public CommonCopy(String fromFile, String toFile){
        this.fromFile = fromFile;
        this.toFile = toFile;
    }

    @Override
    public void run() {

    }

}

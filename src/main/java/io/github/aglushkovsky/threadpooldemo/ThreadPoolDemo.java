package io.github.aglushkovsky.threadpooldemo;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        CopyPaster copyPaster = new CopyPaster();
        copyPaster.addFileToRead("input.txt");
        copyPaster.addFileToRead("input2.txt");
        copyPaster.addFileToRead("input3.txt");
        copyPaster.addFileToRead("input4.txt");
    }
}

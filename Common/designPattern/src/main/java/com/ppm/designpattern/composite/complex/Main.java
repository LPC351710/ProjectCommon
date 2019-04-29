package com.ppm.designpattern.composite.complex;

public class Main {

    public static void main(String[] args) {
        Dir diskC = new Folder("C");
        diskC.addDir(new File("mbaMallLog.txt"));

        Dir win = new Folder("Windows");
        win.addDir(new File("explorer.exe"));
        diskC.addDir(win);

        Dir dirPer = new Folder("PerfLogs");
        dirPer.addDir(new File("null.txt"));
        diskC.addDir(dirPer);

        Dir dirPro = new Folder("Program File");
        dirPro.addDir(new File("ftp.txt"));
        diskC.addDir(dirPro);

        diskC.print();
    }
}

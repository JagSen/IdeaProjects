/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.wreckj;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;

public class FileEventTest {
 
    public static void main(String[] args) throws InterruptedException, ParseException {
        if(args.length < 4){
            System.out.println("Needs 4 arguments. <Watched Dir> <Source Dir> <Filter> <num of files to move>");
        }
        Path pathToWatch = FileSystems.getDefault().getPath(args[0]);
        DirectoryWatcher dirWatcher = new DirectoryWatcher(pathToWatch);
        
        dirWatcher.setTargetDir(pathToWatch);
        dirWatcher.setSourceDir(FileSystems.getDefault().getPath(args[1]));
        dirWatcher.setFilterCriteria(args[2]);
        dirWatcher.setNumFiles(Integer.parseInt(args[3]));
        
        Thread dirWatcherThread = new Thread(dirWatcher);
        dirWatcherThread.start();
        System.out.println("started"); 
        // interrupt the program after 10 seconds to stop it.
        Thread.sleep(1000000);
        dirWatcherThread.interrupt();
 
    }
}

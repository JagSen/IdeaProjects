/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.wreckj;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import javafx.collections.transformation.SortedList;
 
// Simple class to watch directory events.
class DirectoryWatcher implements Runnable {
    
    boolean fillUpTargetWithFiles() throws IOException {
        int filesLeft = 0;
        for(File file : targetDir.toFile().listFiles()){
            if(file.isFile() && file.getName().startsWith(filterCriteria)){
                filesLeft++;
            }
        }
        if(numFiles > filesLeft) {
            moveAnotherFileSet((numFiles-filesLeft));
            System.out.println("\nfillUpTargetWithFiles");
            
            return true;
        }
        
        return false;
    }
    boolean moveAnotherFileSet(int num) throws IOException {
        int counter = 0;
        for (int i = 0; i < num; i++) {
            Path path = sortedFilesList.first();
            sortedFilesList.remove(path);
            System.out.println(counter++ + " : " + path);
            Path targerPath;
            
            String fileaname = path.getFileName().toString();
            String targetDirName = targetDir.toString();
            
            targerPath = FileSystems.getDefault().getPath(targetDirName+"/"+fileaname);
            Files.move(path, targerPath);
        }
        System.out.println("\nmoveAnotherFile");
        return false;
    }
    
    boolean listPathsFromDir() {
        File sourceDirFile = sourceDir.toFile();
        if(sourceDirFile.isDirectory()){
            for(File file : sourceDirFile.listFiles()){
                if(file.isFile() && file.getName().startsWith(filterCriteria)){
                    sortedFilesList.add(FileSystems.getDefault().getPath(file.getAbsolutePath()));
                }
            }
        }
        int counter = 0;
        for (Path path : sortedFilesList){
        //    System.out.println(counter++ + " : " + path);
        }
        
        System.out.println("\nlistPathsFromDir");
        return false;
    }
    // print the events and the affected file
    private void printEvent(WatchEvent<?> event) throws IOException{
        Kind<?> kind = event.kind();
        Path pathChanged = (Path) event.context();
            
        if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
            System.out.println("Entry created:" + pathChanged);
            fillUpTargetWithFiles();
        } 
        if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
            System.out.println("Entry deleted:" + pathChanged);
            fillUpTargetWithFiles();
        }
        if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
            System.out.println("Entry modified:" + pathChanged);
            fillUpTargetWithFiles();
        } 
        if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
            System.out.println("Entry OVERFLOW:" + pathChanged);
            fillUpTargetWithFiles();
        }
    }
 
    @Override
    public void run() {
        try {
            listPathsFromDir();
            moveAnotherFileSet(numFiles);
            WatchService watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.OVERFLOW);
 
            // loop forever to watch directory
            while (true) {
                WatchKey watchKey;
                watchKey = watchService.take(); // this call is blocking until events are present
 
                // poll for file system events on the WatchKey
                for (final WatchEvent<?> event : watchKey.pollEvents()) {
                    printEvent(event);
                }
 
                // if the watched directed gets deleted, get out of run method
                if (!watchKey.reset()) {
                    System.out.println("No longer valid");
                    watchKey.cancel();
                    watchService.close();
                    break;
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("interrupted. Goodbye");
            return;
        } catch (IOException ex) {
            ex.printStackTrace();  // don't do this in production code. Use a loggin framework
            return;
        }
    }
     
    private Path path;

    public Path getSourceDir() {
        return sourceDir;
    }

    public String getFilterCriteria() {
        return filterCriteria;
    }
    
    public void setSourceDir(Path sourceDir) {
        this.sourceDir = sourceDir;
    }

    public void setFilterCriteria(String filterCriteria) {
        this.filterCriteria = filterCriteria;
    }
 
    public void setNumFiles(int numFiles) {
        this.numFiles = numFiles;
    }

    public int getNumFiles() {
        return numFiles;
    }
 
    public DirectoryWatcher(Path path) {
        this.path = path;
    }
    
    public void setTargetDir(Path targetDir) {
        this.targetDir = targetDir;
    }

    public Path getTargetDir() {
        return targetDir;
    }
    
    private String filterCriteria;
    private int numFiles;
    private Path sourceDir;
    private Path targetDir;

    SortedSet<Path> sortedFilesList = new ConcurrentSkipListSet<Path>();
}
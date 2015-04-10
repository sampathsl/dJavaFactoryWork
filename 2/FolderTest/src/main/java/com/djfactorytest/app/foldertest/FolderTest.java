/*
 * Date : 10.04.2015
 *2. Write a solution that returns an Integer with the total number of files in a given folder including any files in its’ subfolders
(if any exist).
 */
package com.djfactorytest.app.foldertest;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sampath Thennakoon
 * @date 10.04.2015
 * @version 0.1
 */
public class FolderTest {

    // Set to static as there is one logger per class.
    private static final Logger lg = Logger.getLogger(FolderTest.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean doAgain = true;
        while (doAgain) {

            Scanner in = new Scanner(System.in);
            System.out.print("Please enter the file path and press enter (To exit type 'e') : ");
            String textInput = in.nextLine();
            if (textInput != null && textInput.equalsIgnoreCase("e")) {
                return;
            }

            if (textInput != null) {
                try {
                    int fileCount = getFilesCount(textInput);
                    lg.log(Level.INFO, "Input Text Is: {0}", textInput);
                    lg.log(Level.INFO, "The file count of the path: {0}", fileCount);
                } catch (Exception ex) {
                    lg.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } else {
                lg.log(Level.INFO, "Please enter a valid file path");
            }

        }
    }

    /**
     * Returns number of files inside a given folder and its sub folders
     *
     * @param path file path
     * @return the number of files inside the folder
     */
    public static int getFilesCount(String path) {
        int count = 0;
        try {
            File fx = new File(path);
            if (!fx.exists()) {
                lg.log(Level.INFO, "The directory does not exist.");
            } else if (!fx.isDirectory()) {
                lg.log(Level.INFO, "File path is not point to a directory");
            } else {
                File[] files = fx.listFiles();
                for (File f : files) {
                    if (f.isDirectory()) {
                        count += getFilesCount(f.getAbsolutePath());
                    } else {
                        count++;
                    }
                }
            }
        } catch (Exception ex) {
            lg.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return count;
    }

}

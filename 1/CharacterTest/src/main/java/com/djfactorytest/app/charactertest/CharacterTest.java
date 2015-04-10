/*
 * Date : 10.04.2015
 * Write a method that takes a String and returns a Boolean. It should return True if the first character is an
 uppercase. You cannot use String APIs.
 */
package com.djfactorytest.app.charactertest;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sampath Thennakoon
 * @date 10.04.2015
 * @version 0.1
 */
public class CharacterTest {

    // Set to static as there is one logger per class.
    private static final Logger lg = Logger.getLogger(CharacterTest.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean doAgain = true;
        while (doAgain) {

            Scanner in = new Scanner(System.in);
            System.out.print("Please enter a text and press enter (To exit type 'e') : ");
            String textInput = in.nextLine();
            if (textInput != null && textInput.equalsIgnoreCase("e")) {
                return;
            }

            if (textInput != null) {
                try {
                    Boolean b = isFirstLetterUpperCase(textInput);
                    lg.log(Level.INFO, "Input Text Is: {0}", textInput);
                    lg.log(Level.INFO, "Is First Letter Upper Case: {0}", b);
                } catch (Exception ex) {
                    lg.log(Level.SEVERE, ex.getMessage(), ex);
                }
            } else {
                lg.log(Level.INFO, "Please enter a valid text");
            }

        }
    }

    /**
     * Returns a Boolean object checking the given text first letter is
     * uppercase or not If the first letter in uppercase it returns Boolean.TRUE
     * other wise returns Boolean.FALSE
     *
     * @param sampleText input text
     * @return boolean object checking if the given text first character is
     * upper case or not
     */
    private static Boolean isFirstLetterUpperCase(String sampleText) {
        if (sampleText != null && !sampleText.equals("") && Character.isUpperCase(sampleText.charAt(0))) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}

/*
Date : 10.04.2015
3. You are in a maze, and you need to find all possible path from an entrance to an exit. Here are the constraints:
 The maze is represented by a 2D grid.
 Spots that you can step on are represented by a 0.
 Pits that you will fall into (aka spots that you cannot step on) are represented by a 1.
 The entrance is represented by a 2.
 The exit is represented by a 3.
 Each path can only have two endpoints; entrance and exit. You cannot use the entrance or exit more than
once for each path.
 You have to step on every spot exactly once.
 You can only move like a King in chess (horizontally or vertically but not diagonally)
 */

package com.djfactorytest.app.mazegame;

import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sampath Thennakoon
 * @date 10.04.2015
 * @version 0.1
 */

public class Main {

    private static Spot[][] spots;
    private int numberOfPits = 0;
    private int result = 0;
    private Stack<String> pathTraversed = new Stack<>();
    
     // Set to static as there is one logger per class.
    private static final Logger lg = Logger.getLogger(Main.class.getName());

    public static void main(String arg[]) {
        
        boolean doAgain = true;
        while (doAgain) {
            
            Scanner in = new Scanner(System.in);
            System.out.print("Press enter two gidits of width and height of the matrix or (To exit type 'e') : ");
            String textInput = in.nextLine();
            if (textInput != null && textInput.equalsIgnoreCase("e")) {
                System.out.print("Inputs are invalid");
                return;
            }
            if(textInput == null && textInput.equals("")){
                System.out.print("Inputs are invalid");
                return;
            }
            
            String[] firstTwoInputs = textInput.split(" ");
            if(firstTwoInputs.length < 2){
                return;
            }
            int width = Integer.parseInt(firstTwoInputs[0]);
            int height = Integer.parseInt(firstTwoInputs[1]);
            
            int[][] intMatrix = new int[height][width];
            
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width ; j++){
                    System.out.print("Press enter Row" + i + "Column " + j + " Value (0 or 1 or 2 or 3) : ");
                    String spotStr = in.nextLine();
                    if(spotStr == null && spotStr.equals("")){
                        return;
                    }
                    intMatrix[i][j] = Integer.parseInt(spotStr);
                }
            }
        
            System.out.println("Your Matrix is : ");
            
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width ; j++){
                    System.out.print(intMatrix[i][j]);
                }
                System.out.println();
            }
            
            Main m = new Main();
            spots = m.addNodes(intMatrix);
            lg.log(Level.INFO, "Please wait ... ");
            m.findPaths(spots[0][0]);
            lg.log(Level.INFO, "The file count of the path: {0}", m.result);
        }
        
    }

    public Spot[][] addNodes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        spots = new Spot[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Spot node = spots[i][j];

                if (node == null) {
                    node = new Spot(matrix[i][j]);
                    spots[i][j] = node;
                }

                if (!(i - 1 < 0)) {
                    spots[i - 1][j].setDownSpot(node);
                }

                if (!((i + 1) > rows - 1)) {
                    Spot downNode = spots[i + 1][j];
                    if (downNode == null) {
                        spots[i + 1][j] = new Spot(matrix[i + 1][j]);
                    }
                    spots[i + 1][j].setUpSpot(node);
                }

                if (!(j - 1 < 0)) {
                    spots[i][j - 1].setRightSpot(node);
                }

                if (!(j + 1 > cols - 1)) {
                    Spot leftNode = spots[i][j + 1];
                    if (leftNode == null) {
                        spots[i][j + 1] = new Spot(matrix[i][j + 1]);
                    }
                    spots[i][j + 1].setLeftSpot(node);
                }

                String name = new String(new char[]{(char) (65 + i),
                    (char) (48 + j)});

                node.setName(name);

                if (node.isPit()) {
                    this.numberOfPits++;
                }
            }
        }
        return spots;
    }

    public void findPaths(Spot startNode) {
        pathTraversed.push(startNode.getName());
        Traverse(startNode.getRightSpot());
        Traverse(startNode.getDownSpot());
        Traverse(startNode.getLeftSpot());
        Traverse(startNode.getUpSpot());
    }

    private void Traverse(Spot sp) {
        
        if (sp == null) {
            return;
        }

        if (sp.isEntrance() || sp.isVisited() || sp.isPit()) {
            return;
        }

        if (sp.isExit()) {

            pathTraversed.push(sp.getName());
            if (pathTraversed.size() == (spots[0].length * spots.length) - numberOfPits) {
                //String pathNumber = "FOUNT - PATH NUMBER: " + (++result);
                //System.out.println(pathNumber);
                //System.out.println(pathTraversed.toString());
                ++result;
            }

            pathTraversed.pop();
            return;
        }

        pathTraversed.push(sp.getName());
        sp.setVisited(true);

        Traverse(sp.getRightSpot());
        Traverse(sp.getDownSpot());
        Traverse(sp.getLeftSpot());
        Traverse(sp.getUpSpot());

        sp.setVisited(false);
        pathTraversed.pop();
    }

}

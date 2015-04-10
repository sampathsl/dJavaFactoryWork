/*
 * Date : 10.04.2015
    2 0 0 0 0 0 0
    0 0 0 0 0 0 0
    0 0 0 0 0 0 0
    0 0 0 0 0 0 0
    0 0 0 0 0 0 0
    0 0 0 0 0 0 0
    0 0 0 0 3 1 1
4. What is the total possibiities for the above maze?
ANSWER IS : 40616
 */

package com.djfactorytest.app.mazegametwo;

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
            System.out.print("Press enter continue or (To exit type 'e') : ");
            String textInput = in.nextLine();
            
            if (textInput != null && textInput.equalsIgnoreCase("e")) {
                return;
            }
            
            int[][] set2 = new int[][]{{2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 3, 1, 1}
            };
        
            Main m = new Main();
            spots = m.addNodes(set2);
            lg.log(Level.INFO, "Please wait ... ");
            m.findPaths(spots[0][0]);
            lg.log(Level.INFO, "Path Count is : " , m.result);
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

package com.djfactorytest.app.mazegame;

public class Spot {

    private String name;
    private int val;
    private boolean isPitSpot;
    private boolean isVisitedSpot;
    private boolean isEntranceSpot;
    private boolean isExitSpot;

    private Spot downSpot;
    private Spot upSpot;
    private Spot leftSpot;
    private Spot rightSpot;

    public Spot(int val) {
        this.val = val;
        this.isPitSpot = (val == 1);
        this.isEntranceSpot = (val == 2);
        this.isExitSpot = (val == 3);
        this.isVisitedSpot = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPit() {
        return isPitSpot;
    }

    public boolean isVisited() {
        return isVisitedSpot;
    }

    public void setVisited(boolean isVisited) {
        this.isVisitedSpot = isVisited;
    }

    public boolean isEntrance() {
        return isEntranceSpot;
    }

    public boolean isExit() {
        return isExitSpot;
    }

    public Spot getDownSpot() {
        return downSpot;
    }

    public void setDownSpot(Spot node) {
        downSpot = node;
    }

    public Spot getUpSpot() {
        return upSpot;
    }

    public void setUpSpot(Spot node) {
        upSpot = node;
    }

    public Spot getLeftSpot() {
        return leftSpot;
    }

    public void setLeftSpot(Spot node) {
        leftSpot = node;
    }

    public Spot getRightSpot() {
        return rightSpot;
    }

    public void setRightSpot(Spot node) {
        rightSpot = node;
    }
}

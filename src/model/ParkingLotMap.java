package model;

import java.io.Serializable;

/**
 * The map of the parking lot. <br>
 */
@SuppressWarnings("unused")
public class ParkingLotMap implements Serializable {

    private static final long serialVersionUID = 2L;
    private Spot leftColumn;
    private Spot bottomRow;
    private Spot rightColumn;
    private Spot spot28;
    private int leftColumnNumber;
    private int bottomRowNumber;
    private int rightColumnNumber;

    /**
     * This is the main constructor of the class. <br>
     */
    public ParkingLotMap() {
        leftColumnNumber = 1;
        bottomRowNumber = 1;
        rightColumnNumber = 1;
        leftColumn = null;
        rightColumn = null;
        bottomRow = null;
        System.out.println("Se creo el mapa");
        setLeftColumn();
        getSpot28();
        uniteLeftAndRight();
    }

    /**
     * Create the right part of the parking lot <br>
     * <b> pre: </b><br>
     * <b> post: </b>The leftColumn attribute is initialized and therefore the virtual parking map is accessible <br>
     */
    public void setLeftColumn() {
        leftColumn = new MotorcycleSpot(-10, -9);
        setLeftColumn(leftColumn);
    }

    /**
     * Create the right part of the parking lot <br>
     * <b> pre: </b><br>
     * <b> post: </b>The leftColumn attribute is initialized and therefore the virtual parking map is accessible <br>
     *
     * @param a a spot that will vary while running the recursive method
     */
    private void setLeftColumn(Spot a) {
        if (leftColumnNumber < 13) {
            Spot b;
            int t = translatorLC(leftColumnNumber);
            if (leftColumnNumber < 5) {
                b = new MotorcycleSpot(t, t + 1);
            } else {
                b = new VehicleSpot(t);
            }
            b.setUp(a);
            a.setDown(b);
            leftColumnNumber++;
            setLeftColumn(a.getDown());
        } else if (leftColumnNumber == 13) {
            setBottomRow();
            a.setRight(bottomRow);
        }
    }

    /**
     * Create the bottom part of the parking lot <br>
     * <b> pre: </b><br>
     * <b> post: </b>The bottomRow attribute is initialized<br>
     */
    public void setBottomRow() {
        bottomRow = new VehicleSpot(10);
        setBottomRow(bottomRow);
    }

    /**
     * Create the bottom part of the parking lot <br>
     * <b> pre: </b><br>
     * <b> post: </b>The bottomRow attribute is initialized<br>
     *
     * @param a a spot that will vary while running the recursive method
     */
    private void setBottomRow(Spot a) {
        if (bottomRowNumber < 7) {
            int t = translatorBR(bottomRowNumber);
            Spot b = new VehicleSpot(t);
            b.setLeft(a);
            a.setRight(b);
            bottomRowNumber++;
            setBottomRow(a.getRight());
        } else if (bottomRowNumber == 7) {
            setRightColumn();
            a.setUp(rightColumn);
        }
    }

    /**
     * Create the right part of the parking lot <br>
     * <b> pre: </b><br>
     * <b> post: </b>The rightColumn attribute is initialized<br>
     */
    public void setRightColumn() {
        rightColumn = new VehicleSpot(17);
        setRightColumn(rightColumn);
    }

    /**
     * Create the right part of the parking lot <br>
     * <b> pre: </b><br>
     * <b> post: </b>The rightColumn attribute is initialized<br>
     *
     * @param a a spot that will vary while running the recursive method
     */
    private void setRightColumn(Spot a) {
        if (rightColumnNumber < 13) {
            int t = translatorRC(rightColumnNumber);
            Spot b = new VehicleSpot(t);
            a.setUp(b);
            b.setDown(a);
            rightColumnNumber++;
            setRightColumn(a.getUp());
        }
    }

    /**
     * Change the number in order to the number that corresponds to that spot <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the respective number for the spot<br>
     *
     * @param n the number in order
     */
    private int translatorBR(int n) {
        switch (n) {
            case 1:
                return 11;
            case 2:
                return 12;
            case 3:
                return 13;
            case 4:
                return 14;
            case 5:
                return 15;
            case 6:
                return 16;
        }
        return 0;
    }

    /**
     * Change the number in order to the number that corresponds to that spot <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the respective number for the spot<br>
     *
     * @param n the number in order
     */
    private int translatorRC(int n) {
        switch (n) {
            case 1:
                return 18;
            case 2:
                return 19;
            case 3:
                return 20;
            case 4:
                return 21;
            case 5:
                return 22;
            case 6:
                return 23;
            case 7:
                return 24;
            case 8:
                return 25;
            case 9:
                return 26;
            case 10:
                return 27;
            case 11:
                return 28;
            case 12:
                return 29;
        }
        return 0;
    }

    /**
     * Change the number in order to the number that corresponds to that spot <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the respective number for the spot<br>
     *
     * @param n the number in order
     */
    private int translatorLC(int n) {
        switch (n) {
            case 1:
                return -8;
            case 2:
                return -6;
            case 3:
                return -4;
            case 4:
                return -2;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 4;
            case 8:
                return 5;
            case 9:
                return 6;
            case 10:
                return 7;
            case 11:
                return 8;
            case 12:
                return 9;
        }
        return 0;
    }

    /**
     * Look for a spot that has the number entered <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the spot that corresponds to that number<br>
     *
     * @param index the number to look for
     * @return The respective spot
     */
    public Spot spotAt(int index) {
        System.out.println(leftColumn.getActualPosition());
        return spotAt(index, leftColumn);
    }

    /**
     * Look for a spot that has the number entered <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the spot that corresponds to that number<br>
     *
     * @param index the number to look for
     * @param spot  a spot that will vary while running the recursive method
     * @return The respective spot
     */
    private Spot spotAt(int index, Spot spot) {
        String[] comparator = spot.getActualPosition().split(" ");
        if (spot.getDown() == null) {
            if (comparator[0].equals(index + "")) {
                return spot;
            } else {
                return searchInBottomRow(index, bottomRow);
            }
        } else {
            System.out.println(comparator[0] + " hola");
            if (comparator[0].equals(index + "")) {
                return spot;
            } else if (comparator.length > 1) {
                System.out.println(comparator[1] + " hola");
                if (comparator[1].equals(index + "")) {
                    return spot;
                } else {
                    return spotAt(index, spot.getDown());
                }
            } else {
                return spotAt(index, spot.getDown());
            }
        }
    }

    /**
     * Look for a spot that has the number entered in the bottomRow <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the spot that corresponds to that number<br>
     *
     * @param index the number to look for
     * @param spot  a spot that will vary while running the recursive method
     * @return The respective spot
     */
    private Spot searchInBottomRow(int index, Spot spot) {
        String[] comparator = spot.getActualPosition().split(" ");
        if (spot.getRight() == null) {
            return searchInRightColumn(index, rightColumn);
        } else {
            if (comparator[0].equals(index + "")) {
                return spot;
            } else {
                return searchInBottomRow(index, spot.getRight());
            }
        }
    }

    /**
     * Look for a spot that has the number entered in the right column <br>
     * <b> pre: </b><br>
     * <b> post: </b>Give the spot that corresponds to that number<br>
     *
     * @param index the number to look for
     * @param spot  a spot that will vary while running the recursive method
     * @return The respective spot
     */
    private Spot searchInRightColumn(int index, Spot spot) {
        String[] comparator = spot.getActualPosition().split(" ");
        if (spot.getUp() == null) {
            if (comparator[0].equals(index + "")) {
                return spot;
            } else {
                System.out.println("Llego aqui");
                return null;
            }
        } else {
            if (comparator[0].equals(index + "")) {
                return spot;
            } else {
                return searchInRightColumn(index, spot.getUp());
            }
        }
    }

    /**
     * Join the spots on the left with those on the right <br>
     * <b> pre: </b><br>
     * <b> post: </b>setRight and setLeft are assigned correctly<br>
     */
    public void uniteLeftAndRight() {
        uniteLeftAndRight(0, leftColumn, spot28);
    }

    /**
     * Join the spots on the left with those on the right <br>
     * <b> pre: </b><br>
     * <b> post: </b>setRight and setLeft are assigned correctly<br>
     *
     * @param i     The iterator
     * @param left  The spots on the left
     * @param right The spots on the right
     */
    private void uniteLeftAndRight(int i, Spot left, Spot right) {
        if (i < 12) {
            left.setRight(right);
            right.setLeft(left);
            uniteLeftAndRight(i + 1, left.getDown(), right.getDown());
        }
    }

    /**
     * Gives the spot28 attribute <br>
     * <b> pre: </b><br>
     * <b> post: </b>Initializes the attribute and returns it<br>
     *
     * @return spot28
     */
    public Spot getSpot28() {
        return getSpot28(rightColumn);
    }

    /**
     * Gives the spot28 attribute <br>
     * <b> pre: </b><br>
     * <b> post: </b>Initializes the attribute and returns it<br>
     *
     * @param a a spot that will vary while running the recursive method
     * @return spot28
     */
    private Spot getSpot28(Spot a) {
        if (a.getActualPosition().contains(28 + "")) {
            spot28 = a;
            return spot28;
        } else {
            getSpot28(a.getUp());
            return null;
        }
    }

    /**
     * @return The left column of the map. <br>
     */
    public Spot getLeftColumn() {
        return leftColumn;
    }

    /**
     * @return The bottom row of the map. <br>
     */
    public Spot getBottomRow() {
        return bottomRow;
    }

    /**
     * @return The right column of the map. <br>
     */
    public Spot getRightColumn() {
        return rightColumn;
    }

    /**
     * @return A number in the left column of the map. <br>
     */
    public int getLeftColumnNumber() {
        return leftColumnNumber;
    }

    /**
     * @param leftColumnNumber A number in the left column of the map. <br>
     */
    public void setLeftColumnNumber(int leftColumnNumber) {
        this.leftColumnNumber = leftColumnNumber;
    }

    /**
     * @return A number in the bottom row of the map. <br>
     */
    public int getBottomRowNumber() {
        return bottomRowNumber;
    }

    /**
     * @param bottomRowNumber A number in the bottom row of the map. <br>
     */
    public void setBottomRowNumber(int bottomRowNumber) {
        this.bottomRowNumber = bottomRowNumber;
    }

    /**
     * @return A number in the right column of the map. <br>
     */
    public int getRightColumnNumber() {
        return rightColumnNumber;
    }

    /**
     * @param rightColumnNumber A number in the bottom row of the map. <br>
     */
    public void setRightColumnNumber(int rightColumnNumber) {
        this.rightColumnNumber = rightColumnNumber;
    }
}

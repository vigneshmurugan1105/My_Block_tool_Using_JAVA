import java.util.Scanner;

public class ProgFunAssignment1 {
    public static void main(String[] args) {
        int row = 0, column = 0, option, rowPos = 0, colPos = 0, x = 0, y = 0;
        String opt;

        Scanner scan = new Scanner(System.in);

        do {
            try {
                System.out.print("Enter the number of rows : ");
                row = Integer.parseInt(scan.nextLine());

                System.out.print("\nEnter the number of columns : ");
                column = Integer.parseInt(scan.nextLine());

                //Validating input
                if (!(row > 2 && row <= 10 && column > 2 && column <= 10)) {
                    System.out.print("\nError: Number of row and number of columns should be greater than 2 and less than or equal to 10\n");
                }
            } catch (NumberFormatException n) {
                System.out.print("\nError: Please enter a valid number\n");
            }
        } while (!(row >= 3 && row <= 10 && column >= 3 && column <= 10));

        MyBlock obj1 = new MyBlock(row, column);

        do {
            //Menu
            System.out.print("\nMenu:\n1.Add a house \n2.Display the block \n3.Clear the block\n4.Quit\nEnter the option:");
            opt = scan.nextLine();

            switch (opt) {
                case "1":
                    do {
                        try {
                            System.out.print("\nEnter the position of house\nRow Position:");
                            rowPos = Integer.parseInt(scan.nextLine());

                            System.out.print("\nEnter the Column position:");
                            colPos = Integer.parseInt(scan.nextLine());

                            //Validating input
                            if (!(rowPos >= 1 && rowPos <= 10 && colPos >= 1 && colPos <= 10)) {
                                System.out.print("\nError: Number should be greater than 0 and less than or equal to 10\n");
                            }
                        } catch (NumberFormatException n) {
                            System.out.print("\nError: Please enter a valid number\n");
                        }
                    } while (!(rowPos >= 1 && rowPos <= 10 && colPos >= 1 && colPos <= 10));

                    do {
                        try {
                            System.out.print("\nEnter the Number of rows of the house:");
                            x = Integer.parseInt(scan.nextLine());

                            System.out.print("\nEnter the Number of columns of the house:");
                            y = Integer.parseInt(scan.nextLine());

                            //Validating input
                            if (!(x >= 1 && x <= 10 && y >= 1 && y <= 10))
                                System.out.print("\nError: Number should be greater than 0 and less than or equal to 10\n");
                        } catch (NumberFormatException n) {
                            System.out.print("\nError: Please enter a valid number\n");
                        }
                    } while (!(x >= 1 && x <= 10 && y >= 1 && y <= 10));

                    if (obj1.addHouse(rowPos, colPos, x, y)) {
                        System.out.print("\nThe house built successfully\n");
                    } else {
                        System.out.print("\nThe house cannot be built\n");
                    }
                    option = 1;
                    break;

                case "2":
                    obj1.displayBlock();
                    option = 1;
                    break;

                case "3":
                    obj1.clearBlock();
                    option = 1;
                    break;

                case "4":
                    option = 0;
                    System.out.print("\nThank you\n");
                    break;

                default:
                    System.out.print("\nError: Enter a valid input\n");
                    option = 1;
            }
            //exit loop when option = 0
        } while (option == 1);
    }
}

// MyBlock class
class MyBlock {
    int houseNumber;
    private int[][] block;
    private boolean vacant;

    public MyBlock(int maxRows, int maxColumns) {
        // Initialise the block to 0
        block = new int[maxRows][maxColumns];
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxColumns; j++) {
                block[i][j] = 0;
            }
        }
        this.houseNumber = 1;
        this.vacant = true;
    }

    // Display the entire block as a matrix
    public void displayBlock() {
        for (int[] row : block) {
            for (int column : row) {
                System.out.print(column + " ");
            }
            System.out.print("\n");
        }
    }

    // Set all elements in block to 0
    public void clearBlock() {
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                this.block[i][j] = 0;
            }
        }
        this.vacant = true;
    }

    // Build a house
    public boolean addHouse(int rowPos, int colPos, int rows, int columns) {
        //Validate input
        if ((rows <= block.length - 2) && (columns <= block[0].length - 2) && rowPos >= 2 && rowPos <= (block.length - rows)
                && colPos >= 2 && colPos <= (block[0].length - columns)) {
            //if the block is vacant build house without checking else check if house can be built
            if (!vacant) {
                for (int itr1 = rowPos - 2; itr1 < (rowPos + rows); itr1++) {
                    for (int itr2 = colPos - 2; itr2 < (colPos + columns); itr2++) {
                        if (block[itr1][itr2] != 0)
                            return false;
                    }
                }
            }
            //Build the house
            for (int itr1 = rowPos - 1; itr1 < (rowPos + rows - 1); itr1++) {
                for (int itr2 = colPos - 1; itr2 < (colPos + columns - 1); itr2++) {
                    block[itr1][itr2] = houseNumber;
                }
            }
            vacant = false;
            houseNumber++;
            displayBlock();
            return true;

        } else {
            return false;
        }
    }
}
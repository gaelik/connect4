import java.util.Scanner;

public class Connect4Class {
	private String[][] gameboard = new String[7][6];
	private int flag;

	public Connect4Class() {
		for (int y=0; y<6; y++) {
			for (int x=0; x<7; x++) {
				gameboard[x][y]="  ";
			}
		}
		flag =0;
	}

	public void printGrid() {
		System.out.print("   ");
		for (int z=0; z<7; z++) {
			System.out.print(z + "    "); //formats by creating column number
		}
		System.out.println();	
		for (int y=5; y >= 0; y--) {
			for (int x=0; x<7; x++) {
				// displays the row number
				if (x==0) {
					System.out.print(y + " " + "["+ gameboard[x][y] + "] ");
				}
				else {
					System.out.print("["+ gameboard[x][y] + "] ");
				}
			}
			System.out.println();
		}
	}
	public void playConnect() {
		Scanner reader = new Scanner(System.in);
		int x=0,y=0,end=0,resultScan=0;
		int column;
		int playernumber=1;
		int full=1;
		do {
			full = 1;
			while ( full == 1 ) {
				column = -1;
				while ( column < 0 || column > 6 ) {
					System.out.println("Player " + playernumber + ", it's your turn. Enter the number of the column to play a coin");
					column = reader.nextInt();
					reader.nextLine();
				}
				// scans the column to find the next available slot.
				y=0;
				while (y<6) {	
					if (gameboard[column][y] == "  ") {
						gameboard[column][y] = "P"+ playernumber;
						full=0;
						break;
					} 
					y++;
				}

				if ( full == 1 ) {
					System.out.println("The column " + column + " is full.");
				}
			}
			printGrid();
			resultScan=scanGameBoard("P" + playernumber);
			if ( resultScan == 1) {
				System.out.println("Player P" + playernumber + " won !!");
				end=1;
			} else if ( resultScan == 3) {
				System.out.println("The game ends with a tie.");
				end=1;
			};
			if ( playernumber == 1 ) {
				playernumber = 2;
			} else {
				playernumber = 1;
			};		
		} while(end==0);
	}

	// scans the board for victories and open spots
	// returns 1 when a victory is detected, 2 when no victory but open spots are available, 3 when there are no more open spots.
	public int scanGameBoard(String player){
		int available=0;

		// horizontal Check - Every row needs to be checked, X goes between 0 and 4 (source point for the line)
		System.out.println("Horinzontal checks");
		for (int x = 0; x<4; x++){
			for (int y = 0; y<6 ; y++ ){
				if (gameboard[x][y].equals(player) && gameboard[x+1][y].equals(player) && gameboard[x+2][y].equals(player) && gameboard[x+3][y].equals(player)){
					return 1;
				}           
			}
		}
		// Vertical Check - x varies between 0 and 6, y between 0 and 2 (source point for the line)
		for (int x = 0; x<7 ; x++ ){
			for (int y = 0; y<3; y++){
				if (gameboard[x][y].equals(player) && gameboard[x][y+1].equals(player) && gameboard[x][y+2].equals(player) && gameboard[x][y+3].equals(player)){
					return 1;
				}           
			}
		}
		// Ascending Diagonal Check - x varies between 0 and 3, y between 0 and 2 (source point for the diagonal)
		for (int x=0; x<4; x++){
			for (int y=0; y<3; y++){
				if (gameboard[x][y].equals(player) && gameboard[x+1][y+1].equals(player) && gameboard[x+2][y+2].equals(player) && gameboard[x+3][y+3].equals(player))
					return 1;
			}
		}

		// Descending Diagonal Check - x varies between 0 and 3, y varies between 3 and 5 (source point for the diagonal)
		for (int x=0; x<4; x++){
			for (int y=3; y<6; y++){
				if (gameboard[x][y].equals(player) && gameboard[x+1][y-1].equals(player) && gameboard[x+2][y-2].equals(player) && gameboard[x+3][y-3].equals(player))
					return 1;
			}
		}

		// Checks if there are any remaining empty fields
		for (int x=0; x<7; x++) {
			for (int y=0; y<6; y++) {
				if (gameboard[x][y].equals("  ") ) {
					available++;
				}
			}
		}

		if ( available > 0 ) {
			return 2;
		} else {
			return 3;
		}
	}
}



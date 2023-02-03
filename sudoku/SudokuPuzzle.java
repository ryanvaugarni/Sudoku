/**
 * C'est une classe qui représente un puzzle Sudoku
 */
package sudoku;

public class SudokuPuzzle {

	// Grille qui représente le puzzle
protected String [][] board;
// Table pour déterminer si un emplacement est modifiable
protected boolean [][] mutable;
// Nombre de lignes dans la grille
private final int ROWS;
// Nombre de colonnes dans la grille
private final int COLUMNS;
// Largeur d'une boîte (groupement de cellules dans la grille)
private final int BOXWIDTH;
// Hauteur d'une boîte (groupement de cellules dans la grille)
private final int BOXHEIGHT;
// Valeurs valides pour remplir la grille
private final String [] VALIDVALUES;

/**
 * Constructeur qui prend en entrée les dimensions de la grille, les dimensions d'une boîte, et les valeurs valides pour la grille
 * @param rows nombre de lignes dans la grille
 * @param columns nombre de colonnes dans la grille
 * @param boxWidth largeur d'une boîte (groupement de cellules dans la grille)
 * @param boxHeight hauteur d'une boîte (groupement de cellules dans la grille)
 * @param validValues valeurs valides pour remplir la grille
 */
public SudokuPuzzle(int rows,int columns,int boxWidth,int boxHeight,String [] validValues) {
	this.ROWS = rows;
	this.COLUMNS = columns;
	this.BOXWIDTH = boxWidth;
	this.BOXHEIGHT = boxHeight;
	this.VALIDVALUES = validValues;
	this.board = new String[ROWS][COLUMNS];
	this.mutable = new boolean[ROWS][COLUMNS];
	// Initialise la grille
	initializeBoard();
	// Initialise les emplacements modifiables
	initializeMutableSlots();
}
/**
 * Constructeur de copie pour une instance de la classe SudokuPuzzle
 * @param puzzle instance de la classe SudokuPuzzle à copier
 */
public SudokuPuzzle(SudokuPuzzle puzzle) {
	this.ROWS = puzzle.ROWS;
	this.COLUMNS = puzzle.COLUMNS;
	this.BOXWIDTH = puzzle.BOXWIDTH;
	this.BOXHEIGHT = puzzle.BOXHEIGHT;
	this.VALIDVALUES = puzzle.VALIDVALUES;
	this.board = new String[ROWS][COLUMNS];
	for(int r = 0;r < ROWS;r++) {
		for(int c = 0;c < COLUMNS;c++) {
			board[r][c] = puzzle.board[r][c];
		}
	}
		this.mutable = new boolean[ROWS][COLUMNS];
		for(int r = 0;r < ROWS;r++) {
			for(int c = 0;c < COLUMNS;c++) {
				this.mutable[r][c] = puzzle.mutable[r][c];
			}
		}
	}
	
	public int getNumRows() {
		return this.ROWS;
	}
	
	public int getNumColumns() {
		return this.COLUMNS;
	}
	
	public int getBoxWidth() {
		return this.BOXWIDTH;
	}
	
	public int getBoxHeight() {
		return this.BOXHEIGHT;
	}
	
	public String [] getValidValues() {
		return this.VALIDVALUES;
	}
	
	public void makeMove(int row,int col,String value,boolean isMutable) {
		if(this.isValidValue(value) && this.isValidMove(row,col,value) && this.isSlotMutable(row, col)) {
			this.board[row][col] = value;
			this.mutable[row][col] = isMutable;
		}
	}
	
	public boolean isValidMove(int row,int col,String value) {
		if(this.inRange(row,col)) {
			if(!this.numInCol(col,value) && !this.numInRow(row,value) && !this.numInBox(row,col,value)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean numInCol(int col,String value) {
		if(col <= this.COLUMNS) {
			for(int row=0;row < this.ROWS;row++) {
				if(this.board[row][col].equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean numInRow(int row,String value) {
		if(row <= this.ROWS) {
			for(int col=0;col < this.COLUMNS;col++) {
				if(this.board[row][col].equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean numInBox(int row,int col,String value) {
		if(this.inRange(row, col)) {
			int boxRow = row / this.BOXHEIGHT;
			int boxCol = col / this.BOXWIDTH;
			
			int startingRow = (boxRow*this.BOXHEIGHT);
			int startingCol = (boxCol*this.BOXWIDTH);
			
			for(int r = startingRow;r <= (startingRow+this.BOXHEIGHT)-1;r++) {
				for(int c = startingCol;c <= (startingCol+this.BOXWIDTH)-1;c++) {
					if(this.board[r][c].equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isSlotAvailable(int row,int col) {
		 return (this.inRange(row,col) && this.board[row][col].equals("") && this.isSlotMutable(row, col));
	}
	
	public boolean isSlotMutable(int row,int col) {
		return this.mutable[row][col];
	}
	
	public String getValue(int row,int col) {
		if(this.inRange(row,col)) {
			return this.board[row][col];
		}
		return "";
	}
	
	public String [][] getBoard() {
		return this.board;
	}
	
	private boolean isValidValue(String value) {
		for(String str : this.VALIDVALUES) {
			if(str.equals(value)) return true;
		}
		return false;
	}
	
	public boolean inRange(int row,int col) {
		return row <= this.ROWS && col <= this.COLUMNS && row >= 0 && col >= 0;
	}
	
	public boolean boardFull() {
		for(int r = 0;r < this.ROWS;r++) {
			for(int c = 0;c < this.COLUMNS;c++) {
				if(this.board[r][c].equals("")) return false;
			}
		}
		return true;
	}
	
	public void makeSlotEmpty(int row,int col) {
		this.board[row][col] = "";
	}
	
	@Override
	public String toString() {
		String str = "Game Board:\n";
		for(int row=0;row < this.ROWS;row++) {
			for(int col=0;col < this.COLUMNS;col++) {
				str += this.board[row][col] + " ";
			}
			str += "\n";
		}
		return str+"\n";
	}
	
	private void initializeBoard() {
		for(int row = 0;row < this.ROWS;row++) {
			for(int col = 0;col < this.COLUMNS;col++) {
				this.board[row][col] = "";
			}
		}
	}
	
	private void initializeMutableSlots() {
		for(int row = 0;row < this.ROWS;row++) {
			for(int col = 0;col < this.COLUMNS;col++) {
				this.mutable[row][col] = true;
			}
		}
	}
}
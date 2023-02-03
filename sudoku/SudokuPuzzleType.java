// Une énumération qui définit les différents types de puzzles Sudoku.
package sudoku;

// Une énumération qui définit les différents types de puzzles Sudoku.
public enum SudokuPuzzleType {
	
	// Une constante qui représente un puzzle 6x6 avec une grille de 3x3 et les valeurs valides de "1" à "9"
	SIXBYSIX(6,6,3,3,new String[] {"1","2","3","4","5","6","7","8","9"},"6 By 6 Game"),
	
	// Une constante qui représente un puzzle 9x9 avec une grille de 3x3 et les valeurs valides de "1" à "9"
	NINEBYNINE(9,9,3,3,new String[] {"1","2","3","4","5","6","7","8","9"},"9 By 9 Game"),
	
	// Une constante qui représente un puzzle 12x12 avec une grille de 3x3 et les valeurs valides de "1" à "9"
	TWELVEBYTWELVE(9,9,3,3,new String[] {"1","2","3","4","5","6","7","8","9"},"12 By 12 Game");
	
	// Une constante commentée qui représenterait un puzzle 16x16 avec une grille de 4x4 et les valeurs valides de "1" à "G"
	//SIXTEENBYSIXTEEN(16,16,4,4,new String[] {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G"},"16 By 16 Game");
	
	// Le nombre de lignes dans le puzzle
	private final int rows;
	// Le nombre de colonnes dans le puzzle
	private final int columns;
	// La largeur de la boîte qui définit une région
	private final int boxWidth;
	// La hauteur de la boîte qui définit une région
	private final int boxHeight;
	// Les valeurs valides pour remplir le puzzle
	private final String [] validValues;
	// La description du puzzle
	private final String desc;
	// Le constructeur qui initialise les valeurs pour chaque constante de l'énumération
	private SudokuPuzzleType(int rows,int columns,int boxWidth,int boxHeight,String [] validValues,String desc) {
		this.rows = rows;
		this.columns = columns;
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		this.validValues = validValues;
		this.desc = desc;
	}
	
	// Une méthode qui renvoie le nombre de lignes dans le puzzle
	public int getRows() {
		return rows;
	}
	// Une méthode qui renvoie le nombre de colonnes dans le puzzle
	public int getColumns() {
		return columns;
	}
	// Fonction qui retourne la largeur de la boîte
	public int getBoxWidth() {
		return boxWidth;
	}
	// Fonction qui retourne la hauteur de la boîte
	public int getBoxHeight() {
		return boxHeight;
	}
	// Fonction qui retourne les valeurs valides
	public String [] getValidValues() {
		return validValues;
	}
	// Fonction qui retourne une description de l'objet
	public String toString() {
		return desc;
	}
}
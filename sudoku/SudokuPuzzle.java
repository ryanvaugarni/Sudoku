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
// constructeur de la classe SudokuPuzzle
public SudokuPuzzle(SudokuPuzzle puzzle) {
	// copie de la valeur de la constante ROWS de l'objet puzzle dans l'objet courant
	this.ROWS = puzzle.ROWS;
	// copie de la valeur de la constante COLUMNS de l'objet puzzle dans l'objet courant
	this.COLUMNS = puzzle.COLUMNS;
	// copie de la valeur de la constante BOXWIDTH de l'objet puzzle dans l'objet courant
	this.BOXWIDTH = puzzle.BOXWIDTH;
	// copie de la valeur de la constante BOXHEIGHT de l'objet puzzle dans l'objet courant
	this.BOXHEIGHT = puzzle.BOXHEIGHT;
	// copie de la valeur de la constante VALIDVALUES de l'objet puzzle dans l'objet courant
	this.VALIDVALUES = puzzle.VALIDVALUES;
	// allocation de la mémoire pour la matrice board avec la même taille que celle de puzzle
	this.board = new String[ROWS][COLUMNS];
	// boucle qui parcourt les lignes de la matrice board
	for(int r = 0;r < ROWS;r++) {
		// boucle qui parcourt les colonnes de la matrice board
		for(int c = 0;c < COLUMNS;c++) {
			// copie de la valeur de la case [r][c] de la matrice board de puzzle dans la matrice board de l'objet courant
			board[r][c] = puzzle.board[r][c];
		}
	}
	// allocation de la mémoire pour la matrice mutable avec la même taille que celle de puzzle
	this.mutable = new boolean[ROWS][COLUMNS];
	// boucle qui parcourt les lignes de la matrice mutable
	for(int r = 0;r < ROWS;r++) {
		// boucle qui parcourt les colonnes de la matrice mutable
		for(int c = 0;c < COLUMNS;c++) {
			// copie de la valeur de la case [r][c] de la matrice mutable de puzzle dans la matrice mutable de l'objet courant
			this.mutable[r][c] = puzzle.mutable[r][c];
		}
	}
}
	// Cette méthode retourne le nombre de lignes dans le tableau
public int getNumRows() {
	return this.ROWS;
}
// Cette méthode retourne le nombre de colonnes dans le tableau
public int getNumColumns() {
	return this.COLUMNS;
}
// Cette méthode retourne la largeur d'un carré dans le tableau
public int getBoxWidth() {
	return this.BOXWIDTH;
}
// Cette méthode retourne la hauteur d'un carré dans le tableau
public int getBoxHeight() {
	return this.BOXHEIGHT;
}
// Cette méthode retourne la liste des valeurs valides pour remplir le tableau
public String [] getValidValues() {
	return this.VALIDVALUES;
}
public void makeMove(int row, int col, String value, boolean isMutable) {
    // Vérifie si la valeur est valide
    if (this.isValidValue(value) 
        // Vérifie si le mouvement est valide
        && this.isValidMove(row, col, value) 
        // Vérifie si l'emplacement est modifiable
        && this.isSlotMutable(row, col)) {
        // Met à jour la grille de jeu
        this.board[row][col] = value;
        // Met à jour la mutabilité de l'emplacement
        this.mutable[row][col] = isMutable;
    }
}
	/**
 * Cette méthode vérifie si le déplacement est valide.
 * @param row La ligne de la grille où le mouvement aura lieu
 * @param col La colonne de la grille où le mouvement aura lieu
 * @param value La valeur à insérer dans la grille
 * @return true si le mouvement est valide, false sinon
 */
public boolean isValidMove(int row,int col,String value) {
	// Vérifie si la ligne et la colonne sont dans les limites de la grille
	if(this.inRange(row,col)) {
		// Vérifie si la valeur n'apparaît pas déjà dans la colonne, la ligne ou le carré
		if(!this.numInCol(col,value) && !this.numInRow(row,value) && !this.numInBox(row,col,value)) {
			return true;
		}
	}
	return false;
}
	// Vérifie si une valeur se trouve déjà dans la colonne donnée
public boolean numInCol(int col,String value) {
	// Vérifie si la colonne est dans les limites du tableau
	if(col <= this.COLUMNS) {
		// Boucle à travers les lignes pour vérifier si la valeur existe déjà dans la colonne
		for(int row=0;row < this.ROWS;row++) {
			if(this.board[row][col].equals(value)) {
				return true;
			}
		}
	}
	return false;
}
	// Cette fonction vérifie si la valeur est déjà présente dans la ligne spécifiée.
public boolean numInRow(int row,String value) {
    // Vérifie si la ligne est dans les limites
    if(row <= this.ROWS) {
        // Boucle sur les colonnes de la ligne
        for(int col=0;col < this.COLUMNS;col++) {
            // Si la valeur est égale à celle sur la planche, retourne vrai
            if(this.board[row][col].equals(value)) {
                return true;
            }
        }
    }
    // Si la valeur n'a pas été trouvée, retourne faux
    return false;
}
	/**
 * La méthode numInBox vérifie si une valeur se trouve déjà dans le même bloc.
 * 
 * @param row la ligne actuelle 
 * @param col la colonne actuelle 
 * @param value la valeur actuelle 
 * 
 * @return true si la valeur se trouve déjà dans le même bloc, false sinon
 */
public boolean numInBox(int row,int col,String value) {
	// Vérifier si les coordonnées sont valides
	if(this.inRange(row, col)) {
		// Calculer la ligne et la colonne du bloc
		int boxRow = row / this.BOXHEIGHT;
		int boxCol = col / this.BOXWIDTH;
		
		// Calculer la première ligne et la première colonne du bloc
		int startingRow = (boxRow * this.BOXHEIGHT);
		int startingCol = (boxCol * this.BOXWIDTH);
		
		// Vérifier si la valeur se trouve déjà dans le même bloc
		for(int r = startingRow; r <= (startingRow + this.BOXHEIGHT) - 1; r++) {
			for(int c = startingCol; c <= (startingCol + this.BOXWIDTH) - 1; c++) {
				if(this.board[r][c].equals(value)) {
					return true;
				}
			}
		}
	}
		return false;
	}
	/**
 * isSlotAvailable verifie si la case specifique a une valeur vide et est modifiable
 * @param row ligne de la case
 * @param col colonne de la case
 * @return boolean, true si la case est vide et modifiable, false sinon
 */
public boolean isSlotAvailable(int row,int col) {
    return (this.inRange(row,col) && this.board[row][col].equals("") && this.isSlotMutable(row, col));
}
	
	// Méthode qui vérifie si la case est modifiable
public boolean isSlotMutable(int row, int col) {
    // retourne l'état de la case (modifiable ou non)
    return this.mutable[row][col];
}

// Méthode qui retourne la valeur d'une case
public String getValue(int row, int col) {
    // Si la case est dans la plage de lignes et de colonnes valides
    if (this.inRange(row, col)) {
        // retourne la valeur de la case
        return this.board[row][col];
    }
    // sinon retourne une chaîne vide
    return "";
}
	/* méthode pour obtenir le tableau de jeu */
public String [][] getBoard() {
	return this.board;
}
/* méthode pour vérifier si une valeur est valide */
private boolean isValidValue(String value) {
	for(String str : this.VALIDVALUES) {
		if(str.equals(value)) return true;
	}
	return false;
}
/* méthode pour vérifier si la case est dans les limites du tableau */
public boolean inRange(int row,int col) {
	return row <= this.ROWS && col <= this.COLUMNS && row >= 0 && col >= 0;
}
/* méthode pour vérifier si le tableau est plein */
public boolean boardFull() {
	for(int r = 0;r < this.ROWS;r++) {
		for(int c = 0;c < this.COLUMNS;c++) {
			if(this.board[r][c].equals("")) return false;
		}
	}
	return true;
}
// La méthode makeSlotEmpty permet de vide une case du jeu
// La méthode toString génère une chaine de caractères avec l'état du plateau de jeu
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
	//Cette méthode initialise le tableau à 2 dimensions d'un jeu d'échecs composé de ROWS lignes et COLUMNS colonnes avec des chaînes vides
	private void initializeBoard() {
		for(int row = 0;row < this.ROWS;row++) {
			for(int col = 0;col < this.COLUMNS;col++) {
				this.board[row][col] = "";
			}
		}
	}
	// La fonction initializeMutableSlots() initialise chaque cellule de la grille à la valeur boolean true. Les boucles for parcourent la matrice pour affecter cette valeur à chaque cellule.
	private void initializeMutableSlots() {
		for(int row = 0;row < this.ROWS;row++) {
			for(int col = 0;col < this.COLUMNS;col++) {
				this.mutable[row][col] = true;
			}
		}
	}
}
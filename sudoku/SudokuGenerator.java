/**
 * Il génère un puzzle sudoku aléatoire
 */
package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Classe qui génère un sudoku aléatoirement
public class SudokuGenerator {
	
	// Générateur de nombre aléatoire
	private Random randomGenerator;
	// Liste de valeurs valides non utilisées
	private List<String> notUsedValidValues;
	// Constructeur par défaut qui initialise le générateur de nombres aléatoires et la liste de valeurs valides non utilisées
	public SudokuGenerator() {
		randomGenerator = new Random();
		notUsedValidValues = new ArrayList<String>();
	}
	// Retourne le générateur de nombres aléatoires
	public Random getRandomGenerator() {
		return randomGenerator;
	}
	// Définit le générateur de nombres aléatoires
	public void setRandomGenerator(Random randomGenerator) {
		this.randomGenerator = randomGenerator;
	}
	// Retourne la liste de valeurs valides non utilisées
	public List<String> getNotUsedValidValues() {
		return notUsedValidValues;
	}
	// Définit la liste de valeurs valides non utilisées
	public void setNotUsedValidValues(List<String> notUsedValidValues) {
		this.notUsedValidValues = notUsedValidValues;
	}
	// Cette méthode génère un puzzle de sudoku aléatoirement en utilisant un certain nombre de stratégies.
	public SudokuPuzzle generateRandomSudoku(SudokuPuzzleType puzzleType) {
	// Créer un nouveau puzzle de sudoku en utilisant les informations fournies dans puzzleType
	SudokuPuzzle puzzle = new SudokuPuzzle(puzzleType.getRows(), 
										   puzzleType.getColumns(), 
										   puzzleType.getBoxWidth(), 
										   puzzleType.getBoxHeight(), 
										   puzzleType.getValidValues());
	// Créer une copie du puzzle qui sera utilisée pour la résolution
	SudokuPuzzle copy = new SudokuPuzzle(puzzle);
	// Initialiser la liste de valeurs valides qui n'ont pas été utilisées
	notUsedValidValues =  new ArrayList<String>(Arrays.asList(copy.getValidValues()));
	// Boucle à travers les lignes du puzzle copié
	for(int r = 0;r < copy.getNumRows();r++) {
		// Sélectionner une valeur valide aléatoire dans la liste
		int randomValue = randomGenerator.nextInt(notUsedValidValues.size());
		// Ajouter la valeur sélectionnée à la première colonne de la ligne actuelle
		copy.makeMove(r, 0, notUsedValidValues.get(randomValue), true);
		// Supprimer la valeur sélectionnée de la liste
		notUsedValidValues.remove(randomValue);
	}
	// Résoudre le puzzle copié en utilisant la méthode de backtracking
	backtrackSudokuSolver(0, 0, copy);
	
	// Calculer le nombre de valeurs à conserver dans le puzzle final
	int numberOfValuesToKeep = (int)(0.22222*(copy.getNumRows()*copy.getNumRows()));
	
	// Boucle pour ajouter des valeurs aléatoires à partir du puzzle copié au puzzle final
	for(int i = 0;i < numberOfValuesToKeep;) {
		// Sélectionner une ligne et une colonne aléatoirement
		int randomRow = randomGenerator.nextInt(puzzle.getNumRows());
		int randomColumn = randomGenerator.nextInt(puzzle.getNumColumns());
		
		// Vérifier si la case est disponible dans le puzzle final
		if(puzzle.isSlotAvailable(randomRow, randomColumn)) {
			// Ajouter la valeur correspondante à partir du puzzle copié au puzzle final
			puzzle.makeMove(randomRow, randomColumn, copy.getValue(randomRow, randomColumn), false);
			i++;
		}
	}
	// Retourner le puzzle final
	return puzzle;
}
	/**
	 * Solves the sudoku puzzle
	 * Pre-cond: r = 0,c = 0
	 * Post-cond
	 * @param r: the current row
	 * @param c: the current column
	 * @return valid move or not or done
	 * Responses: Erroneous data 
	 */
    private boolean backtrackSudokuSolver(int r,int c,SudokuPuzzle puzzle) {
    	//Si le déplacement n'est pas valide, retournez false
		if(!puzzle.inRange(r,c)) {
			return false;
		}
		
		//si l'espace actuel est vide
		if(puzzle.isSlotAvailable(r, c)) {
			
			//boucle pour trouver la valeur correcte de l'espace
			for(int i = 0;i < puzzle.getValidValues().length;i++) {
				
				//si le numéro actuel fonctionne dans l'espace
				if(!puzzle.numInRow(r, puzzle.getValidValues()[i]) && !puzzle.numInCol(c,puzzle.getValidValues()[i]) && !puzzle.numInBox(r,c,puzzle.getValidValues()[i])) {
					
					//passez à l'action
					puzzle.makeMove(r, c, puzzle.getValidValues()[i], true);
					
					//si le puzzle est résolu, retournez true
					if(puzzle.boardFull()) {
						return true;
					}
					
					//passer à l'étape suivante
					if(r == puzzle.getNumRows() - 1) {
						if(backtrackSudokuSolver(0,c + 1,puzzle)) return true;
					} else {
						if(backtrackSudokuSolver(r + 1,c,puzzle)) return true;
					}
				}
			}
		}
		
		//si l'espace actuel n'est pas vide
		else {
			//got to the next move
			if(r == puzzle.getNumRows() - 1) {
				return backtrackSudokuSolver(0,c + 1,puzzle);
			} else {
				return backtrackSudokuSolver(r + 1,c,puzzle);
			}
		}
		//Annuler le déplacement
		puzzle.makeSlotEmpty(r, c);
		
		//retour en arrière
		return false;
	}
}
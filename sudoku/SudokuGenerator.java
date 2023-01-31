/**
 * Il génère un puzzle sudoku aléatoire
 */
package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {

	public SudokuPuzzle generateRandomSudoku(SudokuPuzzleType puzzleType) {
		SudokuPuzzle puzzle = new SudokuPuzzle(puzzleType.getRows(), puzzleType.getColumns(), puzzleType.getBoxWidth(), puzzleType.getBoxHeight(), puzzleType.getValidValues());
		SudokuPuzzle copy = new SudokuPuzzle(puzzle);
		
		Random randomGenerator = new Random();
		
		List<String> notUsedValidValues =  new ArrayList<String>(Arrays.asList(copy.getValidValues()));
		for(int r = 0;r < copy.getNumRows();r++) {
			int randomValue = randomGenerator.nextInt(notUsedValidValues.size());
			copy.makeMove(r, 0, notUsedValidValues.get(randomValue), true);
			notUsedValidValues.remove(randomValue);
		}
		
		//Le problème est qu'il faut l'améliorer afin de pouvoir générer des puzzles 16x16.
		backtrackSudokuSolver(0, 0, copy);
		
		int numberOfValuesToKeep = (int)(0.22222*(copy.getNumRows()*copy.getNumRows()));
		
		for(int i = 0;i < numberOfValuesToKeep;) {
			int randomRow = randomGenerator.nextInt(puzzle.getNumRows());
			int randomColumn = randomGenerator.nextInt(puzzle.getNumColumns());
			
			if(puzzle.isSlotAvailable(randomRow, randomColumn)) {
				puzzle.makeMove(randomRow, randomColumn, copy.getValue(randomRow, randomColumn), false);
				i++;
			}
		}
		
		return puzzle;
	}
	
	/**
	 * Solves the sudoku puzzle
	 * Pre-cond: r = 0,c = 0
	 * Post-cond: solved puzzle
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
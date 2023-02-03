/**
 * C'est un SudokuPuzzle qui a un tableau qui est initialisé au puzzle que nous voulons tester
 */
package sudoku;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SudokuPuzzleTest {

	private SudokuPuzzle puzzle; // Déclare une variable d'instance puzzle de type SudokuPuzzle
	
	@Before // Annotation pour indiquer que cette méthode sera exécutée avant les tests
	public void setUp() {
		/*
		 * Here is the puzzle
		 * 0 0 8 3 4 2 9 0 0
		 * 0 0 9 0 0 0 7 0 0
		 * 4 0 0 0 0 0 0 0 3
		 * 0 0 6 4 7 3 2 0 0
		 * 0 3 0 0 0 0 0 1 0
		 * 0 0 2 8 5 1 6 0 0
		 * 7 0 0 0 0 0 0 0 8
		 * 0 0 4 0 0 0 1 0 0
		 * 0 0 3 6 9 7 5 0 0 
		 */
		String [][] board = new String [][] {
				{"0","0","8","3","4","2","9","0","0"}, // Ligne 1 du tableau de sudoku
				{"0","0","9","0","0","0","7","0","0"}, // Ligne 2 du tableau de sudoku
				{"4","0","0","0","0","0","0","0","3"}, // Ligne 3 du tableau de sudoku
				{"0","0","6","4","7","3","2","0","0"}, // Ligne 4 du tableau de sudoku
				{"0","3","0","0","0","0","0","1","0"}, // Ligne 5 du tableau de sudoku
				{"0","0","2","8","5","1","6","0","0"}, // Ligne 6 du tableau de sudoku
				{"7","0","0","0","0","0","0","0","8"}, // Ligne 7 du tableau de sudoku
				{"0","0","4","0","0","0","1","0","0"}, // Ligne 8 du tableau de sudoku
				{"0","0","3","6","9","7","5","0","0"}  // Ligne 9 du tableau de sudoku
		};
		puzzle = new SudokuPuzzleForTesting(board); // Initialise la variable d'instance puzzle avec un nouvel objet de type SudokuPuzzleForTesting qui prend le tableau `board` en argument
	}
	
	@Test // Annotation pour indiquer que cette méthode contient des tests
	public void testNumInRow() {
		Assert.assertTrue(puzzle.numInRow(0, "9")); // Vérifie si le nombre "9" se trouve dans la premier tableau
		Assert.assertTrue(puzzle.numInRow(1, "7"));
		Assert.assertFalse(puzzle.numInRow(8, "1"));
	}
	
	@Test
	public void testNumInCol() {
		Assert.assertTrue(puzzle.numInCol(0, "4"));
		Assert.assertTrue(puzzle.numInCol(5, "2"));
		Assert.assertFalse(puzzle.numInCol(8, "1"));
	}
	
	@Test
	public void testNumInBox() {
		Assert.assertTrue(puzzle.numInBox(6, 1, "4"));
		Assert.assertFalse(puzzle.numInBox(4, 4, "2"));
		Assert.assertTrue(puzzle.numInBox(4, 4, "8"));
	}
	
	private class SudokuPuzzleForTesting extends SudokuPuzzle { // Déclaration d'une classe interne qui étend la classe SudokuPuzzle
	public SudokuPuzzleForTesting(String [][] board) { // Constructeur de la classe
		super(9,9,3,3,new String [] {"1","2","3","4","5","6","7","8","9"}); // Appel au constructeur de la classe parent en passant les paramètres suivants: 9 pour la largeur et la hauteur du tableau, 3 pour la largeur et la hauteur d'une boîte et une liste contenant les nombres de 1 à 9.
		this.board = board; // Affectation de la valeur de board au board de l'objet courant.
	}
	}
}
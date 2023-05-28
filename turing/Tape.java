package turing;

public class Tape {

	public Cell currentCell;
	
	public Cell getCurrentCell() {
		return currentCell;
	}
	public char getContent() {
		return currentCell.content;
	}
	public void setContent(char ch) {
		currentCell.content = ch;	
	}
	public void moveLeft() {
		if (currentCell.prev==null) {
			Cell newCell;
			newCell = new Cell();
			newCell.content = ' ';
			newCell.next =currentCell;
			currentCell.prev = newCell;
			currentCell = currentCell.prev;
		}
		else {
			currentCell = currentCell.prev;
		}
		
	}
	public void moveRight() {
		if (currentCell.next==null) {
			Cell newCell;
			newCell = new Cell();
			newCell.content = ' ';
			newCell.prev =currentCell;
			currentCell.next = newCell;
			currentCell = currentCell.next;
		}
		else {
			currentCell = currentCell.next;
		}
		
	}
	public String getTapeContents() {
		Cell runner;
		runner = currentCell;
		String tapeContents = "";
		while (runner.prev != null) {
			runner =runner.prev;
		}
		while (runner.next != null) {
			tapeContents += runner.content;
			runner = runner.next;
		}
		return tapeContents;
		
	}
	public Tape() {
		currentCell = new Cell();
		currentCell.content = ' ';
	}
	public Tape(char ch) {
		currentCell = new Cell();
		currentCell.content = ch;
	}
}

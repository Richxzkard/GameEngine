package labs.lab5;

import java.util.ArrayList;

class TicTacToeGame implements Game{
	private String input1 = "012";
	private String input2 = "xo";
	private boolean anyonewin = false;
	private String[][] dataset;
	private String lastplayer = "";
	private boolean firstplayer = false;
	private String firstplayerstr;
	
	public TicTacToeGame() {
		dataset = new String[3][3];
	}
	
	public boolean isValidMove(String move) {
		if (move.length()!=3) return false;
		if (!(input1.contains(move.substring(0,1)))) return false;
		if (!(input1.contains(move.substring(1,2)))) return false;
		if (!(input2.contains(move.substring(2,3)))) return false;
		int row = Integer.parseInt(move.substring(0,1));
		int col = Integer.parseInt(move.substring(1,2));
		String action = move.substring(2,3);
		if ((!((row>=0) && (row<=2))) || !(((col>=0) && (col<=2)))) return false;
		if (!action.equals("o")&&!action.equals("x")) return false;
		if (action.equals(lastplayer)) return false;
		if (!(dataset[row][col]==null)) return false;
		return true;
	}

	public void executeMove(String move) {
		int row = Integer.parseInt(move.substring(0,1));
		int col = Integer.parseInt(move.substring(1,2));
		String action = move.substring(2,3);
		dataset[row][col] = action;
		lastplayer = action;
		if (!firstplayer) {
			firstplayerstr = action;
			firstplayer = true;
		}
	}

	public boolean gameOver() {
		if ((dataset[0][0]!=null && dataset[0][0].equals(dataset[0][1]) && dataset[0][1].equals(dataset[0][2]))||
			(dataset[1][0]!=null && dataset[1][0].equals(dataset[1][1]) && dataset[1][1].equals(dataset[1][2]))||
			(dataset[2][0]!=null && dataset[2][0].equals(dataset[2][1]) && dataset[2][1].equals(dataset[2][2]))||
			(dataset[0][0]!=null && dataset[0][0].equals(dataset[1][0]) && dataset[1][0].equals(dataset[2][0]))||
			(dataset[0][1]!=null && dataset[0][1].equals(dataset[1][1]) && dataset[1][1].equals(dataset[2][1]))||
			(dataset[0][2]!=null && dataset[0][2].equals(dataset[1][2]) && dataset[1][2].equals(dataset[2][2]))||
			(dataset[0][0]!=null && dataset[0][0].equals(dataset[1][1]) && dataset[1][1].equals(dataset[2][2]))||
			(dataset[0][2]!=null && dataset[0][2].equals(dataset[1][1]) && dataset[1][1].equals(dataset[2][0]))){
			anyonewin = true;
			return true;
		}
		int filled = 0;
		for (int i=0; i<dataset.length; i++) {
            for (int j=0; j<dataset[i].length; j++) {
            	if (dataset[i][j]!=null) {
            		filled += 1;
            	}
            }
		}
		if (filled==9) return true;
		return false;
	}

	public String displayBoard() {
		String res = "";
		for (int i=0; i<dataset.length; i++) {
            for (int j=0; j<dataset[i].length; j++) {
            	if (dataset[i][j]!=null) res=res+" "+dataset[i][j]+" ";
            	else res+="   ";
            	if (j!=2) res+="|";
            	else {
            		if (i!=2) res = res + System.lineSeparator()+ "-----------" + System.lineSeparator();
            		else res+=System.lineSeparator();
            	}
            }
		}
		return res;
	}

	public int determineWinner() {
		if (this.gameOver() && anyonewin) {
			if (lastplayer.equals(firstplayerstr)) return 1;
			return 2;
		}
		return 0;
	}
}
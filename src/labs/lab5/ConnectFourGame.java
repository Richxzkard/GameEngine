package labs.lab5;

class ConnectFourGame implements Game{
	private String input1 = "yr";
	private String input2 = "0123456";
	private int lastcol;
	private int lastrow;
	private String[][] dataset;
	private String lastplayer = "";
	private boolean firstplayer = false;
	private String firstplayerstr;
	private boolean anyonewin = false;
	
	private String rowtostring(String[] args) {
		String res = "";
		for (String i:args) {
			res += i;
		}
		return res;
	}
	
	private String coltostring(int col) {
		String res = "";
		for (int i=0;i<=5;i++) {
			if (dataset[i][col]!=null) res+=dataset[i][col];
		}
		return res;
	}
	
	private String diagonaltostring1(int row, int col) {
		String res = dataset[row][col];
		int rowindex = row;
		int colindex = col;
		while ((rowindex>0)&&(colindex<6)) {
			rowindex -= 1;
			colindex += 1;
			if (dataset[rowindex][colindex]!=null) res += dataset[rowindex][colindex];
		}
		rowindex = row;
		colindex = col;
		while ((rowindex<5)&&(colindex>0)) {
			rowindex += 1;
			colindex -= 1;
			if (dataset[rowindex][colindex]!=null) res = dataset[rowindex][colindex] + res;
		}
		return res;
	}
	
	private String diagonaltostring2(int row, int col) {
		String res = dataset[row][col];
		int rowindex = row;
		int colindex = col;
		while ((rowindex>0)&&(colindex>0)) {
			rowindex -= 1;
			colindex -= 1;
			if (dataset[rowindex][colindex]!=null) res += dataset[rowindex][colindex];
		}
		rowindex = row;
		colindex = col;
		while ((rowindex<5)&&(colindex<6)) {
			rowindex += 1;
			colindex += 1;
			if (dataset[rowindex][colindex]!=null) res = dataset[rowindex][colindex] + res;
		}
		return res;
	}
	
	public ConnectFourGame() {
		dataset = new String[6][7];
	}
	
	public boolean isValidMove(String move) {
		if (move.length()!=2) return false;
		if (!(input2.contains(move.substring(0,1)))) return false;
		if (!(input1.contains(move.substring(1,2)))) return false;
		int col = Integer.parseInt(move.substring(0,1));
		String action = move.substring(1,2);
		if (!((col>=0) && (col<=6))) return false;
		if (!action.equals("y")&&!action.equals("r")) return false;
		if (action.equals(lastplayer)) return false;
		if (dataset[0][col]!=null) return false;
		return true;
	}

	public void executeMove(String move) {
		int col = Integer.parseInt(move.substring(0,1));
		String action = move.substring(1,2);
		
		int countspace = 0;
		for (int i=0;i<=5;i++) {
			if (dataset[i][col]==null) countspace+=1;
		}
		dataset[countspace-1][col] = action;
		lastrow = countspace-1;
		lastcol = col;
		
		lastplayer = action;
		if (!firstplayer) {
			firstplayerstr = action;
			firstplayer = true;
		}
	}

	public boolean gameOver() {
		if (!firstplayer) return false;
		String check = lastplayer + lastplayer + lastplayer + lastplayer;
		if ((rowtostring(dataset[lastrow]).contains(check)) ||
			(coltostring(lastcol).contains(check)) ||
			(diagonaltostring1(lastrow,lastcol).contains(check)) ||
			(diagonaltostring2(lastrow,lastcol).contains(check))) {
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
		if (filled==42) return true;
		return false;
	}

	public String displayBoard() {
		String res = "";
		for (int i=0; i<dataset.length; i++) {
            for (int j=0; j<dataset[i].length; j++) {
            	if (dataset[i][j]!=null) res=res+" "+dataset[i][j]+" ";
            	else res+="   ";
            	if (j!=6) res+="|";
            	else {
            		if (i!=5) res = res + System.lineSeparator()+ "---------------------------" + System.lineSeparator();
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
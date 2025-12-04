package project;

public class Mancala {
	private int box1;
	private int box2;
	private boolean isP1;
	private int[] buckets;

	Mancala() {
		box1 = 0;
		box2 = 0;
		isP1 = true;

		buckets = new int[12];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = 4;
		}
	}

	Mancala(int[] buckets, int box1, int box2, boolean isP1) {
		this();
		this.buckets = buckets;
		this.box1 = box1;
		this.box2 = box2;
		this.isP1 = isP1;
	}

	public String toString() {
		String output = "-------------";

		output += "\n     11 10  9  8  7  6\n     [";

		// top row (Player 2)
		for (int i = 11; i >= 6; i--) {
			if (i == 6) {
				output += buckets[i] + "]";
			} else {
				output += buckets[i] + "][";
			}
		}

		output += "\n";
		output += "P2[" + box2 + "]                  [" + box1 + "]P1\n";

		output += "     [";

		// bottom row (Player 1)
		for (int i = 0; i <= 5; i++) {
			if (i == 5) {
				output += buckets[i] + "]";
			} else {
				output += buckets[i] + "][";
			}
		}

		output += "\n      0  1  2  3  4  5";
		
		if (isP1) {
			output += "\nTurn: Player 1";
		} else {
			output += "\nTurn: Player 2";
		}
		
		output += "\n-------------";
		return output;
	}

	public boolean makeMove(int bucket) {
	    // Check bucket valid
	    if (bucket < 0 || bucket > 11 || buckets[bucket] == 0) {
	        return false;
	    }

	    // Check correct player side
	    if (isP1 && bucket > 5) {
	        return false;
	    }
	    if (!isP1 && bucket < 6) {
	        return false;
	    }

	    int stones = buckets[bucket];
	    if (stones == 0)
	        return false;

	    int lastIndex = (bucket + stones) % 12;   // FIXED

	    boolean runCustomRule = (buckets[lastIndex] == 0); // OK

	    buckets[bucket] = 0;

	    int index = bucket;

	    // Distribute stones
	    while (stones > 0) {
	        index = (index + 1) % 12;

	        if (index == 6 && stones >= 2 && isP1) {
	            buckets[index]++;
	            box1++;
	            stones -= 2;
	            continue;
	        }
	        if (index == 0 && stones >= 2 && !isP1) {
	            buckets[index]++;
	            box2++;
	            stones -= 2;
	            continue;
	        }
	        if (index == 6 && stones == 1 && isP1) {
	            box1++;
	            return true;
	        }
	        if (index == 0 && stones == 1 && !isP1) {
	            box2++;
	            return true;
	        }

	        buckets[index]++;
	        stones--;
	    }

	    // FIXED CUSTOM RULE CALL
	    if (isP1) {
	        if (bucket >= 0 && bucket <= 5 && runCustomRule) {
	            customRule(lastIndex);   // FIXED
	        }
	    }

	    // Switch turn
	    isP1 = !isP1;

	    return true;
	}

	public int piecesLeft() {
		return 48 - box1 - box2;
	}

	public void resetBoard() {
		// 1. Create a temporary, fresh Mancala object using the default constructor.
		Mancala defaultState = new Mancala();

		this.box1 = defaultState.box1;
		this.box2 = defaultState.box2;
		this.isP1 = defaultState.isP1;


		//copy whatever is in the buckets
		for (int i = 0; i < this.buckets.length; i++) {
			this.buckets[i] = defaultState.buckets[i];
		}
	}
	
	private void customRule(int lastBox) {
		box1 += this.buckets[11-lastBox];
		this.buckets[11-lastBox] = 0;
		box1 += this.buckets[lastBox];
		this.buckets[lastBox] = 0;
	}

	public int getBox1() {
		return box1;
	}

	public void setBox1(int box1) {
		this.box1 = box1;
	}

	public int getBox2() {
		return box2;
	}

	public void setBox2(int box2) {
		this.box2 = box2;
	}

	public boolean getIsP1() {
		return isP1;
	}

	public void setP1(boolean isP1) {
		this.isP1 = isP1;
	}

	public int[] getBuckets() {
		return buckets;
	}

	public void setBuckets(int[] buckets) {
		this.buckets = buckets;
	}

}
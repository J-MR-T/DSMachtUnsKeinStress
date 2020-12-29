package entities;

class DisplayedFormula {
	private String question, answerA, answerB, answerC;
	private boolean aTrue, bTrue, cTrue;
	private int difficulty;

	
	DisplayedFormula(String question, String answerA, String answerB, String answerC, boolean aTrue,
			boolean bTrue, boolean cTrue, int difficulty) {
		
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.aTrue = aTrue;
		this.bTrue = bTrue;
		this.cTrue = cTrue;
		this.difficulty = difficulty;
	}
	
	public boolean answerFormula(char answer) {
		return switch (Character.toLowerCase(answer)) {
			case 'a' -> aTrue;
			case 'b' -> bTrue;
			case 'c' -> cTrue;
			default -> false;
		};
	}
	
	public boolean answerFormula(int answer) {
		return switch (answer) {
			case 1 -> aTrue;
			case 2 -> bTrue;
			case 3 -> cTrue;
			default -> false;
		};
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswerA() {
		return answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public String getAnswerC() {
		return answerC;
	}
	
	public String[] getAllAnswers() {
		String[] temp = {answerA, answerB, answerC};
		return temp;
	}

	public boolean isaTrue() {
		return aTrue;
	}

	public boolean isbTrue() {
		return bTrue;
	}

	public boolean iscTrue() {
		return cTrue;
	}

	public int getDifficulty() {
		return difficulty;
	}





	
}

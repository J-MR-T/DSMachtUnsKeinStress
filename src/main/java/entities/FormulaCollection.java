package entities;

import java.io.*;
import java.util.ArrayList;
public class FormulaCollection {
	private DisplayedFormula currentQuestion;
	private ArrayList<DisplayedFormula> questionlist = new ArrayList<DisplayedFormula>();
	private File questionFile;
	private int currentquestion = 0;
	
	public FormulaCollection(File questionFile) {
		this.questionFile = questionFile;
	}

	/**
	 * Loads all Questions from Textfile into questionlist
	 * @throws IOException
	 */
	public void loadQuestions() throws IOException{
		String tempLine;
		FileReader fr = new FileReader(questionFile);
		BufferedReader br = new BufferedReader(fr);
		
		String question = "", answerA = "", answerB = "", answerC = "";
		boolean aTrue = false, bTrue = false, cTrue = false;
		int difficulty;
		int subStringStart = 0, counter = 0;

		tempLine = br.readLine();
		
		while (tempLine != null) {
			
			for(int i = 0; i < tempLine.length(); i++) {
				if (tempLine.charAt(i) == '$') {
					switch (counter) {
						case 0: aTrue = true;
								question = tempLine.substring(0, i-1);
								subStringStart = i+1;
								break;
						case 1: bTrue = true;
								answerA = tempLine.substring(subStringStart, i-1);
								subStringStart = i+1;
								break;
						case 2: cTrue = true;
								answerB = tempLine.substring(subStringStart, i-1);
								subStringStart = i+1;
								i = tempLine.length();
								break;
					}
					
				} else if(tempLine.charAt(i) == '§') {
					switch (counter) {
						case 0: aTrue = true;
								question = tempLine.substring(0, i-1);
								subStringStart = i+1;
								break;
						case 1: bTrue = true;
								answerA = tempLine.substring(subStringStart, i-1);
								subStringStart = i+1;
								break;
						case 2: cTrue = true;
								answerB = tempLine.substring(subStringStart, i-1);
								subStringStart = i+1;
								i = tempLine.length();
								break;
					}
						
				}

				answerC = tempLine.substring(subStringStart, tempLine.length()-2);
				difficulty = Character.getNumericValue(tempLine.charAt(tempLine.length()-1));
				questionlist.add(new DisplayedFormula(question, answerA, answerB, answerC, aTrue, bTrue, cTrue, difficulty));
								
			}
			tempLine = br.readLine();
		}

		br.close();
		}

	public DisplayedFormula getRandomFormula() {
		currentQuestion = questionlist.get((int) (Math.random()*(questionlist.size())));
		return currentQuestion;
	}
	
	public DisplayedFormula getNextFormula() {
		currentQuestion = questionlist.get(currentquestion++ % (questionlist.size()-1));
		return currentQuestion;
	}
	
	public DisplayedFormula getCurrentQuestion() {
		return currentQuestion;
	}
	
	
	
	
	
}
package entities;

import org.apache.commons.vfs2.FileSystemException;
import resources.FormulaParser;
import resources.ResourceLoader;

import java.util.List;

public class FormulaCollection {
	private DisplayedFormula currentQuestion;
	final private List<DisplayedFormula> questionList;
	private int questionIndex = 0;
	
	public FormulaCollection() throws FileSystemException {
	    questionList = FormulaParser.parseFormulas(ResourceLoader.getResourceAsStream("Formulas.json"));
	}

	public DisplayedFormula getRandomFormula() {
		currentQuestion = questionList.get((int) (Math.random()*(questionList.size())));
		return currentQuestion;
	}
	
	public DisplayedFormula getNextFormula() {
		currentQuestion = questionList.get(questionIndex++ % (questionList.size()-1));
		return currentQuestion;
	}
	
	public DisplayedFormula getCurrentQuestion() {
		return currentQuestion;
	}
	
	
	
	
	
}
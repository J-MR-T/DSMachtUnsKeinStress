package entities;

import org.apache.commons.vfs2.FileSystemException;
import resources.FormulaParser;
import resources.ResourceLoader;

import java.util.List;

public class FormulaCollection {
	final private List<DisplayedFormula> easy,medium,hard;
	private int questionIndex = 0;
	
	public FormulaCollection(List<DisplayedFormula> easy,List<DisplayedFormula> medium,List<DisplayedFormula> hard){

		this.easy = easy;
		this.medium=medium;
		this.hard=hard;
	}
	public static FormulaCollection getFormulas() throws FileSystemException {
		return FormulaParser.parseFormulas(ResourceLoader.getResourceAsStream("Formulas.json"));
	}


	public DisplayedFormula getRandomFormula(int difficulty) {
		List<DisplayedFormula> list= switch (difficulty) {
			case 0->easy;
			case 1->medium;
			case 2->hard;
			default -> throw new IllegalArgumentException("Unexpected value: " + difficulty);
		};
		return list.get((int) (Math.random()*(list.size())));
	}

}
package resources;

import com.google.gson.Gson;
import entities.Answer;
import entities.DisplayedFormula;
import entities.FormulaCollection;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FormulaParser {

    public static class ParsingAnswer{
        public String answer;
        public boolean result;
        Answer toAnswer(){
            return new Answer(answer,result);
        }
    }

    public class Question{
        public String question;
        public ParsingAnswer[] answers;
        DisplayedFormula toFormula(int difficulty){
            return new DisplayedFormula(question, Arrays.stream(answers).map(ParsingAnswer::toAnswer).toArray(Answer[]::new),difficulty);
        }
    }

    public class Questions{
        public Question[] easy;
        public Question[] medium;
        public Question[] hard;
       FormulaCollection toFormulas(){
           return new FormulaCollection(
                   Arrays.stream(easy).map(q->q.toFormula(0)).collect(Collectors.toList()),
                   Arrays.stream(easy).map(q->q.toFormula(1)).collect(Collectors.toList()),
                   Arrays.stream(easy).map(q->q.toFormula(2)).collect(Collectors.toList()));
       }
    }

    public static FormulaCollection parseFormulas(InputStream from){
        return new Gson().fromJson(new InputStreamReader(from, StandardCharsets.UTF_8),Questions.class).toFormulas();
    }
}

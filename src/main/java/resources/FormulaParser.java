package resources;

import com.google.gson.Gson;
import entities.Answer;
import entities.DisplayedFormula;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FormulaParser {

    public class ParsingAnswer{
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
            return new DisplayedFormula(question, Arrays.stream(answers).map(ParsingAnswer::toAnswer).toArray(Answer[]::new));
        }
    }

    public class Questions{
        public Question[] easy;
        public Question[] medium;
        public Question[] hard;
        List<DisplayedFormula> toFormulas(){
            return IntStream.range(0,3).mapToObj(d->Arrays.stream(switch (d){
                case 0->easy;
                case 1->medium;
                case 2->hard;
                default -> throw new IllegalStateException("Unexpected value: " + d);
            }).map(q->q.toFormula(d))).flatMap(s->s).collect(Collectors.toList());
        }
    }

    public static List<DisplayedFormula> parseFormulas(InputStream from){
        return new Gson().fromJson(new InputStreamReader(from, StandardCharsets.UTF_8),Questions.class).toFormulas();
    }
}

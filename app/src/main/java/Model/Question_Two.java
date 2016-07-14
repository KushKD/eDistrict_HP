package Model;

import java.io.Serializable;

/**
 * Created by kuush on 7/14/2016.
 */
public class Question_Two implements Serializable {

    public String getQuestion_Key() {
        return Question_Key;
    }

    public void setQuestion_Key(String question_Key) {
        Question_Key = question_Key;
    }

    public String getQuestion_Value() {
        return Question_Value;
    }

    public void setQuestion_Value(String question_Value) {
        Question_Value = question_Value;
    }

    String Question_Key;
    String Question_Value;
}

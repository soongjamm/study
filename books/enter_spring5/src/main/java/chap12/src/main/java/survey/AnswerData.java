package survey;

import java.util.List;

public class AnswerData {

    private List<String> responses;
    private Respondent res;

    public List<String> getResponses() {
        return responses;
    }

    public Respondent getRes() {
        return res;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public void setRes(Respondent res) {
        this.res = res;
    }
}

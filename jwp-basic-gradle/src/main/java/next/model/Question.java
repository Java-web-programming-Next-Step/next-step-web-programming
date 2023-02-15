package next.model;

import java.util.Date;

public class Question {
    private final Long questionId;
    private final String writer;
    private final String title;
    private final String contents;
    private final Date createdDate;
    private final long countOfAnswer;

    public Question(final Long questionId, final String writer, final String title, final String contents,
                    final Date createdDate, final long countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public long getCountOfAnswer() {
        return countOfAnswer;
    }
}

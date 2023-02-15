package next.model;

import java.util.Date;

public class Answer {
    private final Long answerId;
    private final String writer;
    private final String contents;
    private final Date createdDate;
    private final Long questionId;

    public Answer(final Long answerId, final String writer, final String contents, final Date createdDate,
                  final Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Long getQuestionId() {
        return questionId;
    }
}
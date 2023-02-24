package next.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import org.apache.tomcat.jni.Time;

public class Question {
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private int countOfAnswer;
    private Timestamp createdDate;

    public Question(long questionId, String writer, String title, String contents, int countOfAnswer, Timestamp createdDate) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.countOfAnswer = countOfAnswer;
        this.createdDate = createdDate;
    }

    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.countOfAnswer = 0;
        this.createdDate = Timestamp.from(Instant.now());
    }

    public long getQuestionId() {
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

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setCountOfAnswer(int countOfAnswer) {
        this.countOfAnswer = countOfAnswer;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
//    public Timestamp getCreatedDateTimestamp() {
//        return Timestamp.valueOf(createdDate);
//    }
//
//    public void setCreatedDateFrom(Timestamp timestamp) {
//        createdDate = timestamp.toLocalDateTime();
//    }

    @Override
    public String toString() {
        return "\n[" + questionId + "] " + writer + "/" + title + "\n" + contents + "\n" + countOfAnswer + " " + createdDate;
    }
}

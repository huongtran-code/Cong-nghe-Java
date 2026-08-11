package vn.edu.eaut.lab3;

public class Student {
    private String id, name;
    private double score;

    public Student(String id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getGrade() {
        if (score >= 8.5)
            return "Giỏi";
        if (score >= 7.0)
            return "Khá";
        if (score >= 5.0)
            return "Trung bình";
        return "Yếu";
    }
}
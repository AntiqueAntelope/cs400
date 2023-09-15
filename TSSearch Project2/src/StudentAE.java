public class StudentAE implements StudentInterface {
    private String firstName;
    private double score;

	public StudentAE(String firstName,  double score) {
        this.firstName = firstName;
        this.score = score;
    }

	public double getScore() {
        return this.score;
    }

	public String getName() {
        return this.firstName;
    }

    public String toString() {
        return this.firstName;
    }

}

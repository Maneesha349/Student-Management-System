import java.time.LocalDateTime;

public class Student
{
    private int rollNumber;
    private String firstName;
    private String lastName;
    private String course;
    private double rating;
    private LocalDateTime createdAt; 
   
    public Student(int rollNumber, String firstName, String lastName, String course, double rating) throws InvalidDetailsException
    {
        this.setRollNumber(rollNumber);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCourse(course);
        this.setRating(rating);
        this.createdAt = LocalDateTime.now();

    }
     public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) throws InvalidDetailsException {
        if (rollNumber > 0) {
              this.rollNumber = rollNumber;
        }else{
            throw new InvalidDetailsException();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InvalidDetailsException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidDetailsException();
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InvalidDetailsException {
         if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidDetailsException();
        }
        this.lastName = lastName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) throws InvalidDetailsException {
         if (course == null || course.trim().isEmpty()) {
            throw new InvalidDetailsException();
        }
        this.course = course;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) throws InvalidDetailsException {
         if (rating <= 0) {
            throw new InvalidDetailsException();
        }
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Student [rollNumber=" + rollNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", course="
                + course + ", rating=" + rating + ", createdAt=" + createdAt + "]";
    }


}
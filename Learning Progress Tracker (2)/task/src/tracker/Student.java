package tracker;


public class Student {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final int id;
    private static int idCounter=1000;
    private int Java;
    private int DSA;
    private int Databases;

    @Override
    public String toString() {
        return id + " " +
                "points: Java=" + Java +
                "; DSA=" + DSA +
                "; Databases=" + Databases +
                "; Spring=" + Spring;
    }

    public int getJava() {
        return Java;
    }

    public void setJava(int java) {
        Java = java;
    }

    public int getDSA() {
        return DSA;
    }

    public void setDSA(int DSA) {
        this.DSA = DSA;
    }

    public int getId() {
        return id;
    }

    public int getDatabases() {
        return Databases;
    }

    public void setDatabases(int databases) {
        Databases = databases;
    }

    public int getSpring() {
        return Spring;
    }

    public void setSpring(int spring) {
        Spring = spring;
    }

    private int Spring;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id=idCounter++;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


}
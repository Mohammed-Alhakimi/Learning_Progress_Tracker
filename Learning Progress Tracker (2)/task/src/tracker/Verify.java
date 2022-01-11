package tracker;

public class Verify {
    public boolean firstName(String s) {
        if (s.matches("^.*(-'+|'-+|''+|--+).*$")) return false;
        return s.matches("^[a-zA-Z][a-zA-Z'-]*[a-zA-Z]$");
    }

    public boolean lastName(String s) {
        if (s.matches("^.*(-'+|'-+|''+|--+).*$")) return false;
        return s.matches("^[a-zA-Z][\\sa-zA-Z'-]*[a-zA-Z]$");
    }

    public boolean email(String s) {
        return s.matches("^[^@]+@[^@]*\\.[^@]+");
    }

    public boolean points(String s) {
        return s.matches("^([\\-\\d\\w]+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$");
    }
}
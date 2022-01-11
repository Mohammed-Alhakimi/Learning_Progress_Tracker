package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentsRecords {
    TreeMap<Integer, Student> map = new TreeMap<>();
    TreeMap<String, Integer> popMap = new TreeMap<>();
    TreeMap<String, Integer> activityMap = new TreeMap<>();
    TreeMap<String, Double> easeMap = new TreeMap<>();
    ArrayList<Student> completedJava = new ArrayList<>();
    ArrayList<Student> completedSpring = new ArrayList<>();
    ArrayList<Student> completedDSA = new ArrayList<>();
    ArrayList<Student> completedDatabases = new ArrayList<>();
    int countJavaTasks = 0;
    int countDSATasks = 0;
    int countDatabasesTasks = 0;
    int countSpringTasks = 0;
    double countJavaPoints = 0;
    double countSpringPoints = 0;
    double countDSAPoints = 0;
    double countDatabasesPoints = 0;

    public void showActions() {
        System.out.println("Learning Progress Tracker");
        while (true) {
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            if (input.equals("exit")) {
                System.out.println("Bye");
                break;
            } else if (input.matches("^\\s*$")) {
                System.out.println("no input");
            } else {
                switch (input) {
                    case "add students":
                        addStudents();
                        break;
                    case "back":
                        System.out.println("Enter 'exit' to exit the program");
                        break;
                    case "list":
                        list(map);
                        break;
                    case "find":
                        find(map);
                        break;
                    case "add points":
                        addPoints();
                        break;
                    case "statistics":
                        buildStatistics(map);
                        break;
                    case "notify":
                        notifyStudent(map);
                        break;
                    default:
                        System.out.println("Error: unknown command!");
                        break;
                }
            }
        }
    }

    private void notifyStudent(TreeMap<Integer, Student> map) {
        ArrayList<Student> completedJava = new ArrayList<>();
        ArrayList<Student> completedSpring = new ArrayList<>();
        ArrayList<Student> completedDSA = new ArrayList<>();
        ArrayList<Student> completedDatabases = new ArrayList<>();
        map.values().forEach(student -> {
            if (student.getJava() >= 600 && !this.completedJava.contains(student)) {
                completedJava.add(student);
                this.completedJava.add(student);
            }
            if (student.getSpring() >= 550 && !this.completedSpring.contains(student)) {
                completedSpring.add(student);
                this.completedSpring.add(student);
            }
            if (student.getDatabases() >= 480 && !this.completedDatabases.contains(student)) {
                completedDatabases.add(student);
                this.completedDatabases.add(student);
            }
            if (student.getDSA() >= 400 && !this.completedDSA.contains(student)) {
                completedDSA.add(student);
                this.completedDSA.add(student);
            }
        });
        Set<Integer> numberOfStudentsNotified = new HashSet<>();
        numberOfStudentsNotified.add(completedDatabases.size());
        numberOfStudentsNotified.add(completedJava.size());
        numberOfStudentsNotified.add(completedDSA.size());
        numberOfStudentsNotified.add(completedSpring.size());
        if (!completedJava.isEmpty()) {
            completedJava.forEach(student -> {
                System.out.println("To: " + student.getEmail());
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.getFirstName() + " " + student.getLastName() + "! You have accomplished our Java course!");
            });
        }
        if (!completedDSA.isEmpty()) {
            completedDSA.forEach(student -> {
                System.out.println("To: " + student.getEmail());
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.getFirstName() + " " + student.getLastName() + "! You have accomplished our DSA course!");
            });
        }
        if (!completedDatabases.isEmpty()) {
            completedDatabases.forEach(student -> {
                System.out.println("To: " + student.getEmail());
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.getFirstName() + " " + student.getLastName() + "! You have accomplished our Databases course!");
            });
        }
        if (!completedSpring.isEmpty()) {
            completedSpring.forEach(student -> {
                System.out.println("To: " + student.getEmail());
                System.out.println("Re: Your Learning Progress");
                System.out.println("Hello, " + student.getFirstName() + " " + student.getLastName() + "! You have accomplished our Spring course!");
            });
        }
        System.out.println("Total " + Collections.max(numberOfStudentsNotified) + " students have been notified.");

    }

    private void buildStatistics(TreeMap<Integer, Student> map) {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        createPopularityMap();
        System.out.println("Most popular: " + findMostPop(popMap));
        System.out.println("Least popular: " + findLeastpopular(popMap));
        createActivityMap();
        System.out.println("Highest activity: " + findMostActive(activityMap));
        System.out.println("Lowest activity: " + findLeastActive(activityMap));
        createEaseMap();
        System.out.println("Easiest course: " + findEasiestCourse(easeMap));
        System.out.println("Hardest course: " + findHardestCourse(easeMap));
        while (true) {
            Scanner s = new Scanner(System.in);
            String input = s.nextLine().toLowerCase(Locale.ROOT);
            String header = "id" + " ".repeat(6) + "points" + " ".repeat(6) + "completed";
            if (input.equals("back")) {
                break;
            }
            switch (input) {
                case "java":
                    System.out.println("Java");
                    System.out.println(header);
                    showJava(map);
                    break;
                case "spring":
                    System.out.println("Spring");
                    System.out.println(header);
                    showSpring(map);
                    break;
                case "dsa":
                    System.out.println("DSA");
                    System.out.println(header);
                    showDSA(map);
                    break;
                case "databases":
                    System.out.println("Databases");
                    System.out.println(header);
                    showDataBases(map);
                    break;
                default:
                    System.out.println("Unknown course.");
                    break;
            }
        }
    }

    private void showDataBases(TreeMap<Integer, Student> map) {
        ArrayList<Student> topStudents = new ArrayList<>();
        map.forEach((s, student) -> {
            double percentage = (double) (student.getDatabases() * 100) / 480;
            if (percentage > 0) {
                topStudents.add(student);
            }
        });
        topStudents.sort(Comparator.comparing(Student::getDatabases).reversed());
        topStudents.forEach(student -> {
            double percentage = (double) (student.getDatabases() * 100) / 480;
            BigDecimal bc = new BigDecimal(percentage);
            bc = bc.setScale(1, RoundingMode.HALF_UP);
            System.out.println(student.getId() + " ".repeat(6) + student.getDatabases() + " ".repeat(6) + bc + "%");
        });
    }

    private void showSpring(TreeMap<Integer, Student> map) {
        ArrayList<Student> topStudents = new ArrayList<>();
        map.forEach((s, student) -> {
            double percentage = (double) (student.getSpring() * 100) / 550;
            if (percentage > 0) {
                topStudents.add(student);
            }
        });
        topStudents.sort(Comparator.comparing(Student::getSpring).reversed());
        topStudents.forEach(student -> {
            double percentage = (double) (student.getSpring() * 100) / 550;
            BigDecimal bc = new BigDecimal(percentage);
            bc = bc.setScale(1, RoundingMode.HALF_UP);
            System.out.println(student.getId() + " ".repeat(6) + student.getSpring() + " ".repeat(6) + bc + "%");
        });
    }

    private void showDSA(TreeMap<Integer, Student> map) {
        ArrayList<Student> topStudents = new ArrayList<>();
        map.forEach((s, student) -> {
            double percentage = (double) (student.getDSA() * 100) / 400;
            if (percentage > 0) {
                topStudents.add(student);
            }
        });
        topStudents.sort(Comparator.comparing(Student::getDSA).reversed());
        topStudents.forEach(student -> {
            double percentage = (double) (student.getDSA() * 100) / 400;
            BigDecimal bc = new BigDecimal(percentage);
            bc = bc.setScale(1, RoundingMode.HALF_UP);
            System.out.println(student.getId() + " ".repeat(6) + student.getDSA() + " ".repeat(6) + bc + "%");
        });
    }

    private void showJava(TreeMap<Integer, Student> map) {
        ArrayList<Student> topStudents = new ArrayList<>();
        map.forEach((s, student) -> {
            double percentage = (double) (student.getJava() * 100) / 600;
            if (percentage > 0) {
                topStudents.add(student);
            }
        });
        topStudents.sort(Comparator.comparing(Student::getJava).reversed());
        topStudents.forEach(student -> {
            double percentage = (double) (student.getJava() * 100) / 600;
            BigDecimal bc = new BigDecimal(percentage);
            bc = bc.setScale(1, RoundingMode.HALF_UP);
            System.out.println(student.getId() + " ".repeat(6) + student.getJava() + " ".repeat(6) + bc + "%");
        });
    }

    private String findHardestCourse(TreeMap<String, Double> easeMap) {

        double highestAverage = 0;
        StringBuilder sb = new StringBuilder();
        for (Double d : easeMap.values()
        ) {
            if (d > highestAverage) {
                highestAverage = d;
            }
        }
        if (highestAverage == 0) {
            return "n/a";
        }
        double lowestAverage = highestAverage;
        for (Double d : easeMap.values()
        ) {
            if (d < lowestAverage && d != 0) {
                lowestAverage = d;
            }
        }
        if (lowestAverage == highestAverage || lowestAverage == 0) {
            return "n/a";
        }
        for (String course : easeMap.keySet()
        ) {
            if (easeMap.get(course) == lowestAverage) {
                sb.append(course);
            }
        }

        return sb.toString();
    }

    private String findEasiestCourse(TreeMap<String, Double> easeMap) {
        double highestAverage = 0;
        StringBuilder sb = new StringBuilder();
        for (Double d : easeMap.values()
        ) {
            if (d > highestAverage) {
                highestAverage = d;
            }
        }
        if (highestAverage == 0) {
            return "n/a";
        }
        for (String course : easeMap.keySet()
        ) {
            if (easeMap.get(course) == highestAverage) {
                sb.append(course);
            }
        }
        return sb.toString();
    }

    private void createEaseMap() {
        easeMap.put("Java", countJavaPoints / countJavaTasks);
        easeMap.put("DSA", countDSAPoints / countDSATasks);
        easeMap.put("Databases", countDatabasesPoints / countDatabasesTasks);
        easeMap.put("Spring", countSpringPoints / countSpringTasks);
    }

    private String findLeastActive(TreeMap<String, Integer> activityMap) {
        int highestActivityNumber = 0;
        StringBuilder sb = new StringBuilder();
        for (Integer integer : activityMap.values()
        ) {
            if (integer > highestActivityNumber) {
                highestActivityNumber = integer;
            }
        }
        int lowestActivityNumber = highestActivityNumber;
        for (Integer integer : activityMap.values()
        ) {
            if (integer < lowestActivityNumber && integer != 0) {
                lowestActivityNumber = integer;
            }
        }
        for (String course : activityMap.keySet()
        ) {
            if (activityMap.get(course) == lowestActivityNumber) {
                sb.append(course);
            }
        }
        if (lowestActivityNumber == 0 || lowestActivityNumber == highestActivityNumber) {
            return "n/a";
        }
        return sb.toString();
    }

    private String findMostActive(TreeMap<String, Integer> activityMap) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int highestActivityNumber = 0;
        for (Integer integer : activityMap.values()
        ) {
            if (integer > highestActivityNumber) {
                highestActivityNumber = integer;
            }
        }
        if (highestActivityNumber == 0) {
            return "n/a";
        }
        for (String course : activityMap.keySet()
        ) {
            if (activityMap.get(course) == highestActivityNumber) {
                list.add(course);
            }
        }
        if (list.size() == 1) {
            return list.get(0);
        } else {

            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i) + ", ");
                }
            }
        }
        return sb.toString();
    }

    private void createActivityMap() {
        activityMap.put("Java", countJavaTasks);
        activityMap.put("DSA", countDSATasks);
        activityMap.put("Databases", countDatabasesTasks);
        activityMap.put("Spring", countSpringTasks);
    }

    private String findLeastpopular(TreeMap<String, Integer> popMap) {
        int highestPopNumber = 0;
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Integer integer : popMap.values()
        ) {
            if (integer > highestPopNumber) {
                highestPopNumber = integer;
            }
        }
        int lowestPopNumber = highestPopNumber;
        for (Integer integer : popMap.values()
        ) {
            if (integer < lowestPopNumber && integer != 0) {
                lowestPopNumber = integer;
            }
        }
        if (lowestPopNumber == 0 || lowestPopNumber == highestPopNumber) {
            return "n/a";
        }
        for (String course : popMap.keySet()
        ) {
            if (popMap.get(course) == lowestPopNumber) {
                list.add(course);
            }
        }
        if (list.size() == 1) {
            return list.get(0);
        } else {

            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i) + ", ");
                }
            }
        }
        return sb.toString();
    }

    private String findMostPop(TreeMap<String, Integer> popMap) {
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int highestPopNumber = 0;
        for (Integer integer : popMap.values()
        ) {
            if (integer > highestPopNumber) {
                highestPopNumber = integer;
            }
        }
        if (highestPopNumber == 0) {
            return "n/a";
        }
        for (String course : popMap.keySet()
        ) {
            if (popMap.get(course) == highestPopNumber) {
                list.add(course);
            }
        }
        if (list.size() == 1) {
            return list.get(0);
        } else {

            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i) + ", ");
                }
            }
        }
        return sb.toString();
    }

    private void createPopularityMap() {
        int enrolledJava = 0;
        int enrolledDSA = 0;
        int enrolledDatabases = 0;
        int enrolledSpring = 0;
        for (Student s : map.values()
        ) {
            if (s.getDSA() > 0) {
                enrolledDSA++;
            }
            if (s.getJava() > 0) {
                enrolledJava++;
            }
            if (s.getDatabases() > 0) {
                enrolledDatabases++;
            }
            if (s.getSpring() > 0) {
                enrolledSpring++;
            }
        }
        popMap.put("Java", enrolledJava);
        popMap.put("DSA", enrolledDSA);
        popMap.put("Databases", enrolledDatabases);
        popMap.put("Spring", enrolledSpring);
    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");

        Pattern pointsPattern = Pattern.compile("^([\\-\\d\\w]+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)$");

        while (true) {
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            if (line.equals("back")) {
                break;
            }
            Matcher matcher = pointsPattern.matcher(line);
            String id;
            int java;
            int dsa;
            int dataBases;
            int Spring;
            if (matcher.find()) {
                id = matcher.group(1);
                java = Integer.parseInt(matcher.group(2));
                dsa = Integer.parseInt(matcher.group(3));
                dataBases = Integer.parseInt(matcher.group(4));
                Spring = Integer.parseInt(matcher.group(5));
                try {
                    if (map.containsKey(Integer.valueOf(id))) {
                        if (java > 0) {
                            countJavaTasks++;
                            countJavaPoints += java;
                        }
                        if (dataBases > 0) {
                            countDatabasesTasks++;
                            countDatabasesPoints += dataBases;
                        }
                        if (dsa > 0) {
                            countDSATasks++;
                            countDSAPoints += dsa;
                        }
                        if (Spring > 0) {
                            countSpringTasks++;
                            countSpringPoints += Spring;
                        }
                        map.get(Integer.valueOf(id)).setJava(map.get(Integer.valueOf(id)).getJava() + java);
                        map.get(Integer.valueOf(id)).setDatabases(map.get(Integer.valueOf(id)).getDatabases() + dataBases);
                        map.get(Integer.valueOf(id)).setDSA(map.get(Integer.valueOf(id)).getDSA() + dsa);
                        map.get(Integer.valueOf(id)).setSpring(map.get(Integer.valueOf(id)).getSpring() + Spring);
                        System.out.println("Points updated.");
                    } else {
                        System.out.println("No student is found for id=" + id + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("No student is found for id=" + id + ".");
                }
            } else {
                System.out.println("Incorrect points format.");
            }
        }
    }

    private void find(TreeMap<Integer, Student> map) {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            Scanner s = new Scanner(System.in);
            String line = s.nextLine();
            if (line.equals("back")) {
                break;
            }
            if (map.containsKey(Integer.valueOf(line))) {
                System.out.println(map.get(Integer.valueOf(line)));
            } else {
                System.out.println("No student is found for id=" + line + ".");
            }
        }
    }

    public void list(TreeMap<Integer, Student> map) {
        if (map.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students:");
            map.forEach((key, value) -> System.out.println(key));
        }
    }

    private void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        Pattern credentials = Pattern.compile("^([^\\s]+)\\s(.*)\\s(.*)$");
        Pattern doubleInvalidChars = Pattern.compile("['\\-]{2,}");
        Pattern emailpattern = Pattern.compile("^[\\w\\d][\\w\\d\\.]*@[\\w\\d]+\\.\\w+$");
        while (true) {
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            if (input.equals("back")) {
                System.out.printf("Total %d students have been added.\n", map.size());
                break;
            } else {
                String firstname;
                String lastname;
                String email;
                Matcher m = credentials.matcher(input);
                if (m.find()) {
                    firstname = m.group(1);
                    lastname = m.group(2);
                    email = m.group(3);
                    if (firstname.length() < 2
                            || !firstname.matches("^[a-zA-Z][a-zA-Z\\-']*[a-zA-Z]")
                            || doubleInvalidChars.matcher(firstname).find()) {
                        System.out.println("Incorrect first name");
                    } else if (lastname.length() < 2
                            || !lastname.matches("^[a-zA-Z][a-zA-Z\\-\\s']*[a-zA-Z]")
                            || doubleInvalidChars.matcher(lastname).find()) {
                        System.out.println("Incorrect last name");
                    } else if (!emailpattern.matcher(email).matches()) {
                        System.out.println("Incorrect email");
                    } else {
                        addStudentToTheMap(new Student(firstname, lastname, email));
                    }
                } else {
                    System.out.println("Incorrect credentials");
                }
            }
        }
    }

    public boolean addStudentToTheMap(Student s) {
        ArrayList<String> emails = new ArrayList<>();
        map.values().forEach(student -> {
            emails.add(student.getEmail());
        });
        if (!emails.contains(s.getEmail())) {
            map.put(s.getId(), s);
            System.out.println("The student has been added.");
            return true;
        } else {
            System.out.println("This email is already taken.");
        }
        return false;
    }
}
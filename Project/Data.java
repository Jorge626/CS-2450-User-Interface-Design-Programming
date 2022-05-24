public class Data {
  public static class Section {
    public Section(String term, double avgGPA, int totalEnrollment, String courseName, String instructor,
        int classNumber, String days,
        String time,
        String location, String mode, double units, int section) {
      this.term = term;
      this.avgGPA = avgGPA;
      this.courseName = courseName;
      this.instructor = instructor;
      this.classNumber = classNumber;
      this.days = days;
      this.time = time;
      this.location = location;
      this.mode = mode;
      this.units = units;
      this.section = section;
      this.totalEnrollment = totalEnrollment;
    }

    String term;
    double avgGPA;
    int totalEnrollment;
    String courseName;
    String instructor;
    int classNumber;
    String days;
    String time;
    String location;
    String mode;
    double units;
    int section;

  }

  public static class Course {
    String courseName;

    public Course(String courseName, String courseDesc) {
      this.courseName = courseName;
      this.courseDesc = courseDesc;
    }

    String courseDesc;
  }

  public static Section[] sectionList = {
      new Section("Spring 2022", 3.56, 70, "CS 2450 - Graphical User Interface Design & Programming", "Ben Steichen",
          33066, "MW",
          "2:30PM-3:45PM", "Bldg 8 Rm 348", "Fully Synchronous", 3, 1),
      new Section("Spring 2022", 3.411, 55, "CS 4250 - Web Search and Recommender Systems", "Ben Steichen", 33098, "MW",
          "4:00PM-5:15PM", "Bldg 8 Rm 302",
          "Fully Synchronous", 3, 1) };

  public static String[] courseNames = { "CS 2450 - Graphical User Interface Design & Programming",
      "CS 4250 - Web Search and Recommender Systems" };
}

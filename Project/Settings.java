
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Settings {
  private ScrollPane root;
  private VBox classList = new VBox();

  private VBox breakList = new VBox();

  public Pane getAddClass() {
    Label label = new Label("Add Class");
    label.getStyleClass().add("settingLabel");
    label.setId("first");

    // Term Select
    Label termLabel = new Label("Term");
    termLabel.setTextFill(Color.WHITE);
    ChoiceBox<String> termChoices = new ChoiceBox<>();
    termChoices.getItems().addAll("Fall 2021", "Spring 2022", "Fall 2022");
    // Default value
    termChoices.setValue("Fall 2022");

    // Course Select
    Label courseSelectLabel = new Label("Course");
    courseSelectLabel.setTextFill(Color.WHITE);
    ChoiceBox<String> courseSelect = new ChoiceBox<>();
    courseSelect.getItems().addAll(Data.courseNames);
    courseSelect.setValue(Data.courseNames[0]);

    // Add Button
    Button button = new Button("Add Course");
    button.setOnAction(e -> {
      addClass(new Course(courseSelect.getValue()));
    });

    VBox.setMargin(courseSelectLabel, new Insets(10, 0, 0, 0));
    VBox.setMargin(button, new Insets(10, 0, 0, 0));

    return new VBox(label, termLabel, termChoices, courseSelectLabel, courseSelect, button);
  }

  public Pane getClassList() {
    return classList;
  }

  private void addClass(Course course) {
    if (classList.getChildren().size() == 0) {
      Label label = new Label("Class List");
      label.getStyleClass().add("settingLabel");
      classList.getChildren().add(label);
    }

    VBox sectionList = new VBox();
    TitledPane coursePane = new TitledPane(course.courseName, sectionList);

    for (Data.Section section : course.sections) {
      String sectionTitle = section.section + " - " + section.instructor + " - " + section.mode + " | " + "GPA:"
          + section.avgGPA + "(" + section.totalEnrollment + ")";
      VBox sectionData = new VBox();
      TitledPane sectionPane = new TitledPane(sectionTitle, sectionData);

      sectionData.getChildren().addAll(new Label("Class Number: " + section.classNumber),
          new Label("Days: " + section.days), new Label("Time: " + section.time),
          new Label("Location: " + section.location), new Label("Units: " + section.units));
      sectionList.getChildren().add(sectionPane);
    }

    Button removeButton = new Button("Remove Class");
    sectionList.getChildren().add(removeButton);
    removeButton.setOnAction(e -> {
      getClassList().getChildren().remove(coursePane);
    });
    removeButton.getStyleClass().add("removeButton");
    VBox.setMargin(removeButton, new Insets(10, 0, 0, 0));

    this.classList.getChildren().add(coursePane);
  }

  public Pane getAddBreak() {
    Label label = new Label("Add Break");
    label.getStyleClass().add("settingLabel");

    // Name input
    Label nameLabel = new Label("Break Name");
    nameLabel.setTextFill(Color.WHITE);
    TextField name = new TextField("Unnamed Break");

    // Days Select
    Label daySelectLabel = new Label("Days");
    daySelectLabel.setTextFill(Color.WHITE);
    daySelectLabel.setPadding(new Insets(10, 0, 0, 0));

    CheckBox mo = new CheckBox("Monday");
    CheckBox tu = new CheckBox("Tuesday");
    CheckBox we = new CheckBox("Wednesday");
    CheckBox th = new CheckBox("Thursday");
    CheckBox fr = new CheckBox("Friday");
    mo.getStyleClass().add("dayButton");
    tu.getStyleClass().add("dayButton");
    we.getStyleClass().add("dayButton");
    th.getStyleClass().add("dayButton");
    fr.getStyleClass().add("dayButton");

    // Time Select
    Label startLabel = new Label("Start Time");
    startLabel.setTextFill(Color.WHITE);
    ChoiceBox<Integer> startHour = new ChoiceBox<>();
    startHour.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    startHour.setValue(12);
    ChoiceBox<Integer> startMinute = new ChoiceBox<>();
    startMinute.getItems().addAll(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
    startMinute.setValue(0);
    ChoiceBox<String> startAP = new ChoiceBox<>();
    startAP.getItems().addAll("AM", "PM");
    startAP.setValue("PM");
    HBox start = new HBox(startHour, startMinute, startAP);
    Label endLabel = new Label("End Time");
    endLabel.setTextFill(Color.WHITE);
    ChoiceBox<Integer> endHour = new ChoiceBox<>();
    endHour.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    endHour.setValue(12);
    ChoiceBox<Integer> endMinute = new ChoiceBox<>();
    endMinute.getItems().addAll(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
    endMinute.setValue(0);
    ChoiceBox<String> endAP = new ChoiceBox<>();
    endAP.getItems().addAll("AM", "PM");
    endAP.setValue("PM");
    HBox end = new HBox(endHour, endMinute, endAP);
    VBox.setMargin(startLabel, new Insets(10, 0, 0, 0));

    // Add Button
    Button button = new Button("Add Break");
    button.setOnAction(e -> {
      String days = "";
      if (mo.isSelected())
        days += "Mo";
      if (tu.isSelected())
        days += "Tu";
      if (we.isSelected())
        days += "We";
      if (th.isSelected())
        days += "Th";
      if (fr.isSelected())
        days += "Fr";
      addBreak(new Break(name.getText(), days,
          startHour.getValue() + ":"
              + (startMinute.getValue().toString().length() == 1 ? "0" + startMinute.getValue()
                  : startMinute.getValue())
              + startAP.getValue()
              + "-" + endHour.getValue() + ":"
              + (endMinute.getValue().toString().length() == 1 ? "0" + endMinute.getValue()
                  : endMinute.getValue())
              + endAP.getValue()));
    });
    VBox.setMargin(button, new Insets(10, 0, 0, 0));

    return new VBox(label, nameLabel, name, daySelectLabel, mo, tu, we, th, fr, startLabel, start, endLabel, end,
        button);

  }

  private void addBreak(Break break1) {
    if (breakList.getChildren().size() == 0) {
      Label label = new Label("Break List");
      label.getStyleClass().add("settingLabel");
      breakList.getChildren().add(label);
    }

    VBox breakPane = new VBox();
    breakPane.getChildren().addAll(new Label(break1.name), new Label("Days: " + break1.days),
        new Label("Time: " + break1.time));
    breakPane.getStyleClass().add("break");
    breakPane.setBorder(
        new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    Button removeButton = new Button("Remove Break");
    breakPane.getChildren().add(removeButton);
    removeButton.setOnAction(e -> {
      getBreakList().getChildren().remove(breakPane);
    });
    removeButton.getStyleClass().add("removeButton");
    VBox.setMargin(removeButton, new Insets(10, 0, 0, 0));
    breakList.getChildren().add(breakPane);
  }

  public Pane getBreakList() {
    return breakList;
  }

  public Button getReset() {
    Button button = new Button("Reset Scheduler");
    button.setOnAction(e -> {
      classList.getChildren().removeAll(classList.getChildren());
      breakList.getChildren().removeAll(breakList.getChildren());
    });
    VBox.setMargin(button, new Insets(10, 0, 0, 0));
    return button;
  }

  public Settings() {
    VBox vbox = new VBox(getAddClass(), getAddBreak(), getClassList(), getBreakList(), getReset());
    vbox.setId("settingsPaneVBox");
    root = new ScrollPane(vbox);
    root.setId("settingsPane");
    root.setFitToHeight(true);
    root.setFitToWidth(true);
  }

  public ScrollPane getView() {
    return root;
  }

  private class Course {
    public Course(String courseName) {
      this.courseName = courseName;
      ArrayList<Data.Section> sections = new ArrayList<>();
      for (Data.Section section : Data.sectionList) {
        if (section.courseName.equals(courseName))
          sections.add(section);
      }
      this.sections = sections.toArray(new Data.Section[0]);
    }

    String courseName;
    Data.Section[] sections;
  }

  private class Break {

    public Break(String name, String days, String time) {
      this.name = name;
      this.days = days;
      this.time = time;
    }

    String name;
    String days;
    String time;
  }
}

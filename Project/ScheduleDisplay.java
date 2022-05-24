import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ScheduleDisplay {
  private Pane root;
  // these are the colors and corresponding label used for the class type in the schedule display
  private static String[] colors = {"#ffbfc5", "#ffc89c", "#faefbe", "#8fffff", "#baffd5", "#d3cfff", "#fac4ff"};
  private static String[] labelNames = {"Face-to-Face", "Fully Synchronous", "Hybrid Sychronous Component", "Fully Asynchronous", "Hybrid Asynchronous Component", "Bisynchronous", "Hyflex"};

  // these are the images used as the schedules in the schedule display
  private static String[] imageName = {"sch1.png", "sch2.png", "sch6.png", "sch3.png", "sch4.png", "sch5.png", "sch7.png", "sch8.png"};


  // function to create a horizontal box of legend item (color & label)
  public HBox setLegend(String color, String labelName){
    HBox hBox = new HBox();
    hBox.setSpacing(3);

    Rectangle rectangle = new Rectangle(12,12);
    rectangle.setFill(Color.web(color));

    Label label = new Label(labelName);
    label.setFont(new Font(10));

    hBox.getChildren().addAll(rectangle, label);

    return hBox;
  }

  // function to get the image view for the correct calendar
  public ImageView getImageView(int index) {
    Image image = new Image("file:assets\\" + imageName[index]);
    ImageView imageView = new ImageView(image);
    return imageView;
  }

  // function that creates the schedule display
  public ScheduleDisplay() {
    // Vbox for the right column that contains the hourly-week schedule (labels, boxes and images)
    root = new VBox();
    root.setId("scheduleDisplay");

    // the title for the hourly-week schedule display
    Label label = new Label("Weekly Schedule");
    label.getStyleClass().add("scheduleLabel");
    VBox labelBox = new VBox(label);
    labelBox.setAlignment(Pos.CENTER);

    // HBox for the sort choice box
    HBox sortBox = new HBox();
    ChoiceBox<String> sort = new ChoiceBox<>();
    sort.getItems().addAll("Sort by: GPA: High to Low");
    sort.setValue("Sort by: GPA: High to Low");
    sort.getStylesheets().add("sort_choicebox_style.css");
    sortBox.getChildren().addAll(sort);
    sortBox.setAlignment(Pos.TOP_RIGHT);
    sortBox.setStyle("-fx-padding: 0 20 0 0;");

    // creating the pagination needed to display the possible schedules
    Pagination pagination = new Pagination(imageName.length);
      pagination.setMaxPageIndicatorCount(12);
      pagination.getStylesheets().add("pagination_styles.css");
      pagination.setPageFactory((pageIndex) -> {
        ScrollPane scheduleScrollPane = new ScrollPane();
        ImageView schedule = getImageView(pageIndex);
        schedule.fitWidthProperty().bind(root.widthProperty());
        schedule.fitHeightProperty().bind(pagination.heightProperty());

        scheduleScrollPane.setContent(schedule);
        scheduleScrollPane.setFitToWidth(true);
        
        return scheduleScrollPane;
    });
    HBox paginationBox = new HBox(pagination);
    paginationBox.setAlignment(Pos.TOP_RIGHT);


    // creating the legend for the schedule display
    HBox legendBox = new HBox();
    System.out.println(colors.length);
    for(int i = 0; i < colors.length; i++) {
      HBox temp = setLegend(colors[i], labelNames[i]);
      legendBox.setAlignment(Pos.CENTER);
      legendBox.setSpacing(12);
      legendBox.getChildren().add(temp);
    };

    // adding all elements to the Schedule Display vertical box
    root.getChildren().addAll(labelBox, sortBox, paginationBox, legendBox);
  }

  public Pane getView() {
    return root;
  }

}

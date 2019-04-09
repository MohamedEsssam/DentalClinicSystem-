package sample;

/**
 * Created by ${Mohamed Essam} on ${3/16/2019}.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Main extends Application {
    private boolean passFlag = true;
    private boolean emailFlag = true;
    private TableView<patients>patientsTableView;
    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private static double date = (Math.random()*((1000000)+1))+0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        DB_Connection connection = new DB_Connection();

        final ImageView imageView1 = new ImageView(new Image(new FileInputStream("C:\\Users\\Mohamed Essam\\Pictures\\dental.png")));
        imageView1.setX(0);
        imageView1.setY(0);
        /*for scene1 Entrance form for doctor*/
        final TextField UserName = new TextField();
        UserName.setPromptText("Username");

        final PasswordField password = new PasswordField ();
        password.setPromptText("Password");

        final Button Enter = new Button("Enter");
        Enter.setStyle("-fx-text-fill: black;-fx-cursor: hand;");

        final Label UsernameError = new Label();
        UsernameError.setLayoutY(135);
        UsernameError.setStyle("-fx-font-size: 20px;-fx-text-fill: red;");

        final Label PasswordError = new Label();
        PasswordError.setLayoutY(165);
        PasswordError.setStyle("-fx-font-size: 20px;-fx-text-fill: red;");

        final GridPane gride = new GridPane();
        gride.setVgap(20);
        gride.setHgap(10);

        gride.setPadding(new Insets(5, 5, 5, 5));
        gride.add(new Label("Username"), 0, 0);
        gride.add(UserName, 1, 0);

        gride.setPadding(new Insets(5, 5, 5, 5));
        gride.add(new Label("Password"), 0, 1);
        gride.add(password, 1, 1);

        gride.add(Enter, 1, 2);

        final Pane pane = new Pane(imageView1);
        pane.getChildren().add(gride);
        pane.getChildren().add(UsernameError);
        pane.getChildren().add(PasswordError);

        final Scene scene = new Scene(pane);
/**********************************************************************************************************************/
        /*for scene2 Show registered patient or register new patients*/
        final ImageView imageView2 = new ImageView(new Image(new FileInputStream("C:\\Users\\Mohamed Essam\\Pictures\\dental.png")));
        imageView1.setX(0);
        imageView1.setY(0);

        final Button newPatient = new Button("New patient");
        newPatient.setStyle("-fx-text-fill: black;-fx-cursor: hand;");
        final Button Showme   = new Button("Showme");
        Showme.setStyle("-fx-text-fill: black;-fx-cursor: hand;");
        final Button ShowmeAll= new Button("ShowmeAll");
        ShowmeAll.setStyle("-fx-text-fill: black;-fx-cursor: hand;");

        final TextField PatientName = new TextField();
        PatientName.setPromptText("Patiant name");

        final GridPane gride1 = new GridPane();
        gride1.setVgap(20);
        gride1.setHgap(10);

        gride1.setPadding(new Insets(5, 5, 5, 5));
        gride1.add(new Label("Patient name"), 0, 0);
        gride1.add(PatientName, 1, 0);
        gride1.add(Showme, 2, 0);

        gride1.add(ShowmeAll, 1, 1);

        gride1.add(newPatient, 1, 2);

        final Pane pane1 = new Pane(imageView2);
        pane1.getChildren().add(gride1);

        final Scene scene1= new Scene(pane1);
/**********************************************************************************************************************/
        /* for scene3 "register Form"*/
        final ImageView imageView3 = new ImageView(new Image(new FileInputStream("C:\\Users\\Mohamed Essam\\Pictures\\dental.png")));
        imageView1.setX(0);
        imageView1.setY(0);
        /*for scene1 Entrance form*/
        final TextField firstName = new TextField();
        firstName.setPromptText("First name");

        final TextField lastName = new TextField ();
        lastName.setPromptText("Last name");

        final TextField nationalNumber = new TextField();
        nationalNumber.setPromptText("National Number");

        final TextField mobileNumber = new TextField ();
        mobileNumber.setPromptText("Mobile Number");

        final TextField Address = new TextField ();
        Address.setPromptText("Address");

        final Button register = new Button("Register");
        register.setStyle("-fx-text-fill: black;-fx-cursor: hand;");

        final Button not_register = new Button("back");
        not_register.setStyle("-fx-cursor: hand;");


        final GridPane gride2 = new GridPane();
        gride2.setVgap(20);
        gride2.setHgap(10);

        gride2.setPadding(new Insets(5, 5, 5, 5));
        gride2.add(new Label("first name"), 0, 0);
        gride2.add(firstName, 1, 0);

        gride2.add(new Label("last name"), 0, 1);
        gride2.add(lastName, 1, 1);

        gride2.add(new Label("National number"), 0, 2);
        gride2.add(nationalNumber, 1, 2);

        gride2.add(new Label("Mobile"), 0, 3);
        gride2.add(mobileNumber, 1, 3);

        gride2.add(new Label("address"), 0, 4);
        gride2.add(Address, 1, 4);

        gride2.add(register, 1, 5);
        gride2.add(not_register, 2, 5);



        final Pane pane2 = new Pane(imageView3);
        pane2.getChildren().add(gride2);


        final Scene scene2= new Scene(pane2);

/**********************************************************************************************************************/
        /*for scene4 for reserve date "time slots" */
        final ImageView imageView4 = new ImageView(new Image(new FileInputStream("C:\\Users\\Mohamed Essam\\Pictures\\dental.png")));
        imageView1.setX(0);
        imageView1.setY(0);

        final DatePicker checkInDatePicker = new DatePicker();
        checkInDatePicker.setValue(LocalDate.now());

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        long p = ChronoUnit.DAYS.between(checkInDatePicker.getValue(), item);
                        setTooltip(new Tooltip("You're about to wait for " + p + " days"));
                        setStyle("-fx-cursor: hand;");
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #EEEEEE;");
                        }
                    }
                };
            }
        };
        checkInDatePicker.setDayCellFactory(dayCellFactory);

        final Label PatientLabel = new Label();
        PatientLabel.setText(PatientName.getText());
        final Label availableSlots = new Label("available Slots");

        final Button done = new Button("Done");
        done.setStyle("-fx-text-fill: black;-fx-cursor: hand;");


        final ToggleGroup group = new ToggleGroup();

        final RadioButton rb1 = new RadioButton("8:30AM");
        rb1.setToggleGroup(group);
        rb1.setUserData("8:30");

        final RadioButton rb2 = new RadioButton("9AM");
        rb2.setToggleGroup(group);
        rb2.setUserData("9");

        final RadioButton rb3 = new RadioButton("9:30AM");
        rb3.setToggleGroup(group);
        rb3.setUserData("9:30");

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    System.out.println(group.getSelectedToggle().getUserData().toString());
                }}});

        final GridPane gride3 = new GridPane();
        gride3.setVgap(20);
        gride3.setHgap(10);

        gride3.setPadding(new Insets(5, 5, 5, 5));
        gride3.add(PatientLabel, 0, 0);
        gride3.add(availableSlots, 0, 1);

        gride3.add(rb1, 0, 2);
        gride3.add(rb2, 0, 3);
        gride3.add(rb3, 0, 4);
        gride3.add(checkInDatePicker, 0, 5);
        gride3.add(done, 1, 6);

        final Pane pane3 = new Pane(imageView4);
        pane3.getChildren().add(gride3);

        final Scene scene3= new Scene(pane3);
/**********************************************************************************************************************/
                      /*for scene5 for show all  patient "Display all patients in table "*/
         final TableColumn<patients, String> nameColumn = new TableColumn<>("Name");
         nameColumn.setMinWidth(200);
         nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
         nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        final TableColumn<patients, String> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        final TableColumn<patients, String> mobileColumn = new TableColumn<>("Mobile Number");
        mobileColumn.setMinWidth(200);
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        patientsTableView = new TableView<>();
        patientsTableView.getColumns().addAll(nameColumn, idColumn, mobileColumn);

        final Button back = new Button("Back");
        back.setStyle("-fx-cursor: hand;");

        final Button delete = new Button("Delete");
        delete.setStyle("-fx-background-color:red;-fx-text-fill: White;-fx-cursor: hand;");
      //  delete.setLayoutX(700);

        final Pane pane4 =new Pane();
        pane4.getChildren().add(patientsTableView);
        pane4.getChildren().add(back);
        pane4.getChildren().add(delete);
        final Scene scene4= new Scene(pane4);


        /*add mouse action for each button*/
        Enter.setOnAction(event -> {
            if(!UserName.getText().equals("Doctor")){UsernameError.setText("- Error Username wrong");
                emailFlag = false;
            }
            else{
                emailFlag = true;
                UsernameError.setText("");
            }

            if(!password.getText().equals("Doctor")){PasswordError.setText("- Error Password wrong");
                passFlag = false;
            }
            else{
                passFlag = true;
                PasswordError.setText("");
            }

            if (passFlag && emailFlag){
                primaryStage.setScene(scene1);
            }
        });

        newPatient.setOnAction(event -> {
             primaryStage.setScene(scene2);
        });

        register.setOnAction(event -> {
            if(!(firstName.getText().equals("")&lastName.getText().equals("")&nationalNumber.getText().equals(""))) {
                if (connection.insertData("insert into patients (fname,lname,id,mobile,address,date,time) values('" + firstName.getText() + "','" + lastName.getText() + "','" + nationalNumber.getText() + "','" + mobileNumber.getText() + "','" + Address.getText() + "','"+Double.toString(date)+"','')","select id from patients where id = '"+nationalNumber.getText()+"'")) {
                    date++;
                    primaryStage.setScene(scene1);
                }
            else{
                    alert.setContentText("Duplicated National number can't register");
                    alert.show();
                }}});

        Showme.setOnAction(event -> {
            if (!PatientName.getText().equals("")) {
                try {
                    if (connection.checkData("select fname from patients where fname = '"+PatientName.getText()+"'")) {
                        PatientLabel.setText(PatientName.getText());
                        primaryStage.setScene(scene3);
                    }
                    else{
                        alert.setContentText("'"+PatientName.getText()+"' not found. register him/her");
                        alert.show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }});

        done.setOnAction(event -> {
            System.out.println(checkInDatePicker.getValue());
            connection.updateData("UPDATE patients SET date = '"+checkInDatePicker.getValue()+"' , time = '"+group.getSelectedToggle().getUserData().toString()+"' WHERE fname = '"+PatientLabel.getText()+"'");
            primaryStage.setScene(scene1);
        });

        ShowmeAll.setOnAction(event -> {
            patientsTableView.setItems(connection.getpatient("select * from patients"));
            primaryStage.setScene(scene4);
        });

        back.setOnAction(event -> {
            primaryStage.setScene(scene1);
        });

        not_register.setOnAction(event -> {
            primaryStage.setScene(scene1);
        });

        delete.setOnAction(event -> {
            connection.deletePatients(patientsTableView);
        });


          /*to start scene*/
        primaryStage.setTitle("Dental Clinic");
        primaryStage.setScene(scene);
        primaryStage.show();

        /****  to display image dynamically  ****/
        pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double)newValue;
                imageView1.setFitHeight(height);
            }});

        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double)newValue;
                imageView1.setFitWidth(width);
            }});

        pane1.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double)newValue;
                imageView2.setFitHeight(height);
            }});

        pane1.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double)newValue;
                imageView2.setFitWidth(width);
            }});

        pane2.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double)newValue;
                imageView3.setFitHeight(height);
            }});

        pane2.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double)newValue;
                imageView3.setFitWidth(width);
            }});

        pane3.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double)newValue;
                imageView4.setFitHeight(height);
            }});

        pane3.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double)newValue;
                imageView4.setFitWidth(width);
            }});

        pane4.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double)newValue;
                patientsTableView.setPrefHeight(height);
            }});

        pane4.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double)newValue;
                patientsTableView.setPrefWidth(width);
                delete.setLayoutX(((double)newValue-50));
            }});

    }

    public static void main(String[] args) {
        DB_Connection connection = new DB_Connection();
        connection.getData("select * from patients");
        launch(args);
    }
}
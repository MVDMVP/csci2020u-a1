/*
CSCI2020, assignment 1
Developed by:
Michael van Dalen   |   100661018
Minh Huynh          |   100669889
*/
package csci2020ua01;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CSCI2020UA01 extends Application {
    Stage window;
    Scene init;
    
    
    private double radius = 10;
    private Circle bigCircle = new Circle(200,140,100); // create big circle
    //create lines
    private Line line1 = new Line();
    private Line line2 = new Line();
    private Line line3 = new Line();
    //Create label to display the angle
    private Text[] text = {new Text(), new Text(), new Text()};

    // create the angle in rad for 3 random points in circle
    double angle1 = Math.random() * 361;
    double angle2 = Math.random() * 361;
    double angle3 = Math.random() * 361;
    double rad1 = Math.toRadians(angle1);
    double rad2 = Math.toRadians(angle2);
    double rad3 = Math.toRadians(angle3);
    
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        //creating a second stage, setting coordinates
        Stage outStage = new Stage();
        outStage.setX(1920/2-350);
        outStage.setY(1080/2-155);
        
        //initializing buttons 
        Button b1 = new Button ("Question 1");
        Button b2 = new Button ("Question 2");
        Button b3 = new Button ("Question 3");
        Button b4 = new Button ("Question 4");
        
        //setting button press actions
        b1.setOnAction(e->{
            showQuestion1(outStage);
        });
        b2.setOnAction(e->{
            showQuestion2(outStage);
        });
        b3.setOnAction(e->{
            showQuestion3(outStage);
        });
        b4.setOnAction(e->{
            showQuestion4(outStage);
        });
        
        //VBox to alight buttons vertically
        VBox root = new VBox(10);
        root.getChildren().addAll(b1,b2,b3,b4);
        
        //setting up initial menu scene, dimensions,title
        init = new Scene(root, 100, 155);
        window.setX(1920/2-500);
        window.setY(1080/2-155);
        window.setTitle("CSICI2020U Assignment 1");
        window.setScene(init);
        //display menu screen
        window.show();  
    }

    public static void main(String[] args) {
        launch(args);
    }
    //question #1
    private void showQuestion1(Stage outStage) {
        //generating 3 random card file names
        String card1,card2,card3;
        card1 = "Cards/"+ (int)(Math.random()*(54)+1) +".png";
        card2 = "Cards/"+ (int)(Math.random()*(54)+1) +".png";
        card3 = "Cards/"+ (int)(Math.random()*(54)+1) +".png";
        //initializing 3 imageviews based off of above card file names
        ImageView card_image1 = new ImageView(card1);
        ImageView card_image2 = new ImageView(card2);
        ImageView card_image3 = new ImageView(card3);
        
        //layout images horizontally
        HBox layout = new HBox(15);
        layout.getChildren().addAll(card_image1,card_image2,card_image3);
        
        //creating scene with above layout, displaying scene on second stage
        Scene outScene = new Scene(layout,250,100);
        outStage.setTitle("Question_1");
        outStage.setScene(outScene);
        outStage.show();
    }

    private void showQuestion2(Stage outStage) {
 //Investment Amount
        Label label1 = new Label("Investment Amount");
        TextField investAmount = new TextField ();
        investAmount.setPromptText("Investment Amount");

        //years
        Label label2 = new Label("Years");
        TextField years = new TextField ();
        years.setPromptText("Years");

        //Annual Interest Rate
        Label label3 = new Label("Annual Interest Rate");
        TextField rate = new TextField ();
        rate.setPromptText("Annual Interest Rate");

        //Future Value
        Label label4 = new Label("Future value");
        TextField futureValue = new TextField ();
        futureValue.setPromptText("Future value");
        futureValue.setEditable(false); //make the text field read only

        //Calculate button
        Button submit = new Button("Calculate");
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(10);

        //add labels
        pane.add(label1, 0, 0);
        pane.add(label2, 0, 1);
        pane.add(label3, 0, 2);
        pane.add(label4, 0, 3);


        //add text fields
        pane.add(investAmount, 1, 0);
        pane.add(years, 1, 1);
        pane.add(rate, 1, 2);
        pane.add(futureValue, 1, 3);


        //add buttons action (calculate the value)
        pane.add(submit, 1, 6);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println(investAmount.getText());
                System.out.println(years.getText());
                System.out.println(rate.getText());
                /*
                    futureValue = investmentAmount * (1 + monthlyInterestRate)^(years*12)
                    monthlyInterestRate = Annual Interested Rate / (12*100)
                 */
                Double value = Double.parseDouble(investAmount.getText()) *
                        Math.pow((1 + Double.parseDouble(rate.getText())/(12*100)),
                                (Double.parseDouble(years.getText()))*12);
                String result = String.format("%.2f", value);
                futureValue.setText(result);
            }
        });

        Scene scene = new Scene(pane, 400, 250);
        outStage.setTitle("Question_2");
        outStage.setScene(scene);
        outStage.show();
    }

    private void showQuestion3(Stage outStage) {
        //create 3 point class to store 3 different (x,y)
        Point pt1 = new Point();
        Point pt2 = new Point();
        Point pt3 = new Point();

        //put the point on the circle perimeter
        pt1.translate((int)(Math.sin(rad1)*100), (int)(Math.cos(rad1)*100));
        pt2.translate((int)(Math.sin(rad2)*100), (int)(Math.cos(rad2)*100));
        pt3.translate((int)(Math.sin(rad3)*100), (int)(Math.cos(rad3)*100));

        bigCircle.setStroke(Color.BLACK);
        bigCircle.setFill(Color.WHITE);

        //create 3 smaller circles for the 3 points
        Circle[] circle = {new Circle(pt1.getX()+200, pt1.getY()+140, 10),
                new Circle(pt2.getX()+200, pt2.getY()+140, 10),
                new Circle(pt3.getX()+200, pt3.getY()+140, 10)};


        for (int i = 0; i < 3; i++) {
            circle[i].setStroke(Color.BLACK);
            circle[i].setFill(Color.RED);
        }

        Pane pane = new Pane();
        setLines(circle);
        pane.getChildren().addAll(bigCircle, circle[0], circle[1], circle[2],
                line1, line2, line3, text[0], text[1], text[2]);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 400, 250);
        outStage.setTitle("Question_3"); // Set the stage title
        outStage.setScene(scene); // Place the scene in the stage
        outStage.show(); // Display the stage

        //set the drag action for 3 point
        circle[0].setOnMouseDragged(e -> {
            if (((int)Math.sqrt((e.getY() - 140) * (e.getY() - 140) + (e.getX() - 200) * (e.getX() - 200))) == 100) {
                // Recompute and display angles
                circle[0].setCenterX(e.getX());
                circle[0].setCenterY(e.getY());
                setLines(circle);
            }
        });

        circle[1].setOnMouseDragged(e -> {
            if (((int)Math.sqrt((e.getY() - 140) * (e.getY() - 140) + (e.getX() - 200) * (e.getX() - 200))) == 100) {
                // Recompute and display angles
                circle[1].setCenterX(e.getX());
                circle[1].setCenterY(e.getY());
                setLines(circle);
            }
        });

        circle[2].setOnMouseDragged(e -> {
            if (((int)Math.sqrt((e.getY() - 140) * (e.getY() - 140) + (e.getX() - 200) * (e.getX() - 200))) == 100) {
                // Recompute and display angles
                circle[2].setCenterX(e.getX());
                circle[2].setCenterY(e.getY());
                setLines(circle);
            }
        });
    }
    private void setLines(Circle[] circle) {

        //get the (x,y) value of 3 points
        line1.setStartX(circle[0].getCenterX());
        line1.setStartY(circle[0].getCenterY());
        line1.setEndX(circle[1].getCenterX());
        line1.setEndY(circle[1].getCenterY());
        line2.setStartX(circle[0].getCenterX());
        line2.setStartY(circle[0].getCenterY());
        line2.setEndX(circle[2].getCenterX());
        line2.setEndY(circle[2].getCenterY());
        line3.setStartX(circle[1].getCenterX());
        line3.setStartY(circle[1].getCenterY());
        line3.setEndX(circle[2].getCenterX());
        line3.setEndY(circle[2].getCenterY());

        // calculate the angle
        double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
                distance(circle[1].getCenterX(), circle[1].getCenterY());
        double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
                distance(circle[0].getCenterX(), circle[0].getCenterY());
        double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).
                distance(circle[0].getCenterX(), circle[0].getCenterY());
        double[] angle = new double[3];
        angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
        angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
        angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

        //print out the angle
        for (int i = 0; i < 3; i++) {
            text[i].setX(circle[i].getCenterX());
            text[i].setY(circle[i].getCenterY() - radius);
            text[i].setText(String.format("%.2f", Math.toDegrees(angle[i])));
        }
    }
    private void showQuestion4(Stage outStage){
        //some initialization
        char[]alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();        
        int []count = new int[26];
        
        //creating chart objects
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart chart = new BarChart<>(xAxis,yAxis);
        XYChart.Series series = new XYChart.Series<>();
        
        //adding empty dataset to chart
        for (int n = 0 ; n<26 ; n++)
            series.getData().add(new XYChart.Data(alphabet[n]+"",count[n]));
        chart.getData().addAll(series);
        chart.setLegendVisible(false);
        chart.setMinWidth(50);

        VBox trueSetup = new VBox(10);
        HBox row = new HBox(10);
        Button go = new Button("View");
        TextField enter = new TextField();
        row.getChildren().addAll(enter,go);
        trueSetup.getChildren().addAll(chart,row);
        Scene outScene = new Scene(trueSetup,1000,200);
            
        //on key press
        outScene.setOnKeyPressed(e->{
            //for enter press only
            if (e.getCode() == KeyCode.ENTER) {
                for (int i = 0 ; i < count.length ;i++)
                    count[i]=0;
                String fileName = enter.getText();
                File file = new File(fileName);
                String word;
                char letter;
                Scanner get;
                try {
                    get = new Scanner  (file);
                    while (get.hasNext()) {
                        word=get.next();
                        word = word.toUpperCase();
                        for (int i = 0 ; i < word.length() ; i++ ){
                            letter = word.charAt(i);

                            if (letter>='A' && letter<='Z')
                                count[letter-'A']++;
                        }
                        for (int n = 0 ; n<26 ; n++){
                            series.getData().add(new XYChart.Data(alphabet[n]+"",count[n]));
                        }
                    }
                } catch (FileNotFoundException ex) {
                Logger.getLogger(CSCI2020UA01.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //on button click
        go.setOnAction(e->{
            //reset letter counts
            for (int i = 0 ; i < count.length ;i++)
                count[i]=0;
            //open fle based on textfield text
            String fileName = enter.getText();
            File file = new File(fileName);
            
            //initialization
            String word;
            char letter;
            Scanner get;
            
            //try using file input
            try {
                get = new Scanner  (file);
                //while next 'word' exists
                while (get.hasNext()) {
                    word=get.next();
                    word = word.toUpperCase();//everything to upper case
                    //reading all chars in 'word'
                    for (int i = 0 ; i < word.length() ; i++ ){
                        letter = word.charAt(i);
                        //increment letter 
                        if (letter>='A' && letter<='Z')
                            count[letter-'A']++;
                    }
                    //add letter to series
                    for (int n = 0 ; n<26 ; n++)
                        series.getData().add(new XYChart.Data(alphabet[n]+"",count[n]));
                }
                //file not found error
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CSCI2020UA01.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //stage setting
        outStage.setTitle("Question_4");
        outStage.setScene(outScene);
        outStage.show();
    } 
}


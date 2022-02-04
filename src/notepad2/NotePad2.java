 
package notepad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class NotePad2 extends Application {
    
    @Override
   
    public void start(Stage primaryStage) {
        //Seprators
        SeparatorMenuItem s1 = new SeparatorMenuItem();
        SeparatorMenuItem s2 = new SeparatorMenuItem();
        SeparatorMenuItem s3 = new SeparatorMenuItem();
        MenuBar bar = new MenuBar();
        Menu file   = new Menu("File");
        Menu edit   = new Menu("Edit");
        Menu about  = new Menu("About");
        
        bar.getMenus().addAll(file,edit,about);
        //File Menu items
        MenuItem nw   = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem ex   = new MenuItem("Exit");
        
        file.getItems().addAll(nw,open,save,ex);
        //Adding Sepratror
        file.getItems().add(3, s1);
        // Edit Menu items
        
        MenuItem undo = new MenuItem("Undo");
        MenuItem cut  = new MenuItem("Cut");
        MenuItem cp   = new MenuItem("Copy");
        MenuItem ps   = new MenuItem("Paste");
        MenuItem dl   = new MenuItem("Delete");
        MenuItem al   = new MenuItem("Select All");
        
        edit.getItems().addAll(undo,cut,cp,ps,dl,al);
        
        //Adding Sepratror
         edit.getItems().add(1, s2);
         edit.getItems().add(6, s3);
        
        //About Menu Items
        MenuItem ab = new MenuItem("About");
        about.getItems().add(ab);
        
        // Text area
        TextArea tx = new TextArea();
        
        //Adding BorderPane
        BorderPane root = new BorderPane();
        root.setTop(bar);
        root.setCenter(tx);
       
     //Adding Event Handlers File Menu
     //Open New file
      nw.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               tx.setText("");
            }
     });
      //Exit application
      ex.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               Platform.exit();
            }
     });
      // open Existing file
      open.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TEXT", "*.txt"));
                File file = fc.showOpenDialog(null);
                
                if(file != null )
                { 
                try {
                   FileReader fr = new FileReader(file);
                   char[] chars = new char[(int) file.length()];
                   fr.read(chars);
                   String content = new String(chars);
                   fr.close();
                   tx.setText(content);
                    } catch (FileNotFoundException ex1) {
                        Logger.getLogger(NotePad2.class.getName()).log(Level.SEVERE, null, ex1);
                    } catch (IOException ex1) {
                        Logger.getLogger(NotePad2.class.getName()).log(Level.SEVERE, null, ex1);
                    }  
                }else{
                    tx.setText("Invaild Path");
                }
            }
        });
      //save a file
      save.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                FileChooser fs = new FileChooser();
                fs.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT", "*.txt"));
                File savef = fs.showSaveDialog(null);
                try {
                    FileWriter fw = new FileWriter(savef);
                    fw.write(tx.getText());
                    fw.close();
               //  tx.setText(tx.getText().replace(tx.getSelectedText(),savef.getPath()));

                } catch (IOException ex1) {
                 
                }   
            }
        });
     
       //Adding Event Handlers Edit Menu
     
      undo.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
              tx.undo();
            }
     });
     
      cut.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
              tx.cut();
            }
     });
      
      cp.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
              tx.copy();
            }
     });
       
      ps.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
              tx.paste();
            }
     });
        
      dl.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
              tx.setText(tx.getText().replace(tx.getSelectedText(),""));

            }
     });
     
      al.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev) {
              tx.selectAll();

            }
     });
       //Adding About Dialog
       
       //Creating a dialog
      Dialog<String> dialog = new Dialog<String>();
      //Setting the title
      dialog.setTitle("About MHA NotePad");
      ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
      //Setting the content of the dialog
      dialog.setContentText("This Simple MHA NotePad\nCreate By Mostafa Hassan @ITI42.");
      //Adding buttons to the dialog pane
      dialog.getDialogPane().getButtonTypes().add(type);
      ab.setOnAction(e -> {
          dialog.showAndWait();
      });
        //Adding Save and Open Dialog
        
        
        
        Scene scene = new Scene(root, 700, 800);
        
        primaryStage.setTitle("MHA NotePad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}















 /*
           open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fc = new FileChooser();
                File file = fc.showOpenDialog(null);
                
                if(file != null )
                {
                    Dialog<String> d3 = new Dialog<String>();
                     
                    d3.setTitle("Save File Path");
                    ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
                   
                    d3.setContentText(file.getPath());
                  
                    d3.getDialogPane().getButtonTypes().add(type);
                    d3.showAndWait();
                }
            }
        });
    */
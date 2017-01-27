package tilak.sample.com.utility;


import javax.swing.*;
import java.io.File;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.getFileDirectory();
    }

    public String getFileDirectory() {
        JFileChooser filechooser = new JFileChooser();
        File db = null;
        String directory;

        if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            db = filechooser.getSelectedFile();
            System.out.println("Opening file: " + db);
        } else {
            System.out.println("No file was chosen or an error occured");
            System.exit(0);
        };

        directory = db.toString();
        return directory;

    }

} 
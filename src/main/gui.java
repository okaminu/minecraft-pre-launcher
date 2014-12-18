package main;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class gui extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textField1;

    public gui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Minecraft PreLauncher");
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here
         String profile = "/Users/mac/Library/Application Support/minecraft/launcher_profiles.json";
        String minecraftApp = "/Applications/Minecraft_Launcher.jar";
        //logic
        File file = new File(profile);
        String line = "";
        String fullText = "";
        String name = textField1.getText();
        try{

            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            while(line != null){
                line = line.replaceAll("\"username\": .*\"", "\"username\": \""+name+"\"");
                line = line.replaceAll("\"displayName\": .*\"", "\"displayName\": \""+name+"\"");
                fullText += line;
                line = br.readLine();

            }
            br.close();


            BufferedWriter wr = new BufferedWriter(new FileWriter(file));
            wr.write(fullText);
            wr.close();
            Desktop.getDesktop().open(new File(minecraftApp));
            System.exit(0);


        }
        catch (Exception ex){
                  String test = ex.getMessage();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        gui dialog = new gui();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}

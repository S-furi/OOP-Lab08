package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JFrame frame = new JFrame();
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    public SimpleGUIWithFileChooser(final Controller controller) { 
        /* Creating upper part of the gui */
        final JTextField path = new JTextField();
        final JButton browse = new JButton("Browse...");
        final JPanel pane1 = new JPanel();
        final JPanel pane2 = new JPanel();
        pane1.setLayout(new BorderLayout());
        pane2.setLayout(new BorderLayout());
        path.setEditable(false);
        path.setText(controller.getPath());
        browse.addActionListener(new ActionListener() {
            private final JFileChooser choice = new JFileChooser();
            @Override
            public void actionPerformed(final ActionEvent e) {
                this.choice.setSelectedFile(controller.getFile());
                if (this.choice.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(this.choice.getSelectedFile());
                    path.setText(controller.getPath());
                } else if (this.choice.showSaveDialog(frame) == JFileChooser.CANCEL_OPTION) {
                    return; 
                } else {
                    JOptionPane.showMessageDialog(pane1, "An error has occurred ...");
                }
            }
        });
        /* Creating center/lower part of the gui */
        final JTextArea text = new JTextArea();
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeStringOnFile(text.getText());
                } catch (IOException exc) {
                    System.out.println("Error: " + exc);
                }
            }
        });
        /* Adding components to the panels and the JFrame */
        pane1.add(path, BorderLayout.NORTH);
        pane1.add(browse, BorderLayout.LINE_END);
        pane2.add(text, BorderLayout.CENTER);
        pane2.add(save, BorderLayout.SOUTH);
        this.frame.getContentPane().add(pane1, BorderLayout.NORTH);
        this.frame.getContentPane().add(pane2, BorderLayout.CENTER);


        /* Adapting frame to the screen size */
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public static void main(final String[] s) {
        final var gui = new SimpleGUIWithFileChooser(new Controller());
        gui.show();
    }
}

package frontend;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainfront {
    public static JTextField txt = new JTextField("Type Here",1);
    public static JButton button = new JButton("Submit");

    public void setup(){
        makeTextField();
        makeButton();

        JFrame frame = new JFrame();


        frame.add(txt);


        // adding button in JFrame
        frame.add(button);

        //frame.pack();
        // 400 width and 500 height
        frame.setSize(500, 600);

        // using no layout managers
        frame.setLayout(null);

        // making the frame visible
        frame.setVisible(true);

    }

    public void makeButton(){
        // Creating instance of JButton
        //JButton button = new JButton(" Submit");

        // x axis, y axis, width, height
        button.setBounds(150, 200, 220, 50);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newtxt = txt.getText();
                txt.setText("");
                System.out.println(newtxt);

            }
        });


    }

    public void makeTextField(){
        //JTextField txt = new JTextField();
        txt.setBounds(150,150,220,50);


    }
}

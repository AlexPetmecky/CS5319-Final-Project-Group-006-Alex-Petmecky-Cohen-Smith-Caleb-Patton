import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainfront {
    public static JTextField txt = new JTextField("Employee ID",1);
    public static JButton button_in = new JButton("Clock in");
    public static JButton button_out = new JButton("Clock Out");

    private SubmitHandler listener;

    public void setup(){
        makeTextField();
        makeButtonOUT();
        makeButtonIN();

        JFrame frame = new JFrame();


        frame.add(txt);


        // adding button in JFrame
        frame.add(button_in);
        frame.add(button_out);

        //frame.pack();
        // 400 width and 500 height
        frame.setSize(500, 600);

        // using no layout managers
        frame.setLayout(null);

        // making the frame visible
        frame.setVisible(true);

    }

    public void setSubmitIn (SubmitHandler listener) {
        this.listener = listener;
    }

    public void setSubmitOut (SubmitHandler listener) {
        this.listener = listener;
    }

    public void makeButtonIN(){
        // Creating instance of JButton
        //JButton button = new JButton(" Submit");

        // x axis, y axis, width, height
        button_in.setBounds(150, 200, 220, 50);

        button_in.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newtxt = txt.getText();
                txt.setText("");
                System.out.println(newtxt);
                if (listener != null) listener.submitIn (newtxt);

            }
        });



    }

    public void makeButtonOUT() {
        // Creating instance of JButton
        //JButton button = new JButton(" Submit");

        // x axis, y axis, width, height
        button_out.setBounds(150, 250, 220, 50);

        button_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newtxt = txt.getText();
                txt.setText("");
                System.out.println(newtxt);
                if (listener != null) listener.submitOut (newtxt);

            }
        });
    }
    public void makeTextField(){
        //JTextField txt = new JTextField();
        txt.setBounds(150,150,220,50);


    }
}

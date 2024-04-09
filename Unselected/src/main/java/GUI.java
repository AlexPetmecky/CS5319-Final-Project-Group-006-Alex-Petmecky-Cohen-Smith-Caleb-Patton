import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//arraylist
import java.util.ArrayList;

public class GUI {
    public static JTextField txt = new JTextField("Employee ID",1);
    public static JButton button_in = new JButton("Clock in");
    public static JButton button_out = new JButton("Clock Out");
    public static JButton showJson = new JButton("Show Current Employees");

    private ArrayList<EVENTHANDLER> inListeners = new ArrayList<>();
    private ArrayList<EVENTHANDLER> outListeners = new ArrayList<>();
    private ArrayList<EVENTHANDLER> showListeners = new ArrayList<>();

    private Main main;
    public void setup(Main m){
        main = m;
        makeTextField();
        makeButtonOUT();
        makeButtonIN();
        makeShowButton();

        JFrame frame = new JFrame();


        frame.add(txt);


        // adding button in JFrame
        frame.add(button_in);
        frame.add(button_out);

        frame.add(showJson);

        //frame.pack();
        // 400 width and 500 height
        frame.setSize(500, 600);

        // using no layout managers
        frame.setLayout(null);

        // making the frame visible
        frame.setVisible(true);

    }

    public void setSubmitIn (EVENTHANDLER listener) {
        this.inListeners.add(listener);
    }

    public void setSubmitOut (EVENTHANDLER listener) {
        this.outListeners.add(listener);
    }

    public void setShowEmployees (EVENTHANDLER listener) {
        this.showListeners.add(listener);
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
                for (EVENTHANDLER listener : inListeners) {
                    listener.submitIn(newtxt);
                }
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
                for (EVENTHANDLER listener : outListeners) {
                    listener.submitOut(newtxt);
                }
            }
        });
    }
    public void makeTextField(){
        //JTextField txt = new JTextField();
        txt.setBounds(150,150,220,50);


    }

    public void makeShowButton(){
        showJson.setBounds(150, 300, 220, 50);

        showJson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();


                JLabel label1 = new JLabel("TEXT");
                //JLabel label1 = new JLabel(main.formatJsonString(main.getAllEmployeesString()));
                label1.setBounds(150,150,620,500);
                frame.add(label1);

                frame.setSize(500, 600);

                // using no layout managers
                frame.setLayout(null);

                // making the frame visible
                frame.setVisible(true);

                for (EVENTHANDLER listener : showListeners) {
                    listener.showEmployees();
                }
            }
        });
    }
}

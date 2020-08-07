import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.TimeZone;


public class View implements Observer {
    
    DigitalClockPanel panel;
    //AnalogClockPanel panel;
    
    public View(Model model) throws IOException, ParserException {
        JFrame frame = new JFrame();
        Container contentPane = frame.getContentPane();
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Options");
        
        
        JMenuItem menuItem = new JMenuItem("About");
        menu.add(menuItem);
        menuBar.add(menu);
        
        //panel = new AnalogClockPanel(model);
        panel = new DigitalClockPanel(model);
        panel.setPreferredSize(new Dimension (200,100));
        contentPane.add(panel, BorderLayout.CENTER);
        
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("New Alarm");
        button.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){ 
            inputAlarm test = new inputAlarm();
            test.setVisible(true);
            
        }  
    });
        menuItem.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){  
            JOptionPane.showMessageDialog(frame, "Created by Fergus Brown, 17019711");  
        }  
    });        
        
        contentPane.add(button, BorderLayout.PAGE_END);
        contentPane.add(menuBar, BorderLayout.PAGE_START);
        frame.pack();
        frame.setVisible(true);
        
        
        
    /*    if (Model.nextAlarmEpoch == Long.MAX_VALUE) {
            
           
            FileInputStream fin = new FileInputStream("mycalendar.ics");

            CalendarBuilder builder = new CalendarBuilder();

            net.fortuna.ical4j.model.Calendar calendar = builder.build(fin);
            
            //load from file, set variables, etc
            calendar = builder.build(this.getClass().getResourceAsStream("mycalendar.ics"));
            

            for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
                Component component = (Component) i.next();
                System.out.println("Component [" + component.getName() + "]");

            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
                }
        } 
       } */
                
    }


    public static void goingOff() {
            JFrame alert = new JFrame();
            System.out.println(Model.goingOff + " view");
            System.out.println("going off!");
            JOptionPane.showMessageDialog(alert, "Alarm going off!!!");      
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
}

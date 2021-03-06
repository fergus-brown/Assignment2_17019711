
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.validate.ValidationException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fergu
 */
public class inputAlarm extends javax.swing.JFrame {

    
    
    net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
    java.util.Calendar cal = java.util.Calendar.getInstance();
            
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    Date time = new Date();
    Date date = new Date();
    Date alarm = new Date();
    
    String dateString = "";
    String timeString = "";
    String alarmString = "";
    
    
    
    /**
     * Creates new form inputAlarm
     */
    public inputAlarm() {
        
        initComponents();
    }

    
    private void myInitComponents() {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner jSpinner3 = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSpinner3, "dd.MM.yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Date today = new Date();
        jSpinner1 = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MINUTE));
        jSpinner3 = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JSpinner.DateEditor editorTime = new JSpinner.DateEditor(jSpinner1, "HH:mm");
        jSpinner1.setEditor(editorTime);
        jSpinner1.setModel(new SpinnerDateModel(today, null, null, Calendar.MINUTE));

        JSpinner.DateEditor editorDate = new JSpinner.DateEditor(jSpinner3, "dd/MM/yy");
        jSpinner3.setEditor(editorDate);
        jSpinner3.setModel(new javax.swing.SpinnerDateModel());

        jTextField1.setText("Alarm at..");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Set");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                        .addComponent(jSpinner3)))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //System.out.println(new SimpleDateFormat("dd/MM/yyyy").format(jSpinner3.getValue()));
        //String spinnerValue = formater.format(jSpinner3.getValue());
        //jTextField1.setText(jSpinner1.getValue().toString() + ":" + jSpinner3.getValue().toString());
        jTextField1.setText("Alarm set!");
        String alarmTime = timeFormat.format(jSpinner1.getValue());
        String alarmDate = dateFormat.format(jSpinner3.getValue());
        alarmString = alarmTime + " " + alarmDate;
        //alarmString = jSpinner1.getValue().toString() + " " + jSpinner3.getValue().toString();
        System.out.println("the alarm is at " + alarmString);
        //takes alarm date and time and set its as an 'alarm' date object
        try {
            alarm = dateTime.parse(alarmString);
        } catch (ParseException ex) {
            Logger.getLogger(inputAlarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //converts alarm to epoch time
        long epochTime = alarm.getTime();
        System.out.println("epoch time " + epochTime);
        try {
            //adds alarm to array and saves to file
            Model.q.add("alarm", epochTime);
            createEvent(alarm);
            try {
                save();
            } catch (ValidationException ex) {
                Logger.getLogger(inputAlarm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(inputAlarm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (QueueOverflowException ex) {
            Logger.getLogger(inputAlarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(Model.q);
        //sets the next alarm in case the new alarm is before the old next alarm
        try {
            Model.nextAlarmEpoch = Model.q.headPriority();
            System.out.println(Model.nextAlarmEpoch + " next");
        } catch (QueueUnderflowException ex) {
            Logger.getLogger(inputAlarm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void save() throws FileNotFoundException, ValidationException, IOException {
        
    String calFile = "alarm.ics";
    
    System.out.println("save!");
    
    //change
    calendar.getProperties().add(new ProdId("-//Fergus Brown//iCal4j 1.0//EN"));
    calendar.getProperties().add(Version.VERSION_2_0);
    calendar.getProperties().add(CalScale.GREGORIAN);
        
    FileOutputStream fout = new FileOutputStream(calFile);

    CalendarOutputter outputter = new CalendarOutputter();
    outputter.setValidating(false);
    outputter.output(calendar, fout);
    }
    
    void createEvent(Date alarm) {
        
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.MONTH, alarm.getMonth());
        cal.set(java.util.Calendar.DAY_OF_MONTH, alarm.getDate());
        cal.set(java.util.Calendar.HOUR_OF_DAY, alarm.getHours());
        cal.set(java.util.Calendar.MINUTE, alarm.getMinutes());
        cal.set(java.util.Calendar.SECOND, 00);

        VEvent newAlarm = new VEvent(new net.fortuna.ical4j.model.Date(cal.getTime()), "Alarm");
        // initialise as an all-day event..
        
        System.out.println("create!");

        calendar.getComponents().add(newAlarm);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inputAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputAlarm().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

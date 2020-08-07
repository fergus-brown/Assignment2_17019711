import java.time.Instant;
import java.util.Calendar;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.GregorianCalendar;

public class Model extends Observable {
    
    public static SortedArrayPriorityQueue<Object> q = new SortedArrayPriorityQueue<>(8);
    public static long nextAlarmEpoch = Long.MAX_VALUE;
    
    int hour = 0;
    int minute = 0;
    int second = 0;

    public static boolean goingOff = false;
    
    int oldSecond = 0;
    public static long currentEpoch;
    
    
    public Model() {
        update();
    }
    
    public void update() {
        
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
        //gets current time in epoch milliseconds
        currentEpoch = Instant.now().toEpochMilli();
        System.out.println("current " + currentEpoch);
        System.out.println("max " + nextAlarmEpoch);
        //checks if the epoch time of the next alarm is past the current time
        //if true, removed from queue and alert created
        if (nextAlarmEpoch < currentEpoch) {
            System.out.println("yay!");
            goingOff = true;
            System.out.println(goingOff + " model");
            goingOff = false;           
            try {
                System.out.println("removing");
                q.remove();
                //System.out.println(inputAlarm.q.head() + " is head");
                             
                if (q.isEmpty() == true) {
                    System.out.println("max!");
                    nextAlarmEpoch = Long.MAX_VALUE;
                    View.goingOff();
                } else if (q.head() == "alarm") {
                    System.out.println("head!");
                    nextAlarmEpoch = q.headPriority();
                    View.goingOff();
                }                  
            } catch (QueueUnderflowException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    

}
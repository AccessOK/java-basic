package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPrinter implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " +
                java.util.Calendar.getInstance().getTime());
    }
}

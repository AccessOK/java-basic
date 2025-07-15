package CalendarTest;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalenderTest {
    public static void main(String[] args) {
        //显示当前月的日历
        LocalDate date = LocalDate.now();
        //获取当前月份和日期
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();
        //将Date设置为这个月的第一天，并获取星期，
        //minusDays()方法返回一个LocalDate对象，该对象表示当前日期减去指定天数
        date = date.minusDays(today-1);
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue(); //1=Monday, 7=Sunday

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++)
            System.out.print("    ");
        //显示当前月
        while (date.getMonthValue() == month){
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today){
                System.out.print("*");
            }else System.out.print(" ");
            //date 往后一天，直到当前月结束
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1){
                System.out.println();
            }
        }
    }
}

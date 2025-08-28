package pair;

import java.time.LocalDate;

public class Pair1Test {
    public static void main(String[] args)
    {
        LocalDate[] dates= {LocalDate.of(1906, 12, 9), // G. Hopper
                            LocalDate.of(1815, 12, 10), // A. Einstein
                            LocalDate.of(1903, 12, 3), // C. Babbage
                            LocalDate.of(1911, 10, 10), // W. Mozart
        };
        Pair<LocalDate> mm = Pair1.minmax(dates);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}

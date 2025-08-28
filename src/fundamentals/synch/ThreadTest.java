package synch;

public class ThreadTest {

    public static final int DELAY= 10;
    public static final int STEPS= 1000;
    public static final double MAX_AMOUNT= 1000;

    public static void main(String[] args) {
        Bank bank = new Bank(5, 1000.0);
        Runnable r = new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < STEPS; i++) {
                        double amount= MAX_AMOUNT * Math.random();
                        //0转给1 amount
                        bank.transfer(0, 1, amount);
                        Thread.sleep((int)(DELAY*Math.random()));
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Runnable t = ()->{
            try{
                for (int i = 0; i < STEPS; i++) {
                    double amount= MAX_AMOUNT * Math.random();
                    //1转给0 amount
                    bank.transfer(0, 3, amount);
                    Thread.sleep((int)(DELAY*Math.random()));
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        new Thread(r).start();
        new Thread(t).start();
    }
}

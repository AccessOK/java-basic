package synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;
    public Bank(int n, double initialBalance) {
        accounts= new double[n];
        for (int i= 0; i < accounts.length; i++)
            accounts[i]= initialBalance;
        //创建重入锁，每个对象都有一个重入锁，调用同一对象时会发生。
        bankLock = new ReentrantLock();
        //创建条件变量，条件变量必须与锁一起使用，条件变量必须与锁一起使用，条件变量必须与锁一起使用。
        sufficientFunds = bankLock.newCondition();
    }
    public void transfer(int from, int to, double amount) throws InterruptedException{
        //上锁
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            //接触等待线程的阻塞，并返回所有正在等待的线程，线程被唤醒时，会重新获取锁。
            sufficientFunds.signalAll();
        }finally {
            //解锁
            bankLock.unlock();
        }
    }
    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum= 0;
            for (double a : accounts)
                sum += a;
            return sum;
        }finally {
            bankLock.unlock();
        }
    }
    public int size() {
        return accounts.length;
    }

}

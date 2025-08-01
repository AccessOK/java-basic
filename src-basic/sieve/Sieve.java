package sieve;

import java.util.BitSet;

public class Sieve {
    public static void main(String[] args) {
        int n=200000;
        long start = System.currentTimeMillis();
        var bitSet = new BitSet(n+1);
        int i;
        for (i = 2; i < n; i++) {
            bitSet.set(i);
        }
        i=2;
        while (i*i <= n) {
            if (bitSet.get(i)) {
                int k = i*2;
                while (k <= n) {
                    bitSet.clear(k);
                    k += i;
                }
            }
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(bitSet.cardinality()+" primes");
        System.out.println("time: "+(end-start));
    }
}

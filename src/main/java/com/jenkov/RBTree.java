package de.bre;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.TreeMap;
import org.openjdk.jmh.annotations.Benchmark;

public class RBTree {

  @Benchmark
  public void runRbTree() {
    TreeMap<Integer, String> rbtree = new TreeMap<>();

    int max = 10000;
    int range = max;
    int min = 0;

    Random random = new Random(42);


    for (int i = 0; i < max; i++) {
      byte[] byteArr = new byte[32];
      random.nextBytes(byteArr);
      String generatedString = new String(byteArr, Charset.forName("UTF-8"));
      rbtree.put(i,generatedString);
    }

    for (int i = 0; i < 100000; i++) {
      rbtree.get(random.nextInt() * range + min);
    }
  }
}

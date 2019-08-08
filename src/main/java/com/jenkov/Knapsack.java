/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.bre;

import org.openjdk.jmh.annotations.Benchmark;

public class Knapsack {

  @Benchmark
  public void runKnapsack() {
    int weight[] = new int[] {3, 2, 4, 1, 6, 3, 7, 9, 4, 6, 8, 7, 1, 4, 9, 6, 5, 2, 6, 4}; //sum 97
    int value[] = new int[] {100, 20, 60, 40, 35, 21, 50, 74, 77, 40, 33, 82, 55, 51, 42, 16, 20, 67, 32, 40}; //sum 955
    int knapsackCapacity = 55;
    dynamicKnapsack(knapsackCapacity, weight, value);
  }


  private static int dynamicKnapsack(int knapsackCapacity, int weight[], int value[]) {
    int valArrLength = value.length;
    int v[][] = new int[valArrLength + 1][knapsackCapacity + 1];

    for (int i = 0; i <= valArrLength; i++) {
      for (int k = 0; k <= knapsackCapacity; k++) {
        if (i == 0 || k == 0) {
          v[i][k] = 0;
        } else if (weight[i - 1] <= k) {
          v[i][k] = Math.max(v[i - 1][k], value[i - 1] + v[i - 1][k - weight[i - 1]]);
        } else {
          v[i][k] = v[i - 1][k];
        }
      }
    }
    return v[valArrLength][knapsackCapacity];
  }

  private static void printTable(int[][] table) {
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[i].length; j++) {
        System.out.print("[" + table[i][j] + "]" + " ");
      }
      System.out.println(System.lineSeparator());
    }
  }
}

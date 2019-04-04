package edu.HMM;

public class CasinoProblem {
	public static void main(String[] args) {
		int N = 10;
		int numStates = 2;
		double[][] A = {{0.7d, 0.3d}, {0.3d, 0.7d}};
		double[][] B = {{1.0d/6.0d, 1.0d/6.0d, 1.0d/6.0d, 1.0d/6.0d, 1.0d/6.0d, 1.0d/6.0d}, {0.1d, 0.1d, 0.1d, 0.1d, 0.1d, 0.5d}};
		double[] Pi = {0.5d, 0.5d};
		int[] O = {6, 6, 6, 6, 2, 1, 5, 3, 2, 6};
		int[] mostLikely = CasinoProblem.FindMostLikely(A, B, Pi, O, N, numStates);
		System.out.print("The most likely state sequence is {");
		for (int i = 0; i < N; i++) {
			System.out.print(mostLikely[i] + " ");
		}
		System.out.println("}");
	}
	
	public static int[] FindMostLikely(double[][] A, double[][] B, double[] Pi, int[] O, int N, int T) {
		int[] mostLikely = new int[N];
		double highestProb = 0.0d;
		for(int j = 0; j < Math.pow(T, N); j++) {
		    int[] chosen = new int[N];
		    String by = Integer.toBinaryString(j);
		    System.out.print("The probability for ");
		    for (int i = 0; i < N; i++) {
		    	if (by.length() <= i) {
		    		chosen[i] = 0;
		    	} else {
		    		chosen[i] = Integer.parseInt(by.substring(i, i + 1));
		    	}
		    	System.out.print(chosen[i]);
		    }
		    double prob = Math.log(Pi[chosen[0]]);
		    for(int i = 1; i < N; i++) {
		    	prob += Math.log(A[chosen[i - 1]][chosen[i]]);
		    }
		    for(int i = 0; i < N; i++) {
		    	prob += Math.log(B[chosen[i]][O[i] - 1]);
		    }
		    System.out.println(" is " + prob);
		    if (prob > highestProb || highestProb == 0.0d) {
		    	highestProb = prob;
		    	mostLikely = chosen;
		    }
		}
		return mostLikely;
	}
}
/*
 * Name: Sebastian Ferragut, David Tsukamoto
 * PID:  A17263077, A17379000
 */

import java.util.*;

public class BSTManual {

/**
 * BST answers for worksheet
 * @author Sebastian Ferragut, David Tsukamoto
 * @since  {05-10-2023}
 */

// No style for this file.	

public static ArrayList<String>  insertElements() {

	ArrayList<String> answer_pr1 = new ArrayList<>(11);
	answer_pr1.add("100");
	answer_pr1.add("10, 140");
	answer_pr1.add("5, 50, 125, 160");
	answer_pr1.add("1, X, 25, X, X, X, X, X");
	return answer_pr1;
}

public static ArrayList<String>  deleteElements() {

	ArrayList<String> answer_pr2 = new ArrayList<>(11);
	answer_pr2.add("54");
	answer_pr2.add("40, 90");
	answer_pr2.add("35, X, 55, 75");
	return answer_pr2;
}

public static ArrayList<String>  traversals() {
	ArrayList<String> answer_pr3 = new ArrayList<>(11);
	//in-order
	answer_pr3.add("30, 35, 40, 45, 50, 54, 55, 60, 75, 80, 90");
	//pre-order
	answer_pr3.add("50, 40, 30, 35, 45, 60, 55, 54, 80, 75, 90");
	//post-order
	answer_pr3.add("35, 30, 45, 40, 54, 55, 75, 90, 80, 60, 50");
	return answer_pr3;

}


}
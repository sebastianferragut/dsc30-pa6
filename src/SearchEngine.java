/*
 * Name: Sebastian Ferragut, David Tsukamoto
 * PID:  A17263077, A17379000
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 *
 * @author Sebastian Ferragut, David Tsukamoto
 * @since  {05-10-2023}
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String [] cast = scanner.nextLine().split(" ");
                String [] studios = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();
                // populate three trees with the information you just read
                //movie
                populateHelper(movieTree, cast, movie);
                //studio
                populateHelper(studioTree, studios, movie);
                //rating
                populateHelper(ratingTree, cast, rating);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper to populate a BSTree with key and data
     *
     * @param tree  - tree to be added to
     * @param key - key for Node
     * @param data - data for Linked List
     */
    public static void populateHelper(BSTree<String> tree, String[] key, String data) {
        for (int i = 0; i < key.length; i++) {
            String lowerKey = key[i].toLowerCase();
            tree.insert(lowerKey);
            if (tree.findKey(lowerKey)) {
                if (!tree.findDataList(lowerKey).contains(data)) {
                    tree.insertData(lowerKey, data);
                }
            }
        }
    }


    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        // process query
        String[] keys = query.toLowerCase().split(" ");
        //"Documents related to harrison-ford mark-hamill are:
        // [empire-strikes-back, new-hope]
        // Documents related to harrison-ford are: [indiana-jones]"
        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
        if (keys.length > 1) {
            LinkedList<String> sharedDocs = (LinkedList<String>) searchTree.
                    findDataList(keys[0]).clone();
            for (int i = 1; i < keys.length; i++) {
                sharedDocs.retainAll(searchTree.findDataList(keys[i]));
            }
            print(query, sharedDocs);
            for (int i = 0; i < keys.length; i++) {
                //grab individual data
                LinkedList<String> indivDoc = (LinkedList<String>)
                        (searchTree.findDataList(keys[i]).clone());
                indivDoc.removeAll(sharedDocs);
                if (indivDoc.size() > 0) {
                    print(keys[i], indivDoc);
                }
            }
        }
        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        if (keys.length == 1) {
            try {
                print(keys[0], searchTree.findDataList(keys[0]));
            } catch (IllegalArgumentException e) {
                System.out.println("The search yielded no results for " + query);
            }

        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty()) {
            System.out.println("The search yielded no results for " + query);
        } else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // initialize search trees
        BSTree<String> movie = new BSTree<>();
        BSTree<String> studio = new BSTree<>();
        BSTree<String> ratings = new BSTree<>();
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        // populate search trees
        populateSearchTrees(movie, studio, ratings, fileName);

        // choose the right tree to query
        String query = "";
        for (int i = 2; i < args.length; i++) {
            if (i < args.length - 1) {
                query += args[i] + " ";
            } else {
                query += args[i];
            }
        }
        if (searchKind == 0) {
            //movies
            searchMyQuery(movie, query);
        }
        if (searchKind == 1) {
            //studios
            searchMyQuery(studio, query);
        }
        if (searchKind == 2) {
            //ratings
            searchMyQuery(ratings, query);
        }
    }
}

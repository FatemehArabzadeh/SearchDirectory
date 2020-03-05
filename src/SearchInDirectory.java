import com.sun.deploy.security.SelectableSecurityManager;
import sun.font.DelegatingShape;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This Class Include main method and it prints name of files in specific directory
 * that matches with the arithmetic expression that user type in console.
 *
 * @author Fatemeh Arabzadeh
 */
public class SearchInDirectory {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String test = scanner.nextLine();
        File f = new File("d:\\ddd");
        List<String> AllFilesPath = new ArrayList<>();
        Boolean b;
        int CheckIfNoTextFileMatch = 0;
        FilenameFilter textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");
        File[] files = f.listFiles(textFilter);
        for (File file : files) {
            AllFilesPath.add(file.getPath());
        }
        if (AllFilesPath.size() == 0) {
            System.out.println("There is no text File in this directory");
            return;
        }
        for (int i = 0; i < AllFilesPath.size(); i++) {
            b = searchWordArithmetic(AllFilesPath.get(i), test);
            if (b.equals(true)) {
                System.out.println(new File(AllFilesPath.get(i)).getName());
                CheckIfNoTextFileMatch = 1;
            }
        }
        if (CheckIfNoTextFileMatch == 0)
            System.out.println("no text file find in this directory that match this expression");
    }
///////////////////////////////////////////////////////////////////////////////////
    /**
     * This method is for first step and just search a word in a text file.
     *
     * @param path The path of text file
     * @param test The string that user type in console
     * @throws IOException
     * @return true if this file contains test string , false otherwise
     */
    public static boolean searchWord(String path, String test) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            int CheckNumber = 0;
            l:
            while ((line = in.readLine()) != null) {
                // process line here.
                if (containsIgnoreCase(line, test)) {
                    CheckNumber = 1;
                    break l;
                }
            }
            if (CheckNumber == 1)
                return true;
            else
                return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * This method is for Second step and search an arithmetic expression that just include AND in a text file.
     *
     * @param path The path of text file
     * @param test The string that user type in console
     * @throws IOException
     * @return true if this file matches with the arithmetic expression , false otherwise
     */
    public static boolean searchWordByAND(String path, String test) throws IOException {
        List<String> SearchList = new ArrayList<>();
        String[] SearchArray = test.split(" AND ");
        for (int i = 0; i < SearchArray.length; i++) {
            SearchList.add(SearchArray[i]);
        }

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            l:
            while ((line = in.readLine()) != null) {
                for (int i = 0; i < SearchList.size(); i++) {
                    if (containsIgnoreCase(line, SearchList.get(i))) {
                        SearchList.remove(i);
                    }
                }
                if (SearchList.size() == 0)
                    break l;
            }
            if (SearchList.size() == 0)
                return true;
            else
                return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * This method is for third step and search an arithmetic expression that just include OR in a text file.
     *
     * @param path The path of text file
     * @param test The string that user type in console
     * @throws IOException
     * @return true if this file matches with the arithmetic expression, false otherwise
     */
    public static boolean searchWordByOR(String path, String test) throws IOException {
        List<String> SearchList;
        SearchList = Arrays.asList(test.split(" OR "));
        int CheckNumber = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            l:
            while ((line = in.readLine()) != null) {
                for (int i = 0; i < SearchList.size(); i++) {
                    if (containsIgnoreCase(line, SearchList.get(i))) {
                        CheckNumber = 1;
                        break l;
                    }
                }
            }
            if (CheckNumber == 1)
                return true;
            else return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * This method is for forth step and search an arithmetic expression that include AND & OR in a text file.
     *
     * @param path The path of text file
     * @param test The string that user type in console
     * @throws IOException
     * @return true if this file matches with the arithmetic expression , false otherwise
     */
    public static boolean searchWordArithmetic(String path, String test) throws IOException {
        List<String> SearchList;
        SearchList = Arrays.asList(test.split(" OR "));
        int CheckNumber = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            l:
            while ((in.readLine()) != null) {
                for (int i = 0; i < SearchList.size(); i++) {
                    if (searchWordByAND(path, SearchList.get(i))) {
                        CheckNumber = 1;
                        break l;
                    }
                }
            }
            if (CheckNumber == 1)
                return true;
            else return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * This method check a string that include another string or not in a non case sensitive way .
     *
     * @param str The string that you want to search in it
     * @param searchStr The string that you want to search it in str
     * @return true if this string contains , false otherwise
     */
    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null)
            return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}

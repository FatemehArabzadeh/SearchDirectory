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

public class SearchInDirectory {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String test = scanner.nextLine();
        File f = new File("d:\\ddd");
        List<String> AllFilesPath = new ArrayList<>();
        Boolean b;
        FilenameFilter textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");
        File[] files = f.listFiles(textFilter);
        for (File file : files) {
            AllFilesPath.add(file.getPath());
        }
        for (int i = 0; i < AllFilesPath.size(); i++) {
            if (test.contains(" AND "))
                b = searchWordByAND(AllFilesPath.get(i), test);
            else if (test.contains(" OR "))
                b = searchWordByOR(AllFilesPath.get(i), test);
            else
                b = searchWord(AllFilesPath.get(i), test);

            if (b.equals(true)) {
                System.out.println(new File(AllFilesPath.get(i)).getName());
            }
        }

    }

    ///////////////////////////////////////first step
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

    //////////////////////////////////
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

    /////////////////////////////////
    public static boolean searchWordByOR(String path, String test) throws IOException {
        List<String> SearchList = new ArrayList<>();
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

    ////////////////////////////////
    public static List<String> searchWordArithmetic(String path, String test) {
        List<String> FileNames = new ArrayList<>();

        return FileNames;
    }

    ////////////////////////////////
    public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) return false;

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

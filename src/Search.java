import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Search {

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
            b = searchWord(AllFilesPath.get(i), test);
            if (b.equals(true)) {
                System.out.println(new File(AllFilesPath.get(i)).getName());
            }
        }

    }

    ///////////////////////////////////////first step
    public static boolean searchWord(String path, String test) throws IOException {
        List<String> LinesThatContains = Files.lines(Paths.get(path))
                .filter(line -> line.contains(test))
                .collect(Collectors.toList());
        if (LinesThatContains.size() != 0)
            return true;
        else
            return false;
    }

    //////////////////////////////////
    public static List<String> searchWordByAND(String path, String test) {
        List<String> FileNames = new ArrayList<>();


        return FileNames;
    }

    /////////////////////////////////
    public static List<String> searchWordByOR(String path, String test) {
        List<String> FileNames = new ArrayList<>();


        return FileNames;
    }

    ////////////////////////////////
    public static List<String> searchWordArithmetic(String path, String test) {
        List<String> FileNames = new ArrayList<>();


        return FileNames;
    }
}

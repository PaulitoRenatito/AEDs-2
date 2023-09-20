import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utils {

    /**
     * Get only the Class name (e.g: AbstractDataTypes.Trees.BTree -> BTree)
     * @param currentClass class you want to get the name
     * @return Class name without the package name
     */
    public static String GetClassNameWithoutPackageName(Class<?> currentClass) {
        if (currentClass.getName().contains(".")) {
            return currentClass.getName().substring(currentClass.getName().indexOf(".") + 1);
        }
        else {
            return currentClass.getName();
        }
    }

    public static int[][] GenerateRandomAdjacencyMatrix(int numberOfInstances) {

        int[][] adjacencyMatrix = new int[numberOfInstances][numberOfInstances];

        for (int i = 0; i < numberOfInstances; i++) {
            for (int j = 0; j < numberOfInstances; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                }
                else {
                    Random random = new Random();
                    adjacencyMatrix[i][j] = random.nextInt(1, 11);
                }
            }
        }

        return adjacencyMatrix;

    }

    public static int[][] GetAdjacencyMatrixFromFile(String fileName) {

        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            int adjacencyMatrixSize = 0;
            while (scanner.hasNextLine()) {

                String nextLine = scanner.nextLine();

                if (nextLine.contains("DIMENSION")) {
                    adjacencyMatrixSize = Integer.parseInt(nextLine.substring(nextLine.indexOf(" ") + 1));
                    //System.out.println("SIZE: " + adjacencyMatrixSize);
                }

                if (Objects.equals(nextLine, "EDGE_WEIGHT_SECTION")) {
                    int[][] adjacencyMatrix;
                    nextLine = scanner.nextLine();
                    int count = 0;
                    int total = 0;
                    ArrayList<Integer> allNumbers = new ArrayList<>();

                    while (!Objects.equals(nextLine, "EOF")) {

                        ArrayList<Integer> numbers = GetNumbersFromLine(nextLine);
                        //System.out.println(numbers);

                        allNumbers.addAll(numbers);
                        count++;
                        total += numbers.size();

                        nextLine = scanner.nextLine();
                    }

                    adjacencyMatrix = GetAdjacencyMatrixFromArrayList(allNumbers, adjacencyMatrixSize);

                    return adjacencyMatrix;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    private static ArrayList<Integer> GetNumbersFromLine(String line) {

        ArrayList<Integer> numbers = new ArrayList<>();

        String[] strings = line.split("\\s+");

        for (String string : strings) {
            if (!string.isBlank() && !string.equals("EOF")) {
                numbers.add(Integer.valueOf(string));
            }
        }

        return numbers;

    }

    private static int[][] GetAdjacencyMatrixFromArrayList(ArrayList<Integer> allNumbers, int size) {

        int[][] numbers = new int[size][size];
        int arrayIndex = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j >= i) {
                    numbers[i][j] = allNumbers.get(arrayIndex);
                    arrayIndex++;
                }
                else {
                    numbers[i][j] = 0;
                }
            }
        }

        return numbers;

    }

}

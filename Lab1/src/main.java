import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

    public static int nodes;
    public static ArrayList<Integer>[] g = new ArrayList[100];
    public static boolean[] used = new boolean[100];
    public static ArrayList<Integer> comp = new ArrayList<Integer>();
    public static List<String> words = new ArrayList<>();

    public static void main (String[] args){
        main rezultat = new main();
        rezultat.Compulsory();

        //String[] test = {"10", "2", "A", "S", "D", "E", "F", "G", "H", "T", "a", "f", "h", "K"};

        nodes = Integer.parseInt(args[0]);
        for ( int i = 0; i < nodes; i++ ){
            g[i] = new ArrayList<Integer>();
        }

        if ( Integer.parseInt(args[0]) <= 30000){
            rezultat.Optional(args);
        }
        else {
            long startTime = System.nanoTime();
            rezultat.Optional(args);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Running time of the application: " + totalTime + " nanoseconds");
        }

        rezultat.find_comps();
    }

    void Compulsory(){
        System.out.println("Hello world!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int)(Math.random() * 1000000);
        String binaryString = "10101";
        String hexadacimal = "FF";
        int result = 0;

        n = n * 3;
        n = n + Integer.parseInt(binaryString,2);
        n = n + Integer.parseInt(hexadacimal,16);
        n = n * 6;

        while(n > 0){
            result = result + n % 10;
            n = n / 10;

            if( !(n > 0) && result > 9){
                n = result;
                result = 0;
            }
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }

    void Optional(String[] args){

        boolean canRun = true;
        int n = 0, k = 0;

        if(args.length < 3){
            System.out.println("There are not enough parameters to run this program");
            canRun = false;
        }

        try{
            n = Integer.parseInt(args[0]);
        }
        catch (Exception e){
            System.out.println("Type a Number, NOT String for n");
            canRun = false;

        }

        try{
            k = Integer.parseInt(args[1]);
        }
        catch (Exception e){
            System.out.println("Type a Number, NOT String for k");
            canRun = false;

        }

        List<String> letters = new ArrayList<>();
        int position = 2;
        boolean isLetter = true;

        while (position < args.length && isLetter){
            if(((args[position].charAt(0)>= 'a' && args[position].charAt(0) <= 'z') || (args[position].charAt(0) >= 'A' && args[position].charAt(0) <= 'Z')) && args[position].length() == 1){
                letters.add(args[position]);
            }
            else{
                isLetter = false;
                System.out.println("Expected array of letters");
                canRun = false;

            }
            position++;
        }

        String word ="";

        if(canRun){
            int n1 = n;
            while ( n1 > 0 ){
                int ct = k;
                while( ct > 0 ){
                    word = word + letters.get((int) (Math.random() * letters.size()));
                    ct --;
                }
                words.add(word);
                word="";
                n1 --;
            }

            System.out.println(words);

            int[][] matrix = new int[n][n];
            boolean notFound = true;

            for(int i = 0; i < words.size(); i++){
                for(int j = 0; j < words.size(); j++){
                    if(i == j){
                        matrix[i][j] = 0;
                    }
                    else{
                        for(int ct1 = 0; ct1 < k && notFound; ct1 ++){
                            for(int ct2 = 0; ct2 < k && notFound; ct2 ++){
                                if(words.get(i).charAt(ct1) == words.get(j).charAt(ct2)){
                                    notFound = false;
                                }
                            }
                        }
                        if(notFound){
                            matrix[i][j] = 0;
                        }
                        else {
                            matrix[i][j] = 1;
                            notFound = true;
                        }
                    }
                }
            }

            if ( n <= 30000 ){
                System.out.println("Matrix: " + Arrays.deepToString(matrix));
            }

            for ( int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (matrix[i][j] == 1){
                        g[i].add(j);
                    }
                }
            }

            System.out.println("List: " + Arrays.toString(g));

            int[] numberOfNeighbors = new int[n];
            int sum = 0;

            for(int i = 0; i < words.size(); i++){
                sum = 0;
                for (int j = 0; j < words.size(); j++){
                    sum = sum + matrix[i][j];
                }
                numberOfNeighbors[i] = sum;
            }

            int minim = n + 1, posMinim = 0, maxim = 0, posMaxim = 0;
            for(int i = 0; i < numberOfNeighbors.length; i++){
                if(numberOfNeighbors[i] < minim){
                    minim = numberOfNeighbors[i];
                    posMinim = i;
                }
                if(numberOfNeighbors[i] > maxim){
                    maxim = numberOfNeighbors[i];
                    posMaxim = i;
                }
            }

            System.out.println("The words that have the most, respectively the last, number of neighbors are: " + words.get(posMaxim) + " and " + words.get(posMinim));

            boolean sameNumberOfNeighbors = true;

            for (int i = 1; i < numberOfNeighbors.length && sameNumberOfNeighbors; i++){
                if (numberOfNeighbors[i] != numberOfNeighbors[0] ){
                    sameNumberOfNeighbors = false;
                }
            }

            System.out.println("Same number of neighbors: " + sameNumberOfNeighbors);

            //System.out.println(Arrays.toString(numberOfNeighbors));
        }
    }

    void dfs(int v) {
        used[v] = true;
        comp.add(v);
        for (int i = 0; i < (int) g[v].size(); ++i)
        {
            int to = g[v].get(i);
            if (!used[to])
            {
                dfs(to);
            }
        }
    }

    void find_comps() {
        for (int i = 0; i < nodes ; ++i)
        {
            used [i] = false;
        }
        for (int i = 0; i < nodes ; ++i)
        {
            if (!used[i])
            {
                comp.clear();
                dfs(i);
                System.out.print("Component:");
                for (int j = 0; j < comp.size(); ++j)
                {
                    System.out.print(' ');
                    System.out.print(words.get(comp.get(j)));
                }
                System.out.print("\n");
            }
        }
    }
    
}

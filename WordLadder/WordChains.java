import java.util.*;
/** 
 * @author Joe Madsen
 * Word Ladder used to find the 
 * ruairi walking me through graphs and nodes
 */
public class WordChains{
    public static String sW,eW;
    public static int steps;
    //if an amount of steps has been put into it or not. null = true.
    public static boolean sflag = false;
    public static boolean eflag = false;
    public static void main(String[] args) {

        //inputs, could add a validation to stop incorrect inputs
        List<String> og = new ArrayList<String>();
        ArrayList<String> dic = new ArrayList<String>();
        sW = args[0];
        eW = args[1];

        //show steps given, if not sets the steps flag to true
        try{
            steps = Integer.valueOf(args[2]);
        }catch(Exception e){
            sflag = true;
        }

        //if same word entered twice then the output is the same number
        if(sW.equals(eW)){
            System.out.println(sW);
            eflag=true;
        }

        //take in the input and put it into the array list for the stored
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            og.add(sc.next());
        }


        //this checks that everything we are doing adds the original words at the fornt and the rest behind it 
        //in the adjacency list
        if(og.contains(args[0])&&og.contains(args[1])){
            //add words in first
            dic.add(args[0]);
            dic.add(args[1]);
            for(int i =0;i<og.size();i++){
                if(og.get(i).equals(sW)||og.get(i).equals(eW)){
                    continue;
                }
                //add in words that arent the sw or ew
                dic.add(og.get(i));
            }
        }else{
            //end case
            System.out.println("One or both of these inputs were not found");
            eflag=true;
        }

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        //creating and adding the adj list with all the neighnours
        for(int z = 0; z<dic.size();z++){
            adjList.add(new ArrayList<Integer>());
            for(int x = 0; x<dic.size();x++){
                if(difference(dic.get(z), dic.get(x))){
                    adjList.get(z).add(x);
                }
            }
        }

        // this just is the case that there is no point carrying on doing the search with the error flag
        if(eflag==true){ 
        }
        else if(sflag==true){ 
            Graph g = new Graph(dic,adjList);
            String bFS = g.bfs(0,1);
            if(bFS != null){
                System.out.println(bFS);
            }
        }else{
            Graph g = new Graph(dic,adjList);
            String dFS = g.dfs(0,1,steps - 1);
            if(dFS != null){
                System.out.println(dFS);
            }
        }
        sc.close(); //debugger reported resource leak
    }
    
    /**
     * @param words this just takes in the two strings and compares them and 
     * they should be one char difference 
     */
    private static boolean difference(String word1, String word2){
        //check adjList lengths are the same and they arent the exact same
        if (word1.length() != word2.length()||word1.equals(word2)) {
            return false;
        }
        //difference incrementer, must only be one
        int diff = 0;
        for (int i = 0; i < word1.length(); i++){
            if(word1.charAt(i) != word2.charAt(i)){
                diff++;
            }
        }
        return (diff == 1);
    }


}

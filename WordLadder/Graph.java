/**
 * Class graph which contains the private node class used to set each node in the tree
 * @author Joe Madsen
 */
import java.util.*;
public class Graph{
    ArrayList<String> words;
    ArrayList<ArrayList<Integer>> adjList;

    public Graph(ArrayList<String> words,ArrayList<ArrayList<Integer>> adjList){
        this.adjList = adjList;
        this.words = words;
    }

    //node class setup, nestled inside of the graph class
    private class Node{
        Node parent;
        int distance;
        boolean [] visited;
        int index;

        public Node(int distance,Node parent,int index, boolean[]visited){
            this.distance = distance;
            this.index = index;
            this.parent = parent;
            this.visited = Arrays.copyOf(visited, visited.length);
        }
        public Node(Node parent,int index){
            this.index = index;
            this.parent = parent;
        }
        public Node(int index){
            this.index = index;
            this.visited = new boolean[words.size()];
        }

    }

    //this is just the breadth first search, through all the unvisited
    /**
     * 
     * @param startIn starting index
     * @param tarIn target index
     * @return string value of the nodes, or not possible if "not possible"
     */
    public String bfs(int startIn, int tarIn){
        boolean []finished = new boolean[words.size()];
        Queue<Node> q = new LinkedList<Node>();
        q.add(new Node(startIn));
        while(!q.isEmpty()){
            Node v=q.poll();
            if(!finished[v.index]){
                finished[v.index]=true;
                if(v.index==tarIn){
                    return sequence(v);
                }
                for(int i : adjList.get(v.index)){
                    if(finished[i]!=true){
                        q.add(new Node(v,i));
                    }
                }
            }
        }
        return "Not possible";
    }
    //dfs the same as bfs except going through the branches rather than the levels

    /**
     * 
     * @param startIn the starting index
     * @param tarIn the target index 
     * @param length the steps going into it
     * @return teh string by the sequence methos
     */
    public String dfs(int startIn, int tarIn, int length){
        Stack<Node> stack = new Stack<Node>();
        stack.push(new Node(startIn));
        while(!stack.isEmpty()){
            Node v = stack.pop();
            if(!v.visited[v.index]){
                v.visited[v.index]=true;
                if(v.index==tarIn){
                    return sequence(v);
                }
                for(int i : adjList.get(v.index)){
                    if(v.distance>=length){
                        continue;
                    }
                    if(v.distance == length-1 || v.index == tarIn){
                        stack.push(new Node(v.distance+1,v,i,v.visited));
                    }else if(i != tarIn){
                    stack.push(new Node(v.distance+1,v,i,v.visited));
                    }
                }
            }
        }
        return "Not possible";
    }
   // goes back through returning the sequence, which is coo. and our final output 
   /**
    * 
    * @param n the node inquestion
    * @return string to be printed to the stdout
    */
    public String sequence(Node n){
        if(n.parent == null) return words.get(n.index);
        else return sequence(n.parent) + " " + words.get(n.index);
    }


}

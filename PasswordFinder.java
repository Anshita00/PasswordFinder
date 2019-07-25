import java.io.File;
import java.util.*; 
  
//Class to represent a graph 
class Graph 
{ 


    Map <Character, List<Character>> adj = new HashMap<Character, List<Character>>();

    // function to add an edge to graph 
    public void addEdge(char u, char v) 
    { 
    	if (adj.get(u) == null) {
    		ArrayList<Character> neighbours = new ArrayList<Character>();
    		neighbours.add(v);
    		adj.put(u, neighbours);
    	} else {
    		List<Character> neighbours = adj.get(u);
    		neighbours.add(v);
    	}
    	
    } 
    
    public void printGraph() {
        for (Map.Entry<Character,List<Character>> entry : adj.entrySet())  {
            for (char c : entry.getValue()) {
            	System.out.println(entry.getKey() + "-" + c);
            }
        }
    }

    // prints a Topological Sort of the complete graph     
    public void topologicalSort() 
    { 
        // Create a array to store indegrees of all 
        // vertices. Initialize all indegrees as 0. 
        HashMap<Character,Integer> indegree = new HashMap<>();
          
        // Traverse adjacency lists to fill indegrees of 
        // vertices. This step takes O(V+E) time  

        for (Map.Entry<Character,List<Character>> entry : adj.entrySet())  {
            for (char node : entry.getValue()) {
            	if (indegree.get(node) != null) {
		              indegree.put(node, indegree.get(node) + 1);	
		      	} else {
		              indegree.put(node, 1);	
		      	}
            	if (indegree.get(entry.getKey()) == null) {
            		indegree.put(entry.getKey(), 0);
            	}
            }
        }
          
        // Create a queue and enqueue all vertices with 
        // indegree 0 
        Queue<Character> q = new LinkedList<Character>(); 
        int numZeroNodes = 0;
        for (Map.Entry<Character,Integer> entry : indegree.entrySet())  {
    		System.out.println(entry.getKey() + " " + entry.getValue());
        	if (entry.getValue() == 0) {
        		q.add(entry.getKey());
        		numZeroNodes++;
        	}
        }
        
          
        // Initialize count of visited vertices 
        int cnt = 0; 
          
        // Create a vector to store result (A topological 
        // ordering of the vertices) 
        Vector <Character> topOrder=new Vector<Character>(); 
        while(!q.isEmpty()) 
        { 
            // Extract front of queue (or perform dequeue) 
            // and add it to topological order 
            char u=q.poll(); 
            topOrder.add(u); 
              
            // Iterate through all its neighbouring nodes 
            // of dequeued node u and decrease their in-degree 
            // by 1 
            int tempNum = 0;
            List<Character> nodes = adj.get(u);
            System.out.println(u);
            if (nodes != null) {
                for(char node : nodes) 
                { 
                    // If in-degree becomes zero, add it to queue 
                	indegree.put(node, indegree.get(node) - 1);
                    if(indegree.get(node) == 0) {
                        q.add(node); 
                        tempNum++;
                    }
                }	
            }
            if (tempNum >= 1) {
            	numZeroNodes = numZeroNodes*tempNum;
            }
            cnt++; 
        } 
             
        // Print topological order             
        for(char i : topOrder) 
        { 
            System.out.print(i+" "); 
        } 
        System.out.println("Number of paths = " + numZeroNodes);
        
    } 
} 
// Driver program to test above functions 
class Main 
{ 
    public static void main(String args[]) 
    { 
        // Create a graph given in the above diagram 
        Graph g = new Graph();
        try {
            Scanner sc = new Scanner(new File("src/graph"));	
            while (sc.hasNext()) {
            	String curLine = sc.next();
            	for (int j = 0; j < curLine.length() - 1; j++) {
            		char source = curLine.charAt(j);
            		char dest = curLine.charAt(j + 1);
            		System.out.println(source + " " + dest + " " + j);
            		g.addEdge(source, dest);
            	}
            }
            sc.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        g.printGraph();
        System.out.println("Following is a Topological Sort"); 
        g.topologicalSort(); 
  
    } 
} 
import java.util.*;
import java.io.*;

/** 
 * Contains static methods (including main) that will run the animal 
 * guessing game. 
 *
 * @author Silvana Saca
 * @version 16 November 2015
 */

public class AnimalGuess{

    /**
     * Writes the tree out to a file AnimalTree.txt
     *
     * @param DecisionTree tree
     */
    public static void writeTree(DecisionTree tree){
        Queue<String> path = new LinkedList<String>();
        Queue<DecisionTree> nodes = new LinkedList<DecisionTree>();
        ArrayList<String> data = new ArrayList<String>();
        DecisionTree node;

        nodes.add(tree);
        path.add("");
        while (!nodes.isEmpty()){
            node = nodes.remove();
            String current = path.remove();
            data.add(current+" "+node.getData());
            if (node.getLeft() != null){
                nodes.add(node.getLeft());
                path.add(current+"Y");
            }
            if (node.getRight() != null){
                nodes.add(node.getRight());
                path.add(current+"N");
            }
        }
        
        try {
            PrintWriter out = new PrintWriter(new FileWriter("AnimalTree.txt"));
            for (String s : data){
                out.println(s);
            }
            out.close();
        } catch (IOException e){
            System.exit(-1);
        }
        
        
    }
    

    /**
     * Reads the user input and returns a "y" or an "n" depending on whether it
     * is a yes or no input
     * 
     * @param Scanner scan
     * @return String decision
     */

    public static String readDecision(Scanner reader){
        String decision= ""; 
        while (reader.hasNext()){
            char c = Character.toLowerCase(reader.next().charAt(0));
            if (Character.toString(c).equals("y")){
                decision = "y";
                break;
            }
            else if (Character.toString(c).equals("n")){
                decision = "n";
                break;
            }
            else {
              System.out.println("Please enter yes or no");
            }   
        }
        //System.out.println(decision);
        return decision;
    }
    

    /**
     * 
     * Recursively travels down the tree asking questions at each branch
     * and assembles the path used in followpath
     *
     * @param DecisionTree tree, Scanner in
     * @return String path
     */
    
    public static String classify(DecisionTree tree, Scanner in) throws IOException{
        String path; 

        if (tree.isLeaf()){
            path = "";
            
        } else {
            System.out.println(tree.getData());
            String decision = readDecision(in);
            if (decision == "y"){
                path= "y"+classify(tree.getLeft(), in);
            }
            else {
                path= "n"+classify(tree.getRight(), in);

            }
            
        }
        return path;
    }
    
    /**
     *
     * Reads a file, creates the basic tree, runs game
     *
     * @param args
     * 
     */ 
    
    public static void main(String [] args){

        String line;
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();
        BufferedReader file = null;

        //Read File 
        try {
            if (args.length == 0){
                file = new BufferedReader( new FileReader("AnimalTree.txt"));
            }
            else {
                file = new BufferedReader( new FileReader(args[0]));
            }
            
            while ((line = file.readLine()) != null){
                path.add(line.substring(0, line.indexOf(" ")));
                data.add(line.substring(line.indexOf(" ")+1));
                      
            }

            DecisionTree tree = new DecisionTree(data.get(0));

            for( int i = 1; i<path.size(); i++){
               
                tree.createTree(path.get(i), data.get(i));
            }

            //When the file has been read, run the game with the user
            //Create a scanner object to pass to 

            
            Boolean guess = true; 
            while(guess){
                System.out.println("Think of an animal and I'll try to guess it.");
                Scanner in = new Scanner(System.in);
                BufferedReader input = new BufferedReader( new InputStreamReader(System.in));
                String paths = classify(tree, in);
                DecisionTree animalNode = tree.followPath(paths);
                System.out.println("Is your animal a "+animalNode.getData()+"?");
                char answer = Character.toLowerCase(in.next().charAt(0));  
                if (Character.toString(answer).equals("y")){
                    System.out.println("I got it right!");
                    System.out.println("Do you want to play again?");
                    answer= Character.toLowerCase(in.next().charAt(0));
                    if ( Character.toString(answer).equals("y")){
                        continue;
                    } else if (Character.toString(answer).equals("n")){
                        System.out.println("Bye!");
                        guess = false;
                    }
             
                    
                }
                else if (Character.toString(answer).equals("n")){
                    System.out.println("I got it wrong!\nPlease help me learn.\nWhat was your animal?");
                    String animal= in.next();
                    System.out.println("Type a yes or no question that would distinguish between a "+animal+" and a "+animalNode.getData());
                    String question = input.readLine();
                    
                    System.out.println("Would you answer yes to this question for the "+animal+"?");
                    String answer2 = in.next();
                    char ans= Character.toLowerCase(answer2.charAt(0));
                    
                    if (Character.toString(ans).equals("y")){
                        animalNode.setLeft(new DecisionTree(animal));
                        animalNode.setRight(new DecisionTree(animalNode.getData()));
                        animalNode.setData(question);
                                        
                        
                        
                    } else if (Character.toString(ans).equals("n")){
                        animalNode.setLeft(new DecisionTree(animalNode.getData()));
                        animalNode.setRight(new DecisionTree(animal));
                        animalNode.setData(question);
                    }

                    System.out.println("Do you want to play again?");
                    char ans2= Character.toLowerCase(in.next().charAt(0));
                    if (Character.toString(ans2).equals("y")){
                        continue;
                    }
                    else if (Character.toString(ans2).equals("n")){
                        System.out.println("Bye!");
                        guess = false;
                    }
                    

                    
                }
                writeTree(tree); 
                               
            } 
        }
            



        catch(IOException e)  {
        System.out.println("Could not find file");
        System.exit(-1);
    
            
        }
    }

}

                     
               

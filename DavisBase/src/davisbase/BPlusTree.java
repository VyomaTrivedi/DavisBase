
package davisbase;

/**
 *
 * @author VYOMA
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Vector;




class BPlusTree {

	private static Node tree;
	private static int degree;

	private BPlusTree(int x) {
		degree = x;
		tree = new LeafNode(degree);
	}
	//inserts node into tree
	private static void insertIntoTree(DataNode dnode) {
		tree = tree.insert(dnode);
	}
	//Used to search node from tree
	private static void searchTree(long x, BufferedWriter output) throws IOException {
		// search the tree starting from the top
		String value = tree.search(new DataNode(x, ""));

		if (value != null) {
			System.out.println(value);
		} else {
			System.out.println("Key not found");
		}
	}

	//used to print the tree
	@SuppressWarnings("unchecked")
	private static void printTree(BufferedWriter output) throws IOException {

		Vector<Node> nodeList = new Vector();
		nodeList.add(tree);

		boolean done = false;

		while (!done) {
			Vector<Node> nextLevelList = new Vector();
			String toprint = "";

			for (int i = 0; i < nodeList.size(); i++) {

				Node node = (Node) nodeList.elementAt(i);
				if (node.isLeafNode()) {
					done = true;
					toprint += node.toString();
				} else {
					for (int j = 0; j < node.size() + 1; j++) {
						nextLevelList.add(((TreeNode) node).getPointerAt(j));
					}
				}
			}
			output.write(toprint + System.getProperty("line.separator"));
			nodeList = nextLevelList;
		}
	}

	//creates index file by creating the B+ tree.
	public static void createIndex(BufferedWriter output, String sourceF, String inputFile)
			throws FileNotFoundException, IOException {
		Scanner sc1 = new Scanner(new File(sourceF));
		Scanner sc2 = new Scanner(new File(inputFile));

		while (sc1.hasNextLine() && sc2.hasNextLine()) {

			String key = sc1.nextLine();
			String line2 = sc2.nextLine();
			String value = line2.substring(16);
			insertIntoTree(new DataNode(Long.valueOf(key), value));
		}
		printTree(output);
		sc1.close();
		sc2.close();
	}
 //Main method.
	public static void main(String[] args) throws IOException {
		// if there are too many arguments error

		   String functionName = args[0];
           
           String tempFile = "CS636011.txt";
           String tempFile2 = "CS636012.txt";
           
           // declare a reader on Standard in, incase the file reader fails
           BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
           BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
           StringBuilderBTree sbt = new StringBuilderBTree();
           
           new BPlusTree(11);
          
//           count_lines cl = new count_lines();
//           int lines = cl.count_file_line(sourceFileName);
//           System.out.println(lines);
           String sourceFileName = "CS6360Asg5TestData.txt";
           int indexKeyLength = 15;
           String destFileName = "cs6360.idx";
           //Create the Index File
           if(functionName.equals("-create"))
           {
           sourceFileName = args[1];
           destFileName = args[2];
           indexKeyLength = Integer.parseInt(args[3]);
           
           
           File file=new File(tempFile);
           
           
           RandomAccessFile indexFile = new RandomAccessFile(tempFile, "rw");
           
           File file1=new File(tempFile2);
           
           RandomAccessFile indexFile2 = new RandomAccessFile(tempFile2, "rw");

           
           in = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFileName)));
           in2 = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile)));
            
           read_file rf = new read_file();
           String[][] input_data = rf.read_File(sourceFileName);
          
           // create a new file to store output
           BufferedWriter output = new BufferedWriter( new FileWriter(new File(destFileName)) ); 
           BufferedWriter output2 = new BufferedWriter( new FileWriter(new File(tempFile2)) );            
          
           sbt.buildLong(sourceFileName, tempFile, indexKeyLength);
           createIndex(output2,tempFile,sourceFileName);
           //sbt.buildString(tempFile2, destFileName, indexKeyLength);
           
           output.close();
           output2.close();

           	
           }
           else if(functionName.equals("-find")){
        	   String result = "";
               String IndexFile = args[1];
               String searchKey = args[2];
               
               for(int i =0; i < searchKey.length(); ++i) 
                   {
                    char c = searchKey.charAt(i);

                    if (Character.isLetter(c)) 
                        {
                        result+= (c - 0x0041 + 101);
                        } 
                       else if (Character.isDigit(c)) 
                        {
                         result+= c;
                        } 
                   }
               long sKey = Long.valueOf(result);
               
               

               File file=new File(tempFile);
               
               
               RandomAccessFile indexFile = new RandomAccessFile(tempFile, "rw");
               
               File file1=new File(tempFile2);
               
               RandomAccessFile indexFile2 = new RandomAccessFile(tempFile2, "rw");

               
               in = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFileName)));
               in2 = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile)));
                
               read_file rf = new read_file();
               String[][] input_data = rf.read_File(sourceFileName);
              
               // create a new file to store output
               BufferedWriter output = new BufferedWriter( new FileWriter(new File(destFileName)) ); 
               BufferedWriter output2 = new BufferedWriter( new FileWriter(new File(tempFile2)) );            
              
               sbt.buildLong(sourceFileName, tempFile, indexKeyLength);
               createIndex(output2,tempFile,sourceFileName);
         
               searchTree(sKey, null);
           }
           //MyIndex.indx "11111111111111D Some new Record
           else if(functionName.equals("-insert"))
           {
        	   destFileName = args[1];
               String dataLine =  args[2];
               
               //String key = dataLine.substring(1, 16);
               //String value = dataLine.substring(17);
                BufferedWriter bx = new BufferedWriter(new FileWriter("CS6360Asg5TestData.txt",true));
                bx.append(dataLine);
                bx.flush();
                bx.close();
               
               in = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFileName)));
               in2 = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile)));
                
               read_file rf = new read_file();
               String[][] input_data = rf.read_File(sourceFileName);
              
               // create a new file to store output
               BufferedWriter output = new BufferedWriter( new FileWriter(new File(destFileName)) ); 
               BufferedWriter output2 = new BufferedWriter( new FileWriter(new File(tempFile2)) );            
              
               sbt.buildLong(sourceFileName, tempFile, indexKeyLength);
               createIndex(output2,tempFile,sourceFileName);
               //sbt.buildString(tempFile2, destFileName, indexKeyLength);
               
               output.close();
               output2.close();

           }

}
}

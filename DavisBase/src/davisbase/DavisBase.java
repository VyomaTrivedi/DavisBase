package davisbase;

import java.io.RandomAccessFile;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;


/**
 *  @author Chris Irwin Davis
 *  @version 1.0
 *  <b>
 *  <p>This is an example of how to create an interactive prompt</p>
 *  <p>There is also some guidance to get started with read/write of
 *     binary data files using RandomAccessFile class</p>
 *  </b>
 *
 */
public class DavisBase {


        /* This can be changed to whatever you like */
        static String prompt = "davisql> ";
        static String version = "v1.0b(example)";
        static String copyright = "©2016 Chris Irwin Davis";
        static boolean isExit = false;

        /*
         * Page size for alll files is 512 bytes by default.
         * You may choose to make it user modifiable
         */
        static long pageSize = 512; 
        static long ps = pageSize;
        static long pageSizec = 2048;
        static long psc = pageSizec;
        static long ps_col = pageSize;
        static long psi=pageSize;




        String tableName = null;
        RandomAccessFile tbl_details;
        RandomAccessFile col_details;
        static int pk_occurence=0;
//        static ArrayList<String> list_col_names=new ArrayList<>();

        /* 
         *  The Scanner class is used to collect user commands from the prompt
         *  There are many ways to do this. This is just one.
         *
         *  Each time the semicolon (;) delimiter is entered, the userCommand 
         *  String is re-populated.
         */
        static Scanner scanner = new Scanner(System.in).useDelimiter(";");

        /** ***********************************************************************
         *  Main method
         */
    public static void main(String[] args) {

        File f1=new File("Data");
                String path="Data\\";
                File sf=new File(path+"catalog");
                File usf=new File(path+"user_data");

                if(!f1.exists())
                {
                    boolean res=false;
                    try
                    {
                        f1.mkdir();
                        sf.mkdir();

                        RandomAccessFile tbl_details=new RandomAccessFile("Data\\catalog\\davisbase_tables.tbl","rw");
                        tbl_details.setLength(pageSize);
                        RandomAccessFile col_details=new RandomAccessFile("Data\\catalog\\davisbase_columns.tbl","rw");
                        col_details.setLength(pageSizec);
                        int l="davisbase_tables".length();
                        int c="davisbase_columns".length();

                        ps=ps-l-1;
                        tbl_details.seek(ps);
                        tbl_details.writeByte(1);
                        tbl_details.writeBytes("davisbase_tables");


                        ps=ps-c-1;
                        tbl_details.seek(ps); 
                        tbl_details.writeByte(2);
                        tbl_details.writeBytes("davisbase_columns");


                        tbl_details.seek(1);
                        tbl_details.writeByte(02);

                        tbl_details.seek(2);
                        tbl_details.writeShort(477);

                        tbl_details.seek(8);
                        tbl_details.writeShort(495);

                        tbl_details.seek(10);
                        tbl_details.writeShort(477);
                        //tbl_details.writeBytes(" ");

                        //pageSizec=2048;
                        col_details.setLength(pageSizec);
                        col_details.seek(8);
                        col_details.writeShort((int) (pageSizec-34));
                        col_details.seek(pageSizec-34);

                        col_details.writeByte(1);
                        col_details.writeByte(1);

                        col_details.writeByte(16);
                        col_details.writeBytes("davisbase_tables");

                        col_details.writeByte(5);
                        col_details.writeBytes("rowid");

                        col_details.writeByte(3);
                        col_details.writeBytes("INT");

                        col_details.writeByte(1);
                        col_details.writeByte(1);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-34));

                        pageSizec= pageSizec - 34;
                        col_details.seek(10);
                        col_details.writeShort((int) (pageSizec-40));
                        col_details.seek(pageSizec-40);

                        col_details.writeByte(1);
                        col_details.writeByte(2);

                        col_details.writeByte(16);
                        col_details.writeBytes("davisbase_tables");

                        col_details.writeByte(10);
                        col_details.writeBytes("table_name");

                        col_details.writeByte(4);
                        col_details.writeBytes("TEXT");

                        col_details.writeByte(1);
                        col_details.writeByte(2);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-40));


                        pageSizec = pageSizec - 40;
                        col_details.seek(12);
                        col_details.writeShort((int) (pageSizec-35));
                        col_details.seek(pageSizec-35);

                        col_details.writeByte(1);
                        col_details.writeByte(3);

                        col_details.writeByte(17);
                        col_details.writeBytes("davisbase_columns");

                        col_details.writeByte(5);
                        col_details.writeBytes("rowid");

                        col_details.writeByte(3);
                        col_details.writeBytes("INT");

                        col_details.writeByte(1);
                        col_details.writeByte(1);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-35));



                        pageSizec = pageSizec - 35;
                        col_details.seek(14);
                        col_details.writeShort((int) (pageSizec-41));
                        col_details.seek(pageSizec-41);

                        col_details.writeByte(1);
                        col_details.writeByte(4);

                        col_details.writeByte(17);
                        col_details.writeBytes("davisbase_columns");

                        col_details.writeByte(10);
                        col_details.writeBytes("table_name");

                        col_details.writeByte(4);
                        col_details.writeBytes("TEXT");

                        col_details.writeByte(1);
                        col_details.writeByte(2);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-41));



                        pageSizec = pageSizec - 41;
                        col_details.seek(16);
                        col_details.writeShort((int) (pageSizec-42));
                        col_details.seek(pageSizec-42);

                        col_details.writeByte(1);
                        col_details.writeByte(4);

                        col_details.writeByte(17);
                        col_details.writeBytes("davisbase_columns");

                        col_details.writeByte(11);
                        col_details.writeBytes("column_name");

                        col_details.writeByte(4);
                        col_details.writeBytes("TEXT");

                        col_details.writeByte(1);
                        col_details.writeByte(3);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-42));


                        pageSizec= pageSizec - 42;
                        col_details.seek(18);
                        col_details.writeShort((int) (pageSizec-40));
                        col_details.seek(pageSizec-40);

                        col_details.writeByte(1);
                        col_details.writeByte(6);

                        col_details.writeByte(17);
                        col_details.writeBytes("davisbase_columns");

                        col_details.writeByte(9);
                        col_details.writeBytes("data_type");

                        col_details.writeByte(4);
                        col_details.writeBytes("TEXT");

                        col_details.writeByte(1);
                        col_details.writeByte(4);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-40));



                        pageSizec = pageSizec - 40;
                        col_details.seek(20);
                        col_details.writeShort((int) (pageSizec-50));
                        col_details.seek(pageSizec-50);

                        col_details.writeByte(1);
                        col_details.writeByte(7);

                        col_details.writeByte(17);
                        col_details.writeBytes("davisbase_columns");

                        col_details.writeByte(16);
                        col_details.writeBytes("ordinal_position");

                        col_details.writeByte(7);
                        col_details.writeBytes("TINYINT");

                        col_details.writeByte(1);
                        col_details.writeByte(5);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-50));



                        pageSizec = pageSizec - 50;
                        col_details.seek(22);
                        col_details.writeShort((int) (pageSizec-42));
                        col_details.seek(pageSizec-42);

                        col_details.writeByte(1);
                        col_details.writeByte(8);

                        col_details.writeByte(17);
                        col_details.writeBytes("davisbase_columns");

                        col_details.writeByte(11);
                        col_details.writeBytes("is_nullable");

                        col_details.writeByte(4);
                        col_details.writeBytes("TEXT");

                        col_details.writeByte(1);
                        col_details.writeByte(5);

                        col_details.writeByte(2);
                        col_details.writeBytes("NO");

                        col_details.seek(2);
                        col_details.writeShort((int) (pageSizec-42));                        
                        usf.mkdir();
                        res=true;

                        col_details.seek(1);
                        col_details.writeByte(8);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    if(res)
                    {
                        System.out.println("Success");
                    }
                }

//                CreateDir();
                /* Display the welcome screen */
                splashScreen();

                /* Variable to collect user input from the prompt */
                String userCommand = ""; 

                while(!isExit) {
                        System.out.print(prompt);
                        /* toLowerCase() renders command case insensitive */
                        userCommand = scanner.next().replace("\n", "").replace("\r", "").trim().toLowerCase();
                        // userCommand = userCommand.replace("\n", "").replace("\r", "");
                        parseUserCommand(userCommand);
                }
                System.out.println("Exiting...");


        }

        public static void splashScreen() {
                System.out.println(line("-",80));
        System.out.println("Welcome to DavisBaseLite"); // Display the string.
                System.out.println("DavisBaseLite Version " + getVersion());
                System.out.println(getCopyright());
                System.out.println("\nType \"help;\" to display supported commands.");
                System.out.println(line("-",80));
        }

        /**
         * @param s The String to be repeated
         * @param num The number of time to repeat String s.
         * @return String A String object, which is the String s appended to itself num times.
         */
        public static String line(String s,int num) {
                String a = "";
                for(int i=0;i<num;i++) {
                        a += s;
                }
                return a;
        }

                /**
                 *  Help: Display supported commands
                 */
                public static void help() {
                        System.out.println(line("*",80));
                        System.out.println("SUPPORTED COMMANDS");
                        System.out.println("All commands below are case insensitive");
                        System.out.println();
                        System.out.println("\tSELECT * FROM table_name;                        Display all records in the table.");
                        System.out.println("\tSELECT * FROM table_name WHERE rowid = <value>;  Display records whose rowid is <id>.");
                        System.out.println("\tDROP TABLE table_name;                           Remove table data and its schema.");
                        System.out.println("\tVERSION;                                         Show the program version.");
                        System.out.println("\tHELP;                                            Show this help information");
                        System.out.println("\tEXIT;                                            Exit the program");
                        System.out.println();
                        System.out.println();
                        System.out.println(line("*",80));
                }

        /** return the DavisBase version */
        public static String getVersion() {
                return version;
        }

        public static String getCopyright() {
                return copyright;
        }

        public static void displayVersion() {
                System.out.println("DavisBaseLite Version " + getVersion());
                System.out.println(getCopyright());
        }

        public static void parseUserCommand (String userCommand) {

                /* commandTokens is an array of Strings that contains one token per array element 
                 * The first token can be used to determine the type of command 
                 * The other tokens can be used to pass relevant parameters to each command-specific
                 * method inside each case statement */
                // String[] commandTokens = userCommand.split(" ");
                ArrayList<String> commandTokens = new ArrayList<String>(Arrays.asList(userCommand.split(" ")));


                /*
                *  This switch handles a very small list of hardcoded commands of known syntax.
                *  You will want to rewrite this method to interpret more complex commands. 
                */
                switch (commandTokens.get(0)) {
                        case "select":
                            if(commandTokens.get(3).equalsIgnoreCase("davisbase_columns"))
                                parseColumnsString(userCommand);
                            else
                                parseQueryString(userCommand);
                        break;

                        case "show":
                                parseQueryString1(userCommand);
                                break;
                        case "insert":
                                parseInsertString(userCommand);
                                break;
                        case "drop":
                                System.out.println("STUB: Calling your method to drop items");
                                dropTable(userCommand);
                                break;
                        case "delete":
                                System.out.println("STUB: Calling your method to delete records");
                                dropTable(userCommand);
                                break;    
                        case "create":
                                parseCreateString(userCommand);

                                break;
                        case "update":
                                parseUpdateString(userCommand);
                                break;
                        case "help":
                                help();
                                break;
                        case "version":
                                displayVersion();
                                break;
                        case "exit":
                                isExit = true;
                                break;
                        case "quit":
                                isExit = true;
                        default:
                                System.out.println("I didn't understand the command: \"" + userCommand + "\"");
                                break;
                }
        }


        /**
         *  Stub method for dropping tables
         *  @param dropTableString is a String of the user input
         */

        public static void parseColumnsString(String userCommand) {
        ArrayList<Character> row_data=new ArrayList<>();
        try{

                ArrayList<String> tmp = new ArrayList<>();

                RandomAccessFile queryFile=new RandomAccessFile("Data\\catalog\\davisbase_columns.tbl", "rw");

                int start=0x08;
                //System.out.println(start);

                queryFile.seek(start);
                int a=0;
                int tem=queryFile.readShort();
                //System.out.println("tem"+tem);
                while(tem != 0){

                queryFile.seek(start);

                tem=queryFile.readShort();

                start+=2;

                queryFile.seek(++tem);
                int row_id=queryFile.read();

                int seek_rlength=tem+1;

                queryFile.seek(seek_rlength);


                int first_length=queryFile.read();
                    //System.out.println("first"+first_length);             
                for(int i=0;i<first_length;i++)
                        {

                            queryFile.seek(++seek_rlength);
                            char b=(char)(queryFile.read());
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data.add(b);
                        }
                //System.out.println(row_data);
                  //  System.out.println("rl"+seek_rlength);
              //  System.out.println("seek rlength"+seek_rlength + " first "+first_length);
                int srlength=seek_rlength+1;
                row_data.add(' ');

                for(int k=2;k<6;k++){

                    queryFile.seek(srlength);
                        int rlength=queryFile.read();
                        //System.out.println("r"+rlength);

                        for(int i=0;i<rlength;i++)
                        {
                            srlength+=1;
                            queryFile.seek(srlength);

                            char b=(char)(queryFile.read());
                            //System.out.println("character: "+b);
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data.add(b);
                            //System.out.println(row_data);
                        }
                        row_data.add(' ');

                        srlength+=1;

                        rlength=queryFile.read();
                }
                //System.out.println(row_data);
                //start=start+2;
                row_data.add(';');
                queryFile.seek(start-2);
                tem=queryFile.read();
//                
                }
//                int y7 = 0;
//                for(int i=0;i<names_col.size();i++)
//                {
//                    System.out.print(ord_position.get(i));
//                    System.out.print("\t\t");
//                    y7++;
//                }
//                int lines = y7*16; 
//                System.out.println();
//                System.out.println(line("-",lines));
               // System.out.println(row_data);
                String final_data="";
                String column_data="";
                String column_wise[]=null;
                for(int j=0;j<row_data.size();j++)
                {
                    final_data=final_data+row_data.get(j);
                }
                String row_wise[]=final_data.split(";");
                String[] key1;
                ArrayList<String> colss=new ArrayList<>();
                for(int k=0;k<row_wise.length-1;k++)
                {

                    //System.out.println(row_wise[k]);

                    key1=row_wise[k].split(" ");

                    for(int jo=0;jo<key1.length;jo++){
                        colss.add(key1[jo]);


                    }
                     //System.out.println("cols"+colss);


                }
                int try3=0;
                int try4=1;
               // System.out.println(colss.size());
                ArrayList<Integer> yug = new ArrayList<>();
                ArrayList<String> yug1 = new ArrayList<>();
                String day = null;    
                
                for(int tre1 = 0;tre1<colss.size();tre1+=5){
                    day = colss.get(tre1);    
                    if(!yug1.contains(day))
                            yug1.add(day);
                }
             //   System.out.println(yug1);
                for (int j=0;j<yug1.size();j++){
                    int count = 0;
                for (int i = 0; i < colss.size(); i+=5) {
                    if ((colss.get(i).equalsIgnoreCase(yug1.get(j)))) {
                        count++;
                        yug.add(count);
                    }
                }
                }
                int ray1=3;
            for(int ray =0; ray < colss.size()/5;ray++){
                colss.set(ray1, yug.get(ray).toString());
                ray1+=5;
            }
                 ArrayList<String> headings = new ArrayList<String>() ;
                headings.add("row_id");
                headings.add("table_name");
                headings.add("column_name");
                headings.add("data_type");
                 headings.add("ordinal_position");
                headings.add("is_nullable");

                
                for(int i=0;i<headings.size();i++){
                	System.out.format("%-20s",headings.get(i));
                }
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------------");
               

            //System.out.println(colss);
                for(int try1=0;try1<colss.size()/5;try1++){
                    System.out.format("%-20s",try4);
                    for(int try2=try3;try2<try3+5;try2++){

                        System.out.format("%-20s",colss.get(try2));

                    }
                    System.out.println();
                    try4++;
                    try3+=5;
                }

        }
        catch(Exception e)
        {
            //System.out.println(e.getMessage());
        }

    }


static void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}


        public static void dropTable(String dropTableString) {
            try {
                System.out.println("STUB: Calling parseQueryString(String s) to process queries");
                System.out.println("Parsing the string:\"" + dropTableString + "\"");

                ArrayList<String> QueryStringTokens1 = new ArrayList<String>(Arrays.asList(dropTableString.split(" ")));
                //String query_col=QeryStringTokens.get(5);
                int rowno = 0;
                String tableName = null;

                //System.out.println(QueryStringTokens1.size());

                if(QueryStringTokens1.size()>3){
                    tableName = QueryStringTokens1.get(3);
                rowno=Integer.parseInt(QueryStringTokens1.get(7));}
                else{
                    tableName = QueryStringTokens1.get(2);
                }

                RandomAccessFile queryFile2=new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+tableName+".tbl", "rw");

                if(QueryStringTokens1.get(0).equalsIgnoreCase("delete")){
                    int start,result;
                        int match_index = 0;
                    int match_id = 0;
                    int start1 = 0x08;

                    queryFile2.seek(start1);
                    while(queryFile2.readShort()!=0 ){
                        queryFile2.seek(start1);
                        if(-1==queryFile2.readShort()){
                                //queryFile2.seek(start1);
                        }
                        else{
                                queryFile2.seek(start1);
                                        result =(int) queryFile2.readShort();
                                        result+=4;
                                        queryFile2.seek(result);
                                    int rec_val = queryFile2.read();

                                        if(rec_val == rowno){
                                                match_index = rec_val;
                                                match_id = result - 4;

                                        start=8;

                                        while(queryFile2.readShort()!=0){
                                            queryFile2.seek(start);
                                            result=queryFile2.readShort();
                                            if(result==match_id){

                                           // System.out.println("ansi"+start1);
                                            queryFile2.seek(start1);
                                            queryFile2.writeShort(0xFFFF);
                                            break;
                                            }
                                            start+=2;
                                        }

                                }

                        }
                        start1+=2;
                    }

                }
                else if(QueryStringTokens1.get(0).equalsIgnoreCase("drop")){
                    String a="Data\\user_data\\" + tableName;
                	File s =new File(a);
                	deleteDir(s);
                	
                	RandomAccessFile queryFile=new RandomAccessFile("Data\\catalog\\davisbase_tables.tbl", "rw");
                	
                	
                	int start=8;
                	int next=6;
                	int len;
                	int sval,nval;
                	queryFile.seek(start);
                	
                	while(queryFile.readShort()!=0){
                		String str="";	
                	queryFile.seek(start);
                	sval= queryFile.readShort();
                	if(sval==-1){
                		
                		
                	}
                	else{
                	sval++;
                	queryFile.seek(next); 
                	nval = queryFile.readShort();
                	if(nval==0){
                		nval=512;
                	}
                	len = nval - sval;
                	queryFile.seek(sval);
                	
                	ArrayList<Character> row_data=new ArrayList<>();
                	
                	for(int i=0;i<len;i++)
                    {
                        
                        queryFile.seek(sval++);
                        char b=(char)(queryFile.read());
                        //row_data.add((String)Integer.toHexString(queryFile.read()));
                        row_data.add(b);
                    }
                	
                	
                	
                	for(int j=0;j<row_data.size();j++)
                    {
                		str=str + row_data.get(j);
                    }
                	
                	if(str.equals(tableName)){
                		queryFile.seek(start);
                		queryFile.writeShort(0xFFFF);
                		System.out.println("Table " + tableName + " has been deleted." );
                		break;
                		}
                	}
                	start+=2;
                	next+=2;
                	queryFile.seek(start);
                	
                	}

                }
            } catch (Exception ex) {
                //System.out.println(ex);
                //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
            }






        }



        /**
         *  Stub method for executing queries
         *  @param queryString is a String of the user input
         */


        private static void parseUpdateString(String updateString) {
            try {
                System.out.println("STUB: Calling parseQueryString(String s) to process queries");
                System.out.println("Parsing the string:\"" + updateString + "\"");

                ArrayList<String> UpdateStringTokens = new ArrayList<String>(Arrays.asList(updateString.split(" ")));
                String tbl = UpdateStringTokens.get(1);
                String update_col_name = UpdateStringTokens.get(3);
                String update_col_value = UpdateStringTokens.get(5);
                String criteria_name = UpdateStringTokens.get(7);
                String criteria_value = UpdateStringTokens.get(9);


                ArrayList<String> ord_position = new ArrayList<>();
                ArrayList<String> names_col =new ArrayList<String>();

                ArrayList<String> tmp = new ArrayList<>();
                File ftbl=new File("Data\\user_data\\"+tbl);

                File[] fileNames=ftbl.listFiles();

                for(int i=0;i<fileNames.length;i++){
                    if(fileNames[i].isFile())
                    {
                        String ndx_names=fileNames[i].getName();
                        if(ndx_names.contains(".ndx"))
                        {
                            names_col.add(ndx_names.substring(0,ndx_names.indexOf(".")));
                        }

                    }
                }
                for(int o=0;o<names_col.size()+15;o++){
                    ord_position.add(".");
                }


                for(int g=0;g<names_col.size();g++)
                {
                    RandomAccessFile nmc=new RandomAccessFile("Data\\user_data\\"+tbl+"\\"+names_col.get(g)+".ndx", "rw");
                    nmc.seek(2);
                    int position=nmc.read();
                    ord_position.set(position, names_col.get(g));
                }
                int yo = ord_position.indexOf(criteria_name);

                 int yo1 = ord_position.indexOf(update_col_name);

                RandomAccessFile queryFile=new RandomAccessFile("Data\\user_data\\"+tbl+"\\"+tbl+".tbl", "rw");

                ArrayList<Character> row_data11 = new ArrayList<>();
                int start1=8;

                queryFile.seek(start1);
                int tem1=queryFile.readShort();

                while(tem1 != 0){
                    if(-1==tem1){
                                start1=start1 + 2;//queryFile2.seek(start1);
                                queryFile.seek(start1);

                                tem1=queryFile.readShort();
                        }
                    else{
                    queryFile.seek(start1);

                    tem1=queryFile.readShort();

                    String h1 = Integer.toHexString(tem1);

                    queryFile.seek(tem1);
                    String data1=(Integer.toString(queryFile.read()));

                    queryFile.seek(tem1+5);
                    int ncol1=queryFile.read();


                    int seek_rlength1=tem1+5+ncol1+1;

                    queryFile.seek(seek_rlength1);

                    int first_length1=queryFile.read();

                    for(int i=0;i<first_length1;i++)
                    {
                        queryFile.seek(seek_rlength1+1);
                        char b1=(char)(queryFile.read());
                        //row_data.add((String)Integer.toHexString(queryFile.read()));
                        row_data11.add(b1);
                    }

                    int srlength1=seek_rlength1+first_length1+1;
                    row_data11.add('`');

                    for(int k=1;k<ncol1;k++){



                        queryFile.seek(srlength1);
                        int rlength1=queryFile.read();


                        for(int i=0;i<rlength1;i++)
                        {
                            srlength1+=1;
                            queryFile.seek(srlength1);

                            char b1=(char)(queryFile.read());

                            row_data11.add(b1);

                        }

                        srlength1+=1;
                        row_data11.add('`');


                    }

                    start1=start1+2;
                    row_data11.add(';');
                    queryFile.seek(start1);
                 //   System.out.println(row_data11);
                }
                }
//                int y77 = 0;
//                for(int i=0;i<names_col.size();i++)
//                {
//                    y77++;
//                }
//
                String final_data1="";
                String column_data1="";
                String column_wise1[]=null;
                for(int j=0;j<row_data11.size();j++)
                {
                    final_data1=final_data1+row_data11.get(j);
                }
           //     System.out.println(final_data1);
//                //String row_wise11[]=final_data.split(";");
                ArrayList<String> row_wise12 = new ArrayList<String>(Arrays.asList(final_data1.split(";")));
              //  System.out.println(row_wise12);
//                
                ArrayList<String> colex=new ArrayList<>();
////                        System.out.println("rowwise"+row_wise12);
                for(int k=0;k<row_wise12.size()-1;k++)
                {
                    //System.out.println(row_wise12.get(k));
                    String column_extract[]=row_wise12.get(k).split("`");
                    //System.out.println(column_extract[index_of_query_element]);
                    String temppp=column_extract[yo];
              //      System.out.println("temp"+temppp);
                    colex.add(temppp);

                    //System.out.println("colex"+colex);
                }
                ArrayList<Integer> recno1 = new ArrayList<>();
                int red1=1;
                for(int io=0;io<colex.size();io++){
                if(colex.get(io).equals(criteria_value)){
                recno1.add(red1);
                }
                red1++;
                }
              //  System.out.println("abcd"+recno1);
                int recno=colex.indexOf(criteria_value)+1;
              //  System.out.println("colex"+colex);
               // System.out.println("rec"+recno);
                int to_update=0;
                for(int y=0;y<colex.size();y++){
               //     System.out.println(colex.get(y));
                    if(colex.get(y).equalsIgnoreCase(criteria_value))
                        to_update=y;
                }
                ArrayList<String> colex1=new ArrayList<>();
                //int yup=0;
////                        System.out.println("rowwise"+row_wise12);
                for(int k=0;k<row_wise12.size()-1;k++)
                {
                    //System.out.println(row_wise12.get(k));
                    //yup++;
                    String column_extract[]=row_wise12.get(k).split("`");
                    //System.out.println(column_extract[index_of_query_element]);
                    String temppp=column_extract[yo1];
                //    System.out.println("temp"+temppp);
                    colex1.add(temppp);

                    //System.out.println("colex"+colex);
                }
                colex1.set(to_update, update_col_value);
               // System.out.println("final"+colex1);

                for(int tu=0;tu<recno1.size();tu++){
                            start1 = 0x08;
                            int result = 0;
                            queryFile.seek(start1);
                            int tem2=queryFile.readShort();
                            while(tem2!=0){
                                if(-1==tem2){
                                start1=start1 + 2;//queryFile2.seek(start1);
                                queryFile.seek(start1);

                                tem2=queryFile.readShort();
                        }else{
                            queryFile.seek(start1);
                            result=queryFile.readShort();
                            result+=4;
                            queryFile.seek(result);
                            int rec_val = queryFile.read();
                            int cntr=1;
                                //System.out.println(rec_val);
                            if(rec_val == recno1.get(tu)){
                            queryFile.seek(result+1);
                            int ncol=queryFile.read();
                              //  System.out.println("ncol:"+ncol);
                                int how_much = result+1+ncol+1;
                              //  System.out.println("how:"+how_much);
                                queryFile.seek(how_much);
                                int rlength=queryFile.read();
                                while(cntr!=yo1+1)
                                {
                                queryFile.seek(how_much);
                                rlength=queryFile.read();
                                for(int u=0;u<rlength+1;u++)
                                {
                                    how_much++;
                                }
                                cntr++;
                                }
                                queryFile.seek(how_much+1);
                                queryFile.writeBytes(update_col_value);

                            }
                            start1+=2;
                            queryFile.seek(start1);
                            }
                }
                }









//                System.out.println(criteria_name);
//                int line7 = 16;
////                    //System.out.println("------------------");
//                System.out.println(line("-",line7));

            } catch (Exception ex) {
                //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

        public static void parseQueryString1(String queryString) {
            try {



                System.out.println("STUB: Calling parseQueryString(String s) to show tables");
                System.out.println("Parsing the string:\"" + queryString + "\"");
                System.out.println("---------------------");
                System.out.println("Table Names");
                System.out.println("---------------------");
                 File file = new File("Data\\user_data");
                 String[] tb_names=file.list();
                 for(String name : tb_names){
                     if(new File("Data\\user_data\\"+name).isDirectory())
                     {
                         System.out.println(name);
                     }
                 }
                 System.out.println("davisbase_columns");
                 System.out.println("davisbase_tables");


            }
            catch(Exception e)
            {

            }
        }


       public static void parseQueryString(String queryString) {
            try {
                System.out.println("STUB: Calling parseQueryString(String s) to process queries");
                System.out.println("Parsing the string:\"" + queryString + "\"");

                ArrayList<String> QueryStringTokens = new ArrayList<String>(Arrays.asList(queryString.split(" ")));
                String deciding_col=null;
                String comp_val=null;
                String tbl_nm=QueryStringTokens.get(3);
             //   System.out.println(tbl_nm);
                if(QueryStringTokens.size()>4){
                deciding_col=QueryStringTokens.get(5);
                comp_val=QueryStringTokens.get(7);
                }
               // System.out.println(QueryStringTokens);
                File ftbl=new File("Data\\user_data\\"+tbl_nm);

                File[] fileNames=ftbl.listFiles();
                ArrayList<String> names_col =new ArrayList<String>();

                ArrayList<String> tmp = new ArrayList<>();


                for(int i=0;i<fileNames.length;i++){
                    if(fileNames[i].isFile())
                    {
                        String ndx_names=fileNames[i].getName();
                        if(ndx_names.contains(".ndx"))
                        {
                        names_col.add(ndx_names.substring(0,ndx_names.indexOf(".")));
                        }

                    }
                }
                //System.out.println(names_col);

                ArrayList<String> ord_position = new ArrayList<>();

                         for(int o=0;o<names_col.size()+15;o++){
                                ord_position.add(".");
                            }


                for(int g=0;g<names_col.size();g++)
                {
                    RandomAccessFile nmc=new RandomAccessFile("Data\\user_data\\"+tbl_nm+"\\"+names_col.get(g)+".ndx", "rw");
                    nmc.seek(2);
                    int position=nmc.read();
                    ord_position.set(position, names_col.get(g));
                }
                ArrayList<Integer> match_id = new ArrayList<>();
                ArrayList<Integer> match_index= new ArrayList<>();
                ArrayList<String> match_index1= new ArrayList<>();
              //  System.out.println(QueryStringTokens);

                if(QueryStringTokens.size()<=4){
                    if(QueryStringTokens.get(1).equals("*")){


                ArrayList<Character> row_data=new ArrayList<>();
                RandomAccessFile queryFile=new RandomAccessFile("Data\\user_data\\"+tbl_nm+"\\"+tbl_nm+".tbl", "rw");

                int start=8;

                queryFile.seek(start);
                int a=0;
                int tem=queryFile.readShort();
                      //  System.out.println("tem"+tem);
                while(tem != 0){
                        if(-1==tem){
                                start=start + 2;//queryFile2.seek(start1);
                                queryFile.seek(start);

                                tem=queryFile.readShort();
                        }

                        else{
                queryFile.seek(start);

                tem=queryFile.readShort();

                String h = Integer.toHexString(tem);

                queryFile.seek(tem);
                String data=(Integer.toString(queryFile.read()));

                queryFile.seek(tem+5);
                int ncol=queryFile.read();


                int seek_rlength=tem+5+ncol+1;

                queryFile.seek(seek_rlength);

                int first_length=queryFile.read();

                for(int i=0;i<first_length;i++)
                        {
                            a++;
                            queryFile.seek(seek_rlength+1);
                            char b=(char)(queryFile.read());
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data.add(b);
                        }
              //  System.out.println(row_data);
              //  System.out.println("seek rlength"+seek_rlength + " first "+first_length);
                int srlength=seek_rlength+first_length+1;
                row_data.add('\t');
                row_data.add('\t');
                for(int k=1;k<ncol;k++){
                        //a=tem+5+ncol+3;

                    //System.out.println(srlength);
                    queryFile.seek(srlength);
                        int rlength=queryFile.read();
                        //System.out.println("r"+rlength);

                        for(int i=0;i<rlength;i++)
                        {
                            srlength+=1;
                            queryFile.seek(srlength);

                            char b=(char)(queryFile.read());
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data.add(b);
                            //System.out.println(row_data);
                        }
                        row_data.add('\t');
                        srlength+=1;
                        row_data.add('\t');

                        //rlength=queryFile.read();
                }
                //System.out.println(row_data);
                start=start+2;
                row_data.add(';');
                queryFile.seek(start);
                }
                }
                int y7 = 0;
                for(int i=0;i<names_col.size();i++)
                {
                    System.out.print(ord_position.get(i));
                    System.out.print("\t\t");
                    y7++;
                }
                int lines = y7*16; 
                System.out.println();
                System.out.println(line("-",lines));
                //System.out.println(row_data);
                String final_data="";
                String column_data="";
                String column_wise[]=null;
                for(int j=0;j<row_data.size();j++)
                {
                    final_data=final_data+row_data.get(j);
                }
                String row_wise[]=final_data.split(";");


                for(int k=0;k<row_wise.length-1;k++)
                {
                    //System.out.println(row_wise[k]);
                    //column_data=row_wise[k];
                    //row_wise[k].replace('<', '\t');
                    System.out.println(row_wise[k]);
                    //column_wise=column_data.split("^");
                }
                    }
                    else{
                        String yu = QueryStringTokens.get(1);
                        int yo = ord_position.indexOf(yu);



                RandomAccessFile queryFile=new RandomAccessFile("Data\\user_data\\"+tbl_nm+"\\"+tbl_nm+".tbl", "rw");

                ArrayList<Character> row_data11 = new ArrayList<>();
                int start1=8;

                queryFile.seek(start1);
                int tem1=queryFile.readShort();

                while(tem1 != 0){
                if(-1==tem1){
                                start1=start1 + 2;//queryFile2.seek(start1);
                                queryFile.seek(start1);

                                tem1=queryFile.readShort();
                        }
                    else{
                            queryFile.seek(start1);

                tem1=queryFile.readShort();

                String h1 = Integer.toHexString(tem1);

                queryFile.seek(tem1);
                String data1=(Integer.toString(queryFile.read()));

                queryFile.seek(tem1+5);
                int ncol1=queryFile.read();


                int seek_rlength1=tem1+5+ncol1+1;

                queryFile.seek(seek_rlength1);

                int first_length1=queryFile.read();

                for(int i=0;i<first_length1;i++)
                        {
                            queryFile.seek(seek_rlength1+1);
                            char b1=(char)(queryFile.read());
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data11.add(b1);
                        }

                int srlength1=seek_rlength1+first_length1+1;
                row_data11.add('`');

                for(int k=1;k<ncol1;k++){



                    queryFile.seek(srlength1);
                        int rlength1=queryFile.read();


                        for(int i=0;i<rlength1;i++)
                        {
                            srlength1+=1;
                            queryFile.seek(srlength1);

                            char b1=(char)(queryFile.read());

                            row_data11.add(b1);

                        }

                        srlength1+=1;
                        row_data11.add('`');


                }

                start1=start1+2;
                row_data11.add(';');
                    //ln(row_data11);
                    queryFile.seek(start1);
                }
                }
                int y77 = 0;
                for(int i=0;i<names_col.size();i++)
                {
                    y77++;
                }

                String final_data1="";
                String column_data1="";
                String column_wise1[]=null;
                for(int j=0;j<row_data11.size();j++)
                {
                    final_data1=final_data1+row_data11.get(j);
                }
                        //System.out.println(final_data1);
                //String row_wise11[]=final_data.split(";");
                ArrayList<String> row_wise12 = new ArrayList<String>(Arrays.asList(final_data1.split(";")));
                     //   System.out.println(row_wise12);

                ArrayList<String> colex=new ArrayList<>();
//                        System.out.println("rowwise"+row_wise12);
                for(int k=0;k<row_wise12.size()-1;k++)
                {
                    //System.out.println(row_wise12.get(k));
                    String column_extract[]=row_wise12.get(k).split("`");
                    //System.out.println(column_extract[index_of_query_element]);
                    String temppp=column_extract[yo];
                //    System.out.println("temp"+temppp);
                    colex.add(temppp);

                    //System.out.println("colex"+colex);
                }

              //      System.out.println(yu);
                    int line7 = 16;
//                    //System.out.println("------------------");
                    System.out.println(line("-",line7));
                    for(int y=0;y<colex.size();y++){
                    System.out.println(colex.get(y));
                    }
//                }
//                        System.out.println(ord_position.indexOf("name"));
                    }
                }



                else if(QueryStringTokens.contains("where"))
                {
                    if(deciding_col.equals("rowid")||deciding_col.equals("row_id")){
                    int fg = 0;
                    int row_no = Integer.parseInt(QueryStringTokens.get(7));
                    //String query_col=QueryStringTokens.get(5);
                    String operator=QueryStringTokens.get(6);
                    if(operator.equals("=")){
                        fg = 1;
                     } else if(operator.equals("<")){
                        fg = 2;
                     } else if(operator.equals(">")){
                        fg = 3;
                     } else {
                        fg = 0;
                     } 
                    RandomAccessFile queryFile=new RandomAccessFile("Data\\user_data\\"+tbl_nm+"\\"+tbl_nm+".tbl", "rw");
                    int start1,result;
                        //System.out.println(operator);
                    switch(fg){
                        case 0:
                            System.out.println(operator+" Operator not supported");
                            break;
                        case 1:
                            queryFile.seek(0x10);
                            start1 = 0x08;
                            result = 0;
                            queryFile.seek(start1);
                            while(queryFile.readShort()!=0){
                            queryFile.seek(start1);
                            result=queryFile.readShort();
                            result+=4;
                            queryFile.seek(result);
                            int rec_val = queryFile.read();
                                //System.out.println(rec_val);
                            if(rec_val == row_no){
                            match_index.add(row_no);
                            match_id.add(result);
                            }
                            start1+=2;
                            }
                         //   System.out.println(match_index);
                           // System.out.println(match_id);
                            break;
                        case 2:
                            queryFile.seek(0x10);
                            start1 = 0x08;
                            result = 0;
                            queryFile.seek(start1);
                            while(queryFile.readShort()!=0){
                            queryFile.seek(start1);
                            result=queryFile.readShort();
                            result+=4;
                            queryFile.seek(result);
                            int rec_val = queryFile.read();
                            if(rec_val < row_no){
                            match_index.add(row_no);
                            match_id.add(result);
                            }
                            start1+=2;
                            }
                        //    System.out.println(match_index);
                            break;
                        case 3:
                            queryFile.seek(0x10);
                            start1 = 0x08;
                            result = 0;
                            queryFile.seek(start1);
                            while(queryFile.readShort()!=0){
                            queryFile.seek(start1);
                            result=queryFile.readShort();
                            result+=4;
                            queryFile.seek(result);
                            int rec_val = queryFile.read();
                            if(rec_val > row_no){
                            match_index.add(row_no);
                            match_id.add(result);
                            }
                            start1+=2;
                            }
                       //     System.out.println(match_index);
                            break;    
                    }

                    ArrayList<Character> row_data1=new ArrayList<>();
                    RandomAccessFile queryFile1=new RandomAccessFile("Data\\user_data\\"+tbl_nm+"\\"+tbl_nm+".tbl", "rw");
                    for(int r=0;r<match_id.size();r++){
                        queryFile.seek(match_id.get(r));

                String data=(Integer.toString(queryFile.read()));
                int tem1=match_id.get(r);
                queryFile1.seek(tem1+1);
                int ncol=queryFile1.read();
                   //     System.out.println(tem1+1);

                int seek_rlength=tem1+1+ncol+1;
                    //    System.out.println(seek_rlength);
                queryFile1.seek(seek_rlength);

                int first_length=queryFile1.read();
                    //    System.out.println(first_length);

                for(int i=0;i<first_length;i++)
                        {

                            queryFile1.seek(seek_rlength+1);
                            char b=(char)(queryFile1.read());

                            row_data1.add(b);
                        }

                int srlength=seek_rlength+first_length+1;
                    //    System.out.println("1"+srlength);
                row_data1.add('\t');
                row_data1.add('\t');
                     //   System.out.println("n " +ncol);
                for(int k=1;k<ncol;k++){

                 //   System.out.println(srlength);
                    queryFile1.seek(srlength);
                        int rlength=queryFile1.readByte();
                   //     System.out.println("r"+rlength);

                        for(int i=0;i<rlength;i++)
                        {
                            srlength+=1;
                            queryFile1.seek(srlength);

                            char b=(char)(queryFile1.read());

                            row_data1.add(b);
//                         
                        }
                        row_data1.add('\t');
                        srlength+=1;
                        row_data1.add('\t');

                        //rlength=queryFile.read();
                }
                //System.out.println(row_data);
//                start=start+2;
                row_data1.add(';');
                    }
                        //    System.out.println(row_data1);
                int y71 = 0;
                for(int i=0;i<names_col.size();i++)
                {
                    System.out.print(ord_position.get(i));
                    System.out.print("\t\t");
                    y71++;
                }            
                int lines = y71*16; 
                System.out.println();
                System.out.println(line("-",lines));
                //System.out.println(row_data);
                String final_data="";
                String column_data="";
                String column_wise[]=null;
                for(int j=0;j<row_data1.size();j++)
                {
                    final_data=final_data+row_data1.get(j);
                }

                String row_wise[]=final_data.split(";");

                    //System.out.println(row_wise);
                for(int k=0;k<row_wise.length;k++)
                {
                    //System.out.println(row_wise[k]);
                    //column_data=row_wise[k];
                    //row_wise[k].replace('<', '\t');
                    System.out.println(row_wise[k]);
                    //column_wise=column_data.split("^");
                }
                        //System.out.println(names_col);
                }



                    else if((names_col.contains(deciding_col))){
                     ArrayList<Character> row_data=new ArrayList<>();

                RandomAccessFile queryFile=new RandomAccessFile("Data\\user_data\\"+tbl_nm+"\\"+tbl_nm+".tbl", "rw");


                int start=8;

                queryFile.seek(start);
                int a=0;
                int tem=queryFile.readShort();

                while(tem != 0){
                    if(-1==tem){
                                start=start + 2;//queryFile2.seek(start1);
                                queryFile.seek(start);

                                tem=queryFile.readShort();
                        }
                    else{
                queryFile.seek(start);

                tem=queryFile.readShort();

                String h = Integer.toHexString(tem);

                queryFile.seek(tem);
                String data=(Integer.toString(queryFile.read()));

                queryFile.seek(tem+5);
                int ncol=queryFile.read();


                int seek_rlength=tem+5+ncol+1;

                queryFile.seek(seek_rlength);

                int first_length=queryFile.read();

                for(int i=0;i<first_length;i++)
                        {
                            a++;
                            queryFile.seek(seek_rlength+1);
                            char b=(char)(queryFile.read());
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data.add(b);
                        }
              //  System.out.println(row_data);
              //  System.out.println("seek rlength"+seek_rlength + " first "+first_length);
                int srlength=seek_rlength+first_length+1;
                row_data.add('\t');
                row_data.add('\t');
                for(int k=1;k<ncol;k++){
                        //a=tem+5+ncol+3;

                    //System.out.println(srlength);
                    queryFile.seek(srlength);
                        int rlength=queryFile.read();
                        //System.out.println("r"+rlength);

                        for(int i=0;i<rlength;i++)
                        {
                            srlength+=1;
                            queryFile.seek(srlength);

                            char b=(char)(queryFile.read());
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data.add(b);

                        }
                        row_data.add('\t');
                        srlength+=1;
                        row_data.add('\t');

                        //rlength=queryFile.read();
                }

                start=start+2;
                row_data.add(';');
                queryFile.seek(start);
                }
                    }
                int y7 = 0;
                for(int i=0;i<names_col.size();i++)
                {
                    System.out.print(ord_position.get(i));
                    System.out.print("\t\t");
                    y7++;
                }

                int lines = y7*16; 
                System.out.println();
                System.out.println(line("-",lines));
                //System.out.println(row_data);
                String final_data="";
                String column_data="";
                String column_wise[]=null;
                for(int j=0;j<row_data.size();j++)
                {
                    final_data=final_data+row_data.get(j);
                }

                String row_wise[]=final_data.split(";");
                ArrayList<String> tempo = new ArrayList<>();

                //        System.out.println(tempo);
                for(int k=0;k<row_wise.length-1;k++)
                {
                    //System.out.println(row_wise[k]);
                    //column_data=row_wise[k];
                    //row_wise[k].replace('<', '\t');
                    if(row_wise[k].contains(comp_val))
                    System.out.println(row_wise[k]);
                    //column_wise=column_data.split("^");
                }




                ArrayList<Character> row_data11 = new ArrayList<>();
                int start1=8;

                queryFile.seek(start1);
                int tem1=queryFile.readShort();

                while(tem1 != 0){
                    if(-1==tem){
                                start=start + 2;//queryFile2.seek(start1);
                                queryFile.seek(start);

                                tem=queryFile.readShort();
                        }
                    else{
                queryFile.seek(start1);

                tem1=queryFile.readShort();

                String h1 = Integer.toHexString(tem1);

                queryFile.seek(tem1);
                String data1=(Integer.toString(queryFile.read()));

                queryFile.seek(tem1+5);
                int ncol1=queryFile.read();


                int seek_rlength1=tem1+5+ncol1+1;

                queryFile.seek(seek_rlength1);

                int first_length1=queryFile.read();

                for(int i=0;i<first_length1;i++)
                        {
                            queryFile.seek(seek_rlength1+1);
                            char b1=(char)(queryFile.read());
                            //row_data.add((String)Integer.toHexString(queryFile.read()));
                            row_data11.add(b1);
                        }

                int srlength1=seek_rlength1+first_length1+1;
                row_data11.add('`');

                for(int k=1;k<ncol1;k++){



                    queryFile.seek(srlength1);
                        int rlength1=queryFile.read();


                        for(int i=0;i<rlength1;i++)
                        {
                            srlength1+=1;
                            queryFile.seek(srlength1);

                            char b1=(char)(queryFile.read());

                            row_data11.add(b1);

                        }

                        srlength1+=1;
                        row_data11.add('`');


                }

                start1=start1+2;
                row_data11.add(';');
                queryFile.seek(start1);
                //System.out.println(row_data11);
                }
                }
                int y77 = 0;
                for(int i=0;i<names_col.size();i++)
                {
                    y7++;
                }

                String final_data1="";
                String column_data1="";
                String column_wise1[]=null;
                for(int j=0;j<row_data11.size();j++)
                {
                    final_data1=final_data1+row_data11.get(j);
                }
                        //System.out.println(final_data1);
                //String row_wise11[]=final_data.split(";");
                ArrayList<String> row_wise12 = new ArrayList<String>(Arrays.asList(final_data1.split(";")));
                      //  System.out.println(row_wise12);

                int index_of_query_element=ord_position.indexOf(deciding_col);
                ArrayList<Integer> cols_to_output=new ArrayList<>();


                ArrayList<String> depth = new ArrayList<>();

                ArrayList<String> colex=new ArrayList<>();
                    //    System.out.println("rowwise"+row_wise12);
                for(int k=0;k<row_wise12.size()-1;k++)
                {
                    //System.out.println(row_wise12.get(k));
                    String column_extract[]=row_wise12.get(k).split("`");
                    //System.out.println(column_extract[index_of_query_element]);
                    //String temppp=column_extract[index_of_query_element];
                   // System.out.println("temp"+temppp);
                    colex.add(temppp);
                    depth = colex;
                    //System.out.println("colex"+colex);
                }
                     //   System.out.println("cp0"+colex);
                        int red = 0;
                        //if(colex.contains(comp_val)){

                        int fg = 0;
                    int row_no = Integer.parseInt(QueryStringTokens.get(7));
                    //String query_col=QueryStringTokens.get(5);
                    String operator=QueryStringTokens.get(6);
                    if(operator.equals("=")){
                        fg = 1;
                     } else if(operator.equals("<")){
                        fg = 2;
                     } else if(operator.equals(">")){
                        fg = 3;
                     } else {
                        fg = 0;
                     }








                      if(fg==1){  
                        for(String cv:colex){

                        if(cv.equalsIgnoreCase(comp_val)){
                        cols_to_output.add(red);
                        //System.out.println(row_wise12.get(red));
                        }
                        red++;
                        }
                      }
                      else if(fg==2){  
                        for(String cv:colex){

                        if(Integer.parseInt(cv)<Integer.parseInt(comp_val)){
                         //   System.out.println(cv+" "+comp_val);
                        cols_to_output.add(red);
                        //System.out.println(row_wise12.get(red));
                        }
                        red++;
                        }
                      } 
                      else if(fg==3){  
                        for(String cv:colex){

                        if(Integer.parseInt(cv)>Integer.parseInt(comp_val)){
                   //     System.out.println(cv+" "+comp_val);
                            cols_to_output.add(red);
                        //System.out.println(row_wise12.get(red));
                        }
                        red++;
                        }
                      }
                        //System.out.println(cols_to_output);

                if(QueryStringTokens.get(1).equals("*")){
                    int y74 = 0;
                for(int i=0;i<names_col.size();i++)
                {
                    System.out.print(ord_position.get(i));
                    System.out.print("\t\t");
                    y74++;
                }

                int lines4 = y74*16; 
                System.out.println();
                System.out.println(line("-",lines4));
                        for(int n=0;n<cols_to_output.size();n++)
                        {
                            //row_wise12.get(n).replace("`", "\t\t");
                            System.out.println(row_wise12.get(cols_to_output.get(n)).replaceAll("`", "\t\t"));
                        }
                }
                else
                {
                    String col_name_q=QueryStringTokens.get(1);
                    int q=ord_position.indexOf(col_name_q);
                    ArrayList<String> colex1=new ArrayList<>();
                   //     System.out.println("rowwise"+row_wise12);
                for(int k=0;k<row_wise12.size()-1;k++)
                {
                    //System.out.println(row_wise12.get(k));
                    String column_extract[]=row_wise12.get(k).split("`");
                    //System.out.println(column_extract[index_of_query_element]);
                    String temppp=column_extract[q];
                    //System.out.println("temp"+temppp);
                    colex1.add(temppp);
                    //depth = colex;
                    //System.out.println("colex"+colex);
                }

               //     System.out.println(q);
                //    System.out.println(col_name_q);
                    int line7 = 16;
                    //System.out.println("------------------");
                    System.out.println(line("-",line7));
                    for(int y=0;y<colex1.size();y++){
                    System.out.println(colex1.get(cols_to_output.get(y)));
                    }
                }
                        //System.out.println(ord_position.indexOf("name"));
                    }
                    else{
                        System.out.println("Column "+deciding_col+" not found in "+tbl_nm+" table");}
                }
                else
                {
                    System.out.println("Syntax error....");
                }


            } 
            catch (Exception ex) {
                //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                //System.out.println("msg"+ex.getMessage());
            }

        }

        /**
         *  Stub method for creating new tables
         *  @param queryString is a String of the user input
         */
        public static void parseCreateString(String createTableString) {
                ArrayList<String> list_col_names=new ArrayList<>();
                System.out.println("STUB: Calling your method to create a table");
                System.out.println("Parsing the string:\"" + createTableString + "\"");
                ArrayList<String> createTableTokens = new ArrayList<String>(Arrays.asList(createTableString.split(" ")));
                int occurence;
                occurence=Collections.frequency(createTableTokens, "primary");
                //System.out.println(occurence);
                /* Define table file name */

                String tableName = createTableTokens.get(2);
                String temp[]=tableName.replace("(", " ").split(" ");
                String tableFileName = temp[0] + ".tbl";
                DavisBase d=new DavisBase();

//                Boolean b = d.check_datatype("int");
//                System.out.println(b);

                /* YOUR CODE GOES HERE */


                /*  Code to create a .tbl file to contain table data */
                try {
                        /*  Create RandomAccessFile tableFile in read-write mode.
                         *  Note that this doesn't create the table file in the correct directory structure
                         */
                         File f=new File("Data\\user_data\\"+temp[0]);
                         boolean e=f.exists();
                         //RandomAccessFile cf = new RandomAccessFile("Data\\catalog\\davisbase_columns.tbl","rw");
                         String dataTypeString;

                         String colname;
                         createTableString = createTableString.substring(createTableString.indexOf("(")+1,createTableString.length()-1);
                         int record_size=0;
                         int serialCode=0;
                         //System.out.println(pk_occurence);
                         String datatype[] = createTableString.split(",");
                         int ordinal_pos1 = 0;
                         if(!e)
                             { 
                                 if(occurence<=1)
                                 {
                                 Boolean datatypetrue= false;
                                 for(int i=0;i<datatype.length;i++){
                                    //System.out.println(datatype[i]);
                                    //dataTypeString=datatype[i].substring(datatype[i].indexOf(" ")+1,datatype[i].length()).toString();
                                    //dataTypeString=datatype[i].toLowerCase();
                                    dataTypeString=datatype[i];
                                    int cnt=0;

                                    String temp1[]=dataTypeString.split(" ");

                                    //System.out.println(cnt);
                                    //System.out.println(temp1[1]);
                                    //System.out.println(temp1[2]);
                                    colname=datatype[i].substring(0,datatype[i].indexOf(" "));

                                    list_col_names.add(colname);

                                    datatypetrue=d.check_datatype(temp1[1]);
                                    if(datatypetrue == false)
                                        break;
                                    //System.out.println(datatypetrue);
                                    String pk = null;

                                    if(temp1[1].equalsIgnoreCase("int"))
                                    {
                                        record_size=record_size+4;
                                        serialCode=0x06;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("tinyint"))
                                    {
                                        record_size=record_size+1;
                                        serialCode=0x04;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("smallint"))
                                    {
                                        record_size=record_size+2;
                                        serialCode=0x05;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("bigint"))
                                    {
                                        record_size=record_size+8;
                                        serialCode=0x07;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("real"))
                                    {
                                        record_size=record_size+4;
                                        serialCode=0x08;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("double"))
                                    {
                                        record_size=record_size+8;
                                        serialCode=0x09;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("datetime"))
                                    {
                                        record_size=record_size+8;
                                        serialCode=0x0A;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("date"))
                                    {
                                        record_size=record_size+8;
                                        serialCode=0x0B;
                                    }
                                    else if(temp1[1].equalsIgnoreCase("text"))
                                    {
                                        //record_size=record_size+8;
                                        serialCode=0x0C;
                                    }
                                    //System.out.println("Record size"+record_size);
                                    int ordinal_pos=0;
                                    int data_type_length=temp1[1].length();
                                    int col_length = colname.length();
                                    int tbl_length = temp[0].length();
                                    int isnull_length=0;
                                    int primary_occurence = 0;
                                    String primary = null;
                                    if(dataTypeString.toLowerCase().contains("primary key") || dataTypeString.toLowerCase().contains("[not null]") )
                                    {
                                        if(dataTypeString.toLowerCase().contains("primary key")){
                                         isnull_length=3;
                                         pk="PRI";
                                        }
                                        else
                                        {
                                        isnull_length=2;
                                        pk="NO";
                                        }
                                    }
                                    else
                                    {
                                        isnull_length=3;
                                        pk="YES";
                                    }


                                        int total_size = 1 + tbl_length + col_length + data_type_length + 1 + isnull_length +6;
                                    //System.out.println(total_size);
                                    if(datatypetrue && occurence<=1)
                                        {
                                            f.mkdir();

                                            RandomAccessFile colFile1 = new RandomAccessFile("Data\\user_data\\"+temp[0]+"\\"+colname+".ndx", "rw");
                                            colFile1.setLength(pageSize);
                                            colFile1.seek(0);
                                            colFile1.writeByte(serialCode);
                                            int null_val;
                                            if(pk.equalsIgnoreCase("yes"))
                                            {
                                                null_val=1;
                                            }
                                            else
                                            {
                                                null_val=0;
                                            }
                                            colFile1.writeByte(null_val);
                                            //System.out.println(ordinal_pos1);

                                            colFile1.write(ordinal_pos1);
                                            ordinal_pos1++;
                                            colFile1.writeByte(0x00);
                                            colFile1.writeByte(0x10);
                                            RandomAccessFile colFile = new RandomAccessFile("Data\\catalog\\davisbase_columns.tbl", "rw");
                                            colFile.setLength(2048);

                                            colFile.seek(1);
                                            int columnNumber = colFile.read();

                                            colFile.seek(2);
                                            int b1 = colFile.readShort();

                                            colFile.seek(2);
                                            colFile.writeShort((int) (b1-total_size));
                                            colFile.seek(b1-total_size);
                                            columnNumber++;
                                            colFile.writeByte(1);
                                            colFile.writeByte(columnNumber);

                                            colFile.writeByte(tbl_length);
                                            colFile.writeBytes(temp[0]);

                                            colFile.writeByte(col_length);
                                            colFile.writeBytes(colname);

                                            colFile.writeByte(data_type_length);
                                            colFile.writeBytes(temp1[1]);

                                            colFile.writeByte(1);
                                            ordinal_pos++;
                                            colFile.writeByte(ordinal_pos);

                                            colFile.writeByte(isnull_length);
                                            colFile.writeBytes(pk);

                                            colFile.seek(1);
                                            colFile.writeByte(columnNumber);


                                            colFile.seek(2);
                                            colFile.writeShort((int) (b1-total_size));

                                            colFile.seek(8 + columnNumber*2 - 2);
                                            colFile.writeShort(b1-total_size);


                                        }
                                }
                                 if(datatypetrue && occurence<=1)
                                        {
                                            f.mkdir();
                                            //RandomAccessFile tableFile = new RandomAccessFile("Data\\user_data\\"+temp[0]+"\\"+temp[0], "rw");
                                            //tableFile.setLength(pageSize);

                                            RandomAccessFile tf = new RandomAccessFile("Data\\catalog\\davisbase_tables.tbl","rw");
                                            tf.seek(1);
                                            int rec_cnt=tf.read();
                                            tf.seek(2);
                                            ps=tf.readShort();
                                            tf.setLength(pageSize);
                                            ps = ps - temp[0].length()-1;
                                            tf.seek(ps);
                                            rec_cnt = rec_cnt +1;
                                            tf.writeByte(rec_cnt);
                                            //System.out.println(rec_cnt);
                                            tf.writeBytes(temp[0]);
                                            tf.seek(1);
                                            tf.writeByte(rec_cnt);
                                            tf.seek(2);
                                            tf.writeShort((int)(ps));
                                            tf.seek(8 + rec_cnt*2 - 2);
                                            tf.writeShort((int) ps);


                                            RandomAccessFile tableFile = new RandomAccessFile("Data\\user_data\\"+temp[0]+"\\"+temp[0]+".tbl", "rw");
                                            tableFile.setLength(pageSize);
                                            tableFile.seek(0);
                                            tableFile.write(0x0d);
                                            tableFile.seek(1);
                                            int col_rec_count=tableFile.readByte();
                                            int size_col_header=0;
                                            if(col_rec_count==0)
                                            {
                                                ps_col=512;
//                                                tableFile.seek(2);
//                                                tableFile.writeShort((int)(ps_col-1));
//                                                tableFile.seek(2);
//                                                ps_col = tableFile.readShort();
                                                for(int cl=0;cl<list_col_names.size();cl++)
                                                {
                                                    size_col_header+=list_col_names.get(cl).length();
                                                }
                                                tableFile.seek(ps_col-size_col_header-list_col_names.size());

                                                for(int wr=0;wr<list_col_names.size();wr++)
                                                {
                                                    tableFile.writeByte(list_col_names.get(wr).length());
                                                    tableFile.writeBytes(list_col_names.get(wr));
                                                }
                                                ps_col=ps_col-size_col_header-list_col_names.size();
                                                tableFile.seek(2);
                                                tableFile.writeShort((int)ps_col);
                                            }
                                            else{

                                            }


                                        }

                             }
                                 else
                                 {
                                     System.out.println("A table cannot have two primary keys.....");
                                 }
                             }
                             else
                             {
                                 System.out.println("File "+temp[0]+" already exists");
                             }
                          //System.out.println(list_col_names);

//                        
                }
                catch(Exception e) {
                        System.out.println(e.getMessage());
                }





                /*  Code to insert rows in the davisbase_columns table  
                 *  for each column in the new table 
                 *  i.e. database catalog meta-data 
                 */

        }
public boolean check_datatype(String type)
{
    try{
    switch(type)
    {
        case "int":
        case "smallint":
        case "tinyint":
        case "bigint":
        case "null":
        case "text":
        case "real":
        case "double":
        case "datetime":
        case "date":
                return true;
        default:
            throw new Exception("invalid Datatype!!!");	

    }            
    }
    catch(Exception e){
                        System.out.println(e);
                        return false;
}
}

    public static void parseInsertString(String insertTableString) {

                System.out.println("STUB: Calling your method to insert into a table");
                System.out.println("Parsing the string:\"" + insertTableString + "\"");
                ArrayList<String> insertTableTokens = new ArrayList<String>(Arrays.asList(insertTableString.split(" ")));

                String tableName = insertTableTokens.get(4);
                int try1=0;
                int is_pk=0;

                File ftbl=new File("Data\\user_data\\"+tableName);
                if(ftbl.exists())
                {
                String insert_temp1=insertTableTokens.get(3);
                String insert_temp2=insertTableTokens.get(6);
//                  System.out.println(insert_temp1.length());
//                   System.out.println(insert_temp2.length());
                insert_temp1 = insert_temp1.substring(insert_temp1.indexOf("(")+1,insert_temp1.length()-1);
                ArrayList<String> col_names1 =new ArrayList<>();
                //col_names.add(insert_temp1.split(","));
                String col_names[]=insert_temp1.split(",");

                for(int y=0;y<col_names.length;y++)
                {
                    col_names1.add(col_names[y]);
                }

                insert_temp2 = insert_temp2.substring(insert_temp2.indexOf("(")+1,insert_temp2.length()-1);
                String col_values[] = insert_temp2.split(",");

                 ArrayList<String> col_values1 =new ArrayList<>();

                for(int y=0;y<col_values.length;y++)
                {
                    col_values1.add(col_values[y]);
                }


                ArrayList<String> remaining_col=new ArrayList<>();
                Boolean length = true;
                int diff=col_names.length-col_values.length;
                if(col_names.length < col_values.length)
                {
                    length = false;
                }
                else
                {

                   // System.out.println(diff+ " " + col_names.length + " " + (col_names.length - diff));

                    for(int v1=col_names.length;v1>(col_names.length-diff);v1--)
                    {
                    String remaining=col_names[v1-1];
                    //System.out.println(remaining);
                    remaining_col.add(remaining);
                    }       
                }

                //File ftbl=new File("Data\\user_data\\"+tableName);
                ArrayList<String> names_of_col =new ArrayList<String>();
                File[] fileNames=ftbl.listFiles();
                int flag=0;
                int null_flag=0;

                ArrayList<Integer> t = new ArrayList<>();
                ArrayList<Integer> t1 = new ArrayList<>();

                int crecord_size=0;

                ArrayList<String> tmp = new ArrayList<>();


                for(int i=0;i<fileNames.length;i++){
                    if(fileNames[i].isFile())
                    {
                        String ndx_names=fileNames[i].getName();
                        if(ndx_names.contains(".ndx"))
                        {
                        names_of_col.add(ndx_names.substring(0,ndx_names.indexOf(".")));
                        }

                    }
                }
                tmp=names_of_col;
                   // System.out.println(tmp);

                for(int m=0;m<col_names1.size();m++)
                        {
                            if(names_of_col.contains(col_names1.get(m)))
                            {
                                flag=1;
                            }
                            else
                            {
                                flag=0;
                                break;
                            }
                        }

              //  System.out.println("Flag =" +flag);
                //    System.out.println(names_of_col.size());
                for(int j=0;j<col_names1.size();j++){
                    if(tmp.contains(col_names1.get(j))){
                            tmp.remove(col_names1.get(j));
                            }
                }
                   for(int r=0;r<tmp.size();r++)
                        {
                            remaining_col.add(tmp.get(r));
                        }
                   // System.out.println("a"+names_of_col);
                 //System.out.println(remaining_col);
//                    
//                    
//                    System.out.println(tmp);
                    for(int h=0;h<remaining_col.size();h++){
                    try {

                        RandomAccessFile cols = new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+remaining_col.get(h)+".ndx","rw");
                        cols.seek(0);
                        try1=cols.read();
                 //       System.out.println(try1);
                        t.add(try1);
//                        System.out.println("d "+t);
                        cols.seek(1);
                        is_pk=cols.read();
                        t1.add(is_pk);
//                        System.out.println("n "+cols.read());
                    } catch (Exception ex) {

                    }

                    }
             //       System.out.println(t + " " + t1);

//                 
                    if(t1.contains(0))
                    {
                        null_flag=0;
                    }
                    else
                    {
                        null_flag=1;
                    }
//                    

                //    System.out.println(flag+" "+length + " "+null_flag);
                    ArrayList<Integer> null_type = new ArrayList<>();
                    if(flag==1 && length)
                    {
                        if(null_flag==1){
                         System.out.println("Success...");
                         for(int y=0;y<t.size();y++){
                         if(t.get(y)==0x04)
                         {
                             null_type.add(1);
                         }
                         else if(t.get(y)==0x05)
                         {
                             null_type.add(2);
                         }
                         else if(t.get(y)==0x06 || t.get(y)==0x08)
                         {
                             null_type.add(4);
                         }
                         else if(t.get(y)==0x09 || t.get(y)==0x0A || t.get(y)==0x0B)
                         {
                             null_type.add(8);
                         }
                         else
                         {
                             null_type.add(0);
                         }
                         }

                         //   System.out.println(null_type);
                        for(int g=0;g<col_names.length;g++)
                        {
                             try {
                                 RandomAccessFile cols_given = new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+col_names[g]+".ndx","rw");
                                 cols_given.seek(0);
                                 int data_type=cols_given.read();
                                 if(data_type==0x04)
                                 {
                                     crecord_size+=1;
                                 }
                                 else if(data_type==0x05)
                                 {
                                     crecord_size+=2;
                                 }
                                 else if(data_type==0x06)
                                 {
                                     crecord_size+=4;
                                 }
                                 else if(data_type==0x07)
                                 {
                                     crecord_size+=8;
                                 }
                                 else if(data_type==0x08)
                                 {
                                     crecord_size+=4;
                                 }
                                 else if(data_type==0x09)
                                 {
                                     crecord_size+=8;
                                 }
                                 else if(data_type==0x0A)
                                 {
                                     crecord_size+=8;
                                 }
                                 else if(data_type==0x0B)
                                 {
                                     crecord_size+=8;
                                 }
                                 else if(data_type==0x0C)
                                 {

                                     crecord_size+=col_values[g].length();
                                 }
                             } catch (Exception ex) {
                                 //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                             }

                        }
                        // System.out.println("Record size:"+crecord_size);
                         names_of_col.clear();
                         for(int i=0;i<fileNames.length;i++){
                    if(fileNames[i].isFile())
                    {
                        String ndx_names=fileNames[i].getName();
                        if(ndx_names.contains(".ndx"))
                        {
                        names_of_col.add(ndx_names.substring(0,ndx_names.indexOf(".")));
                        }

                    }
                }
                         ArrayList<String> ord_values = new ArrayList<>();
                         int null_byte=0;
                         for(int o=0;o<names_of_col.size()+15;o++){
                                ord_values.add(".");
                            }
//                         for(int cc=0;cc<names_of_col.size();cc++){
//                             try {
//                                 RandomAccessFile rcols_records = new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+names_of_col.get(cc)+".ndx","rw");
//                                 rcols_records.seek(3);
//                                 int psx = rcols_records.readShort();
//                                 rcols_records.seek(psx);
//                                 
//                                 
//                             } catch (Exception ex) {
//                                 //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
//                             }
//                         }   
                         ArrayList<String> ord_values_type = new ArrayList<>();

                         for(int o=0;o<names_of_col.size()+15;o++){
                                ord_values_type.add(".");
                            }
                            String null_print=null;
                         //   System.out.println("diff "+diff);
                            if(diff>=0)
                            {
                                for(int u=0;u<remaining_col.size();u++)
                                {
                                    try {
                                        RandomAccessFile rcols_type = new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+remaining_col.get(u)+".ndx","rw");
                                        rcols_type.seek(2);
                                        int asd = rcols_type.read();
                                     //   System.out.println("ordinal pos"+asd);
                                        rcols_type.seek(0);
                                        int dtype=rcols_type.read();
                                       // System.out.println("type"+dtype);
                                            if(dtype==0x04)
                                            {
                                                ord_values.set(asd,"~");
                                                null_byte+=1;
                                            }
                                            else if(dtype==0x05)
                                            {
                                                null_byte+=2;
                                                ord_values.set(asd,"~");
                                            }
                                            else if(dtype==0x06 || dtype==0x08)
                                            {
                                                null_byte+=4;
                                                ord_values.set(asd,"~");
                                            }
                                            else if(dtype==0x09 || dtype==0x0A || dtype==0x0B)
                                            {
                                                null_byte+=8;
                                                ord_values.set(asd,"~");
                                            }
                                            else
                                            {
                                                null_byte+=0;
                                                ord_values.set(asd,"~");
                                            }
                                    } catch (Exception ex) {
                                        //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }



                         int bytes_req = 2+4+(2*names_of_col.size())+1+crecord_size+null_byte;
                          //  System.out.println(bytes_req);
                           // System.out.println(names_of_col.size());
                            //String[] ord_values= new String[100];

                            System.out.println("Size:"+ord_values.size());

                            //ord_values.add(0,"@");

                           // System.out.println("null byte"+null_byte);
                            //System.out.println("ord values"+ord_values);
                            for(int a=0;a<col_values1.size();a++)
                            {
                             try {
                                 RandomAccessFile cols_ord = new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+col_names1.get(a)+".ndx","rw");
                                 cols_ord.seek(2);
                                 int ord=cols_ord.read();
                                 ord_values.set(ord,col_values[a]);
                                 System.out.println(ord_values);
                             } catch (Exception ex) {
                                 //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                             }
                            }
                            //for(int g=0;g<ord_values.size();g++){
                 //           System.out.println("help "+ord_values);
                            //}

                            try {
                                RandomAccessFile insert_table=new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+tableName+".tbl", "rw");
                                insert_table.setLength(pageSize);
                                insert_table.seek(1);
                                int row_id=insert_table.read();
                      //          System.out.println("row id"+row_id);
                                row_id++;
                                insert_table.seek(1);
                                insert_table.write(row_id);

                                insert_table.seek(2);
                                psi=insert_table.readShort();
                                psi=psi-bytes_req;
                     //           System.out.println(psi);
                                insert_table.seek(2);
                                insert_table.writeShort((int)psi);
                                insert_table.seek(psi);
                                insert_table.writeByte(crecord_size);
                                insert_table.writeInt(row_id);
                                int y7 = 0;
                                for(int y=0;y<names_of_col.size();y++){
                                    y7++;
                                }
                        //        System.out.println("colnames"+names_of_col);
                                insert_table.write(y7);
                                for(int d=0;d<names_of_col.size();d++)
                                {
                                  RandomAccessFile cols_dt = new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+names_of_col.get(d)+".ndx","rw");  
                                  cols_dt.seek(0);
                                  int dt=cols_dt.read();

                                  cols_dt.seek(2);
                                  int op=cols_dt.read();
                                  ord_values_type.set(op,Integer.toString(dt));
                                  //insert_table.write(dt);
                                }
                                for(int o=0;o<ord_values_type.size();o++)
                                {
                                    if(ord_values_type.get(o).equalsIgnoreCase("."))
                                    {

                                    }
                                    else{
                                        //insert_table.writeByte(ord_values_type.get(o).length());
                                        insert_table.writeByte(Integer.parseInt(ord_values_type.get(o)));
                                    }
                                }
                                for(int o=0;o<ord_values.size();o++)
                                {
                                    if(ord_values.get(o).equalsIgnoreCase("."))
                                    {

                                    }
                                    else{
                                        insert_table.writeByte(ord_values.get(o).length());
                                        insert_table.writeBytes(ord_values.get(o));
                                    }
                                }
                                insert_table.seek(8+2*row_id-2);
                                insert_table.writeShort((int)psi);

                                for(int cc=0;cc<names_of_col.size();cc++){
                             try {
                                 RandomAccessFile rcols_records = new RandomAccessFile("Data\\user_data\\"+tableName+"\\"+names_of_col.get(cc)+".ndx","rw");
                                 rcols_records.seek(3);
                                 //rcols_records.writeBytes("\n");
                                 int psx = rcols_records.readShort();
                                 rcols_records.seek(2);
                                 int ordp = rcols_records.read();
                                 rcols_records.seek(psx);
                                 rcols_records.writeByte(row_id);
                                 rcols_records.writeBytes(ord_values.get(ordp));
                                 psx+=16;
                                 rcols_records.seek(3);
                                 rcols_records.writeShort(psx);
                             } catch (Exception ex) {
                                 //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }

                            } catch (Exception ex) {
                                //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else{
                           System.out.println("Null Values not allowed");    
                        }
                    }
                    else
                    {
                        System.out.println("Column does not exist.....");
                    }
                }

                else 
                {
                    System.out.println("Please create the table "+tableName+" before inserting values");
                }


    }                
}

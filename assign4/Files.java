// class Box
// Author:  Bob Myers
//
// For COP3252, Java Programming
//
//
// By Taylor Brockhoeft
//
// Files and Directories, assignment 4
//
import java.util.*;
import java.text.*;
import java.io.File;

public class Files{
	static List<File> fileList;
	
	static void listFiles(final File dir) {
	    for (final File fileEntry : dir.listFiles()) {
	        if (fileEntry.isDirectory()) { //If Directory
	            //listFiles(fileEntry);//Print Directory Files Recursivle
	        	printFile(fileEntry);
	        } else {
	        	printFile(fileEntry);
	        }
	    }
	}

	/*Name: A - Z*/
	public static Comparator<File> nameAZ = new Comparator<File>() {
        @Override
        public int compare(File f1, File f2) {
        	String str1 = f1.getName();
        	String str2 = f2.getName();
	        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
	        if (res == 0) {
	            res = str1.compareTo(str2);
	        }
        return res;
        }
    };

    /*Name: z - A*/
	public static Comparator<File> nameZA = new Comparator<File>() {
        @Override
        public int compare(File f1, File f2) {
     		String str1 = f2.getName();
        	String str2 = f1.getName();
	        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
	        if (res == 0) {
	            res = str1.compareTo(str2);
	        }
        return res;
        }
    };


	/*Date: Newest to Oldest*/
	public static Comparator<File> dateNewToOld = new Comparator<File>() {
        @Override
        public int compare(File f1, File f2) {
            return  (int)f1.lastModified() -  (int)f2.lastModified();
        }
    };

    /*Date: Oldest to Newest*/
	public static Comparator<File> dateOldToNew = new Comparator<File>() {
        @Override
        public int compare(File f1, File f2) {
            return  (int)f2.lastModified() -  (int)f1.lastModified();
        }
    };

    /*Size: Smallest to largest*/
    public static Comparator<File> sizeSmallToLarge = new Comparator<File>() {
        @Override
        public int compare(File f1, File f2) {
            return  (int)f1.length() -  (int)f2.length();
        }
    };
    /*Size: Largest to Smallest*/
    public static Comparator<File> sizeLargeToSmall = new Comparator<File>() {
        @Override
        public int compare(File f1, File f2) {
            return  (int)f2.length() -  (int)f1.length();
        }
    };

	static void sortFiles(int f){
		switch(f){
			case 0:	Collections.sort(fileList, nameAZ);
			break;
			case 1:	Collections.sort(fileList, dateNewToOld); 
			break;
			case 2:	Collections.sort(fileList, sizeSmallToLarge);
			break;
			default:	
		}
	}

	static void addFiles(final File dir) {
	    for (final File fileEntry : dir.listFiles()) {
			fileList.add(fileEntry);
	    }
	}

	static void printFileList(){
		for(int i = 0; i < fileList.size(); i++){
			printFile(fileList.get(i));
		}
	}

	static void printFile( File entry){
		Date d = new Date(entry.lastModified()*1000);
		String datestring = new SimpleDateFormat("MMM d HH:mm:ss").format(d);
        //System.out.println(entry.length() + " " + datestring + " " + entry.getName()); //Print Regular files
        System.out.printf("%d\t%s\t%s\n", entry.length(), datestring, entry.getName());
	}

	static void printHelp(){
		System.out.println("usage:  java -jar hw4.jar [directory] [-a | -l | -s]");
        System.out.println("\t(current directory is default)");
        System.out.println("\t-a alphabetical sorting");
        System.out.println("\t-l last time modified sorting");
        System.out.println("\t-s sort by size\n");
	}

	public static void main(String[] args){
		File directory;
		int flag;//0,1,2 -> alpha, last modified, size
		fileList = new ArrayList<File>();
		flag = 0;		
		directory = new File("./");//Reset Directory

		if(args.length > 0){ //Used Specified Directory
			if(args[0].equals("-a")){//alphabetically
        		flag = 0;
			}
			else if(args[0].equals("-l")){//last time modified
				flag = 1;
			}
			else if(args[0].equals("-s")){//size
				flag = 2; 
			}
			else{
				//Validate Directory?
				directory = new File(args[0]);
				if(directory.exists()){
					if(args.length > 1){
						if(args[1].equals("-a")){//alphabetically
			        		flag = 0;
						}
						else if(args[1].equals("-l")){//last time modified
							flag = 1;
						}
						else if(args[1].equals("-s")){//size
							flag = 2;
						}
					}
					else{
						flag = 0;//Default
					}
				}
				else{
					System.out.println("Invalid Directory Name: " + args[0]);		
					directory = new File("./");//Reset Directory
					printHelp();
					System.exit(1);
				}
			}
		}//End If args.length > 0
		addFiles(directory);
		sortFiles(flag);
		printFileList();		
	}
}
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;

public class Score{


	public static void main(String[] args){
		String name_folder = "Score/";
		File folder = new File(name_folder);
		if(folder.exists()){
			System.out.println("Exists");
			String[] names_file = folder.list();
			int nbFiles = names_file.length;
			String[][] classement = new String[nbFiles][2];

			for(int i = 0;i<nbFiles;i++) 
				{	
					System.out.println(names_file[i]); 
					Reader file = new Reader(name_folder+names_file[i]);
					String line = file.readLine();
					System.out.println(line);
					String[] result = line.split(":");
					classement[i] = result;
				}
			for(int i=0;i<nbFiles;i++){
				System.out.println("pax n"+i+", name:"+classement[i][0]+", score:"+classement[i][1]);
			}
		}
			
	}
}
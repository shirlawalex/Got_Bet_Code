import java.io.File;

public class Score{


	public static void main(String[] args){
		String name_folder = "File/";
		File folder = new File(name_folder);
		if(folder.exists()){
			System.out.println("Exists");
			String[] names_file = folder.list();
			System.out.println(names_file.length);
			/*
			for(int i = 0;i<names_file.length();i++) 
				{System.out.println(names_file[i]); }*/
		}
			
	}
}
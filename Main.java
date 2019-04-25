import java.io.*;
import java.io.File;

public class Main{

	public static void main(String[] args){
		String fileResultat = "File/Result.txt";
		//Read of the args
		int nb_args = args.length ;
		System.out.println("nb args="+nb_args);
		for(int i = 0;i< nb_args;i++){
			System.out.println(args[i]);
		}

		int start =0;
		int end = 0;
		String name_folder = "Pax/";
		File folder = new File(name_folder);
		if(folder.exists()){
			String[] names_file = folder.list();
			int nbFiles = names_file.length;
			end = nbFiles;
		}

		for(int i=0; i<nb_args;i++){
			int len = args[i].length();
						
			
			System.out.println("longueur de l'arg :"+len);
			if(args[i].charAt(0) == ':'){
				if(args[i].length()==1){
					System.out.println("Case 0 \":\": All pax");
					start = 0;
					end = end -1;
				}else{
					System.out.println("Case 1 \":n\": All pax from 0 to n");
					//Case 1 : All pax from 0 to n
					start = 0;
					//args[i].substring(1,len-1);
					try{
						end = Integer.parseInt(args[i].substring(1,len));
					}catch(NumberFormatException e){
						System.out.println("L'argument n'est pas un chiffre");
						end = -1;
					}
					System.out.println("debut: "+start+", fin: "+end);
					System.out.println("Error : nombre limite de pax non connu");
				}
			}else{
				if(args[i].charAt(len-1) == ':'){
					System.out.println("Case 2 \"n:\": All pax from n to last pax");
					//Case 2 : All pax from n to last pax
					//end = -1;
					start = Integer.parseInt(args[i].substring(0,len-1));
					System.out.println("debut: "+start+", fin: "+end);
					System.out.println("Error : nombre limite de pax non connu");
				}else{
					boolean suite = false;
					for(int j=0; j<len;j++){
						if(args[i].charAt(j) == ':'){
							suite = true;
							String tmp1 = args[i].substring(0,j);
							String tmp2 = args[i].substring(j+1,len);
							System.out.println(tmp1 +" : "+ tmp2);							
							start = Integer.parseInt(tmp1);
							end = Integer.parseInt(tmp2);
							
						}
					}
					if(suite){
						System.out.println("Case 3 \"n:m\": All pax from n to m");
						//Case 3 : All pax from n to m
						System.out.println("debut: "+start+", fin: "+end);
					}else{
						//Case 4 : Only pax n
						try{
							start =  Integer.parseInt(args[i]);
							end = start;
							System.out.println("Case 4 \"n\" : Only pax n");
						}catch(NumberFormatException e){
							//Case Error
							start = 0;
							end = -1;
							System.out.println("l'argument ne correspond à aucun cas");
						}
						System.out.println("debut: "+start+", fin: "+end);
					}
				}
			}

			//Lecture des pax(s) 

			System.out.println("Liste des pax fait:");
			for(int k=start;k<end+1;k++){
				String filePax = "Pax/pax"+k+".txt";
				String fileScore = "Score/scorePax"+k+".txt";
				try{
					ReaderDeathnote pax = new ReaderDeathnote(filePax);
					ReaderDeathnote result = new ReaderDeathnote(fileResultat);

					Writer score = new Writer(fileScore);

					//Fonction principale
					String nom = Checker.checkAll(pax,result,score);
					System.out.println(nom);
	 				score.close();

				}catch(Exception e){
					//e.printStackTrace();
					System.out.println("Pax numero "+k+" n'existe pas");
				}
			}
			System.out.println("");
		}
 	} 
	
}
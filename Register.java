//Remplissage par terminal des pax

import java.io.*;

public class Register{
	//Constante
	static String fileQuestion = "Question";
	static String fileIndex = "Index";
	static String fileCharacter = "Personnage";
	static int nbQuestion = 7;  
	static int nbCharacter = 36;


	public static void main (String[] args){
		
		//Initialisation
		Reader question = new Reader(fileQuestion); 
		Reader index = new Reader(fileIndex);
		Reader character = new Reader(fileCharacter);
		String[] personnage = new String[nbCharacter];  
		for(int i = 0;i<nbCharacter;i++){
			personnage[i] = character.readLine();
			//System.out.println(personnage[i]);
		}

		String test = searchName(personnage,"kyrion");
		System.out.println(test);
		//Main
		
		String content = "";

		boolean end = false;
			while(!end){
			content = "";

			//NOM
			System.out.println("Est tu bien le pax numéro ? ");
			System.out.println("Quel est ton nom? :");
			content += System.console().readLine() +"\n###\n";

			//QUESTION
			int i = 0;
			while(i<nbQuestion){
				System.out.println("Question n"+i+": "+question.readLine());
				String answer = System.console().readLine();
				String result = searchName(personnage,answer);
				System.out.println("Do you mean "+result+"? [O/n]");
				
				if(confirmation()){
					content += result+"\n";  
					i++;
				}
				else System.out.println("Recommence");
			}
			
			content += "###\n";
			//DEATHNOTE
			int j = 0;
			while(j<nbCharacter){
				System.out.println(personnage[j]);
				System.out.println("Vivant ? [OUI] Mort ? [non]");
				String answer = confirmation() ? "VIVANT" : "MORT" ;
				System.out.println("Marcheur blanc ? [o/N]");
				String zombie = deconfirmation() ? "FALSE" : "TRUE";
				System.out.println("Do you mean : "+answer+" "+zombie+" ?");
				if(confirmation()){
					content += answer+" "+zombie+"\n";  
					j++;
				}
				else System.out.println("Recommence");
			}


			end = true;						
		}
		
		System.out.println(content);
		
	}

	public static String searchName(String[] tab, String answer){
		answer = answer.toLowerCase();
		String nom = "NONE";
		int[] best = new int[nbCharacter];

		for(int i =0;i<nbCharacter;i++){
			int tmp = -1;
			boolean correct = false;
			for(int k=1;k < answer.length()  && k <tab[i].length() ;k++){
				correct = answer.substring(k-1,k).equals(tab[i].toLowerCase().substring(k-1,k));
				tmp = correct ? tmp +1 : tmp;
			}
			best[i] = tmp;
		}	
		int idx = max(best);
		nom = idx < 0 ? "NONE" : tab[idx];
		return nom;
	}


	public static int max(int [] tab){
		int val = -1;
		int ret = -1;
		for(int i = 0;i<tab.length;i++){
			if ( val < tab[i] ){val = tab[i]; ret = i;}
		}
		return ret;
	}

	public static boolean confirmation(){
		String answer = System.console().readLine().toLowerCase();
		
		switch(answer.toLowerCase()){
			case "" :
			case "y" :
			case "o" :
			case "oui": 
			case "yes":
			case  "\n" : 
				return true;
			default:
				break;
		}	
		return false;
	}

	public static boolean deconfirmation(){
		String answer = System.console().readLine().toLowerCase();
		
		switch(answer.toLowerCase()){
			case "" :
			case "n" :
			case "non": 
			case "no":
			case  "\n" : 
				return true;
			default:
				break;
		}	
		return false;
	}
}
//Remplissage par terminal des pax

import java.io.*;

public class Register{
	//Constante
	static String fileQuestion = "File/Question.txt";
	static String fileIndex = "File/Index.txt";
	static String fileCharacter = "File/Personnage.txt";
	static int nbQuestion = 8;  
	static int nbCharacter = 36+1; //36 + 1 "Personne"
	static String [] yesNoQuestions = {"OUI","NON","NULL"};


	public static void main (String[] args) 
		throws Exception{
		
		if(args.length != 1){
			throw new Exception("pas le bon nombre d'argument"); 
		}
		//Initialisation
		Reader question = new Reader(fileQuestion); 
		Reader index = new Reader(fileIndex);
		Reader character = new Reader(fileCharacter);
		String[] listePersonnage = new String[nbCharacter];
		String[] listeQuestion = new String [nbQuestion];
		listePersonnage[0] = "Personne";
		for(int i = 1;i<nbCharacter;i++){
			listePersonnage[i] = character.readLine();
			System.out.println(i+":"+listePersonnage[i]);
		}
		for(int i=0;i<nbQuestion;i++){
			listeQuestion[i] = question.readLine();
			System.out.println(listeQuestion[i]);
		}


		//Boucle principale		
		Writer pax;
		String content = "";
		boolean end = false;
		while(!end){

			//Verification du numero du pax	
			System.out.println(args[0]+" est le numéro du pax? [O/n]");
			if(!confirmation()){
				throw new Exception("pas le bon numero du pax");
			}

			//NOM
			System.out.println("Quel est ton nom? :");
			String nom = System.console().readLine();
			if(nom.equals("")){nom = "UNKNOWN_"+args[0];} 
			content += nom +"\n###\n";
			
			//Questionnaire
			content += questionnaire(listePersonnage,listeQuestion);
		
			//ecriture du questionnaire
			System.out.println(content);
			pax = new Writer("Pax/pax"+args[0]+".txt");
			pax.write(content);
			pax.close();
			//re-start loop
			end = true;						
		}
		
	}

	//Questionnaire
	public static String questionnaire(String[] personnage, String[] question){
		String content = "";
		
		//QUESTION
		int i = 0;
		while(i<nbQuestion){
			System.out.println("Question n"+i+": "+question[i]);
			String answer = System.console().readLine();
			String result = "NULL";
			if(i<3){
				//Reponse nom d'un personnage
				result = searchName(personnage,answer);
			}else{
				//Reponse OUI ou NON
				result = searchName(yesNoQuestions,answer);
			}
			System.out.println("Recap Do you mean: "+result+"? [O/n]");
		
			if(confirmation()){
				content += result+"\n";  
				i++;
			}
			else System.out.println("Recommence");
		}
		
		content += "###\n";
		//DEATHNOTE
		int j = 1;
		
		while(j<nbCharacter){
			System.out.println(personnage[j]);
			System.out.println("Vivant ? [v] Mort ? [m] Abstention ? [*]");
			int tmp = verif();
			String answer = tmp == -1 ? "NULL" : tmp == 0 ? "VIVANT" : "MORT" ;
			if(tmp == -1){
				System.out.println("Vous voulez vraiment vous abstenir pour ce personnage? [O/n]");
				if(confirmation()){
					content += "NULL NULL "+personnage[j]+"\n";	
					j ++;
				}else System.out.println("Recommence");
			}else{
				System.out.println("Marcheur blanc ? [o/N]");
				String zombie = deconfirmation() ? "FALSE" : "TRUE";
				System.out.println("Recap Do you mean : "+answer+" "+zombie+" ? [O/n]");
				if(confirmation()){
					content += answer+" "+zombie+" "+personnage[j]+"\n";  
					j++;
				} else System.out.println("Recommence");
			}
		}

		return content; 
	}

	//Retourne le nom qui a la meilleure correspondance avec l'othographe correcte
	public static String searchName(String[] tab, String answer){
		answer = answer.toLowerCase();
		String nom = "NULL";
		int[] best = new int[tab.length];

		for(int i =0;i<tab.length;i++){
			int tmp = -1;
			boolean correct = false;
			for(int k=1;k < answer.length()  && k <tab[i].length() ;k++){
				correct = answer.substring(k-1,k).equals(tab[i].toLowerCase().substring(k-1,k));
				tmp = correct ? tmp +1 : tmp;
			}
			best[i] = tmp;
		}	
		int idx = max(best);
		nom = idx < 0 ? "NULL" : tab[idx];
		return nom;
	}

	//retourne l'index de la case qui a la valeur max du tableau
	public static int max(int [] tab){
		int val = -1;
		int ret = -1;
		for(int i = 0;i<tab.length;i++){
			if ( val < tab[i] ){val = tab[i]; ret = i;}
		}
		return ret;
	}

	//Question avec comme reponse [v/m/*] (* n'importe quoi)
	public static int verif(){
		String answer = System.console().readLine().toLowerCase();
		
		switch(answer.toLowerCase()){
			case "v" :
			case "vivant" :
			case "vie": 
 			case "y" :
			case "o" :
			case "oui": 
			case "yes":
				return 0;
			case "m" :
			case "mort" :
			case "dead" :
			case "morte" :
			case  "n" :
			case "non": 
			case "no":
				return 1;
			default:
				break;
		}	
		return -1;
	}
	//Quetion avec comme reponse [O/n]
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

	//Quetion avec comme reponse [o/N]
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
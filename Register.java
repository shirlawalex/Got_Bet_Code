//Remplissage par terminal des pax

import java.io.*;

public class Register{
	//Constante
	static String canceled = "back";
	static String fileQuestion = "File/Question.txt";
	static String fileIndex = "File/Index.txt";
	static String fileCharacter = "File/Personnage.txt";
	static int nbQuestion = 8;  
	static int nbCharacter = 36+2; //36 + 2 "Personne","canceled","Daenerys",etc ..
	static String [] yesNoQuestions = {"OUI","NON","NULL",canceled}; 

	//Le fichier final est stocké sous forme de tableau avant d'etre imprimer
	//fonction d'initialisatio
	public static String[] init_content(String[] listePersonnage){
		String [] ret = new String[47];
		ret[0] = "UNFINISHED";
		ret[1] = "###";
		ret[10] = "###";
		for(int i = 2;i<10;i++){
			ret[i] = "NULL";
		}
		for(int i = 11;i<47;i++){
			ret[i] = "NULL NULL "+listePersonnage[i-9];
		}
		return ret;
	} 

	public static void write(String num,String[] content){
		Writer pax = new Writer("Pax/pax"+num+".txt");	
		String stringContent = "";
		for(int i=0;i<content.length;i++){
			stringContent += content[i]+"\n";
		}
		System.out.println(stringContent);
		pax.write(stringContent);
		pax.close();
	}

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
		listePersonnage[1] = canceled;
		//Remplissage du tableau des noms de personnages par le fichier
		for(int i = 2;i<nbCharacter;i++){
			listePersonnage[i] = character.readLine();
			//System.out.println(i+":"+listePersonnage[i]);
		}
		for(int i=0;i<nbQuestion;i++){
			listeQuestion[i] = question.readLine();
			//System.out.println(listeQuestion[i]);
		}

		//Contenu
		String [] tabContent = init_content(listePersonnage);

		//Boucle principale		
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
			tabContent[0] = "N"+args[0]+"-"+nom;
			
			//Questionnaire
			questionnaire(tabContent,listePersonnage,listeQuestion);
		
			//ecriture du questionnaire
			write(args[0],tabContent);
			
			//re-start loop
			end = true;						
		}
		
	}

	//Questionnaire
	public static void questionnaire(String[] tabContent,String[] personnage, String[] question){
		
		//QUESTION
		int i = 0;
		while(i<nbQuestion){
			System.out.println("Question n"+(i+1)+": "+question[i]);
			String answer = System.console().readLine();
			String result = "NULL";
			if(i<3){
				//Reponse nom d'un personnage
				result = searchName(personnage,answer);
			}else{
				//Reponse OUI ou NON
				result = searchName(yesNoQuestions,answer);
			}
			if(result.equals(canceled)){
				i = (i<=0)? 0: i - 1;
				System.out.println("Refaire la question précédante");
			}else{
				System.out.println("\tReponse: "+result);
				tabContent[i+2] = result;  
				i++;
			}

		}
		
		//DEATHNOTE
		int j = 2;
		
		while(j<nbCharacter){
			System.out.println("Personnage: "+personnage[j]);
			System.out.println("Vivant ? [v], Mort ? [m], Abstention ? [*]");
			int tmp = verif();
			if(tmp == 3){
				//Retour à la réponse précédantes
				j = (j<=2)? 2: j - 1;				
			}else{
				String answer = tmp == 0 ? "NULL" : tmp == 1 || tmp == -1 ? "VIVANT" : tmp == 2 || tmp == -2 ? "MORT" : "ERROR" ;
				if(tmp == 0){
					System.out.println("Vous voulez vraiment vous abstenir pour ce personnage? [O/n]");
					if(confirmation()){  
						tabContent[j+9] = "NULL NULL "+personnage[j];	
						j ++;
					}else System.out.println("Recommence");
				}else{
					String zombie = "";
					//Si tmp est supérieur à zero alors on verifie pour les marcheurs blanc
					//sinon c'est d'office false
					if(tmp > 0){
						System.out.println("Marcheur blanc ? [o/N]");
						zombie = deconfirmation() ? "FALSE" : "TRUE";
					}else{
						zombie = "FALSE";
					}
					System.out.println("\tRéponse: "+answer+" "+zombie);
					tabContent[j+9] = answer+" "+zombie+" "+personnage[j];
					j++;
					
				}
			}
		}
	}

	//Retourne l'element dans tab qui a la meilleure correspondance avec l'othographe correcte de answer
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
			//if(tmp>0) System.out.println(tab[i]);
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
		String answer = System.console().readLine();
		
		switch(answer){
			case "back":
				return 3;
			case "v" :
			case "vivant":
			case "vivante":
			case "vie": 
 			case "y" :
			case "o" :
			case "oui": 
			case "yes":
				return 1;
			case "V" :
			case "VIVANT" :
			case "VIVANTE" :
			case "VIE": 
 			case "Y" :
			case "O":
			case "OUI": 
			case "YES":
				return - 1;
			case "m" :
			case "mort" :
			case "dead" :
			case "morte" :
			case "n" :
			case "non": 
			case "no":
				return 2;
			case "M" :
			case "MORT" :
			case "DEAD" :
			case "MORTE" :
			case "N" :
			case "NON": 
			case "NO":
				return -2;
			default:
				break;
		}	
		return 0;
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
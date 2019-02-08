public class Checker{

	public static int big = 5;
	public static int medium = 2;
	public static int small = 1;


	public static String checkAll(Reader pax, Reader result,Writer score){
		String content ="";
		int points = 0;
		//System.out.println("### Resultat Question ###");
		score.write("### Resultat Question ###\n");
		points = Checker.checkQuestion(points,pax.question,result.question,score);
		//System.out.println("### Resultat Deathnote ###");
		score.write("### Resultat Deathnote ###\n");
		points = Checker.checkDeathNote(points,pax.deathnote,result.deathnote,score);
 		//System.out.println(points);
		score.write("Total: "+points);
		return content;
	} 

	public static int checkQuestion(int points,String[] reponse,String[] correction,Writer score) {
		String content = "";
		int taille = reponse.length;
		if(taille != correction.length){
			System.out.println("Error");
			return 0;
		}
		for(int i = 0;i<taille;i++){
			if(!correction[i].equals("NONE")){
				//System.out.println("Reponse:"+reponse[i]+" correction:"+correction[i]);
				content += "Reponse:"+reponse[i]+" correction:"+correction[i]+"\n";
				if(reponse[i].equals(correction[i])){
					int add = (i == 0 ? big : 
								i <= 3 ? medium :
								small);
					//System.out.println("	points ="+points+"+"+add);
					content +="	points ="+points+"+"+add+"\n";
					points += add;
				}
			}else{
				//System.out.println("Question : NONE ");
				content += "Question : NONE "+"\n";
			}
		}
		System.out.println(content);
		score.write(content);
		return points;
	} 

	public static int checkDeathNote(int points, String[][] deathnote, String[][] correction,Writer score){
		int taille = deathnote.length;
		String content = "";

		if(taille != correction.length){
			System.out.println("Error");
			return 0;
		}

		for(int i = 0;i<taille;i++){
			if(!correction[i][0].equals("NONE")){
				//System.out.println("Reponse deathnote:"+deathnote[i][0]+" correction:"+correction[i][0]);
				content += "Reponse deathnote:"+deathnote[i][0]+" correction:"+correction[i][0]+"\n";
				
				if(deathnote[i][0].equals(correction[i][0])){
					//Bonne prédiction
					//System.out.println("	points = "+points+"+1");
					content += "	points = "+points+"+1"+"\n";
					points += 1;

					if(deathnote[i][1].equals("TRUE")){
						//System.out.print("	Transformation ? ");
						content += "	Transformation ? ";

						//Transformation en marcheur blanc
						if(correction[i][1].equals("TRUE")){
							//System.out.println("TRUE");
							content += "TRUE"+"\n";
							//Avéré
							//System.out.println("	points = "+points+"+1");
							content += "	points = "+points+"+1"+"\n";
							points += 1;
						}else{
							//System.out.println("FALSE");
							content += "FALSE"+"\n";
							//Non avéré
							//System.out.println("	points = "+points+"-1");
							content += "	points = "+points+"-1"+"\n";
							points -= 1;
						}
					}
				}else{ 
					//Mauvaise prédiction
					//System.out.println("	points ="+points+"-1");
					content += "	points ="+points+"-1"+"\n";
					points -= 1;
				}
			}else{
				//System.out.println(correction[i][0]);
				content += correction[i][0]+"\n";
			}
		}
		System.out.println(content);
		score.write(content);
		return points;
	}
	
}
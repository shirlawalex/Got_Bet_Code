public class Checker{

	public int bigPoint;
	public int bigIndex;
	public int mediumPoint;
	public int mediumIndex;
	public int smallPoint;
	public int smallIndex;

	public Checker(int bigIndex, int bigPoint, int mediumIndex, int mediumPoint,int smallIndex, int smallPoint){
		this.bigIndex = bigIndex;
		this.bigPoint = bigPoint ;
		this.mediumPoint = mediumPoint;
		this.mediumIndex = mediumIndex;
		this.smallIndex = smallIndex;
		this.smallPoint = smallPoint;
	}

	public static String checkAll(ReaderDeathnote pax, ReaderDeathnote result,Writer score){
		Pax paxInfo = new Pax();
		Checker parametreQuestion = new Checker(pax.bigIndex,pax.bigPoint,pax.mediumIndex,pax.mediumPoint,pax.smallIndex,pax.smallPoint);
		
		//calcule les points des questions
		paxInfo.addContent("### Resultat Question ###\n");
		Checker.checkQuestion(paxInfo,pax.question,result.question,parametreQuestion);
		//calcule les points du deathnote
		paxInfo.addContent("### Resultat Deathnote ###\n");
		Checker.checkDeathNote(paxInfo,pax.deathnote,result.deathnote);
		
		//total des points
		String content = pax.name+", Total:"+paxInfo.getPoints()+"\n"+paxInfo.getContent();
		
		//ecriture
		score.write(content);
		//System.out.println(content);
		
		String ret = pax.name;
		return ret;
	} 

	public static int checkQuestion(Pax pax,String[] reponse,String[] correction,Checker parametre) {
		String content = "";
		int points = 0;
		int taille = reponse.length; //nbQuestions
		if(taille != correction.length){
			System.out.println("Error");
			return -1;
		}
		for(int i = 0;i<taille;i++){
			if(!correction[i].equals("NULL")){
				//System.out.println("Reponse:"+reponse[i]+" correction:"+correction[i]);
				content += "Reponse:"+reponse[i]+" correction:"+correction[i]+"\n";
				if(reponse[i].equals(correction[i])){
					int add = (i == parametre.bigIndex ? parametre.bigPoint : 
								i <= parametre.mediumIndex ? parametre.mediumPoint :
								parametre.smallPoint);
					//System.out.println("	points ="+points+"+"+add);
					content +="	points ="+points+"+"+add+"\n";
					points += add;
				}
			}else{
				//System.out.println("Question : NULL ");
				content += "Question : NULL "+"\n";
			}
		}
		//System.out.println(content);
		pax.addContent(content);
		pax.addPoints(points);
		return 0;
	} 

	public static int checkDeathNote(Pax pax,String[][] deathnote, String[][] correction){
		int taille = deathnote.length;
		String content = "";
		int points = 0;

		if(taille != correction.length){
			System.out.println("Error");
			return -1;
		}

		for(int i = 0;i<taille;i++){
			if(!correction[i][0].equals("NULL")){
				//System.out.println("Reponse deathnote:"+deathnote[i][0]+" correction:"+correction[i][0]);
				content += "Reponse deathnote:"+deathnote[i][0]+" correction:"+correction[i][0]+"\n";
				if(deathnote[i][0].equals(correction[i][0]) && deathnote[i][1].equals(correction[i][1])){
					if( correction[i][1].equals("FALSE")){
						content += "FALSE"+"\n";
						content += "	points = "+points+"+1"+"\n";
						points += 1;
					}else{
						content += "TRUE"+"\n";
						content += "	points = "+points+"+2"+"\n";
						points += 2;
					}
				}else{
					//Mauvaise prédiction
					content += "	points ="+points+" 0"+"\n";
				}
			}else{
				//System.out.println(correction[i][0]);
				content += correction[i][0]+"\n";
			}
		}
		//System.out.println(content);
		pax.addContent(content);
		pax.addPoints(points);
		return 0;
	}
	
}
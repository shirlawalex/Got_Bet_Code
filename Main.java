public class Main{

	public static void main(String[] args){
		String file = "Pax/pax"+args[0]+".txt";
		Reader pax = new Reader(file);
		Reader result = new Reader("result.txt");

		Writer score = new Writer("Score/scorePax"+args[0]+".txt");
		String content = "";

		content += pax.name + "\n";
		System.out.println(pax.name);

		int points = 0;
		/*
		System.out.println("### Resultat Question ###");
		points = Checker.checkQuestion(points,pax.question,result.question,score);
		System.out.println("### Resultat Deathnothe ###");
		points = Checker.checkDeathNote(points,pax.deathnote,result.deathnote,score);
 		System.out.println(points);
 		*/

 		Checker.checkAll(pax,result,score);

 		score.close();
 	} 
	
}
public class Main{

	public static void main(String[] args){
		//Read of the args
		int nb_args = args.length ;
			
		for(int i=0; i<nb_args;i++){
			int len = agrs[i].length();
			int start, end;

			if(args[i].charAt(0) == ':'){
				//Case 1 : All pax from 0 to n
				start = 0;
				//args[i].substring(1,len-1);
				end = Integer.parseInt(args[i].substring(1,len-1));
			}else{
				if(args[i].charAt(len-1) == ':'){
					//Case 2 : All pax from n to last pax
					System.out.println("Error : nombre limite de pax non connu");
				}else{
					boolean suite = false;
					for(int j=0; j<args[i];i++){
						if(args[i].charAt(j) == ':'){
							suite = true;
							start = Integer.parseInt(args[i].substring(0,j-1));
							end = Integer.parseInt(args[i].substring(j+1,len));
						}
					}
					if(suite){
						//Case 3 : All pax from n to m

					}else{
						//Case 4 : Only pax n
					}
				}
			}

		}

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
public class Main{

	public static void main(String[] args){
		String fileResultat = "File/Result.txt";
		//Read of the args
		int nb_args = args.length ;
		System.out.println("nb args="+nb_args);
		for(int i = 0;i< nb_args;i++){
			System.out.println(args[i]);
		}

		for(int i=0; i<nb_args;i++){
			int len = args[i].length();
			int start =0;
			int end = 0;			
			
			System.out.println("longueur de l'arg :"+len);
			if(args[i].charAt(0) == ':'){
				System.out.println("Case 1 : All pax from 0 to n");
				//Case 1 : All pax from 0 to n
				start = 0;
				//args[i].substring(1,len-1);
				end = Integer.parseInt(args[i].substring(1,len));
				System.out.println("debut: "+start+", fin: "+end);
				System.out.println("Error : nombre limite de pax non connu");
			}else{
				if(args[i].charAt(len-1) == ':'){
					System.out.println("Case 2 : All pax from n to last pax");
					//Case 2 : All pax from n to last pax
					end = -1;
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
						System.out.println("Case 3 : All pax from n to m");
						//Case 3 : All pax from n to m
						System.out.println("debut: "+start+", fin: "+end);
					}else{
						System.out.println("Case 4 : Only pax n");
						//Case 4 : Only pax n
						start =  Integer.parseInt(args[i]);
						end = start;
						System.out.println("debut: "+start+", fin: "+end);
					}
				}
			}

			//Lecture des pax(s) 

			for(int k=start;k<end+1;k++){
				String filePax = "Pax/pax"+k+".txt";
				String fileScore = "Score/scorePax"+k+".txt";
				ReaderDeathnote pax = new ReaderDeathnote(filePax);
				ReaderDeathnote result = new ReaderDeathnote(fileResultat);

				Writer score = new Writer(fileScore);

				//Fonction principale
				Checker.checkAll(pax,result,score);

 				score.close();
			}
		}
 	} 
	
}
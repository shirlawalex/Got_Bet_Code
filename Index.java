public class Index{
	//fonction mettant à jour le fichier Index.txt
	//format de chaque ligne: i/Nom_du_pax
	//si le numéro est le dernier du ficher simple ajout du pax
	//si le numéro est deja indexé, alors écriture par dessus
	//si le numéro n'est pas le dernier et qu'il existe un écart,
	//		alors appel de la fonction maj puis pour créer toutes les pax manquant 

	static Reader index = new Reader("File/Index.txt");
	static String [] content;
	
	public static int init(){
		
	} 
	public static int maj(int numero){

		return 0; 
	}

	public static int add(int numero,String name){


		return 0;
	}
}
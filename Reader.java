import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Reader{

	public int nbQuestion = 7;
	public int nbPers = 36;

    public String name;   
    public String [] question = new String[nbQuestion];
    public String [][] deathnote = new String[nbPers][2];

    public Reader(String fileName){
        try{
            InputStream flux = new FileInputStream(fileName);    //Pour linstant changement manuel des textes
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);

            name = buff.readLine();
            buff.readLine();
            for(int i = 0;i<nbQuestion;i++){
	            question[i] = buff.readLine();
	        }
            System.out.println(buff.readLine());
            for(int i = 0;i<nbPers;i++){
            	String  ligne = buff.readLine();
                String [] tab;
                tab = ligne.split(" ");
                deathnote[i][0] = tab[0];
                deathnote[i][1] = tab[1];
               	//deathnote[i][2] = tab[2];
            }

            buff.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
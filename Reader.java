import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Reader{
    BufferedReader buff;
    
    public Reader(String fileName){
        try{
            InputStream flux = new FileInputStream(fileName);    //Pour linstant changement manuel des textes
            InputStreamReader lecture = new InputStreamReader(flux);
            buff = new BufferedReader(lecture);
            }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String readLine(){
        String ligne = "";
        try{
            ligne = buff.readLine();
            }
        catch (Exception e){
            e.printStackTrace();
        }
        return ligne;
    }

    public int read(){
    	try{
    		return buff.read();
    	}catch(IOException e){
    		return -1;
    	}
    }

    public void close(){
    	 try {
                if (buff != null)
                    buff.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

}

class ReaderDeathnote{

	public int nbQuestion = 8;
	public int nbPers = 36;

	public int bigIndex = 0;
	public int bigPoint = 5;
	public int mediumIndex = 3;
	public int mediumPoint = 3;
	public int smallIndex = nbQuestion;
	public int smallPoint = 1;

    public String name;   
    public String [] question = new String[nbQuestion];
    public String [][] deathnote = new String[nbPers][2];

    public ReaderDeathnote(String fileName) throws Exception {
        //try{
            InputStream flux = new FileInputStream(fileName);    //Pour linstant changement manuel des textes
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);

            
            name = buff.readLine();
            
            buff.readLine();
            for(int i = 0;i<nbQuestion;i++){
	            question[i] = buff.readLine();
	        }
            buff.readLine(); //lis la ligne "###"
            for(int i = 0;i<nbPers;i++){
            	String  ligne = buff.readLine();
                String [] tab;
                tab = ligne.split(" ");
                deathnote[i][0] = tab[0];
                deathnote[i][1] = tab[1];
               	//deathnote[i][2] = tab[2];
            }

            buff.close();
        //}
        //catch (Exception e){
        //    e.printStackTrace();
        //}
    }

}
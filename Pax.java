public class Pax{
	int points;
	String content;

	public Pax(){
		this.content = "";
		int points = 0;
	}

	public void addPoints(int i){
		this.points += i;
	}

	public void addContent(String str){
		this.content += str;
	}

	public String getContent(){return this.content;}
	public int getPoints(){return this.points;}
}
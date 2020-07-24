import java.util.ArrayList;
import java.util.Collections;

public class Test extends Question{
	private String[] options;
	private int numOfOptions = 4;
	private ArrayList<Character> labels = new ArrayList<Character>();
	private String labels_options = "";
	ArrayList<String> a_options = new ArrayList<>();

	Test(){	
		
		for(int i = 65; i < this.numOfOptions + 65; i++){
			labels.add((char)i);
		}

	}

	public void setOptions(String[] s){
		this.options = new String[numOfOptions];
		for(int i = 0; i < s.length; i++){
			this.options[i] = s[i];
			a_options.add(this.options[i]);
		}
		Collections.shuffle(a_options);

	}

	public String getOptionAt(int index){
		return a_options.get(index);
	}

	 public String toString(){
	 	

	 	for(int i = 0; i < a_options.size(); i++){
	 		labels_options += labels.get(i) + ") " + a_options.get(i) + "\n";
	 	}
	 	return labels_options;
	 }
}
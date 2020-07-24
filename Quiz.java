import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.lang.Character;


public class Quiz{
	private String name;
	private ArrayList<Question> questions = new ArrayList<Question>();
	ArrayList<String> global_opt = new ArrayList<>();
	public Quiz() throws IOException{

	}

	public void setName(String name) throws InvalidQuizFormatException{
		 if(name.contains("_")){
		 	throw new InvalidQuizFormatException();
		 }
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void addQuestion(Question other){
		this.questions.add(other);
	}

	public static Quiz loadFromFile(String file) throws Exception, InvalidQuizFormatException{
		if(file.contains("_")){
			throw new InvalidQuizFormatException();
		}
		File filee = new File(file);
		Scanner in = new Scanner(filee);
		
		int lc = 0;
		Quiz quiz =  new  Quiz();
		quiz.setName(file.replaceAll(".txt", ""));
		while(in.hasNextLine()){
			String line = in.nextLine();
			if(line.contains("{blank}")){
				Question question_f = new Fillin();
				question_f.setDescription(line);
				String ans_line = in.nextLine();
				if(ans_line.equals("")){
					throw new InvalidQuizFormatException();
				}
				question_f.setAnswer(ans_line);
				quiz.addQuestion(question_f);
				lc = 0;
			}
			else if(line.equals("")){
				continue;
			}
			else{ //if(lc == 5){
				Test question_t = new Test();
				String[] opt = new String[4];
				question_t.setDescription(line);
				for(int i = 0; i < opt.length; i++){
					opt[i] = in.nextLine();
					if(opt[i].equals("")){
						throw new InvalidQuizFormatException();
					}
				}
				String ans_line = opt[0];
				question_t.setAnswer(ans_line);
				question_t.setOptions(opt);
				quiz.addQuestion(question_t);
				//System.out.println(line + " ");
				lc = 0;
			}
			lc++;
		}
		in.close();


		return quiz;
	}

	public void start(){

		Scanner user = new Scanner(System.in);
		System.out.println("=========================================================\n");
		System.out.printf("WELCOME TO \"%s\" QUIZ!\n", name);
		Collections.shuffle(questions);
		
		//System.out.println(questions.size());
		
		int number_of_q = 1;
		int correct_ans_cnt = 0;
		int in_correct_ans_cnt = 0;
		String ans = "";
		String desc = "";
		String user_ans = "";

		while(number_of_q <= questions.size()){
			System.out.println("---------------------------------------------------------");
			if(questions.get(number_of_q - 1) instanceof Fillin){
				desc = (questions.get(number_of_q - 1)).getDescription();
				String [] desc_arr = desc.split(" ");
				desc = "";
				for(String d: desc_arr){
					if(d.equals("{blank}")) d = "_____";
					desc += d + " ";
				}
				System.out.println(number_of_q + ". " + desc);
				System.out.println("- - - - - - - - - - - - - - - -");
				ans = (questions.get(number_of_q - 1)).getAnswer();
				System.out.print("Type your answer: ");
				user_ans = user.next();
				if(user_ans.equalsIgnoreCase(ans)){
					System.out.println("Correct!");
					correct_ans_cnt++;
				}
				else{
					System.out.println("Incorrect!");
					in_correct_ans_cnt++;
				}
				//System.out.println("");
			}
			else{
				desc = (questions.get(number_of_q - 1)).getDescription();
				System.out.println(number_of_q + ". " + desc);
				//System.out.println(questions.get(number_of_q));
				System.out.println("- - - - - - - - - - - - - - - -");
				ans = (questions.get(number_of_q - 1)).getAnswer();
				Test t_class = (Test)questions.get(number_of_q - 1);

				System.out.println(t_class);
				System.out.print("Enter the correct choice: ");
				char user_ans_t;
				while(true){
					try{
						user_ans_t = user.next().charAt(0);
						if((t_class.getOptionAt( ( (int)user_ans_t ) - 65).equals(ans))){
							System.out.println("Correct!");
							correct_ans_cnt++;
						}
						else{
							System.out.println("Incorrect!");
							in_correct_ans_cnt++;
						}
						break;
					}
					catch(ArrayIndexOutOfBoundsException e){
						System.out.print("Invalid chice! Try again (Ex: A, B, ...)");
					}
					catch(InputMismatchException e){
						System.out.print("Invalid chice! Try again (Ex: A, B, ...)");
					}
					catch(java.lang.IndexOutOfBoundsException e){
						System.out.print("Invalid chice! Try again (Ex: A, B, ...)");
					}

				}

			
	
			}
			number_of_q++;
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("Correct answers: " + correct_ans_cnt +"/"+ (number_of_q - 1) + " (" +(((double)correct_ans_cnt / (number_of_q-1)) * 100) + "%)\n");

	}
	
	// public String toString(){

	// }
	
}
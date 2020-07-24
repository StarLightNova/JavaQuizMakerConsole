import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

public class QuizMaker{
	public static void main(String[] args) throws IOException{
		Quiz quiz = Quiz.loadFromFile(args[0]);
		quiz.start();
	}
}
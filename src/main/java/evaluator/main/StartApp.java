package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import evaluator.model.Intrebare;
import evaluator.model.Statistica;

import evaluator.controller.AppController;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.model.Test;

//functionalitati
//i.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//ii.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//iii.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "intrebari.txt";
	
	public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		AppController appController = new AppController();
		
		boolean activ = true;
		String optiune = null;
		
		while(activ){
			
			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");
			
			optiune = console.readLine();
			
			if(optiune.equals("1")) {
				String dom, enunt, var1, var2, varcorecta;
				System.out.println("Domeniu:");
				dom = keyboard.nextLine();

				System.out.println("Enunt:");
				enunt = keyboard.nextLine();

				System.out.println("Varinta 1:");
				var1 = keyboard.nextLine();

				System.out.println("Varinta 2:");
				var2 = keyboard.nextLine();

				System.out.println("Varinta corecta:");
				varcorecta = keyboard.nextLine();

				try {
					Intrebare intrebare = new Intrebare(enunt, var1, var2, varcorecta, dom);
					appController.addNewIntrebare(intrebare);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} else if(optiune.equals("2")) {
				try {
					Test test = appController.createNewTest();
					for (Intrebare intreb : test.getIntrebari()) {
						System.out.println(intreb);
					}
					System.out.println();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}

			}
			else if(optiune.equals("3")) {
				try {
					appController.loadIntrebariFromFile(file);
				} catch (Exception ex) {
					System.out.println("File error");
				}
				Statistica statistica;
				try {
					statistica = appController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
					System.out.println(e.getMessage());
				}

			} else if(optiune.equals("4")) {
				activ = false;
			}
		}
		
	}

}

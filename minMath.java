import java.lang.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.*;
public class minMath
/************************************************
** PROGRAM: 		minMath.java				*
** PROGRAMMER:  	Darryl Naawu				*
** DATE CREATED:	10/10/2012 					*
** LAST EDITED:		11/26/2012					*
** PURPOSE: To make a basic minute math	game	*
* To use in an app								*
*  OTHER FILES:	. TXT: easyprob,Medprob,hardprob*
* .JAVA: 										*
*************************************************/

//10/29- I found the calculate program for -****
/* for basic functionality, the problem files would
have the String- Question and int answer
*/

/*TO DO: 11/14

Fix timer issues
11/20

Fixed timer with threading*
Fix the ending messages*
finish for hard and medium

11/26
Add problems to question bank

Add selection checking loop

12/19
Fix Checker
Make Savefile
-read datafile
-write to datafile
*/
{
	static	int  AnsTot=0;
	static	int  RightTot=0;
	static int  WrongTot=0;

	public static void main(String[]args)
	{
	String[] easy=new String[60];
	String[] medium=new String[115];
	String[] hard=new String[75];

	int 	 choice = 0;
	String 	 RepeatChecker="Y";
	int cursor;// For answer array

//Main objects

	Scanner scMain =new Scanner(System.in);
	Random rselect= new Random();

	System.out.println("Answer as many questions you can in a minute");
	System.out.println("What Difficulty? Input 1, 2 or 3");


		choice=scMain.nextInt();

			if(choice==1)
			{
				File inf= new File("easyprob.txt");
				problemSolver(inf,easy);

				System.out.println("Do you want to try again? (Y/N) :");
				RepeatChecker=scMain.next();
				RepeatChecker=RepeatChecker.toUpperCase();
			}

	//****************** Medium Difficulty********************//
		if(choice==2)
			{
				File inf= new File("medprob.txt");
				problemSolver(inf,medium);

					System.out.println("Do you want to try again? (Y/N) :");
					RepeatChecker=scMain.next();
					RepeatChecker=RepeatChecker.toUpperCase();
			}
	//********************Hard Difficulty********************//
		if(choice==3)
			{
				File inf= new File("hardprob.txt");
				problemSolver(inf,hard);

				System.out.println("Do you want to try again? (Y/N) :");
				RepeatChecker=scMain.next();
				RepeatChecker=RepeatChecker.toUpperCase();
				}



	while(RepeatChecker.equals("Y"))
	{
	System.out.println("Answer as many questions you can in a minute");
	System.out.println("What Difficulty? Input 1, 2 or 3");

	choice=scMain.nextInt();
		if(choice==1)
		{
			File inf= new File("easyprob.txt");
			problemSolver(inf,easy);

		}

//****************** Medium Difficulty********************//
	if(choice==2)
		{
			File inf= new File("Medprob.txt");
			problemSolver(inf,medium);

		}
//********************Hard Difficulty********************//
	if(choice==3)
		{
			File inf= new File("hardprob.txt");
			problemSolver(inf,hard);


			System.out.println("Do you want to try again? (Y/N) :");
			RepeatChecker=scMain.next();
RepeatChecker=RepeatChecker.toUpperCase();
		}

/// Repeat program
System.out.println(RepeatChecker);
System.out.println("Do you want to try again? (Y/N) :");
RepeatChecker=scMain.next();
RepeatChecker=RepeatChecker.toUpperCase();

}//end while
	}//end main

		static public int Solvec (int Guess, int Answer)
		{
			if(Guess==Answer)
			{
				System.out.print(" Correct!\n");
				return(1);
			}
			else
			{
				System.out.print("Wrong. Answer is "+Answer+"\n");
				return(0);
			}
		}

	static public void problemSolver(File inf,String[] difficultyArray)
		{
			Thread thr =new Thread();
			int ans;
			int ii=0;
			int l=difficultyArray.length;
			Random rselect= new Random();
			int cursor=0;
			int[] ansArray= new int[l];
			//timeLength - Length of time program runs milliseconds

			int timeLength = 60000;

			try
			{
			//scan in prob into scanner
				Scanner sc1= new Scanner(inf);
				while (sc1.hasNext())
				{
					StringTokenizer st=new StringTokenizer(sc1.nextLine());
					while(st.hasMoreTokens())
					{
						difficultyArray[ii]=st.nextToken();
						ansArray[ii]=Integer.parseInt(st.nextToken());
						ii++;
					}

				}
			 }//END TRY

			catch(FileNotFoundException E)
			{
				System.out.println("File Not Found");
			}

			//start timer
			//random select problem

			long timeStart=System.currentTimeMillis();
			long timeEnd=timeStart+timeLength;

			long sleepTime=100;
			Scanner scMain = new Scanner(System.in);

		try	{
			while(System.currentTimeMillis()<=timeEnd)
					{
					cursor=rselect.nextInt(ii);
					System.out.println(difficultyArray[cursor]);
					ans=scMain.nextInt();
					//Solvec - Solve problem function
					RightTot+=Solvec(ans,ansArray[cursor]);
					AnsTot++;
					thr.sleep(sleepTime);
					}
			}

			catch(InterruptedException I)
				{//Exception for thread
					System.out.println(sleepTime);
			}

				WrongTot=AnsTot-RightTot;
				System.out.println("\n\nCongradulations! You Solved "+AnsTot+" Problems!");
				System.out.println("Correct Answers "+RightTot);
				System.out.println("Wrong Answers "+WrongTot);

			}

	}



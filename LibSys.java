import java.util.*;
import java.util.Scanner;

//Nichole Mercier

public class LibSys{

private class Member {
	String fname;
	String lname;
	Date birthday;
	int ID;
	List<Book> checkedOut;

	public Member(String fn, String ln, Date bd, int id){
		fname = fn;
		lname = ln;
		birthday = bd;
		ID = id;
		checkedOut = new ArrayList<Book>();
	}


}
public class Book{
	String author;
	String title;
	Date published;
	int ID;
	Boolean available;

	public Book(String auth, String t, Date pub, int id){
		author = auth;
		title = t;
		published = pub;
		ID = id;
		available = true;
	}



}

	
	public static List<Book> books;
	public static List<Member> members;
	public static Scanner input;

	public static void main( String[] args) {
		
		LibSys sys = new LibSys();
		books = new ArrayList<Book>();
		members = new ArrayList<Member>();

		input = new Scanner(System.in);
		System.out.printf("WELCOME TO THE LIBRARY SYSTEM\n");//system title

		
		while(true){

		sys.prompt();// call menu option;
		int selection = input.nextInt();

		switch(selection){
			case 1: sys.addMember();//add member function
            			break;
        		case 2: sys.addPublication();// add new book
        			break;
        		case 3: sys.checkOut(); // checkout book
      			      break;
       			case 4: sys.returnBook(); //return book
         			break;
        		case 5: sys.viewMember(); // view member
         		        break;
        		case 6: sys.printAllBooks();
				break;
			case 0:
            			System.exit(0);
            			break;
        		default:
            			break;

		}// end switch
		}
	}//end main

	public void prompt(){ // menu options

      		System.out.println("\nWhat would you like to do?");
    
      		System.out.println("Press 1 to add a new member\n"
              	+ "Press 2 to add publication\n"
              	+ "Press 3 to checkout book\n"
              	+ "Press 4 to return book\n"
              	+ "Press 5 to view member information\n"
		+ "Press 6 to view all books\n"
              	+ "Press 0 to quit\n"); //prompt
  	}//end prompt

	public void addMember(){
		String fname;
		String lname;
		Date birthday = null;
		int day;
		int mon;
		int year;
		boolean invalid = true;		

		input.nextLine();
		System.out.println("Please enter member information.\n");
		System.out.println("First Name: ");
		fname = input.nextLine();
		System.out.println("Last Name: ");
		lname = input.nextLine();
		
		while(invalid){
			System.out.println("Birthday month (MM) : ");
			mon = input.nextInt();
			if ((mon < 1) || (mon > 12)){
				System.out.println("Invalid month. Try again");	
				 
			}			
			else{

			System.out.println("Birthday day (DD) : ");
			day = input.nextInt();
			
			if (day < 1) { System.out.println("Invalid day. Try again");}
			else if((mon == 2) && (day > 28)) { System.out.println("Invalid day. Try again");}
			else if(((mon == 4) || (mon == 6) || (mon == 9) || (mon == 11)) && (day > 30))
				{ System.out.println("Invalid day. Try again");}
			else if( day > 31) { System.out.println("Invalid day. Try again");} 
			else{
				System.out.println("Birthday year (YYYY) : ");
				year = input.nextInt();
				if((year < 0000) || (year > 2014)) { System.out.println("Invalid day. Try again");}
				else{
					birthday = new Date(year, mon, day);
					invalid = false;
				}
			}	
			}
		}	
	
				


		Member newmemb = new Member(fname, lname, birthday, members.size());
		
		members.add(newmemb);

		System.out.println("New Member added.");	
		System.out.println("Member id: "+members.size());
	}
	public void addPublication(){

		String auth;
                String title;
		Date pub = null;
                int day;
                int mon;
                int year;
		boolean invalid = true;

                input.nextLine();
                System.out.println("Please enter book information.\n");
                System.out.println("Author: ");
                auth = input.nextLine();
                System.out.println("Title: ");
                title = input.nextLine();


		while(invalid){
                        System.out.println("Published month (MM) : ");
                        mon = input.nextInt();
                        if ((mon < 1) || (mon > 12)){
                                System.out.println("Invalid month. Try again");

                        }
                        else{

                        System.out.println("Published day (DD) : ");
                        day = input.nextInt();

                        if (day < 1) { System.out.println("Invalid day. Try again");}
                        else if((mon == 2) && (day > 28)) { System.out.println("Invalid day. Try again");}
                        else if(((mon == 4) || (mon == 6) || (mon == 9) || (mon == 11)) && (day > 30))
                                { System.out.println("Invalid day. Try again");}
                        else if( day > 31) { System.out.println("Invalid day. Try again");}
                        else{
                                System.out.println("Published year (YYYY) : ");
                                year = input.nextInt();
                                if((year < 0000) || (year > 2014)) { System.out.println("Invalid day. Try again");}
                                else{
                                        pub = new Date(year, mon, day);
                                        invalid = false;
                                }
                        }
                        }
                }


                Book newbook = new Book(auth, title, pub, books.size());

                books.add(newbook);

                System.out.println("New Book added.");
		System.out.println("Book id: "+books.size());





	}
	public void checkOut(){
		String in;
		int num;
		int mnum;

		System.out.println("Please enter member ID: ");
		mnum = input.nextInt();
		
		if((mnum < 0) || (mnum > members.size())){
                        System.out.println("Invalid member ID.");
			return;
                }


		input.nextLine();
		System.out.println("Do you know the book ID number? (y / n)");
		in = input.nextLine();

		if( in.equals("y")){
			System.out.println("Please enter book ID number: ");
			num = input.nextInt();
			
			if((num < 0) || (num > books.size())){
                	        System.out.println("Invalid book ID.");
                        	return;
                	}	

			for( Book book : books ){

				if( book.ID == num-1){
					if(book.available.equals(true)){
						members.get(mnum-1).checkedOut.add(book);	
						book.available = false;
						System.out.println("Book successfully checked out.");
					}
					else{
						System.out.println("Sorry. Book already checked out!");
					}
				}
 			}
		}

	}
	public void returnBook(){
		String in;
                int num;
                int mnum;

                System.out.println("Please enter member ID: ");
                mnum = input.nextInt();

		if((mnum < 0) || (mnum > members.size())){
                        System.out.println("Invalid member ID.");
                        return;
                }

		 input.nextLine();
                System.out.println("Please enter the book ID number: ");
                num = input.nextInt();
                
		if((num < 0) || (num > books.size())){
                        System.out.println("Invalid book ID.");
                        return;
                }


		for( Book book : books ){
                        	if( book.ID == num){
                                	members.get(mnum-1).checkedOut.remove(book);
                                       	book.available = true;
                                        }
                                }
	}
	public void viewMember(){
		int mnum;
		System.out.println("Please enter member ID: ");
		mnum = input.nextInt();


		if((mnum < 0) || (mnum > members.size())){
                        System.out.println("Invalid member ID.");
                        return;
                }

		System.out.println("\nMEMBER INFORMATION");
		System.out.println("Name: "+members.get(mnum-1).fname+" "
			+members.get(mnum-1).lname);

		System.out.println("ID: "+mnum);
		System.out.println("\nCHECKED OUT BOOKS");
		System.out.println(members.get(mnum-1).checkedOut.size()+" books checked out");
		for(Book book : members.get(mnum-1).checkedOut){
			System.out.println(book.title+" "+book.author);
		}


	}
	public void printAllBooks(){
		
		System.out.println("\nALL BOOKS IN LIBRARY");
		System.out.println("number of books: "+books.size());
		for( Book book : books) {
			System.out.println(book.title+" by "+book.author+"\tavailable: "+book.available);
		}
	}


}//end class

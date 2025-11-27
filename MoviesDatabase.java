package com.Task2;

import java.util.*;


//Main Class
public class MoviesDatabase {

 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     MovieService service = new MovieService();
     int choice = 0;

     while (choice != 4) {

         System.out.println("\n==== MOVIE DATABASE SYSTEM ====");
         System.out.println("1. Add Movie");
         System.out.println("2. Search Movie");
         System.out.println("3. Display All Movies");
         System.out.println("4. Exit");
         System.out.print("Enter your choice: ");

         // FIXED INPUT ISSUE
         if (!sc.hasNextInt()) {
             System.out.println("Invalid input! Enter a number.");
             sc.next(); // clear wrong input
             continue;
         }

         choice = sc.nextInt();
         sc.nextLine(); // clear buffer

         switch (choice) {

             case 1:
                 try {
                     System.out.print("Enter Movie ID: ");
                     int id = sc.nextInt();
                     sc.nextLine();

                     System.out.print("Enter Title: ");
                     String title = sc.nextLine();

                     System.out.print("Enter Genre: ");
                     String genre = sc.nextLine();

                     System.out.print("Enter Release Year: ");
                     int year = sc.nextInt();

                     System.out.print("Enter Rating (1–10): ");
                     double rating = sc.nextDouble();

                     service.addMovie(new Movie(id, title, genre, year, rating));

                 } catch (Exception e) {
                     System.out.println("Invalid input! Please enter numbers correctly.");
                     sc.nextLine(); // clear error
                 }
                 break;

             case 2:
                 System.out.print("Enter movie title to search: ");
                 String searchTitle = sc.nextLine();
                 Movie result = service.searchMovie(searchTitle);

                 if (result != null) {
                     System.out.println("\nMovie Found:");
                     System.out.println(result);
                 } else {
                     System.out.println("Movie not found!");
                 }
                 break;

             case 3:
                 service.displayMovies();
                 break;

             case 4:
                 System.out.println("Exiting Program...");
                 break;

             default:
                 System.out.println("Invalid Choice!");
         }
     }

     sc.close();
 }
}

//Movie Class
class Movie {
private int id;
private String title;
private String genre;
private int year;
private double rating;

public Movie(int id, String title, String genre, int year, double rating) {
   this.id = id;
   this.title = title;
   this.genre = genre;
   this.year = year;
   this.rating = rating;
}

public int getId() {
   return id;
}

public String getTitle() {
   return title;
}

@Override
public String toString() {
   return "\nMovie ID: " + id +
           "\nTitle: " + title +
           "\nGenre: " + genre +
           "\nRelease Year: " + year +
           "\nRating: " + rating + "/10";
}
}


//Service Class
class MovieService {
private List<Movie> movieList = new ArrayList<>();

public void addMovie(Movie movie) {
   movieList.add(movie);
   System.out.println("✔ Movie added successfully!");
}

public Movie searchMovie(String title) {
   for (Movie m : movieList) {
       if (m.getTitle().equalsIgnoreCase(title)) {
           return m;
       }
   }
   return null;
}

public void displayMovies() {
   if (movieList.isEmpty()) {
       System.out.println("No movies available!");
   } else {
       System.out.println("\n------- Movie List -------");
       for (Movie m : movieList) {
           System.out.println(m);
           System.out.println("--------------------------");
       }
   }
}
}



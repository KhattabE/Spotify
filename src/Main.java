import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();
        Scanner scanner = new Scanner(System.in);


        String databaseURL = ("jdbc:sqlite:C:/Users/Khatt/Desktop/Databases/spotify.db");
        DBConnection dbConnection = new DBConnection();
        dbConnection.connect(databaseURL);

        //1
        //dbConnection.atristWithLongestSong();

        //2
        //dbConnection.nameOfLongestSong();

        //3
        //dbConnection.amountOfAlbumsAtristHave();

        //5
        //dbConnection.topTenArtistSongs();

        //6
        //dbConnection.howManySecondsDoesAlbumLast();

        //7
        //dbConnection.searchedArtistMostPopulareAlbum();

        main.mainMenu(scanner, dbConnection);







    }



    public void welcomeMessage(Scanner scanner){
        System.out.println("----------------------------------------");
        System.out.println("Welcome to Spotify database ");
        System.out.print("Enter your name to enter: ");
        String userName = scanner.nextLine();
        System.out.println("Welcome " + userName + "!");
        System.out.println("----------------------------------------");


    }


    public void mainMenuOptions(){
        ArrayList<String> optionsMenu = new ArrayList<>();
        System.out.println("----------------------------------------");
        System.out.println("You got the following Options: ");
        optionsMenu.add("""
                1: Top Ten Artist Songs
                2: Name of longest song
                3: Amount of albums an artist have
                4: Exit
                """);
        System.out.println(optionsMenu.getFirst());




    }

    public void choice(Scanner scanner, DBConnection dbConnection){
        mainMenuOptions();
        int userChoice;

        while(true){
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();

            switch (userChoice){

                case 1 -> dbConnection.topTenArtistSongs();
                case 2 -> dbConnection.nameOfLongestSong();
                case 3 -> dbConnection.amountOfAlbumsAtristHave();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid option try again!");

            }

        }




    }

    public void mainMenu(Scanner scanner, DBConnection dbConnection){
        welcomeMessage(scanner);
        choice(scanner, dbConnection);




    }




}

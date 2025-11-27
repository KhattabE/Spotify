import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                4: Insert new artist and their song to the database
                5: Exit
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
                case 4 -> insertIntoDatabase(scanner, dbConnection);
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid option try again!");

            }

        }




    }

    public void mainMenu(Scanner scanner, DBConnection dbConnection){
        welcomeMessage(scanner);
        choice(scanner, dbConnection);

    }


    public void insertIntoDatabase(Scanner scanner, DBConnection dbConnection){

        scanner.nextLine();

        System.out.print("Enter Artist Name: ");
        String newArtist = scanner.nextLine();

        System.out.print("Enter their track: ");
        String newSong = scanner.nextLine();

        //The '?' is just a place holder, since i am using preparedStatements
        String sql = """
            INSERT INTO song (track_artist, track_name)
            VALUES (?, ?);
            """;

        try {

            //What this does is that it lets me insert data safely into the sql query
            PreparedStatement pstmt = dbConnection.connection.prepareStatement(sql);

            //I insert the variables into the sql here
            pstmt.setString(1, newArtist);
            pstmt.setString(2, newSong);

            //And here i execute the insert
            pstmt.executeUpdate();

            //Just feedback to check if it is working
            System.out.println("Song and artist has been added successfully!");

        } catch (SQLException sqe) {
            System.out.println("An error has occurred");
        }
    }



}









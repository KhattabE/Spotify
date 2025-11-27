//Represents a link between your Java program and a database.
import java.sql.*;

public class DBConnection {


    //This object is the connection that is needed to connect to the database
    Connection connection;

    //The method to be able to connect to the database etc
    public void connect(String databaseURL){
        try {
            //DriveManger purpose is to manage database drivers and give you a Connection when you ask for it.
            //"It creates the Connection for you"
            connection = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            System.out.println("Could not connect to the database!");
        }
    }


    //A method to get the artist name with the longest song
    public void atristWithLongestSong(){
        //The sql command that we will send to the database
        String sql = """
               SELECT  song.track_artist FROM song
               ORDER BY duration_ms DESC
               LIMIT 1;
               """;
        try{
            //Statement is an object you use to send SQL commands to the database.
            //A Statement lets you run SQL queries.
            Statement stmt = connection.createStatement();

            //ResultSet is the result of a SELECT query.
            //The database sends back a table of data, and Java stores that table in a ResultSet object
            ResultSet set = stmt.executeQuery(sql);

            //A while loop to loop through the ResultSet of the data that has been retrived from the database
            while(set.next()){
                System.out.println(set.getString("track_artist"));
            }


        //To catch exceptions
        }catch (SQLException se){
            System.out.println("An error has occurred");
        }
    }


    //A method to get the artist name and the longest song they have
    public void nameOfLongestSong(){

        //The sql command that i will send to the database
        String sql = """
                    SELECT song.track_name, duration_ms FROM song
                    ORDER BY duration_ms DESC
                    LIMIT 1;
                """;

        try{
            //Statement lets you run SQL queries
            //Statement is needed to send SQL commands to the database
            Statement stmt = connection.createStatement();

            // executeQuery(sql) sends the SQL command to the database.
            // The database returns a table of results.
            // That table is stored inside a ResultSet object called 'set'.
            ResultSet set = stmt.executeQuery(sql);

            //A while loop to loop through the ResultSet of the data that has been retrived from the database
            while(set.next()){
                //the getString is to get the string value from the ResultSet (which the set retrived from the database)
                String trackName = set.getString("track_name");
                //the getInt is to get the string integer from the ResultSet (which the set retrived from the database)
                int durationMS = set.getInt("duration_ms");

                System.out.println(trackName + " - " + "(" + durationMS + ")");

            }

        }catch (SQLException se){
            System.out.println("An error has occurred");
        }

    }


    public void amountOfAlbumsAtristHave(){
        String sql = """
                SELECT song.track_artist, 
                COUNT(DISTINCT song_album.track_album_id) AS albumCount
                FROM song
                JOIN song_album ON song.track_id = song_album.track_id
                GROUP BY song.track_artist;
                """;

            try{
                Statement stmt = connection.createStatement();
                ResultSet set = stmt.executeQuery(sql);

                while(set.next()){
                    String trackArtist = set.getString("track_artist");
                    int albumCount = set.getInt("albumCount");


                    System.out.println("Artist: " + trackArtist + " - " + "Album ID: " +  albumCount  );
                }

            }catch (SQLException e){
                System.out.println("Error has occurd");
            }
    }

    public void topTenArtistSongs(){
        String sql = """
                    SELECT song.track_artist, song.track_name, song.track_popularity FROM song
                    LIMIT 10;
                """;

        try{
            Statement stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery(sql);

            while (set.next()){
                String trackArtist = set.getString("track_artist");
                String trackName = set.getString("track_name");
                int trackPopularity = set.getInt("track_popularity");

                System.out.println("Artist: " + trackArtist + " - " + "Track name: " + trackName + " - " + "(Track popularity: " + trackPopularity + ")" );

            }


        }catch (SQLException sqe){
            System.out.println("Error has occurd");
        }
    }

    public void howManySecondsDoesAlbumLast(){
        String sql = """
                SELECT album.track_album_name, song.duration_ms
                FROM song
                         JOIN song_album ON song.track_id = song_album.track_id
                         JOIN album ON album.track_album_id = song_album.track_album_id;
                """;
        try{
            Statement stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery(sql);

            while(set.next()){
                String albumName = set.getString("track_album_name");
                int durationMS = set.getInt("duration_ms");

                System.out.println(albumName + " - " + durationMS);

            }

        }catch (SQLException sqe){
            System.out.println("Error occurred");
        }

    }


    public void searchedArtistMostPopulareAlbum(){
        String sql = """
                SELECT song.track_artist, album.track_album_name
                    FROM song
                        JOIN song_album ON song.track_id = song_album.track_id
                        JOIN album ON album.track_album_id = song_album.track_album_id
                        WHERE track_artist = 'Ed Sheeran'
                    ORDER BY track_popularity DESC
                LIMIT 1;
                """;
        try {
                Statement stmt = connection.createStatement();
                ResultSet set = stmt.executeQuery(sql);

                while (set.next()){
                    String trackAtrist = set.getString("track_artist");
                    String albumName = set.getString("track_album_name");

                    System.out.println(trackAtrist + " - " + albumName);
                }

        }catch (SQLException sqe){
            System.out.println("Error har occurred");
        }

    }





}

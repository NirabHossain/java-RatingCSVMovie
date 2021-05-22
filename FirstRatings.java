import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class FirstRatings {

    public ArrayList<Rater> loadRater(String fname){
        ArrayList<Rater>raters = new ArrayList<Rater>();
       
        FileResource fr= new FileResource("data/"+fname+".csv");
        for(CSVRecord rec: fr.getCSVParser()){
            Rater rater= new Rater(rec.get("rater_id"));
            raters.add(rater);
            rater.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
        }
        return raters;
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> raters= new ArrayList<Rater>();
        raters=loadRater("ratings_short");
        for(Rater rater: raters){
            System.out.println(rater.getID()+rater.getRating(rater.getID()));
        }
    }
    
    public ArrayList<Movie> loadMovies(String fname){
        ArrayList<Movie>movies = new ArrayList<Movie>();
        FileResource fr= new FileResource("data/"+fname+".csv");
        for(CSVRecord rec: fr.getCSVParser()){
            Movie movie= new Movie(rec.get("id"), rec.get("title"), rec.get("year"),
                rec.get("genre"),rec.get("director"),rec.get("country"),
                rec.get("poster"),Integer.parseInt(rec.get("minutes")));
            movies.add(movie);
        }
        return movies;
    }
    
    public void countDirectorMap(ArrayList<Movie> movies){
    	HashMap<String,Integer> map = new HashMap<String,Integer>();
    
    	for(Movie movie : movies){
    		String w = movie.getDirector();
    		if (!map.containsKey(w))map.put(w,1);
    		else map.put(w,map.get(w)+1);
    	}
    	int total = 0;
    	for(String w : map.keySet()){
    		int value = map.get(w);
    		if (value > 10)System.out.println(value+"\t"+w);    
    		total += value;
    	}
    	//System.out.println("total count: "+total+" different = "+map.keySet().size());
    }
    
    public void tester(){
        ArrayList<Movie> movies;
        movies=loadMovies("ratedmoviesfull");
        int gen=0,gret=0;
        for(Movie movie: movies){
            //System.out.println(movie);
            if(movie.getGenres().contains("Comedy"))gen++;
            if(movie.getMinutes()>150)gret++;
        }
        
        
        System.out.println("Total movies: "+movies.size()+"\nComedy Genre movies: "+
            gen+"\nGreater 150: "+gret);
//        int index = findMax();
//        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
        countDirectorMap(movies);

    }



}

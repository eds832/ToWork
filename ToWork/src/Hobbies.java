import java.util.*;
import java.util.stream.Collectors;

public class Hobbies {
    
    private final HashMap<String, String[]> hobbies = new HashMap<String, String[]>();
    
    public void add(String hobbyist, String... hobbies) {
        this.hobbies.put(hobbyist, hobbies);
    }
    
    public List<String> findHobbyists(String hobby) {
	 List<String> list = hobbies.entrySet().stream().filter( personRecord -> {
		 boolean isMatch = false;
	   for(String personHobby : personRecord.getValue()) {		
		if(personHobby.equals( hobby )) {
			isMatch = true;
		}
	   }
	   return isMatch;
	 }).map(Map.Entry<String, String[]>::getKey).collect( Collectors.toList());
	 return list;
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }
    
    public static void main(String[] args) {
        Hobbies hobbies = new Hobbies();
        hobbies.add("John", "Piano", "Puzzles", "Yoga");
        hobbies.add("Adam", "Drama", "Fashion", "Pets");
        hobbies.add("Mary", "Magic", "Pets", "Reading");
        
        System.out.println(Arrays.toString(hobbies.findHobbyists("Yoga").toArray()));
    }
}

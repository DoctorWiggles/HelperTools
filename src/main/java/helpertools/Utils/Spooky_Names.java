package helpertools.Utils;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;


public class Spooky_Names {
	
	/** Note - I just grabbed random slavic names
	 * I have no idea if these mean something offensive :^)
	 */
	static Set<String> Random_Names = Sets.newHashSet(new String[] {
			"Danica","Lazlo","Nicholai","Tierney",
			"Ivah","Kasmira","Milos","Nadege","Pyotr",
			"Vallen","Wendelin","Rorik","Miri","Bogdan",
			"Dalibor","Dmitrei","Georgei","Milogost","Nikola",
			"Radomil","Svetopolk","Anatol","Boris","Florian"
	});	
	
	public static String Get_Name(){
		
		String name = getRandomObject(Random_Names);
		return name;
		
	}

	private static String getRandomObject(Collection from) {
		Random rnd = new Random();
		int i = rnd.nextInt(from.size());
		return (String) from.toArray()[i];
	}


}

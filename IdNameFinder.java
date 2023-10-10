import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
public class IdNameFinder {
    public static void main(String[] args) throws Exception {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter email ID: ");
        String line = inputReader.readLine();

        URL ecsURL = new URL("https://www.ecs.soton.ac.uk/people/" + line);
        BufferedReader webReader = new BufferedReader(new InputStreamReader(ecsURL.openStream()));
        while((line = webReader.readLine()) != null) {
            if(line.contains("<title>")) {
                String name = line.substring(line.indexOf("<title>") + 7, line.indexOf("|"));
                System.out.println(name);
                break;
            }
        }
    }
}
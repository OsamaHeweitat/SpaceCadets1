import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
public class IdNameFinder {
    public static void main(String[] args) throws Exception {
        IdNameFinder nameFinder = new IdNameFinder();
        while(true){
            String name = nameFinder.findName();
            if(name != null){
                System.out.println("Name: " + name);
            } else {
                System.out.println("Invalid input.");
            }
            System.out.println("Would you like to try again? (y/n)");
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            inputLoop: while(true){
                String answer = inputReader.readLine();
                switch(Character.toLowerCase(answer.toCharArray()[0])){
                    case 'n':
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break inputLoop;
                    case 'y':
                        break inputLoop;
                    default:
                        System.out.println("Invalid answer. Would you like to try again? (y/n)");
                }
            }
        }
    }

    private String findName() throws Exception{
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter email ID: ");
        String line = inputReader.readLine();
        line = line.replaceAll("\\s", "");

        URL ecsURL = new URL("https://www.ecs.soton.ac.uk/people/" + line);
        BufferedReader webReader = new BufferedReader(new InputStreamReader(ecsURL.openStream()));

        while((line = webReader.readLine()) != null) {
            if(line.contains("<title>")) {
                String name = line.substring(line.indexOf("<title>") + 7, line.indexOf("|"));
                if(name.contains("People")){
                    System.out.println("No user found with such ID, would you like to try again? (y/n)");
                    String answer = inputReader.readLine();
                    switch(answer.toLowerCase()){
                        case "y":
                            findName();
                            break;
                        case "n":
                            System.out.println("Goodbye!");
                            System.exit(0);
                    }
                    return null;
                }
                else{
                    return name;
                }
            }
        }
        return null;
    }
}
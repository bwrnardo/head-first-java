package chapter_06;

import java.util.ArrayList;

public class StartupBust {
    // Declare and initialize the variables we need
    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<Startup>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        // first make some Startups and give them locations
        // Make three startup objects, give 'em names, and stick 'em in the ArrayList
        Startup one = new Startup();
        one.setName("poniez");
        Startup two = new Startup();
        two.setName("hacqi");
        Startup three = new Startup();
        three.setName("cabista");
        startups.add(one);
        startups.add(two);
        startups.add(three);

        // Print brief instructions for user
        System.out.println("Your goal is to sink three Startups.");
        System.out.println("poniez, hacqi, cabista");
        System.out.println("Try to sink them all in the fewest number of guesses");

        // Repeat with each startup in the list
        for (Startup startup : startups) {
            // Ask the helper for a startup location
            ArrayList<String> newLocation = helper.placeStartup(3);
            // Call the setter method on this startup to give it the location
            startup.setLocationCells(newLocation);
        } // close for loop
    } // close setUpGame method

    private void startPlaying() {
        // as long as the startup is NOT empty
        while (!startups.isEmpty()) {
            // get user input
            String userGuess = helper.getUserInput("Enter a guess");
            // call our own checkUserGuess method
            checkUserGuess(userGuess);
        } // close while
        // call our own finishGame method
        finishGame();
    } // close startPlaying method

    private void checkUserGuess(String userGuess) {
        // increment the number of guesses the user has made
        numOfGuesses++;
        // assume its a miss, unless told otherwise
        String result = "miss";

        // repeat with all startups in the list
        for (Startup startupToTest : startups) {
            // ask the startup to check the user guess, looking for a hit (or kilL)
            result = startupToTest.checkYourself(userGuess);

            if (result.equals("hit")) {
                // get out of the loop early, no point in testing the others
                break;
            }
            if (result.equals("kill")) {
                // this one is dead, so take it out of the startups list
                startups.remove(startupToTest);
                break;
            }
        } // close for

        // print the result for the user
        System.out.println(result);
    } // close method

    private void finishGame() {
        // print a message telling the user how they did in the game
        System.out.println("All Startups are dead! Your stock is now worthless");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    } // close method

    public static void main(String[] args) {
        // create the game object
        StartupBust game = new StartupBust();
        // tell the game object to set up the game
        game.setUpGame();
        // tell the game object to start the main game play loop
        game.startPlaying();
    } // close method
}
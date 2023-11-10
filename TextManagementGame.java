import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * The TextManagementGame class represents a text-based management game where the player manages resources and resource generators.
 */
public class TextManagementGame {
    // Define game variables
    private int round;
    private ArrayList<Resource> resources = new ArrayList<>();
    private ArrayList<Generator> generators = new ArrayList<>();

    // Define a Scanner for user input
    private Scanner scanner;

    /**
     * Creates a new TextManagementGame instance with initial resource and time values.
     */
    public TextManagementGame() {
        round = 1;       // Start at time 1
        scanner = new Scanner(System.in);

        // TODO: Add logic to initialize starting resources
        Resource wood = new WoodResource();
        Resource metal = new MetalResource();
        Resource energy = new EnergyResource();

        wood.add(10);    // Starting quantity of wood
        metal.add(10);    // Starting quantity of metal
        energy.add(20);   // Starting quantity of energy

        resources.add(wood);
        resources.add(metal);
        resources.add(energy);

        // Initialize starting generators
        ArrayList<Resource> generator1Cost = new ArrayList<>();
        generator1Cost.add(wood);
        Generator generator1 = new Generator("Wood Generator", generator1Cost, 3, 0, wood);

        ArrayList<Resource> generator2Cost = new ArrayList<>();
        generator2Cost.add(metal);
        generator2Cost.add(energy);
        Generator generator2 = new Generator("Metal-Energy Generator", generator2Cost, 2, 0, metal);

        ArrayList<Resource> generator3Cost = new ArrayList<>();
        generator3Cost.add(wood);
        generator3Cost.add(metal);
        generator3Cost.add(energy);
        Generator generator3 = new Generator("Resource Mixer", generator3Cost, 5, 0, energy);

        generators.add(generator1);
        generators.add(generator2);
        generators.add(generator3);

    }

    /**
     * Check if a method should run with a 1 in number chance.
     *
     * @param number the chance number
     * @return true if the method should run, false otherwise
     */
    public boolean haveEventThisTurn(int number) {
        Random random = new Random();
        int chance = random.nextInt(number); // Generates a random number between 0 (inclusive) and number (exclusive)
        return chance == 0; // Returns true with a 1 in number chance
    }

    /**
     * Prints the list of resources
     */
    public void viewResources() {
        for (Resource r : resources) {
            System.out.println(r);
        }
    }

    /**
     * Prints the list of Generators
     */
    public void viewGenerators() {
        for (Generator b : generators) {
            System.out.println(b);
        }
    }

    /**
     * Checks if a Generator can be constructed and then adds it to the list of Generators.
     */
    public void constructGenerator() {
        System.out.println("Available Generators:");
        for (Generator generator : generators) {
            System.out.println("- " + generator.getName());
        }
        scanner.nextLine(); // Consume the newline character
        while (true) {
            System.out.print("Choose a generator to construct: ");
            String chosenGenerator = scanner.nextLine().trim(); // Use nextLine to capture the entire line with spaces and trim leading/trailing spaces
    
            for (Generator generator : generators) {
                if (generator.getName().equalsIgnoreCase(chosenGenerator)) {
                    // Check if the construction cost can be met
                    boolean canConstruct = true;
                    for (Resource cost : generator.getConstructionCost()) {
                        if (cost.getQuantity() < 1) {
                            System.out.println("Not enough " + cost.getName() + " to construct " + generator.getName());
                            canConstruct = false;
                            break;
                        }
                    }
    
                    // If construction is possible, deduct the construction cost and add the generator
                    if (canConstruct) {
                        for (Resource cost : generator.getConstructionCost()) {
                            cost.consume(1);
                        }
                        addGenerator(generator);
                        System.out.println(generator.getName() + " constructed!");
                    }
    
                    return;
                }
            }
    
            System.out.println("Invalid generator choice. Please try again.");
        }
    }
    

    /**
     * Increments the time counter and then adds more resources based on what generators are present.
     */
    public void endRound() {
        round++;

        // Generate resources based on what generators are present
        for (Generator generator : generators) {
            Resource product = generator.getProduct();
            int productionRate = generator.getResourceProductionRate() * generator.getNumberConstructed();
            product.add(productionRate);
        }
    }

    /**
     * Adds a Generator object to the ArrayList of Generators.
     *
     * @param generator the Generator object to add
     */
    public void addGenerator(Generator generator) {
        generators.add(generator);
    }

    /**
     * Adds a Resource object to the ArrayList of resources.
     *
     * @param resource the Resource object to add
     */
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    /**
     * Checks if we are out of any critical resources
     *
     * @return true if we are out of any critical resources, false otherwise
     */
    public boolean isCriticalResourceEmpty() {
        for (Resource r : resources) {
            if (r.isCritical() && r.getQuantity() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles a random event.
     */
    private void handleRandomEvent() {
        Random random = new Random();
        int eventType = random.nextInt(3); // Assuming 3 types of random events

        switch (eventType) {
            case 0:
                handleBeneficialEvent();
                break;

            case 1:
                handleChallengingEvent();
                break;

            case 2:
                handleNeutralEvent();
                break;

            default:
                System.out.println("Unexpected event type. Something went wrong.");
        }
    }

    // Helper method to handle a beneficial event
    private void handleBeneficialEvent() {
        System.out.println("A beneficial event happened!");
        // Example: Bonus resources
        Resource bonusResource = getRandomResource();
        bonusResource.add(5);  // Add 5 units of a random resource
        System.out.println("You gained 5 units of " + bonusResource.getName() + "!");
    }

    // Helper method to handle a challenging event
    private void handleChallengingEvent() {
        System.out.println("A challenging event happened!");
        // Example: Resource depletion
        Random random = new Random();
        Resource depletingResource = getRandomResource();
        int depletionAmount = random.nextInt(5) + 1;  // Random depletion between 1 and 5 units
        depletingResource.consume(depletionAmount);
        System.out.println("You lost " + depletionAmount + " units of " + depletingResource.getName() + "!");
    }

    // Helper method to handle a neutral event
    private void handleNeutralEvent() {
        System.out.println("A neutral event happened!");
        // Example: No impact on resources
        System.out.println("Nothing significant happened. Your resources remain unchanged.");
    }

    // Helper method to get a random resource from the list
    private Resource getRandomResource() {
        Random random = new Random();
        int randomIndex = random.nextInt(resources.size());
        return resources.get(randomIndex);
    }
    /**
     * Starts the game and manages the game loop.
     */
    public void start() {
        System.out.println("Welcome to the Resource Management Game!"); // TODO: Change Text
        int oddsOfRandomEvent = 4; // a 25% chance of a random event occurring

        // Main game loop
        while (!isCriticalResourceEmpty()) {
            System.out.println("\nRound " + round);
            if (haveEventThisTurn(oddsOfRandomEvent)) {
                handleRandomEvent();
            }
            System.out.println("Options:");
            System.out.println("1. Collect resources");
            System.out.println("2. Manage resources");
            System.out.println("3. Add a new Generator");
            System.out.println("4. End round");
            System.out.println("5. Quit game");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewResources();
                    break;
                case 2:
                    viewGenerators();
                    break;
                case 3:
                    constructGenerator();
                    break;
                case 4:
                    endRound();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Game Over! You ran out of resources.");
        System.out.println("You played for " + round + " rounds");
    }

    /**
     * Main method to run the game
     *
     * @param args the command-line arguments (not used in this game)
     */
    public static void main(String[] args) {
        TextManagementGame game = new TextManagementGame();
        game.start();
    }
}


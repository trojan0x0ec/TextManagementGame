import java.util.ArrayList;

/**
 * The Generator class represents a generic resource generating item in the game.
 * Generators have a name, a construction cost, and a resource production rate.
 */
public class Generator implements Comparable<Generator> {
    private String name;
    private ArrayList<Resource> constructionCost;
    private int resourceProductionRate;
    private int numberConstructed;
    private Resource product;

    /**
     * Creates a new Generator with the given name, construction cost, and resource production rate.
     *
     * @param name                  the name of the Generator
     * @param constructionCost      the cost in resources required to construct the Generator
     * @param resourceProductionRate the rate at which the Generator produces resources per unit of time
     * @param numberConstructed     the number of units of this generator constructed at this time
     * @param product               the type of resource this generator produces
     */
    public Generator(String name, ArrayList<Resource> constructionCost, int resourceProductionRate, int numberConstructed, Resource product) {
        this.name = name;
        this.constructionCost = constructionCost;
        this.resourceProductionRate = resourceProductionRate;
        this.numberConstructed = numberConstructed;
        this.product = product;
    }

    @Override
public String toString() {
    return name + " - Cost: " + constructionCost + ", Production Rate: " + resourceProductionRate + ", Number Constructed: " + numberConstructed;
}

    /**
     * Gets the name of the Generator.
     *
     * @return the name of the Generator
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the construction cost of the Generator.
     *
     * @return the construction cost of the Generator
     */
    public ArrayList<Resource> getConstructionCost() {
        return constructionCost;
    }

    /**
     * Gets the resource production rate of the Generator.
     *
     * @return the resource production rate of the Generator
     */
    public int getResourceProductionRate() {
        return resourceProductionRate;
    }

    /**
     * Gets the number of units constructed of this Generator.
     *
     * @return the number of units constructed of the generator
     */
    public int getNumberConstructed() {
        return numberConstructed;
    }

    /**
     * Gets the product (resource) produced by this generator.
     *
     * @return the product resource
     */
    public Resource getProduct() {
        return product;
    }

    /**
     * Implements the compareTo method from the Comparable interface.
     * Compares two generators based on their names.
     *
     * @param other the other Generator to compare to
     * @return a negative integer, zero, or a positive integer as this generator is less than, equal to, or greater than the specified generator.
     */
    @Override
    public int compareTo(Generator other) {
        return this.name.compareTo(other.getName());
    }

    /**
     * Calculates the impact score of the generator based on its production rate and the number of units constructed.
     *
     * @return the impact score
     */
    public int scoreImpact() {
        return getResourceProductionRate() * getNumberConstructed();
    }

    /**
     * Constructs one unit of the generator by deducting the construction cost.
     */
    public void construct() {
        for (Resource cost : constructionCost) {
            cost.consume(1);
        }
        numberConstructed++;
    }
}

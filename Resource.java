/**
 * The Resource class represents a generic resource in the game.
 * Resources have a name, a quantity, and a status of critical or not critical.
 */
public class Resource {
    private String name;
    private int quantity;
    private boolean isCritical;

@Override
public String toString() {
    return name + ": " + quantity;
}

    /**
     * Creates a new Resource with the given name and initializes the quantity to 0.
     *
     * @param name the name of the resource
     */
    public Resource(String name) {
        this.name = name;
        this.quantity = 0;
        this.isCritical = false;
    }

    /**
     * Gets the name of the resource.
     *
     * @return the name of the resource
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the resource.
     *
     * @return the quantity of the resource
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Reports if a resource is critical. If a resource is critical, reaching 0 ends the game.
     *
     * @return if the resource is critical
     */
    public boolean isCritical() {
        return isCritical;
    }

    /**
     * Sets if a given resource is critical.
     *
     * @param isCritical boolean value for isCritical
     */
    public void setIsCritical(boolean isCritical) {
        this.isCritical = isCritical;
    }

    /**
     * Adds the specified amount to the quantity of the resource.
     *
     * @param amount the amount to add
     */
    public void add(int amount) {
        quantity += amount;
    }

    /**
     * Consumes the specified amount of the resource if available. Sets the resource to 0 if there is not enough to consume.
     *
     * @param amount the amount to consume
     */
    public void consume(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            quantity = 0;
            System.out.println("Not enough " + name + " to consume.");
        }
    }

    /**
     * Implements the scoreImpact method from the Score interface.
     * Returns the impact of this resource on the game's overall score.
     *
     * @return the impact of this resource on the score
     */
    
    public int scoreImpact() {
        // For simplicity, the impact is the current quantity of the resource
        return quantity;
    }
}

/**
 * Represents a specific type of resource: WoodResource.
 */
class WoodResource extends Resource {
    public WoodResource() {
        super("Wood");
    }
}

/**
 * Represents a specific type of resource: MetalResource.
 */
class MetalResource extends Resource {
    public MetalResource() {
        super("Metal");
    }
}

/**
 * Represents a specific type of resource: EnergyResource.
 */
class EnergyResource extends Resource {
    public EnergyResource() {
        super("Energy");
    }
}

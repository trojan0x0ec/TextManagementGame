import java.util.ArrayList;

public class Event {
    private String name;
    private ArrayList<Resource> effectOnResources;

    /**
     * Creates a new Event with the given name and effect on resources
     *
     * @param name the name of the event
     * @param effectOnResources the effect of the event on the player's resources
     */
    public Event(String name, ArrayList<Resource> effectOnResources) {
        this.name = name;
        this.effectOnResources = effectOnResources;
    }

    /**
     * Gets the name of the event.
     *
     * @return the name of the event
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the effect of the event on the player's resources.
     *
     * @return the effect of the event on the player's resources
     */
    public ArrayList<Resource> getEffectOnResources() {
        return effectOnResources;
    }

    /**
     * Applies the event's effect on the player's resources.
     */
    public void applyEffect() {
        for (Resource effect : effectOnResources) {
            effect.add(effect.getQuantity());
        }
    }

    /**
     * Implements the scoreImpact method from the Score interface.
     * Returns the impact of this event on the game's overall score.
     *
     * @return the impact of this event on the score
     */
   
    public int scoreImpact() {
        // Calculate the impact based on the affected resources
        int impact = 0;
        for (Resource resource : effectOnResources) {
            impact += resource.scoreImpact();
        }
        return impact;
    }
}

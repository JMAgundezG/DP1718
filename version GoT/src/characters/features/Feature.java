package characters.features;

import characters.Characters;

/**
 * Created by spassky on 7/02/17.
 */
public abstract class Feature {
    /**
     * The character that will have the feature
     */
    private Characters featured;

    public Feature(Characters character){
        this.featured = character;
    }

    public Characters getFeatured() {
        return featured;
    }

    /**
     * Makes the feature's action
     */
    public abstract void action();

    /**
     *
     * @return shows the feature's information
     */
    public abstract String showInfo();

}

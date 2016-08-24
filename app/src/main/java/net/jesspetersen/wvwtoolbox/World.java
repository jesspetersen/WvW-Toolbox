package net.jesspetersen.wvwtoolbox;

/**
 * Created by jessi on 7/07/2016.
 */
public class World {

    private String id;
    private String name;
    private String population;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public World(){

    }

    public World(String id, String name, String population)
    {
        this.id = id;
        this.name = name;
        this.population = population;
    }
}

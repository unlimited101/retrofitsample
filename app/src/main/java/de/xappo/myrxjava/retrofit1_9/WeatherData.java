package de.xappo.myrxjava.retrofit1_9;

/**
 * Created by knoppik on 21.02.16.
 */
public class WeatherData {
    private int cod;
    private String base;
    private String name;

    public WeatherData() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

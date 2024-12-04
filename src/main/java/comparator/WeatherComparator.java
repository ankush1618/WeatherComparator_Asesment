package comparator;

public class WeatherComparator {
    public void compareTemperatures(double tempUI, double tempAPI, double variance) {
        if (Math.abs(tempUI - tempAPI) > variance) {
            throw new RuntimeException(
                    String.format("Temperature variance exceeded! UI: %.2f, API: %.2f, Variance: %.2f", tempUI, tempAPI, variance));
        }
        System.out.println("Temperature comparison passed. UI: " + tempUI + ", API: " + tempAPI);
    }
}

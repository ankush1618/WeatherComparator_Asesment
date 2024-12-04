# Weather Comparator Automation Framework

## Overview
This project compares weather data between AccuWeather (UI) and OpenWeatherMap (API). It uses Log4j 2 for logging and Extent Reports for reporting.
This complete framework validates and compares weather data across two sources, includes variance logic, and supports CI/CD integration via Jenkins.
This framework integrates Log4j 2 for detailed logging and Extent Reports for professional test reporting. The logs and reports are essential for debugging and analysis, making the framework robust and user-friendly.
## Setup Instructions
1. Clone this repository.
2. Install Maven and JDK 8 or later.
3. Update the `path/to/chromedriver` in `AccuWeatherUI.java`.
4. Install Chrome Browser.

## Execution
### Run from Command Line:
```bash
mvn clean test

# Web Scraper Automation Suite

## OverView:
This project involves creating a mini scraper to scrape film and sports information from a given website

![1000049924](https://github.com/user-attachments/assets/4099553d-f352-4610-a4e4-f1ace39ee8b5)



## Scope of Work

### Automated the following Test Cases:

#### TestCase01:

1. Go to this website and click on "Hockey Teams: Forms, Searching and Pagination"
2. Iterate through the table and collect the Team Name, Year and Win % for the teams with Win % less than 40% (0.40)
3. Iterate through 4 pages of this data and store it in a ArrayList
4. Convert the ArrayList object to a JSON file named hockey-team-data.json. Feel free to use Jackson library. (In the example, Maven is used, but you can resolve dependencies similarly by copying Gradle import from Maven Central). Each Hashmap object should contain:
    1. Epoch Time of Scrape
    2. Team Name
    3. Year
    4. Win %


#### TestCase02:

1. Go to this website and click on "Oscar Winning Films"
2. Click on each year present on the screen and find the top 5 movies on the list - store in an ArrayList.
3. Keep a Boolean variable "isWinner" which will be true only for the best picture winner of that year.
4. Keep a variable to maintain the year from which the data is scraped
5. Convert the ArrayList object to a JSON file named oscar-winner-data.json. Each HashMap object should contain:
    1. Epoch Time of Scrape
    2. Year
    3. Title
    4. Nomination
    5. Awards
    6. isWinner
6. Store the file in the output folder in the root directory. Assert using TestNG that the file is present and not empty

## Tech Stack
- **Programming Language:** Java
- **Build Tool:** Gradle
- **Testing FrameWork:** TestNG
- **Automation Tool:** Selenium WebDriver
- **Assertions:** HardAssert (TestNG)
- **Waits:** WebDriverWait
- **Design Pattern:** Page Object(POM)
- **Report:** Extent Report

## Features
- POM-based structure for better maintenance
- WebDriverWait for handling dynamic elements
- JackSon library for converting list into JSON 

## Future Enhancements
- Integrate Jenkins for CI/CD
- Deploying tests in various machines Using Docker with help of Jenkins Agent

## How To Run

### 1. Clone the Repository
    git clone https://github.com/Vignesh1234567891010/-saravana2018asd-ME_QA_XSCRAPE_DATA.git

### 2. Change to directory
    cd saravana2018asd-ME_QA_XYOUTUBE_SEARCH

### 3. Run Build command to Build the dependencies
```bash
    ./gradlew build   
   ```

### 4. Run Test
```bash 
   ./gradlew test 

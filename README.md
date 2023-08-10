# Introduction
Auto Tests project for Red Flag Security Transit Management Platform. The project contains E2E tests

Autotests are run on the staging1 environment - https://staging1.stm.redflag.cc/#/login


# Installation
Navigate to the desired directory in the terminal and clone the project using


`git clone https://gitlab.com/rfccs/stm/auto-tests.git`

or download the archive from gitlab

![img.png](img.png)
# Usage
## Tests running
### Tests running with terminal
To run all the tests, use the command

`mvn test`

To run a single test class use

`mvn -Dtest=CompanyPageTest test`

To run a multiple test classes use

`mvn -Dtest=CompanyPageTest,PurchaseOrderPageTest test`

To run a single method use. Before the hash sign is the name of the class, after the hash sign is the name of the method

`mvn -Dtest=PurchaseOrderCreatingTest#createPurchaseOrderWithSpecifiedBuyer test`

### Tests running with IDEA
To run all the tests, use test step in the maven lifecycle

![img_1.png](img_1.png)

To run a single test class use Run test button

![img_2.png](img_2.png)

To run a single method use Run test button

![img_3.png](img_3.png)

## Dependencies 

All dependencies are in the pom file in the dependencies block. When adding a new dependency, specify the version of the library or framework in a separate properties block
## Results

The results are located in the autotests/target/surefire-reports folder. You can also generate a report using allure. To do this, go to the project directory and use the command

`allure serve target/surefire-reports/`


## Creating new tests

### Packages
The project uses the Page object design pattern. Therefore, the description of each page with locators and methods of interacting with them are located in the src/main/java/PageObject folder. The PageObject folder is divided into the corresponding system sets. There are set pages in each set

The tests are located in the src/test/java folder. This folder is also divided into sets and each set contains tests corresponding to the set
### Constants

All constants are located in the src/main/java/constants folder.

- EndPoints folder - contains endpoints
- Entities folder - contains common constants
- Json folder - contains the body of small requests
- URLs folder - contains links

### Test creating
To create a test, you need to
1. Create a page using the page object pattern
2. Make sure that the created page is in the right folder of the set
3. Make sure that only the locators and methods necessary for tests are located on the page object page
4. Make sure that the tests being created are in the correct folder of the corresponding set
5. Inherit the Base test

---
Read more about git flow, libraries and frameworks, approaches to writing code, checklists, Jenkins at [this link](https://redflag.atlassian.net/wiki/spaces/RFS/pages/49545217/2022-12-05+Autotest+project+kick-off+roadmap)


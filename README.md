# LeaderShip App
## Introduction:
As e-commerce has become one of the inevitable part of our day-to-day life, it's time to make it more interactive with the users. Promotions are so boring that, they do not match what ever I am purchasing regularly. It would be a lot better when I get promotions on what I buy regularly in a more personalized way that I get more enthused to purchased the same product from the same store. Or tryout a new brand of the product that I used to buy but from a different brand.
# Solution:
With problem being stated we propose an interactive e-commerce web application that has an option to make a purchase online. Based on what the customer purchases, the customer will be placed on a level in the leadership board( based on the type of product that has been purchased). (e.g. When a customer purchases vegetable oils[from one brand each month] regularly from the system, he/she will attain a particular level in the leadership board of those who purchase vegetable oils[irrespective of brand](internally he/she will also have a level associated in the subsequent top level category[say food/grocery, which is the parent category of vegetable oils]). This part is done.
     In order to enhance the business value, the above system can act as a data input system to the another which is an analytical engine for customer loyalty program that uses the above system’s output as input and send out promotions based on the level that the user has acquired in the leadership board. These user promotions are applied on all leadership boards that the user is part of(such as the user will receive a promotion specific to vegetable oils as well as he/she will receive promotions on the food/grocery category on any products related to vegetable-oil[say wheat flour or rice]). This part is not done.
## Architecture:
![Architecture](https://github.com/rajagopal28/leaderboard-app/raw/master/static/images/image01.png "Architecture")
## Database Schema:
![schema](https://github.com/rajagopal28/leaderboard-app/raw/master/static/images/image02.png "Database")
## Technical stack:
This is primarily focused on me getting to know angular 2(angular 2 + type script + emac script 6), which was the talk of the town when I started this project.
## Server side:

- **Java spring-boot** : the latest java web framework to rapidly build rest applications that can be tied with various data sources through JPA repository library.
- Apache Tomcat web server: The web runtime container to hold the spring application that is built in java
- Gradle 2.8: The build tool used to package the java application and deploy it to the tomcat web container.
- MySQL v5.5: to maintain user data and query based on the user activity for leadership board.
- OpenShift by Red-Hat(DIY cartridge): Open cloud platform provided by Openshift, the RedHat’s PaaS.

### Client side:

- Angular2: Angular 2 was emerging and the hackathon that I’ve participated is on Angular 2. Angular 2 with Typescript was so easy but adding d3 charts raised the complexity with certain DOM manipulations.
- TypeScript: TypeScript is a language for application-scale JavaScript. TypeScript adds optional types, classes, and modules to JavaScript.
- D3 charts: For visualizing user activities or purchasing habits.
- ES6: Emac script v6 is the evolution of emac scripts that helps users to type less and readable code and perform wonders with their scripts.
- Bootstrap CSS: The CSS library to use templates UI components and styles to present data.
- GitHub pages: Hosting provided by GitHub to host static pages which mostly depend on REST based and static data.

## Challenges faced:
Following are the major challenges I’ve faced when developing this solution.

- MySQL - SpringBoot integration: Spring boot uses spring data libraries to ease the integration with backend which are much useful for simple joint related queries. However the integration is not so smooth for custom queries involving aggregation and complex joins. The good part is that spring-data libraries actually support native queries, despite the inbuilt method to query mappings.
- Gradle in Openshift cartridge: Openshift does not provide out of the box cartridges that support gradle builds and java spring boot applications. However with DIY(do-it-yourself custom) we can include things that are needed by writing custom hooks and deployment scripts , which include setting up gradle and build executables from the deployed code.
- The new Angular 2: I have a pet website designed in AngularV1 as a whole but angular 2 was in no way similar to angular1 in all aspects. However with my knowledge on OO design and applications it was easy to understand how the components and dependency injection works in angular 2 along with their interactions and navigations.
- D3 charts in angular 2: Well angular 2 is full of components and interactions while , d3 deals with DOM interactions and manipulation. Making the chart as a re-usable angular component using my OO knowledge helped a great way through the solution.
- GitHub pages: Maintaining git hub pages was easy but toggling the code between master and the gh-pages branch.

## Preview:
A small preview of the Application is added below. The following is the order history visualization view, which shows the category and subcategory vise purchasing habits visualization.
[Live Site](http://dextrousinc.github.io/leaderboard-app/)
![screen1](https://github.com/rajagopal28/leaderboard-app/raw/master/static/images/image3.png "screen1")
![screen2](https://github.com/rajagopal28/leaderboard-app/raw/master/static/images/image4.png "screen2")

## References:
Following are the references used while developing this solution:

- http://chariotsolutions.com/blog/post/angular2-observables-http-separating-services-components/
- http://jakzaprogramowac.pl/pytanie/6983,using-d3-js-with-angular-2
- https://www.vertabelo.com/
- https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
- http://projects.spring.io/spring-boot/#quick-start
- https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-jooq.html
- https://dannguyen.github.io/github-for-portfolios/lessons/deploy-github-pages/
- https://blog.openshift.com/run-gradle-builds-on-openshift/
- https://docs.spring.io/spring-boot/docs/current/reference/html/cloud-deployment.html

# P2_LFG


Project 2 - Looking for Gamers

### Conventions
___

### Project 2 - Looking for Good Gamers

## Project Descriptions


Looking for Gamers (LFG) is a service that users find and join other gamers for short-term collaborative gaming. Users connect their steam profiles to their account and are able to view and join various game groups currently looking for gamers. Players can also choose to host their own groups, allowing them to reject or deny the users based on their stats with the respective game. 

---

## Technologies Used


- Java JDK 8
- Spring Data JPA
- Spring Web
- Spring Boot
- Spring AOP
- Angular
- Jenkins
- JWT
- Mockito
- EC2
- S3 Buckets
- RDS
- JUnit 5
- Jasmine
- Karma
- H2 Database
- Postman
- PostgreSQL

---
## Needed for Runtime
-Jenkins pipeline has been shut down. Should be fine; just donwload files and mvn package
-Runs on a Postgres RDS that has since been shut down. There is a register function, so H2 database can be used 
-Hosted on S3 bucket which is likely shut down by now, so angular files will need to be hosted elsewhere

---
## Features


- Third Party API connection through Spring RestTemplates retrieving user Steam Profiles and game data
- Jenkins pipeline with webhook integration with GitHub building on updates to main branch
- Full unit testing using JUnit 5, Mockito, H2 Memory database, Postman automated testing
- Behaviour Driven Development Testing with Jasmine and Karma on Angular
- Real time updating of user activity allowing fast interactions between other clients while still maintaining RESTful systems
- JWT authentication and generation

---

### Working on the Project

- Camel Case for names 
  - `variableName`


- Clear variable names and obvious methods
  - ~~a = 1~~
  - `number = 1`


- Tests for every component on completion
>Follow TDD as much as reasonably possible, way easier to make tests as you progress than all at the end

- Create a feature branch whenever you are working on something

```
//If you wish to use the app
$ git clone git@github.com:DementedTiger/P2_LFG.git
// or if ssh is not setup 
$ git clone https://github.com/DementedTiger/P2_LFG.git

// Starting from main branch

$ git branch newBranchName
$ git switch newBranchName

// Now on new feature branch
// Work on here, when done merge into 

$ git add .
$ git commit -m "message related to what you did"
$ git switch devBranch
$ git pull origin devBranch

// Deal with any new remote commits and conflicts that could arise

$ git merge newBranch

// Deal with any new conflicts if they arise
// On success, push to remote dev branch

$ git push origin devBranch

// Don't push your feature branch on your local repo unless you are sharing it with someone else
```


- **NEVER** work on main directly


- Commit at the minimum daily
  - **Ensure** you have useful messages with them


- Whenever there is an update to the main, pull and resolve conflicts on local machines
  - Pull from origin main, merge with dev branch, and continue to work with devBranch
  - This will reduce overall merge conflicts
  - Ravioli Ravioli give me the formuoli!!!!!!!!!!!!!!!!!!!!

---

## Contributors


- Brian Arayathel - https://github.com/dementedtiger
- Christopher Scullin - https://github.com/christophers7 
- Kymon Williams - https://github.com/Meezy98 
- Mike Maschmeir - https://github.com/mekha454 

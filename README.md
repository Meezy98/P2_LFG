# P2_LFG

Project 2 - Looking for Gamers

### Conventions
___

- Camel Case for names 
  - `variableName`


- Clear variable names and obvious methods
  - ~~a = 1~~
  - `number = 1`


- Tests for every component on completion
>Follow TDD as much as reasonably possible, way easier to make tests as you progress than all at the end

- Create a feature branch whenever you are working on something

```
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


## CrudIdeaPublicSpark
Yet another CRUD web app in JAVA using SPARK and Mustache templating 
#### Features
- Login by name
- Simple Java core data structures to track user login and their associated texts
- SPARK lambda framework for serving RESTful API
- Mustache templating framework that writes user posts to a public page
- Formatted hash that utilizes Mustache's * *sections* * 
output looks something like this
```{PUBLIC_VIEW_NAME_AND_TEXTS_HASH={Papa Smurf=[Have you seen my glasses?, Ohh!], Dopey SMurf=[What are glasses?, Yikes!]}}```
After logging in a user post an idea to their current view. While creating texts the user has the option to edit and delete any of their texts. The texts that are created are displayed in a public view.

The public page is the index and only displays texts. It does not reveal the texts's author. In this way I am hoping to create a community generated textual view which both removes a texts origin from the view and inserts the texts into a larger context by which it assumes a different, community oriented providence. __In others words all logged in users are writing a single poem or text__.

In order for users to have control over their texts I had to create a login, a minor inconvenience. I plan to add features that interrupt or mutate the users posted texts on the public page with various web api data sources and algorithms that process the users data and return new data. By leveraging more powerful data processing with existing apis I am able to employ complex instrumentation without having to be a rocket scientist.

### Why? 

you probably haven't asked....

Ultimately I am exploring an old interest with new technologies. By creating a simple crud app I am building a foundation to sketch out ideas similar to the way a composer may use technology to generate new musical ideas. I look forward to exploring technologies that I don't understand but want to use such as functional programming, lightweight web frameworks such as SPARK, and temporary or mutable data structures. 

This isn't simply a technical exercise but a lifelong fascination with how meaning as a single comprehensive entity is generated not from a single individual but as part of a greater whole or community of individuals. _Perhaps this is what Laurie Anderson meant with the line "...these electronic arms."_
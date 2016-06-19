The Task

Write a RESTFUL webservice that can be accessed via http.

Use Java for coding
Use any framework and build managers you like (please provide a short statement why you chose a particular framework)
Include some testing (unit, integration, end-to-end)
 

The webservice needs to be publicly available:

Deploy on any service you like (e.g. AWS)
You will provide us with a link when you are done

It should be able to do the following:

Create a superhero with the following properties
Name
Pseudonym
The publisher it is from (e.g. DC or Marvel)
List of "skills" or powers
List of allies (e.g Batman's ally would be Robin) if the superhero has any.
Date of first appearance (in the format YYYY-MM-DD).
Get a list of all superheros in JSON format.
Get the details of a specific superhero in JSON format

-------------------------------------------------------

<h1> Description of endpoints </h1>

<h4> Create power:  </h4>
    POST /api/heropowers 
    Body: { "name":"name", "description":"description" }

<h4> Get all powers </h4> 
    GET /api/heropowers 
      
<h4> Create superhero </h4>

    POST /api/superhero <br>   
    Body { "name":"hero1",
             "description":"description",
             "pseudonym":"pseudonym",
             "publisher":"DC", - ( "DC" or "MARVEL"
             "dateOfFirstAppearance":"1993-12-12", (format = "yyyy-mm-dd")
             "powers": [1,2] -  list of powers id's (optional)
             "allies": [1,2] -  list of allies superheroes id's (optional) 
         }
<h4>  Get all superheroes </h4> 
    GET /api/superheroes 

<h4> Get detailed info about superhero </h4>
    GET /api/superheroes/{id}
    
<h4> Add powers to superhero </h4> 

    PUT /api/superheroes/{id}/powers   
    Body { "idList" : [] }

<h4> Add allies to superhero </h4>

    PUT /api/superheroes/{id}/allies    
    Body { "idList" : [] }


<h1> Public access </h1>

Application deployed to Heroku and can be accessed by <b> https://paperworks-test.herokuapp.com/ </b>
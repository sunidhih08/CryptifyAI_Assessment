# CryptifyAI_Assessment

1.	**Overview** : 
Cryptify AI assessment project is a backend service with a database (moviemaster) and API that supports the features needed for a web app. It also implements a hardcoded API key system to allow users to access the API without requiring full authentication.
2. **Setup instructions** : Follow the below instructions to setup the project
•	Clone the repository to a local workspace.
•	Create the database using the database scripts provided in the folder- “Database Script”.
•	Add your API Key, database connection string, database username and password, dialect (ex: org.hibernate.dialect.MySQLDialect ) in the application.properties file (shown below).

 ![image](https://github.com/user-attachments/assets/7c8b4dd8-0918-4cf3-a778-64bea4934c40)


•	Open the project in an IDE and sync the dependencies using maven and run the application.

3.	**Database schema description** : The database – moviemaster consists of two tables namely movies and movies_seq.
Below is a description of all the columns along with the data types and constraints:

Movies

![image](https://github.com/user-attachments/assets/6c10ff5a-e666-4ae4-9fb3-6b954dd441f0)

Movies_seq: Used for auto-incrementing the movie ID (“id”) in movies table using this sequence.

![image](https://github.com/user-attachments/assets/50ee6336-3e8b-4166-92d8-dca3a49172c0)


Note: The database scripts are inserting 100 sample records in movie table with unique movies along with their details.

4.	**API Key System** : The implementation is done in a way such that a valid apiKey is defined in the application.properties file which is accessed in the code. For each controller class methods, the annotation @RequireApiKey is given. This invokes the function of ApiKeyAspect (using Spring AOP) to validate the API key (or its presence). This function is executed before each request. If the api_key passed in the query params does not match with valid apiKey then the request is not authenticated. This is how the access to the APIs is restricted.

5.	**API specification** : There are 3 different API endpoints defined below:
   
a)	**Popular Movies** 
- URL: /movies/popular
- Method: GET
- Purpose: Retrieve the top 50 popular movies.
- Query Parameters: api_key(required), page (optional) for pagination. If page not passed then top 50 movies are returned based on rating. If page is passed then 10 movies (page size static as of now but can be set as dynamic if needed) are returned based on the specified page number.
- Response:
 - Movie ID 
 - Title
- Release Date
- Poster URL
- Average Rating

b)	**Search for Movies**
- URL: /movies/search
- Method: GET
- Purpose: Search for movies by title, and optionally sort(based on title, release date or rating in ascending or descending order) or filter (based on rating with greater than or equal to a certain input value) results.
- Query Parameters: api_key(required),  query (required), sort_by (optional), ascending (optional :true or false), filter (optional : rating) and filterValue (optional).
- Response:
 - Movie ID 
 - Title
- Release Date
- Poster URL
- Average Rating

c)	**Movie Details**
- URL: /movies/{id}
- Method: GET
- Purpose: Retrieve detailed information about a specific movie having id specified in 
input.
- Query Parameters: api_key(required).
- Response:
- Title
- Release Date
- Full Poster URL
- Overview
- Genres
- Average Rating
- Runtime
- Language

6.	**How to Test the API: with each scenario and screenshots**

•	**/movies/popular**

Scenario 1 : page parameter is not passed.  
 
![image](https://github.com/user-attachments/assets/8434ac9f-f0a1-4545-944c-33300eeb447e)


Scenario 2: page parameter is passed.

![image](https://github.com/user-attachments/assets/f3e6afed-9953-4849-8285-5c118fa1e73e)


Scenario 3: Without api_key. 

 ![image](https://github.com/user-attachments/assets/def55217-c58e-4b3f-a4e5-9f274375fa54)

Scenario 4: With wrong api_key

 ![image](https://github.com/user-attachments/assets/46d27097-2819-4c16-8298-5f669dd45be7)

•	**/movies/search**

Scenario 1 : Without query parameter. 
 
![image](https://github.com/user-attachments/assets/2237f0a8-020f-4492-98fa-75ef795f2975)

Scenario 2 : With query parameter

![image](https://github.com/user-attachments/assets/2ab7eb40-74bc-4833-ae7f-a3e3a66f30a2)

 

Scenario 3 : With sort_by but wrong value 

![image](https://github.com/user-attachments/assets/0937639d-6f98-491d-9670-5ce582c1f7c7)

Scenario 4 : With sort_by ( correct value )

 ![image](https://github.com/user-attachments/assets/7cceca83-04be-4589-a9a0-2891c9e39310)


Scenario 5 : With ascending but wrong value. 

 ![image](https://github.com/user-attachments/assets/9ffefcfe-68b2-4b70-97dd-37f4090e5a95)

Scenario 6 : With ascending false.

 ![image](https://github.com/user-attachments/assets/0fadd9f6-42a9-479d-882d-a2fd063c2fe2)

Scenario 7 : With filter wrong value. 

  ![image](https://github.com/user-attachments/assets/6df4883c-18bf-4046-9698-f39dd4a1b7f5)

Scenario 8 : With correct filter parameter but without filterValue.

 ![image](https://github.com/user-attachments/assets/4834a014-eb12-408c-b479-a34e6a80bd3a)


Scenario 9 : With correct filter parameter but wrong filterValue.

 ![image](https://github.com/user-attachments/assets/27bc13b0-a075-4c1a-b1c4-e082ef065869)


Scenario 10 : With correct filter parameter and correct filterValue. 

![image](https://github.com/user-attachments/assets/017d0ce6-7083-4043-9257-377aabcbbce8)

•	**/movies/{id}**

Scenario 1 : With correct id

 ![image](https://github.com/user-attachments/assets/747d2f6b-1e49-4a2b-82ed-65915b9641c0)


Scenario 2 : With id not present in database. 

![image](https://github.com/user-attachments/assets/b7057596-9f9f-48df-880d-cae5a089222f)



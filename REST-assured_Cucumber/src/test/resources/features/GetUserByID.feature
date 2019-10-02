Feature: Get User by ID
  Scenario: Get a User details by its ID
	Given User exists with an ID of 2
	When User retrieves details by ID
	Then The status code is 200
	And Response includes the following
	| avatar 	 		  | https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg|
   And Response includes the following in any order
	| first_nam			| Janet			            |
	| last_name			| Weaver			          |
	| email				 	| janet.weaver@reqres.in|
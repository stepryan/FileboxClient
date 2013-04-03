Filebox client usage:

   $ java -jar fbclient.jar <ARGUMENTS>

Arguments:

   adduser USERNAME PASSWORD     - creates a new user
   getuser USER_ID               - retrieves user's details for given id

   addfile FULL_PATH, TITLE, TYPE, STATUS, USER_ID  - adds a new file and its title for identification
                                 Types: 1 - text, 2 - binary (not implemented yet!)
                                 Status: 0 - private, 1 - public            

   printstatus                   - prints the code and text of status of the latest performed operation. 
                                 Codes: 0 - success, 1 - error, 2 - undefined
   

Note: Word in lowercase are keywords and must be used as specified, 
and ones in uppercase are values to be providd by users of the application.

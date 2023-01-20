Login Test Cases
==========================


 | email                       | password                               | error                                                         |
 |-----------------------------|----------------------------------------|---------------------------------------------------------------|
 |  emrekonyar8@gmail.c        | emreemreemre1997.                      | Please enter a valid email address (Ex: johndoe@domain.com).  |
 |  emrekonyar8@gmail.com      |                                        | This is a required field.                                     |


* Open the homepage and check
* Click Sign in button on the home page
* Check Customer Login Page

## Login fail cases
* Set e-mail to <email>
* Set password to <password>
* Click Sign in button on the customer login page
* Check error is <error>

## Login Success Scenario
* Set e-mail to "emrekonyar8@gmail.com"
* Set password to "emreemreemre1997."
* Click Sign in button on the customer login page
* Check login successed

## Login password wrong
* Set e-mail to "emrekonyar8@gmail.com"
* Set password to "emreemre"
* Click Sign in button on the customer login page
* Check Customer Login Page
* Check password is wrong error message


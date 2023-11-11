# Crypto Forum

Forum System where peoples can share their ideas and perspectives on the crypto market.

## Team Members
* Dimitar Tsanev - GitHub [https://github.com/DimitarTsanev]
* Preslav Marinov - GitHub [https://github.com/Pmarinov344]
* Karina Ivanova - GitHub [https://github.com/KarinaIvanova01]

## Project Description
Design and implements a Forum System, where the users can create posts,add comments,add tags, and like and dislike posts.

### Areas
* **Public part** - accessible without authentication.
* **Private part** - available for registered users only.
* **Administrative part** - accessible for users with administrative privileges.

#### Public part
* On the home page, anonymous users be able to see a list of the top 10 commented posts and a list of the 10 most recently
created posts. They can see the core features of the platform as well how many people are using it and how many posts 
have been created so far.

! [photo]

* Anonymous users can register.
![registerUser.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2FregisterUser.png)
* Users can log in.
![login.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2Flogin.png)

#### Private part
* Users be able to browse posts created by the other users with an option to sort and filter them.
![userPage.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2FuserPage.png)
* Users can create a new post.
![createPost.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2FcreatePost.png)
* Users can update your information.
![updateInfo.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2FupdateInfo.png)
* Users can delete and edit their own posts.
[photo]

#### Administrative part
* Admins can search for a user by their username, email, first name.
[photo]
* Admins can block / unblock users.
[photo]
* Admins can delete any post.
[photo]

## Optional feature
### Posts tags 
In order for the users to navigate easier and find certain topics faster, you can implement tags.
A tag is additional information that can be put under each post after creating the post.

## Technologies
* Spring Web
* Spring Data
* Thymeleaf
* Hibernate
* HTML
* CSS

## Notes
* Mockito and JUnit for testing
* DTO (data transfer objects)
* 91% Unit test code coverage of the business logic
* Swagger documentation - 



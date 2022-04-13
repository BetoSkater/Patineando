# Patineando

## 🔑 Keywords
- AndroidStudio
- Java
- Firebase
- Exoplayer
- Glide
 
## 📋 Introduction
**Patineando** is an app that emulates the dynamic management of a skating school. The students can join various courses, whatch the resources in each course, check the weather, etc. 

## 🧠 Why did I make this App? The idea
I was a student in a developer course for unemployed people. We had to make a project in a month. I wanted to create a complex app as a challenge. The main requirements where: 
  1) To use a database in the cloud in order to create an inmediate management of the users, resources, ...
  2) Implement a dynamic MenuNavigatorDrawer dependant on the user  access permission level.
  3) Video based resources can be uploaded to the courses based on skating technique and level. 
  4) Make the images, resources and so on from scratch. 
  5) Extra: wheather tab, user profile management and so much more. 

I love inline skating, so I dicided to create this app to a fictional inline skating school. Where the administration can manage the users, the resources, the courses and so on. The resources are mostly videos which perfectly match with a skating school.

 </br>

## ⌨ Technologies
**PiensaJuegos** is an Android Native app developed in Java using AndroidStudio. About the database, **PiensaJuegos** only has a remote non-relational database, Realtime Database by Firebase. All the resources, such as images, videos and so on are stored in the **Firebase Storage**. In order to use good practices, this app follows the MVC model as much as posible.



## 🛠 Functionality 

- **Sign up & Sign in**: </br>
After the loading screen, the authentication window is shown.

<p align="center">
  <img src="./acceso.jpg" alt="acceso" height="720">
</p>

 In order to use the app, the users needs to register eaither from scrath or using Google Sign in. 



<p align="center">
  <img src="./seleccion.gif" alt="seleccion" height="720">
</p>



- **Dynamic NavigatorDrawer Menu**:


<p align="center">
  <img src="./bucle.gif" alt="bucle" height="720">
</p>

- **Create a course**

- **Upload resources**

- **Search a course**:


<p align="center">
 <img src="./correcto.gif" alt="correcto" height="720">
</p>



- **Course subscription**:
<p align="center">
 <img src="./error.gif" alt="error" height="720">
</p>


- **End Game**:
The game ends whenever the user answer all the questions or when the timer reaches zero. At this moment, the score is calculated. If it is among the top 10, an alert dialog with a textbox is shown, in which the user can input a text, like a name or nickname. Afterwards the top 10 score view is shown. If the score is not among the top 10, an average alerdialog is show and then, the top 10 scores.

<p align="center">
 <img src="./puntuaciones.gif" alt="puntuaciones" height="720">
</p>



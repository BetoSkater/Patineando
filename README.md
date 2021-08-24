# Patineando
Ninguna
# Pendiente:
   Me pongo con esto para crear la autenticacion:
    **Agrega acceso a tu app para Android fácilmente con FirebaseUI** https://firebase.google.com/docs/auth/android/firebaseui?authuser=0
       
 #Envio de un string entre fragments bien explicado sin usar los mParams autogenerados que son un lio, es la respuesta de Joao Marcos:
  * https://stackoverflow.com/questions/24555417/how-to-send-data-from-one-fragment-to-another-fragment
  
Editor de textos en línea: https://stackedit.io/app#
                           https://gauge.org
 #Ojo, he usado este acceso de google, expone las diferencias con lo del acceso de Google normal sin tener en cuenta lo de firebase, esta guia es de las de firebase:
 * https://firebase.google.com/docs/auth/android/google-signin?hl=es   
 https://firebase.google.com/docs/auth/android/google-signin?hl=es#java_6
 * **Pendiente por hacer el que un mismo usuario pueda acceder tanto por correo y contraseña como con Google, es decir, que las cuentas estén vinculadas**
   *   https://firebase.google.com/docs/auth/android/account-linking?hl=es
            
                           
# Añadir informacion usuaios firebase: https://stackoverflow.com/questions/39076988/add-extra-user-information-with-firebase
# Importante
* **Al poner el proyecto en el mac, me ha saltado un error de que la version android en la que estaba creado el proyecto era diferente a 
        la version de AS del Mac, he seguido esta respuesta para solucionarlo (la tercera, la del cambio de versiones): 
        ** Android Support plugin for IntelliJ IDEA (or Android Studio) cannot open this project
        https://stackoverflow.com/questions/62678785/android-support-plugin-for-intellij-idea-or-android-studio-cannot-open-this-pr
    **Al cambiar de version y ejecutar, el compilador se quejaba de que varias clases estaban duplicadas y daba problemas:
        **Solucion**: añadir en gradle.properties la siguiente línea de código: android.enableJetifier=true
        **Fuente**: https://stackoverflow.com/questions/55909804/duplicate-class-android-support-v4-app-inotificationsidechannel-found-in-modules
* **Obtención del SHA-1: Se ha seguido el método de Crear un Proyecto de Google Maps en Android por su sencillez:
    * Enlace: https://stackoverflow.com/questions/15727912/sha-1-fingerprint-of-keystore-certificate 
    **Problema**: como no estoy desde mi ordenador de casa, este SHA-1 obtenido es el del MAC. 
    **Solucion**: Se crea una funcion que se llama en el onCreate para que imprima el SHA por consola de este proyecto. ESte proyecto tiene el SHA de mi ordenador, por lo que es lo correcto.
                    Para obtener más información, ver el documento de acceso con Facebbook en los ejemplos extra:
                    
     **Solucion2** desde task del menu gradle:
         Enlaces interesnets para esto:
         Solucion a que no salga task en el gradle (deshabilitado por defecto desde la 4.2)
            * https://stackoverflow.com/questions/67405791/gradle-tasks-are-not-showing-in-the-gradle-tool-window-in-android-studio-4-2
            *https://stackoverflow.com/questions/27609442/how-to-get-the-sha-1-fingerprint-certificate-in-android-studio-for-debug-mode
            **27/07/21** Al ejecutar el aplicativo desde casa, no se podía acceder con Google, el error era algo de "invalid token", se ha solucionado al obtener el SHA-1 en esta máquina, y añadirlo a la lista de SHA-1 permitidos en la pestaña de COnfiguracion de Proyecto de Firebase 
            
# Enlaces de interés:
* **Cajas de Texto bonitas** : https://material.io/components/text-fields/android#filled-text-field
* https://proandroiddev.com/make-elegant-apps-with-palette-api-c1ca094190eb  
* https://developer.android.com/guide/topics/appwidgets/overview  
  
* Screen slide entre fragments. Creo que justo es lo que necesito  
https://developer.android.com/training/animation/screen-slide  
* EL TUTORIAL TAMBIEN LO PUEDO HACER CON ESTO!!!  
https://blog.mindorks.com/exploring-android-view-pager2-in-android  
https://blog.mindorks.com/exploring-android-view-pager2-in-android  
  
  
* Puede que sea interenate para las imagenes de la cámara:  
 https://stackoverflow.com/questions/21701447/how-to-set-selected-area-of-image-to-imageview  
  
* https://stackoverflow.com/questions/36837356/android-create-clickable-area-on-imageview  
  
* https://living-sun.com/es/android/31461-how-to-set-a-clickable-region-for-an-imageview-android-imageview-clickable.html //TODO Como con un lienzo?  
  
* Justo esto es lo que necesito para la imagen con regiones seleccionables:  
https://stackoverflow.com/questions/16670774/clickable-area-of-image  
	* La que parece emejor respuesta es una en la que pone que se crea un arrayList de objetos area seleccionadas  
https://github.com/LukasLechnerDev/ClickableAreasImages  
https://github.com/Baseflow/PhotoView  
  
* Forma un poco chapuza para conocer las coordenadas de una imagen:  
	* https://stackoverflow.com/questions/8909835/android-how-do-i-get-the-x-y-coordinates-within-an-image-imageview  
	* https://stackoverflow.com/questions/17190576/how-to-get-all-x-and-y-coordinates-of-an-imageview-in-android         
	
*Poner **bombilla de error** en una caja EditText para indicar que falta algo:
    * https://stackoverflow.com/questions/6290531/how-do-i-check-if-my-edittext-fields-are-empty

* **Capturar imagen o seleccionar una de la galeria** : https://medium.com/@hasangi/capture-image-or-choose-from-gallery-photos-implementation-for-android-a5ca59bc6883
    * Nota: Esto lo dejo sim implementar ya que en mi base de datos va un enlace que referencia al CloudStore, por lo que aqui tengo un lio y no sé como hacerlo, igual las imagenes de los usuarios si que las debería meter en RealTimeDatabase

* **Navigator Drawer**: Nota, el tutorial del documento del profesor tiene fragments, justo es lo que necesito que en vez de que el fragment del menú cambie con botones, cambie con un booleano 
            en funcion de si el usuario tiene rango de alumno, profesor o personal administrativo.
            
    * How to have two different navigation drawer activities in one android studio project for two different types of users?: 
        https://stackoverflow.com/questions/58158440/how-to-have-two-different-navigation-drawer-activities-in-one-android-studio-pro
    * Navigation Drawer con Fragments – Android Design Support Library: 
        https://desarrollador-android.com/material-design/desarrollo-material-design/pautas-desarrollo/navigation-drawer-con-fragments/
    * Android Custom Navigation Drawer: 
        https://web.archive.org/web/20171208103611/http://www.tutecentral.com/android-custom-navigation-drawer/
    * https://www.youtube.com/watch?v=fGcMLu1GJEc 
    * **Añadir campos al menú de forma dinámica, creo que me puede servir**: https://pick8chu.github.io/android_navigation_drawer.html
    * **Drawer Navigation - Change list of items dynamically after creation**: https://stackoverflow.com/questions/27152645/drawer-navigation-change-list-of-items-dynamically-after-creation
    * **Navigation Drawer in Android**: https://www.geeksforgeeks.org/navigation-drawer-in-android/
    * **Tutorial Para Crear Un Navigation Drawer En Android**: https://www.develou.com/android-navigation-drawer-tutorial/
    * **How to Create and Edit NAVIGATION DRAWER ACTIVITY 2021 | EASIEST WAY | Android Studio Latest Version**:https://www.youtube.com/watch?v=Mr__NdcIxqs
    * **Dynamic Menus in NavigationView**: https://stackoverflow.com/questions/31064483/dynamic-menus-in-navigationview
   
    * **Menus de navegacion para la parte superior e inferior (!= a los nagigation drawer)**:https://developer.android.com/guide/navigation/navigation-ui?hl=es#java
    * **POSIBLES SOLUVIONES A QUE NO CARGUE EL DRAWER**
    * **Android navigation drawer won't open** https://stackoverflow.com/questions/30218875/android-navigation-drawer-wont-open
    * **Why won't this Navigation Drawer open?**:https://stackoverflow.com/questions/31451778/why-wont-this-navigation-drawer-open
# **PINTA A QUE ESTE EJEMPLO DE NAVIGATOR DRAWER ES EL MEJOR:**
    * **Fragment Navigation Drawer**: https://guides.codepath.com/android/Fragment-Navigation-Drawer
    * https://guides.codepath.com/android/Fragment-Navigation-Drawer

* **FIREBASE**:
    * **Autentica con Firebase mediante cuentas con contraseña en Android**:https://firebase.google.com/docs/auth/android/password-auth?hl=es  
    * **Guias de Firebase**: https://firebase.google.com/docs/guides?authuser=0 
    * **Primeros pasos con las reglas de seguridad de Firebase**: https://firebase.google.com/docs/database/security/get-started?authuser=0 
    
    * **Estructura tu base de datos**: https://firebase.google.com/docs/database/web/structure-data?hl=es
    * **Lee y escribe datos en Android**: https://firebase.google.com/docs/database/android/read-and-write?hl=es
        *https://firebase.google.com/docs/database/android/read-and-write?hl=es
    * **Indexa tus datos** : https://firebase.google.com/docs/database/security/indexing-data 
    * **Primeros pasos con las reglas de seguridad de Firebase** : https://firebase.google.com/docs/database/security/get-started
    * **Explicación de las reglas de Firebase Realtime Database**: https://firebase.google.com/docs/database/security#:~:text=Firebase%20Realtime%20Database%20Security%20Rules,if%20your%20rules%20allow%20it.
    * **Cómo funcionan las reglas de seguridad**: https://firebase.google.com/docs/rules/rules-behavior
    * **Administrar Usuarios**: Son cosas como crear un usuario, mandar un mail de verificación o cambio de contraseá, etc.
        *   https://firebase.google.com/docs/auth/android/manage-users?hl=es-419 
    * **Firebase: User Sign Up, Login & Data Management**: https://medium.com/@felicity.johnson.mail/firebase-user-sign-up-login-data-management-992d778b167
    * **Comprobar si un correo ya está en uso. etc** : https://stackoverflow.com/questions/51562995/how-can-i-check-if-user-exists-in-firebase
    * **Trabaja con listas de datos en Android**: https://firebase.google.com/docs/database/android/lists-of-data#java_6 
    * **Retrieve data of all user id from firebase**: https://stackoverflow.com/questions/46651782/retrieve-data-of-all-user-id-from-firebase
    
    
    14/08/2021
    * **How to retrieve user from firebase:** :https://stackoverflow.com/questions/60155498/how-to-retrieve-a-username-from-firebase-realtime-database?rq=1 
    * **java.util.HashMap cannot be cast to Object**: https://stackoverflow.com/questions/45795656/java-util-hashmap-cannot-be-cast-to-object
    * **How to update the listview when i click the button?**: https://stackoverflow.com/questions/14598197/how-to-update-the-listview-when-i-click-the-button/14598358 
    * **How to convert a Map to List in Java?**: https://stackoverflow.com/questions/1026723/how-to-convert-a-map-to-list-in-java
    * **java.util.HashMap cannot be cast to java.util.ArrayList** : https://stackoverflow.com/questions/42089524/java-util-hashmap-cannot-be-cast-to-java-util-arraylist/42089578
    * **How to check if data already exists in Firebase Realtime Database with Android? [duplicate]**: https://stackoverflow.com/questions/39260729/how-to-check-if-data-already-exists-in-firebase-realtime-database-with-android
    * **Checking if a user already exists at firebase realtime database**: https://stackoverflow.com/questions/51772812/checking-if-a-user-already-exists-at-firebase-realtime-database
    
    
#Notas importantes de interés:
    * **Clases llamadas tPalabra** : Estas Clases hacen referencia a los objetos que se quieren introducir en las tablas de la base de datos.
        así, la clase tUsuarios hace referencia a la tablaUsuarios, la clase tCursos a los cursos que imparte la escuela (sin alumnos, no son grupos ni nada de eso), etc.
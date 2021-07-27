# Patineando
Ninguna
# Pendiente:
   Me pongo con esto para crear la autenticacion:
    **Agrega acceso a tu app para Android fácilmente con FirebaseUI** https://firebase.google.com/docs/auth/android/firebaseui?authuser=0
        * Paso 1: Ok:
        * Paso 2: Ok:
        * Paso 3: nota: importante par aque el usuario no tenga que poner la contraseña todo el rato:
                    https://firebase.google.com/docs/auth/android/email-link-auth?authuser=0
        * Paso 4: ok, el SHa ya está metido:
        * Paso 5:no me hace falta ya que no se quiere crear un acceso con Facebook o twitter
        
    Acceso: 
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
	
* **FIREBASE**:
    * **Autentica con Firebase mediante cuentas con contraseña en Android**:https://firebase.google.com/docs/auth/android/password-auth?hl=es  
    * **Guias de Firebase**: https://firebase.google.com/docs/guides?authuser=0 
    * **Primeros pasos con las reglas de seguridad de Firebase**: https://firebase.google.com/docs/database/security/get-started?authuser=0 
    
    * **Estructura tu base de datos**: https://firebase.google.com/docs/database/web/structure-data?hl=es
    * **Lee y escribe datos en Android**: https://firebase.google.com/docs/database/android/read-and-write?hl=es
    * **Indexa tus datos** : https://firebase.google.com/docs/database/security/indexing-data 
    * **Primeros pasos con las reglas de seguridad de Firebase** : https://firebase.google.com/docs/database/security/get-started
    * **Explicación de las reglas de Firebase Realtime Database**: https://firebase.google.com/docs/database/security#:~:text=Firebase%20Realtime%20Database%20Security%20Rules,if%20your%20rules%20allow%20it.
    * **Cómo funcionan las reglas de seguridad**: https://firebase.google.com/docs/rules/rules-behavior
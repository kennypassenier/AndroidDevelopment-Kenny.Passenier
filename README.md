# AndroidDevelopment-Kenny.Passenier

Werkstuk voor de cursus Android development.

Referenties: 

* Transitions: https://codinginflow.com/tutorials/android/slide-animation-between-activities
* Broadcasts: https://stackoverflow.com/questions/46971153/fatal-exception-android-app-remoteserviceexception-cant-deliver-broadcast-at/48866003#48866003
* Bitmap uit URL halen: https://stackoverflow.com/questions/8992964/android-load-from-url-to-bitmap
* Bitmap met intent verzenden: https://stackoverflow.com/questions/2459524/how-can-i-pass-a-bitmap-object-from-one-activity-to-another/2459624
* StringRequest: https://developer.android.com/training/volley/simple
* Snackbar: https://developer.android.com/training/snackbar/showing

Vereisten: 

* Minstens 2 activities waartussen informatie wordt uitgewisseld met behulp van Intents
  - MainActivity bevat een fragment MainListViewFragment waar we de movie_id doorsturen naar MovieDetailActivity
* Het gebruik van Fragments die met elkaar communiceren in minstens 1 activity van je applicatie
  - MovieDetailActivity bevat twee fragments (movieDetailInfoFragment en DarkModeSwitchFragment) waar we de achtergrond en textkleur veranderen door een switch
* Het gebruik van minstens 1 activity waarin een complexe layout wordt opgebouwd
  - activity_movie_detail bevat twee fragments, hiervan heeft fragment_movie_detail_text_info een complexe layout dankzij ConstraintLayout en Scrollview
* Het gebruik van threading voor het afhalen van elementen (afbeeldingen, muziek, video of andere artifacts) van het internet en/of het gebruik van minstens 1 service
  - We gebruiken AsyncTasks voor de database transacties en een IntentService voor het ophalen van afbeeldingen van het internet en dan te tonen in een ImageView
* Aanwezigheid van action bar of navigation drawer voor de navigatie
  - Zie de layout in MainActivity
* Persistentie van gegevens, gebruikmakende van SQLite of een ORM-oplossing
  - Room wordt gebruikt voor de opslag, zie Movie, MovieDAO en MovieDatabase in de folder models
* Ondersteuning van apparaten met minimum API 16 (Android versie 4.1)
  - API 16+ werd gekozen bij het aanmaken van het project
* Minimaal ondersteuning van hdpi, xhdpi en xxhdpi schermen
  - Alle layout files gebruiken dp voor de grootte van de elementen en sp als grootte van de text
* Ondersteuning van landscape/portrait mode (m.a.w. verschillende layoutbestanden) voor minstens 1 activity
  - fragment_movie_detail_text_info heeft een landscape variant, de rest gebruikt enkel portrait layouts
* Gebruik van JUnit-framework voor het schrijven van testen voor je applicatie (minimum 1)
  - De klasse MovieTest bevat twee unit tests
* Toepassing van de navigation design patterns binnen Android
  - Zie code
* Ondersteuning voor minstens Nederlands en Engels. Extra talen mogen voorzien, maar tellen niet voor extra punten.
  - Resource files voor zowel Engels als Nederlands zijn aanwezig en worden gebruikt in de code

Optionele componenten
* Automatische UI Testing
  - Er zijn twee tests via Espresso die de verschillende functionaliteiten testen van de UI
* Testen met behulp van monkeyrunner
* Gebruik van een broadcast receiver
  - De MovieDetailActivity gebruikt een BroadcastReceiver om het resultaat van de FetchImageService (IntentService) naar een dochter fragment MovieDetailInfoFragment die het vervolgens gebruikt om de afbeelding weer te geven in een ImageView
* Implementatie van een content provider
  - In AddMovieFragment gebruiken we een content provider om de data van de film te achterhalen ([OMDBApi](http://www.omdbapi.com))
* Aanspreken van web services of het gebruik van een cloud provider voor de opslag van gegevens (bv: Firebase)
* Multi-touch ondersteuning
* Ondersteuning van 1 of meerdere widgets
* Gebruik van OpenGL
* Gebruik van transities
  - De standaard transities van de app zijn overschreven met custom transities voor het inladen en verlaten van alle activities
  - Zie de folder "anim" bij resources
* Sensoren (GPS, beweging, …)
* Bluetooth, NFC
* Android Wear uitbreidingen op je app
* App is voorbereid voor publicatie of werd gepubliceerd
  - [Play Store](https://play.google.com/store/apps/details?id=com.kennypassenier.androiddevelopment_kennypassenier)


# EventCalendar
A weekly event calender following MVVM with Android Jetpack.

## Thought process
---
Firstly, I started by asking myself and answering some questions, where to start, what will be the 
features? how is it different from a regular monthly calendar? , how much knowledge do I have with 
the Necessary Technologies mentioned?

After that, I started developing just a simple weekly calendar using Sunday as the start date of a 
week, and I was able to do that with ease. Then comes the part of showing an event in a date box . 
For that, I started with creating the model layer.

* __Model layer__:  I added the model layer with creating model class, Dao, Database, Repository. 
  After initial implementation, I had to work on them later for adding a new query, column.
  Difficulty faced: With inserting LocalDateTime and LocalDate used converters for tat.

After that, I moved to the presentation layer. Then a question arose about showing an event in a 
date box, and that was "should I give the user to add multiple events or just a single event in a 
date box?". I also tried to contact the team through email but got no response so I went with showing 
multiple events in a single date box. I decider to use recycler view inside another recycler view of
a weekly calendar. I created a ViewModel to communicate with the data layer. I created the necessary
classes and layouts. Then comes the interesting part that is communicating with the data layer and 
updating the views. As I haven't used recycler view inside another recycler view before,  for that, 
I have to research. I planned when the parent adapter would bind it's views, it would also notify 
the child adapter with the updates. Then I added a dialog fragment to take user input.  I was close 
to the implementation but got a problem with notifying the child adapter. Then I got a reply from the 
team about showing a single event in a date box. So I created another branch for the part with multiple 
events in a date box and decided to work on that later on after the submission.

* __Presentation layer__:  

* __Dialog to take User input__: I added a dialog fragment to take user input. I managed button text
  dynamically for Add, Update, Delete, Cancel.
  I was stuck for a while about communicating with activity from the dialog. For that, I used callback
  and also passed parameters from the activity to handle button action accordingly and notifying the 
  activity with the help of the callback.

  * __Difficulty faced__: Recycler view messed the views because of calling notifying adapter change 
    from a wrong place, and live data observer of getting event list by date was blocking the UI(Calling
    inside a loop as LiveData observers are always called on the main thread.
> Solved the issue by using coroutine. Used IO coroutine dispatcher to get the data from DB and then move to Main thread to notify the adapter with the help of the Main dispatcher.
In the presentation layer, I also faced another issue with deleting an event. An event was getting deleted but not reflecting on the layout instantly. For that, I had to clear the dateEventHashmap before getting a new event list from the DB and notifying the adapter.

  * __Choosing data structure for storing date wise events__:
  I have chosen hashMap whose key is a date and value is a list of an event; though I am showing a 
  single event now, I'll implement showing multiple events in a date box. I have chosen it rather 
  than a class containing the date and list of an event because of its easy and faster-accessing capability.




Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Room][90] - The Room persistence library provides an abstraction layer over SQLite to allow fluent
    database access while harnessing the full power of SQLite.
    * [Data Binding][11] - Declaratively bind observable data to UI elements.
    * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
    * [LiveData][13] - Build data objects that notify views when the underlying database changes.
    * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
      asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Lay out widgets using different algorithms.
* Third party and miscellaneous libraries
  * [Kotlin Coroutines][91] for managing background threads with simplified code and reducing needs for callbacks

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[16]: https://developer.android.com/topic/libraries/architecture/room
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[18]: https://developer.android.com/topic/libraries/architecture/workmanager
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[90]: https://developer.android.com/training/data-storage/room




# FetchChicago
<a href="https://fetch.com/">
    <img alt="©2023 Fetch. This is Fetch icon, a little bit redesigned by me, it's not meaning that you can use that!" src="https://raw.githubusercontent.com/cj3dreams/FetchChicago/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png">
</a>

©2023 Fetch. This is Fetch icon, a little bit redesigned by me, it's not meaning that you can use that!

## This is a Take Home Test from Fetch Rewards

**Used languages:**
Kotlin, Android XML
Groovy(build.gradle), SQL(one request)

**My stack:**
MVVM + Clean Architecture + Usecases, Single Activity

**Used dependencies:**
Koin, Coroutines, Retrofit, Room,
Jetpack Navigation, Logging Interceptor (for tests).

## The target of testing task was:
1. To get an array from the REST Api;
2. Order that first of all by "listId" and only then by "name";
3. Filter out any item which includes null or blank data in column "name".
4. Show as understandable list

## Extra things which I added
1. Additional sorting via SQLite + saving to see data offline.
2. Coroutines: Competently(for my level) wrote code using Kotlin Coroutines, espsically of REST & SQL Requests.
3. SwypeToUpdate: As you know, it caching data in database, for updating remote list or database cache you can just swype to update.
4. Main thing: I wrote code as good as I can, if even it wasn't indeed to write/make a lot things for this small testing task. Some  little things: Theme, splashScreen, icon, cardView, bottomNavView, navigation, etc.

  ![App Icon](https://github.com/cj3dreams/FetchChicago/assets/90767203/2a657576-1627-479a-811f-1ccf168354d8)  ![Empty Database/No Network Connection](https://github.com/cj3dreams/FetchChicago/assets/90767203/af289c4e-8cba-4b39-996b-83631e03d925)  ![After swype to update](https://github.com/cj3dreams/FetchChicago/assets/90767203/ac3260df-dc47-404f-8557-e5f429f65fad)

### Shortly how it works

**Sorted/Rest Display:**

*Application is getting an array from https://fetch-hiring.s3.amazonaws.com/hiring.json using Retrofit+GsonConvertor via usecase from data repository to the viewModel, sorting that in viewModel and providing to UI(in this case RecyclerView), mostly vice versa, because UI is observed to MutableLiveData in viewModel. Easyly realized with Koin :)*

**Sorted/Local Display:** 

*Almost same like above, however first of all it's inserting remote list to the local base and then getting list from database with SQL request using "where", "order by" etc.*

**Unsorted Display:**

*Just getting list from remote source and showing without any sorting/filtering to see difference beetwen two previouses.*

**More screenshots:**

  ![Splash Screen](https://github.com/cj3dreams/FetchChicago/assets/90767203/8ceec1ba-e9a5-49de-b9c4-e187854a91ea)  ![Sorted/Rest Display](https://github.com/cj3dreams/FetchChicago/assets/90767203/9f2f2273-ec1e-4fd9-b9ec-e39482b0fe87)  ![Fetch icon on Desktop](https://github.com/cj3dreams/FetchChicago/assets/90767203/5e4e0d28-c8bf-4a5e-8cc5-ef76bf23cb86)  ![Opened first time local display without network connection](https://github.com/cj3dreams/FetchChicago/assets/90767203/8ce56598-0c4b-4dd6-9b49-9359fd246a09)



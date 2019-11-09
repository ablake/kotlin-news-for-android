# kotlin-news-for-android
A simple Android app for browsing Kotlin news, meant to demonstrate architectural principles and
implement core functionality applicable to most apps.

## Goal

- Display a news feed from <https://www.reddit.com/r/kotlin/.json>
- Display a selected news item in a new full-screen view.

## Requirements

- Display news item titles and their associated thumbnails in the news feed.
- Display a news item's thumbnail and body on the individual item view, with its title in the
  navigation bar.
- News item layouts should accommodate for images of different sizes, preserving their aspect
  ratios, in the newsfeed and the individual item view.
- Layouts should work on different screen sizes.
- Network errors and missing data should be handled appropriately.
- Code should be clean and readable.
- Code should be unit tested.

## Plan

1. Implement networking with tools like Retrofit and OkHttp and request the news feed on app launch.
   Create a Newsfeed model from the response.
2. Create Fragments for each screen and use Android Jetpack's Navigation component to define paths
   between them.
3. Use ConstraintLayout and RecyclerView to implement the news feed view.
4. Connect the RecyclerView to the news feed with an Adapter. Separate the logic from the view with
   an architectural pattern like MVP or MVVM.
5. Implement the individual news item view. Pass a NewsItem to it from the newsfeed view when one is
   selected.

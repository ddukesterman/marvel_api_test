# Marvel Api Test


<img src="https://user-images.githubusercontent.com/3778517/132232466-fa178b53-5f8e-4bef-997a-a74e9edeb798.png" width="300">|<img src="https://user-images.githubusercontent.com/3778517/132232475-84736a87-2221-4637-a390-8b15020c71cd.png" width="300">|<img src="https://user-images.githubusercontent.com/3778517/132232482-75f02fbe-1d0a-4a3f-bac6-d36032324f00.png" width="300">


## Summary
Simple application to display a Comic from the API at developer.marvel.com. It has a refresh button in the toolbar to randomly flip through a few comics.

## Libraries used 
- Basic AndroidX libraries/dependencies 
- Retrofit libraries for network communication
- Picasso for loading images
- RXJava
- Hilt/Dagger for DI
- Timber for logging 

## Updating API key
To add your own API key, modify the file `MarvelConfigService` located at `com.trenton.marvel.config.MarvelConfigService`

## UI Unit Testing
The test `ComicActivityBehaviorTest` will run through a simple UI test to click the refresh button.

## Unit Testing
There really isn't much to test in this applcation. So I created the test `LoginValidatorTest` and imagined the user had to create an account. This would run some email creation checks.

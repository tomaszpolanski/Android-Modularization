# AndroidSandbox

Repository that I use to try out new things and ideas related to Android.

## Branches

- [**master**](https://github.com/tomaszpolanski/AndroidSandbox/tree/master) - base branch for other branches
- [**rx_mvvm_master**](https://github.com/tomaszpolanski/AndroidSandbox/tree/rx_mvvm_master) - simple architecture for using RxJava and MVVM together
- [**modular_app**](https://github.com/tomaszpolanski/AndroidSandbox/tree/modular_app)  - dividing Android application into modules to improve compilation time and for separation of concerns 
- [**gradle3_kotlin**](https://github.com/tomaszpolanski/AndroidSandbox/tree/gradle3_kotlin)  - using Gradle 3.0 and kotlin script instead of groovy + trying out new kotlin 1.1 features


## Modularizing Android application - WIP

In this branch I modularize layers of the application.
The base layers are horizontal - that means that next module depends on the previous.

The horizontal layers consists of:
- utility classes
- common services
- styles

The vertical layers are the features. 
In this app they are only devided by basic activities, but in real app they could be features like:
- home activity
- settings activity and it's only dependencies
- search activity
- and many more


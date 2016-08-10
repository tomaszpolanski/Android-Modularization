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


# mobitech_content_ui_sdk_demo
Demonstration regarding how to use Mobitech content display sdk. 


For quick demo: [(Download apk here)](https://www.dropbox.com/s/qoit44xylr8z1pi/content_ui_demo.apk?dl=1)


To use Mobitech content SDK, follow these steps:

_ 1. Define dependency to Mobitech's content SDK in your app gradle.build file:
```sh
compile('io.mobitech.content:content_ui_sdk:3.4.1@aar') {
        transitive = true
    }
```

_ 2. Initiate the SDK with a user ID (in Application class):
```sh
 ContentService.init(ContentDemoContext.this, userID);
```

_ 3. In your app define a fragment to include the content list

_ 4. Set the content list ui fragment according to yhe fragment id you set.
e.g:
```sh
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        
        //choose API 1 or API 2
        ft.replace(R.id.content_ui_container, UserNewsFragment.newInstance(), "UserNewsFragment.TAG"); // API 1        
        ft.replace(R.id.content_ui_container, UserNewsFragmentNewAPI.newInstance(), "UserNewsFragmentNewAPI.TAG"); // API 2
        
        ft.commit();
        
        
```


[![See the demo video here](https://i.ytimg.com/vi/cON5zcx_FCc/default.jpg?v=57da500d&sqp=CNCg6b4F&rs=AOn4CLCv06oDjftA7bH-tkStiG-a4_R2rQ)](https://youtu.be/cON5zcx_FCc)


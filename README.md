# mobitech_content_ui_sdk_demo
Demonstration regarding how to use Mobitech content display sdk.


// TODO: update
For quick demo: [(Download apk here)](https://www.dropbox.com/s/qoit44xylr8z1pi/content_ui_demo.apk?dl=1)


To use Mobitech content SDK, follow these steps:

1. Add Mobitech's content SDK into your app gradle.build file:
```java
compile('io.mobitech.content:content_ui_sdk:4.1.13@aar') {
        transitive = true
    }
```

2. In your app define a fragment to include the content list

3. Set the content list ui fragment according to the fragment id you set.
e.g:
```java
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // choose fragment according to desired content type

        // example for organic content
        ft.replace(R.id.content_ui_container, OrganicContentFragment.newInstance(), OrganicContentFragment.TAG);
        ft.commit();

        // example for promoted content
        ft.replace(R.id.content_ui_container, PromotedContentFragment.newInstance(), PromotedContentFragment.TAG);
        ft.commit();
```

// TODO: update
[![See the demo video here](https://i.ytimg.com/vi/cON5zcx_FCc/default.jpg?v=57da500d&sqp=CNCg6b4F&rs=AOn4CLCv06oDjftA7bH-tkStiG-a4_R2rQ)](https://youtu.be/cON5zcx_FCc)

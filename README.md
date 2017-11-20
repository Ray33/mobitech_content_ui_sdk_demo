# mobitech_content_ui_sdk_demo
Demonstration regarding how to use Mobitech content display sdk.


For quick demo: [(Download apk here)](https://www.dropbox.com/s/c2qn9lxbkdovw6s/content_ui_demo-release.apk?dl=1)
Or download app from Google Play: https://play.google.com/store/apps/details?id=io.mobitech.content_ui_demo

To use Mobitech content SDK, follow these steps:
0. Get API key from Mobitech support and add it as a parameter in strings.xml
```java
<string name="MOBITECH_CONTENT_PUBLISHER_API_KEY">YOUR_APP_API_KEY</string>
```

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


[![See the demo video in YouKu Site](http://v.youku.com/v_show/id_XMzE3MTg3MTg0NA==.html)]

[![See the demo video in Youtube Site](https://youtu.be/rAqc3EF3G8U)]

----------------------------------
If you want to work directly with the API, you can use the project:
https://github.com/Ray33/content_sdk

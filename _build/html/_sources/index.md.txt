# Wordpress-SDK | Android
Minimal Wordpress SDK for Android.

[![Build Status](https://travis-ci.org/VenomVendor/Wordpress-SDK.svg?branch=release%2Fsdk)](https://travis-ci.org/VenomVendor/Wordpress-SDK)
[![Code Factor](https://www.codefactor.io/repository/github/venomvendor/wordpress-sdk/badge)](https://www.codefactor.io/repository/github/venomvendor/wordpress-sdk)
[![Codacy](https://api.codacy.com/project/badge/Grade/8faaef1c15744e79a7a9f843a921bfb6)](https://www.codacy.com/app/VenomVendor/Wordpress-SDK?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=VenomVendor/Wordpress-SDK&amp;utm_campaign=Badge_Grade)
[![Codebeat](https://codebeat.co/badges/35ec2b4c-4898-4288-a2fc-c4849bce2a50)](https://codebeat.co/projects/github-com-venomvendor-wordpress-sdk-release-sdk)
[![Code Coverage](https://codecov.io/gh/VenomVendor/Wordpress-SDK/branch/release%2Fsdk/graph/badge.svg)](https://codecov.io/gh/VenomVendor/Wordpress-SDK)
[![Documentation Status](https://readthedocs.org/projects/wordpress-sdk/badge/?version=latest)](http://wordpress-sdk.readthedocs.io/en/latest/?badge=latest)

---

## Installation
In `build.gradle` add the following dependencies

```coffeescript
repositories {
    maven {
        url  'http://dl.bintray.com/venomvendor/maven'
    }
}

dependencies {
    compile 'com.venomvendor:Wordpress-SDK:1.0.0-beta'
}
```

### Initialization
```java
WordpressSDK.initialize("VenomVendor.com/wp", false);
```

### Available APIs
```java
/* Request for posts */
PostRequests postReq = (PostRequests) NetworkHandler.getInstance(PostRequests.IDENTIFIER);

/* Request for comments */
CommentRequests commentReq = (CommentRequests) NetworkHandler.getInstance(CommentRequests.IDENTIFIER);

/* Request for categories */
CategoryRequests CategoryReq = (CategoryRequests) NetworkHandler.getInstance(CategoryRequests.IDENTIFIER);
```

### Get Recent Posts
```java
PostRequests postReq = (PostRequests) NetworkHandler.getInstance(PostRequests.IDENTIFIER);
postReq.getRecentPosts(new ResponseHandler<GetPost[]>() {
    @Override
    public void onResponse(@Nullable Response<GetPost[]> response,
                           @Nullable WordpressException ex) {
        if (response != null) {
            GetPost[] allPosts = response.body();
            for (GetPost post : allPosts) {
                Log.d(TAG, post.getTitle().getRendered());
            }
        } else if (ex != null) {
            Log.d(TAG, ex.getMessage());
        } else {
            Log.d(TAG, "Unknown Error");
        }
    }
});
```

### Get Posts
```java
PostRequests postReq = (PostRequests) NetworkHandler.getInstance(PostRequests.IDENTIFIER);

PostsParams.Builder params = new PostsParams.Builder();
params.setPage(1);
params.setOffset(10);
params.setResultSize(20);
params.setSearch("Mario");
params.setCategory("Mobile");
params.setSlug("some-slug");
params.setPublishedBefore("2017-03-18T13:18:35");
params.setPublishedAfter("2016-01-01T13:18:35");

postReq.getPosts(postParams.build(), new ResponseHandler<GetPost[]>() {
    @Override
    public void onResponse(@Nullable Response<GetPost[]> response,
                           @Nullable WordpressException ex) {
        if (response != null) {
            GetPost[] allPosts = response.body();
            for (GetPost post : allPosts) {
                Log.d(TAG, post.getTitle().getRendered());
            }
        } else if (ex != null) {
            Log.d(TAG, ex.getMessage());
        } else {
            Log.d(TAG, "Unknown Error");
        }
    }
});
```

## Setup
[Refer Sample ***Factory.json*** with comments](https://github.com/VenomVendor/Wordpress-SDK/blob/d2894b651c58cf9d6cb6d8153ced10b122c5c93f/config/request/Factory.json)
```json
{
  "dateError": "GMT+05:30",
  "perReq": 20,
  "perReqNew": 100,
  "secure": true,
  "domain": "VenomVendor.com",
  "protocolDefault": "http://",
  "protocolSecure": "https://",
  "path": {
    "root": "/wp-json/wp/v2/",
    "token": "token/",
    "posts": "posts/",
    "comments": "comments/",
    "category": "categories/"
  },
  "filter": {
    "fetchPost": {
      "id": "include",
      "publishedAfter": "after",
      "publishedBefore": "before",
      "page": "page",
      "search": "search",
      "offset": "offset",
      "resultSize": "per_page",
      "slug": "slug",
      "category": "categories"
    },
    "fetchComment": {
      "id": "include",
      "publishedAfter": "after",
      "publishedBefore": "before",
      "page": "page",
      "search": "search",
      "offset": "offset",
      "resultSize": "per_page",
      "postId": "post",
      "userId": "author",
      "inReplyTo": "parent"
    }
  },
  "create": {
    "newComment": {
      "postId": "post",
      "userId": "author",
      "email": "author_email",
      "name": "author_name",
      "comment": "content",
      "userAgent": "author_user_agent",
      "replyTo": "parent"
    }
  },
  "update": {
    "updateParams": {
      "postId": "post",
      "userId": "author",
      "email": "author_email",
      "name": "author_name",
      "comment": "content"
    }
  },
  "delete": {
    "oldComment": {
      "force": "force",
      "password": "password"
    }
  }
}
```

## Code Coverage
<a href="https://codecov.io/gh/VenomVendor/Wordpress-SDK"><img src="https://codecov.io/gh/VenomVendor/Wordpress-SDK/branch/release%2Fsdk/graphs/tree.svg" width="250" /><a/>

## Java Docs
<a target="_blank" href="https://venomvendor.github.io/Wordpress-SDK/docs/javadocs/v1.0.0/">Click here for Java documentation<a/>

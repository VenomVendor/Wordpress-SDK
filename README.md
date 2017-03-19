# Wordpress-SDK
Minimal Wordpress SDK for Android.

[![Build Status](https://travis-ci.org/VenomVendor/Wordpress-SDK.svg?branch=release%2Fsdk)](https://travis-ci.org/VenomVendor/Wordpress-SDK)
[![Code Factor](https://www.codefactor.io/repository/github/venomvendor/wordpress-sdk/badge)](https://www.codefactor.io/repository/github/venomvendor/wordpress-sdk)
[![Codacy](https://api.codacy.com/project/badge/Grade/8faaef1c15744e79a7a9f843a921bfb6)](https://www.codacy.com/app/VenomVendor/Wordpress-SDK?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=VenomVendor/Wordpress-SDK&amp;utm_campaign=Badge_Grade)
[![Codebeat](https://codebeat.co/badges/35ec2b4c-4898-4288-a2fc-c4849bce2a50)](https://codebeat.co/projects/github-com-venomvendor-wordpress-sdk-release-sdk)
[![Code Coverage](https://codecov.io/gh/VenomVendor/Wordpress-SDK/branch/release%2Fsdk/graph/badge.svg)](https://codecov.io/gh/VenomVendor/Wordpress-SDK)

---

## Setup
[Refer Sample Factory.json](https://github.com/VenomVendor/Wordpress-SDK/blob/release/sdk/config/request/Factory.json)
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

## Initialization

```java
WordpressSDK.initialize("VenomVendor.com/wp", false);
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
PostsParams.Builder postParams = new PostsParams.Builder();
postParams.setPage(1);
postParams.setOffset(10);
postParams.setResultSize(20);
postParams.setSearch("Mario");
postParams.setCategory("Mobile");
postParams.setSlug("some-slug");
postParams.setPublishedBefore("2017-03-18T13:18:35");
postParams.setPublishedAfter("2016-01-01T13:18:35");
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

## Code Coverage
<a href="https://codecov.io/gh/VenomVendor/Wordpress-SDK"><img src="https://codecov.io/gh/VenomVendor/Wordpress-SDK/branch/release%2Fsdk/graphs/tree.svg" width="100" /><a/>

## Java Docs
<a href="https://venomvendor.github.io/Wordpress-SDK/docs/">Java Documentation<a/>

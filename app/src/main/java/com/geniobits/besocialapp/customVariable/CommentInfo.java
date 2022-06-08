package com.geniobits.besocialapp.customVariable;

public class CommentInfo {
    String id;
    String postID;
    String name;
    String email;
    String body;

    CommentInfo(String id, String postID, String name, String email, String body)   {
        this.id = id;
        this.postID = postID;
        this.name = name;
        this.email = email;
        this.body = body;
    }

}

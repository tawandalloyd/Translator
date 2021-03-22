package com.example.translator.models

class User {
    var userName: String? = null
    var profileImage: String? = null

    constructor() {}
    constructor(userName: String?, profileImage: String?) {
        this.userName = userName
        this.profileImage = profileImage
    }
}
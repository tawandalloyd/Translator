package com.example.translator.models

class User {
    var firstname: String? = null
    var profileimage: String? = null
    var email:String ?=null
    var address:String ?=null
    var lastname:String ?=null


    constructor() {}
    constructor(firstname: String?, profileimage: String?,email:String ,address:String ,lastname:String) {
        this.firstname = firstname
        this.profileimage = profileimage
        this.email=email
        this.address=address
        this.lastname=lastname
    }
}
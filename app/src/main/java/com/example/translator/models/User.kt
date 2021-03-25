package com.example.translator.models

class User {
    var firstname: String? = null
    var profileimage: String? = null
    var address:String ?=null
    var lastname:String ?=null;
    var userid:String?=null;
    var phonenumber:String?=null;


    constructor() {}
    constructor(
        firstname: String?,
        profileimage: String?,
        address: String?,
        lastname: String?,
        userid: String?,
        phonenumber:String

    ) {
        this.firstname = firstname
        this.profileimage = profileimage
        this.address = address
        this.lastname = lastname
        this.userid = userid
        this.phonenumber=phonenumber
    }

}
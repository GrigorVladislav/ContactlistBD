package com.example.contactlistdb
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


open class Charters(
    @PrimaryKey
    open var Id: Int = 0,
    open var Name: String = "",
    open var ImageId: Int? = null,
    open var Role: String? = null,
    open var Details: String? = null): RealmObject() {
}
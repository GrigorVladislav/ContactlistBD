package com.example.contactlistdb

import io.realm.Realm

interface DatabaseInterface {
    fun addCharter(realm: Realm, charter: Charters): Boolean
    fun deleteCharter(realm: Realm, id: Int): Boolean
    fun editCharter(realm: Realm, charter: Charters): Boolean
    fun getContact(realm: Realm, id: Int): Charters?
}
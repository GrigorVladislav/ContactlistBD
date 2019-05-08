package com.example.contactlistdb

import io.realm.Realm

class Database: DatabaseInterface {

    override fun addCharter(realm: Realm, charter: Charters): Boolean {
        try {
            realm.executeTransaction {
                it.copyToRealm(charter)}
            return true
        } catch (e: Exception) {
            print(e)
            return false
        }
    }

    override fun deleteCharter(realm: Realm, id: Int): Boolean {
        try {
            realm.executeTransaction {
                it.where(Charters::class.java).equalTo("Id", id).findFirst()!!.deleteFromRealm()}
            return true
        } catch (e: Exception) {
            print(e)
            return false
        }
    }

    override fun editCharter(realm: Realm, charter: Charters): Boolean {
        try {
            realm.executeTransaction {
                it.copyToRealmOrUpdate(charter)}
            return true
        } catch (e: Exception) {
            print(e)
            return false
        }
    }

    override fun getContact(realm: Realm, id: Int): Charters? {
        var charter = Charters()
        try {
            realm.executeTransaction {
                charter = it.where(Charters :: class.java).equalTo("Id", id).findFirst()!!
            }
        } catch (ex: Exception) {
            print(ex)
        }
        return charter
    }

    fun getAllContacts(realm: Realm): ArrayList<Charters> {
        var charters = ArrayList<Charters>()
        try {
            realm.executeTransaction {
                var realmData = realm.where(Charters::class.java).findAll()
                for(contact in realmData){
                    charters.add(contact)
                }
            }
        } catch (ex: Exception) {
            print(ex)
        }

        return charters
    }

    fun getNewUniqueIndex(realm: Realm): Int {
        var key = 0
        try {
            realm.executeTransaction {
                key = it.where(Charters::class.java).max("Id")!!.toInt() + 1
            }
        } catch(ex: Exception) {
            print(ex)
        }
        return key
    }

    fun initDataIfNotExist(realm: Realm): Boolean {
        try {
            if(realm.isEmpty){
                for(charter in CharterInfo.All){
                    this.addCharter(realm,charter)
                }
                return true
            }
            return false
        } catch (e: Exception) {
            print(e)
            return false
        }
    }
}
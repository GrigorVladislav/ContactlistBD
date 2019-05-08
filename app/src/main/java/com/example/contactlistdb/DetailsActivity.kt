package com.example.contactlistdb

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.telecom.Call
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity:AppCompatActivity() {

    private var entry_id = -1
    lateinit var charter : Charters
    var helper = Database()
    lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        entry_id =intent.extras!!.get("ID") as Int
        realm = Realm.getDefaultInstance()
        charter =helper.getContact(realm,entry_id)!!

        fillData()
    }

    private fun fillData() {
        charterName.text ="${charter.Name}"
        charterImage.setImageResource(charter.ImageId ?: R.drawable.hollow)
        charterDetails.text ="Details: ${charter.Details}"
        rule.text = charter.Role
    }
}
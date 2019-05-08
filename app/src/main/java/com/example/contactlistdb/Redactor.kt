package com.example.contactlistdb

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration
import io.realm.internal.OsSharedRealm
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_redactor.*

class Redactor:AppCompatActivity() {

    private var isEditing = false
    private var entryId = -1
    private lateinit var tempCharter: Charters
    var helper = Database()
    lateinit  var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redactor)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        Realm.init(this)

        realm = Realm.getDefaultInstance()

        if(intent.hasExtra("EDIT")) {
            isEditing = intent.getBooleanExtra("EDIT", false)
            entryId = intent.getIntExtra("ID", -1)
    }
        fillFields()

    val fab: View = fab
        fab.setOnClickListener{
        if(!add_edit_rule.text.isEmpty()&& !add_edit_rule.text.isEmpty()){
            saveChanges()

            val intent= Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }else {
        Toast.makeText(this,"Name and Rule fields couldn't be blank!",Toast.LENGTH_SHORT ).show()
        }

        }
}

    private fun fillFields() {
        if (isEditing == true) {
            tempCharter = helper.getContact(realm,entryId)!!
            add_edit_charter.setText(tempCharter.Name)
            add_edit_details.setText(tempCharter.Details)
            add_edit_rule.setText(tempCharter.Role)
        }
    }
    private fun getDataFromFields():Charters {
        var images = arrayOf(R.drawable.hollow,
            R.drawable.jiji,
            R.drawable.mato,
            R.drawable.nailsmith,
            R.drawable.salubra,
            R.drawable.sly,
            R.drawable.zote)

        var name = add_edit_charter.text.toString()
        var details = add_edit_details.text.toString()
        var role = add_edit_rule.text.toString()

        return Charters(entryId,name,images.random(),role,details)
    }

    private fun saveChanges(){
        var charter = getDataFromFields()
        var notification = Notification()
        if (isEditing == true){
            var res = helper.editCharter(realm,charter)
            if(res){
                var notificationIntent = Intent(this,MainActivity::class.java)
                notificationIntent.putExtra("openId",charter.Id)
                notification.makeNotifications(this,1,R.drawable.edit_white_24dp,"Charter edited",
                    "Charter ${charter.Name} successfully updated",notificationIntent)
            }
        }else {
            charter.Id = helper.getNewUniqueIndex(realm)
            var res = helper.addCharter(realm,charter)
            if (res){
                var notificationIntent = Intent(this,MainActivity::class.java)
                notificationIntent.putExtra("openId",charter.Id)
                notification.makeNotifications(this,2,R.drawable.add_white_24dp,"Charter added",
                    "Charter ${charter.Name} successfully added",notificationIntent)
            }
        }
    }
}
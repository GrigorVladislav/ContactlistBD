package com.example.contactlistdb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.view.MenuItem
import android.view.View
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var charters = ArrayList<Charters>()
    lateinit var adapter: CharterAdapter

    var helper = Database()
    lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)

        realm = Realm.getDefaultInstance()
        helper.initDataIfNotExist(realm)
        charters = helper.getAllContacts(realm)
        recyclerViewAdapter()
        detailsExtra()

    val fab: View = fab
        fab.setOnClickListener{
        val intent = Intent(baseContext, Redactor::class.java)
            intent.putExtra("ID", -1)
            intent.putExtra("EDIT",false)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        detailsExtra()
    }

    private fun detailsExtra(){
    if (intent.hasExtra("openWithId")){
        var id=intent.getIntExtra("openWithId",0)
        goToDetails(null,id)
    }
    }

    private fun recyclerViewAdapter(){
        adapter = CharterAdapter(charters, this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setListener(object :CharterAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(baseContext,DetailsActivity::class.java)
                intent.putExtra("ID",charters[position].Id)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        registerForContextMenu(recyclerView)
    }



    private fun goToDetails(item: MenuItem?,charterId:Int?){
        var id = 0
        if(charterId !=null){
            id = charterId
        }else if ( item != null){
            id = charters[item.itemId].Id
        }
        val intent = Intent(baseContext,DetailsActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }

    private fun goToEdit(item: MenuItem?){
        if(item !=null){
            val intent = Intent(baseContext,Redactor::class.java)
            intent.putExtra("ID",charters[item.itemId].Id)
            intent.putExtra("EDIT",true)
            startActivity(intent)
        }
    }

    private fun deleteItem(item: MenuItem?){
        if(item != null){
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Delete charter")
                .setMessage("Confirm removal")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setNegativeButton("Cancel"){_,_ -> }
                .setPositiveButton("Yes"){_,_ ->
            var res = helper.deleteCharter(realm,charters[item.itemId].Id)
            charters.removeAt(item.itemId)
                    adapter.notifyDataSetChanged()

                    if (res){
                        var notification = Notification()
                        var notificationIntent =Intent(this, MainActivity::class.java)
                        notification.makeNotifications(this,0,R.drawable.delete_white_24dp,
                            "Contact deleted","Successfully deleted", notificationIntent)
                    }
                }
            var alert =builder.create()
                alert.show()
        }
    }
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item!!.groupId) {
            0 -> {
                goToDetails(item, null)
            }
            1 -> {
                goToEdit(item)
            }
            2 -> {
                deleteItem(item)
            }
        }
        return true
    }


    }



package br.com.galaga.firebaseapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*
import kotlin.math.log

class ListActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val listReference = database.getReference("listusuario")
    val list = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val user = list.get(position)
            Snackbar.make(view, "User: ${user.nome} Selecionado", Snackbar.LENGTH_LONG)
                    .setAction("DELETE", View.OnClickListener {
                        val user = list.get(position)
                        listReference.child(user.uuid).removeValue()
                    }).show()
            true

        }

        listReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list.clear()
                dataSnapshot.children.mapNotNullTo(list,{
                    it.getValue(User::class.java)
                })
                val adapter = ArrayAdapter<User>(baseContext, android.R.layout.simple_list_item_1, list)
                listView.adapter = adapter
            }

        })

        fab.setOnClickListener { view ->
            val user = User("Jp","jp@email.com","999999999", "18/11/1999")
            val key = listReference.push().key
            user.uuid = key
            listReference.child(key).setValue(user)
            Snackbar.make(view, "User $user, adicionado", Snackbar.LENGTH_LONG).show()

        }
    }

}

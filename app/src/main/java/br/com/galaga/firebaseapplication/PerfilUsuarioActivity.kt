package br.com.galaga.firebaseapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_perfil_usuario.*

class PerfilUsuarioActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val userReference = database.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)

        userReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(User::class.java)
                if (value != null) {
                    txtNome.text = value.nome
                    txtTelefone.text = value.telefone
                    txtEmail.text = value.email
                    txtDtaNascimento.text = value.dataNascimento
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun save(view: View) {
        val data = edtDtaNascimento.text.toString()
        val nome = edtNome.text.toString()
        val telefone = edtTelefone.text.toString()
        val email = edtEmail.text.toString()
        val user = User(nome, data, email, telefone)
        userReference.setValue(user)
    }
}

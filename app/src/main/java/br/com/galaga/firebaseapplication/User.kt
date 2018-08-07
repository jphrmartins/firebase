package br.com.galaga.firebaseapplication

import android.provider.ContactsContract

class User (){
    var uuid:String = ""
    var nome: String = ""
    var email: String = ""
    var telefone: String = ""
    var dataNascimento: String = ""

    constructor(nome:String, email: String, telefone:String, dataNascimento:String) : this() {
        this.nome = nome
        this.email = email
        this.telefone = telefone
        this.dataNascimento = dataNascimento
    }

    override fun toString(): String {
        return "{nome: $nome, email: $email, telefone: $telefone, dataNascimento: $dataNascimento}"
    }
}
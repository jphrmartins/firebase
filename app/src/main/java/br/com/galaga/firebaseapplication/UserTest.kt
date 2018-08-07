package br.com.galaga.firebaseapplication

class UserTest {
    var nome = ""
    var idade = 0
    constructor(idade:Int, nome:String) {
        this.nome = nome
        this.idade = idade
    }
    constructor()
    override fun toString(): String {
        return "{nome: $nome, Idade: $idade}"
    }
}

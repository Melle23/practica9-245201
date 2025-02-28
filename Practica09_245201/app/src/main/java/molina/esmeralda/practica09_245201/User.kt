package molina.esmeralda.practica09_245201

data class User(
    var firstName: String = "",
    var lastName: String = "",
    var age: String = ""
) {
    constructor() : this("", "", "")

    override fun toString()=firstName+"\t"+lastName+"\t"+age

}
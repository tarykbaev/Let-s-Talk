package kg.turar.arykbaev.letstalk.extension


fun String.isValidEmail(): Boolean {
    return true
    //(this.count() == 23) && (this.substringAfterLast("@") == "manas.edu.kg") && (this.count { it == '@' } == 1)
}
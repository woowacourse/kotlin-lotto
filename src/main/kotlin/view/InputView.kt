package view

object InputView {

    fun readNumber(): Int? = readln().toIntOrNull()

    fun readNumbers(): List<Int>? = readln().split(",").map { it.trim().toIntOrNull() ?: return null }
}

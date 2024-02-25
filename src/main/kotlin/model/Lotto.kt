package model

class Lotto(private val numbers: List<Int>) {
    fun getNumbers(): List<Int> = numbers

    override fun toString(): String {
        return numbers.sorted().joinToString(", ", "[", "]")
    }
}

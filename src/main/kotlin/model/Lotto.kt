package model

open class Lotto(val numbers: List<Int>) {
    override fun toString(): String {
        return numbers.sorted().joinToString(", ", "[", "]")
    }
}

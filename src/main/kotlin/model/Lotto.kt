package model

class Lotto(private val numbers: List<LottoNumber>) {
    fun getNumbers(): List<LottoNumber> = numbers

    override fun toString(): String {
        return numbers.joinToString(", ", "[", "]")
    }
}

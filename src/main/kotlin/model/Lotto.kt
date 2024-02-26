package model

open class Lotto(val numbers: List<LottoNumber>) {
    override fun toString(): String {
        return numbers.map { it.number }.sorted().joinToString(", ", "[", "]")
    }
}

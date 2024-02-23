package model

private const val DEFAULT_SEPARATOR = ", "
private const val START = "["
private const val END = "]"

class Lotto(val numbers: List<LottoNumber>) {
    override fun toString(): String {
        return numbers.map { it.number }.sorted().joinToString(DEFAULT_SEPARATOR, START, END)
    }
}

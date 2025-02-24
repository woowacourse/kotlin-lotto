package lotto.model

class Lotto(
    val numbers: LottoNumbers,
    val prize: Amount,
) {
    fun getNumbers(): List<LottoNumber> = numbers.numberList.map { it }

    fun findNumber(number: LottoNumber): Boolean = numbers.include(number)

    fun countMatchedNumber(other: Lotto): Int = numbers.numberList.count { other.findNumber(it) }
}

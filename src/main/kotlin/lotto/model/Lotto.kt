package lotto.model

class Lotto(
    val numbers: LottoNumbers,
) {
    fun getNumbers(): List<LottoNumber> = numbers.numberList.map { it }

    fun findNumber(number: LottoNumber): Boolean = numbers.include(number)
}

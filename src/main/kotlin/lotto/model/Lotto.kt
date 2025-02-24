package lotto.model

class Lotto(
    val numbers: LottoNumbers,
    val prize: Amount,
) {
    fun getNumbers(): List<LottoNumber> = numbers.numberList.map { it }
}

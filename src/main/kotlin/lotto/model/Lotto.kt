package lotto.model

class Lotto(
    val numbers: LottoNumbers,
    val prize: Amount,
) {
    fun getNumbers(): List<LottoNumber> {
        val returnList: MutableList<LottoNumber> = mutableListOf()
        numbers.numberList.forEach { returnList.add(it) }
        return returnList
    }
}

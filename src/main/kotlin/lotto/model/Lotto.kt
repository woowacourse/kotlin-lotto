package lotto.model

class Lotto(
    val numbers: LottoNumbers,
) {
    fun getNumbers(): List<LottoNumber> {
        val returnList: MutableList<LottoNumber> = mutableListOf()
        numbers.numberList.forEach { returnList.add(it) }
        return returnList
    }

    fun countMatchingNumber(lotto: Lotto): Int =
        this
            .getNumbers()
            .intersect(lotto.numbers.numberList)
            .size

    fun checkBonusNumber(lottoNumber: LottoNumber): Boolean = this.getNumbers().contains(lottoNumber)
}

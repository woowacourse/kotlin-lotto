package domain

class Lotto(val numbers: List<LottoNumber>) {

    init {
        require(numbers.size == 6) { LOTTO_SIZE_ERROR_MESSAGE }
        require(numbers.toSet().size == 6) { LOTTO_DUPLICATE_ERROR_MESSAGE }
    }

    fun countMatchNumber(lotto: Lotto): Int {
        return numbers.filter { lotto.numbers.contains(it) }.size
    }

    fun hasBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.contains(bonusNumber)
    }

    fun matchLotto(lotto: Lotto, bonusNumber: LottoNumber): Rank? =
        Rank.valueOf(countMatchNumber(lotto), hasBonusNumber(bonusNumber))

    companion object {
        const val LOTTO_SIZE_ERROR_MESSAGE = "당첨 번호가 6개가 아닙니다"
        const val LOTTO_DUPLICATE_ERROR_MESSAGE = "당첨 번호가 중복되었습니다."
    }
}

package lotto

class WinningLotto private constructor(
    private val lottoNumbers: List<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    fun findRank(lotto: Lotto): Rank {
        val countOfMatch =
            lottoNumbers.intersect(lotto.numberList).size
        val bonusMatched = lotto.numberList.contains(bonusNumber)
        return Rank.valueOf(countOfMatch, bonusMatched)
    }

    companion object {
        fun createOrNull(
            inputNumbers: List<LottoNumber>,
            bonusNumber: LottoNumber,
        ): WinningLotto? {
            if (inputNumbers.contains(bonusNumber)) return null
            return WinningLotto(inputNumbers, bonusNumber)
        }
    }
}

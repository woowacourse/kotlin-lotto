package lotto.domain.model

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
        const val NUMBER_LIST_DUPLICATED = "[ERROR] 당첨 번호가 중복됩니다."
        const val BONUS_NUMBER_DUPLICATED = "[ERROR] 보너스 번호가 중복됩니다."

        fun valueOf(
            inputNumbers: List<LottoNumber>,
            bonusNumber: LottoNumber,
        ): WinningLotto {
            require(!inputNumbers.contains(bonusNumber)) { BONUS_NUMBER_DUPLICATED }
            require(inputNumbers.distinctBy { it.value } == inputNumbers) { NUMBER_LIST_DUPLICATED }
            return WinningLotto(inputNumbers, bonusNumber)
        }

        fun createOrNull(
            inputNumbers: List<LottoNumber>,
            bonusNumber: LottoNumber,
        ): WinningLotto? {
            if (inputNumbers.contains(bonusNumber)) return null
            if (inputNumbers.distinctBy { it.value } != inputNumbers) return null
            return WinningLotto(inputNumbers, bonusNumber)
        }
    }
}

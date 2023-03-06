package model

import model.domain.Rank

class WinningNumbers(private val lotto: Lotto, private val bonusNumber: LottoNumber) {

    init {
        require(checkDuplicateNumber()) { DUPLICATE_BONUS_NUMBER }
    }

    private fun checkDuplicateNumber() = lotto.ticket.contains(bonusNumber)

    private fun checkRank(lotto: Lotto): Rank {
        val matchOfCount = lotto.getMatchOfNumber(this.lotto)
        val isMatchBonus = lotto.isMatchBonus(this.bonusNumber)
        return Rank.valueOf(matchOfCount, isMatchBonus)
    }

    fun getLottoResult(lottoList: List<Lotto>): LottoResult {
        val rankList = lottoList.map { checkRank(it) }
        return LottoResult(rankList)
    }

    companion object {
        private const val DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 번호입니다"
    }
}

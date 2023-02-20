package domain.lotto

import domain.lotto.number.LottoNumber

class WinningLotto(numbers: List<LottoNumber>, bonusNumber: LottoNumber) : Lotto(numbers) {
    init {
        check(!this.contains(bonusNumber)) { ERROR_MESSAGE_CONTAINED_BONUS_NUMBER }
    }

    fun matchLotto(purchasedLotto: PurchasedLotto, bonusNumber: LottoNumber): Pair<Int, Boolean> {
        val countOfMatch = matchPurchasedLotto(purchasedLotto)
        val matchBonus = matchBonusNumber(purchasedLotto, bonusNumber)

        return Pair(countOfMatch, matchBonus)
    }

    private fun matchPurchasedLotto(purchasedLotto: PurchasedLotto): Int =
        purchasedLotto.count { this.contains(it) }

    private fun matchBonusNumber(purchasedLotto: PurchasedLotto, bonusNumber: LottoNumber): Boolean =
        purchasedLotto.any { it.value == bonusNumber.value }

    companion object {
        private const val ERROR_MESSAGE_CONTAINED_BONUS_NUMBER = "[ERROR] 6개의 로또 당첨 번호에 보너스 번호가 포함될 수 없습니다."
    }
}

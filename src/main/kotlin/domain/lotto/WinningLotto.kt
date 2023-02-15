package domain.lotto

class WinningLotto(numbers: List<LottoNumber>) : Lotto(numbers) {
    fun matchLotto(purchasedLotto: PurchasedLotto, bonusNumber: BonusNumber): Pair<Int, Boolean> {
        val countOfMatch = matchPurchasedLotto(purchasedLotto)
        val matchBonus = matchBonusNumber(purchasedLotto, bonusNumber)

        return Pair(countOfMatch, matchBonus)
    }

    private fun matchPurchasedLotto(purchasedLotto: PurchasedLotto): Int =
        purchasedLotto.count { this.contains(it) }

    private fun matchBonusNumber(purchasedLotto: PurchasedLotto, bonusNumber: BonusNumber): Boolean =
        purchasedLotto.any { it.value == bonusNumber.value }
}

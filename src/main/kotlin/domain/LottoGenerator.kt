package domain

import domain.model.PurchaseMoney
import domain.model.lotto.Lotto

class LottoGenerator(
    private val numberGenerator: () -> Set<Int> = {
        (MINIMUM_NUMBER..MAXIMUM_NUMBER).drawLotteryNumbers()
    }
) {

    fun generateLottos(purchaseMoney: PurchaseMoney): List<Lotto> {
        purchaseMoney.validateMoneyUnit()

        return List(purchaseMoney.getNumberOfLottos()) {
            Lotto(numberGenerator())
        }
    }

    private fun PurchaseMoney.validateMoneyUnit() = require(this.money % domain.LottoGenerator.LOTTO_PRICE == domain.LottoGenerator.ZERO) {
        domain.LottoGenerator.NUMBER_UNIT_ERROR
    }

    private fun PurchaseMoney.getNumberOfLottos(): Int = this.money / domain.LottoGenerator.LOTTO_PRICE

    companion object {
        private const val MINIMUM_NUMBER = 1
        private const val MAXIMUM_NUMBER = 45
        private const val NUMBER_COUNT = 6
        private const val LOTTO_PRICE = 1000
        private const val NUMBER_UNIT_ERROR = "[ERROR] 천원 단위로 입력해주세요."
        private const val ZERO = 0

        private fun IntRange.drawLotteryNumbers() = this.shuffled().subList(ZERO, NUMBER_COUNT).sorted().toSet()
    }
}

package domain

import domain.lottogenerator.AutomaticLottoGenerator
import domain.lottogenerator.ManualLottoGenerator
import domain.model.PurchaseMoney

class LottoSeller(
    private val automaticLottoGenerator: AutomaticLottoGenerator = AutomaticLottoGenerator(),
    private val manualLottoGenerator: ManualLottoGenerator = ManualLottoGenerator()
) {

    fun receiveMoneyFromCustomer(money: PurchaseMoney, manualLottoCount: Int){
        money.validateMoneyUnit()
        manualLottoCount.validateManualLottoCount(money.calcGeneratingLottoCount())
    }

    private fun PurchaseMoney.calcGeneratingLottoCount(): Int = this.money / LOTTO_PRICE

    private fun PurchaseMoney.validateMoneyUnit() = require(this.money % LOTTO_PRICE == 0) {
        MONEY_UNIT_ERROR
    }

    private fun Int.validateManualLottoCount(totalLottoCount: Int) = require(totalLottoCount >= this) {
        MANUAL_LOTTO_COUNT_ERROR
    }

    companion object {
        private const val LOTTO_PRICE = 1000

        private const val MONEY_UNIT_ERROR = "[ERROR] 천원 단위의 돈을 입력해주세요."
        private const val MANUAL_LOTTO_COUNT_ERROR = "[ERROR] 구매하려는 로또의 개수보다 수동으로 발급되는 로또의 개수가 많을 수 없습니다."
    }
}

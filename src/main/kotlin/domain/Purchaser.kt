package domain

import domain.lotto.LottoBundle
import domain.lotto.generator.LottoVendingMachine
import util.PREFIX

class Purchaser(spentMoney: Money) {
    val totalLottoCount: Int = (spentMoney / LOTTO_PRICE).toInt()
    var manualLottoCount: Int = UNINITIALIZED_INT
        private set
    lateinit var purchasedLottoBundle: LottoBundle
        private set

    init {
        require(totalLottoCount > 0) { "$PREFIX 구매할 수 있는 로또가 없습니다. 구매하려는 로또 개수: $totalLottoCount" }
    }

    fun decideManualLottoCount(manualCount: Int) {
        validateLottoCount(manualCount)
        manualLottoCount = manualCount
        val autoLottoCount = totalLottoCount - manualCount
        purchasedLottoBundle = LottoVendingMachine.getLottoBundle(autoLottoCount)
    }

    fun purchaseManualLottoBundle(manualLottoBundle: LottoBundle) {
        validateLottoBundleToAdd(manualLottoBundle)
        purchasedLottoBundle += manualLottoBundle
    }

    private fun validateLottoCount(manualCount: Int) {
        require(manualCount >= 0) { "$PREFIX 수동으로 구매할 로또의 개수가 음수일 수 없습니다. 수동 로또 개수: $manualCount" }
        require(manualCount <= totalLottoCount) { "$PREFIX 수동으로 구매할 로또의 개수가 총 로또 개수보다 많을 수 없습니다. 수동 로또 개수: $manualCount, 총 로또 개수: $totalLottoCount" }
    }

    private fun validateLottoBundleToAdd(lottoBundle: LottoBundle) {
        require(manualLottoCount == lottoBundle.size) { "$PREFIX 수동으로 구매한 로또 개수가 맞지 않습니다. 수동으로 구매할 로또 개수: $manualLottoCount, 발행된 수동 로또 개수: ${lottoBundle.size}" }
        require((purchasedLottoBundle.size + manualLottoCount) == totalLottoCount) { "$PREFIX 이미 수동 로또가 발행되었습니다." }
    }

    companion object {
        private val LOTTO_PRICE: Money = Money(1000)
        private const val UNINITIALIZED_INT: Int = -1
    }
}

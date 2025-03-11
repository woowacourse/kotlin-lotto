package lotto.model

class LottoMarket(
    private val purchaseAmount: Amount,
    private val manualLottoNumbers: List<List<Int>>,
) {
    private val manualQuantity: Int = manualLottoNumbers.size
    private val autoLottoQuantity: Int = purchaseAmount.getAutoLottoQuantity(manualQuantity)

    init {
        validateManualLottoQuantity()
    }

    fun buy(
        manualLottoMachine: ManualLottoMachine,
        autoLottoMachine: AutoLottoMachine,
    ): List<Lotto> = getLottos(manualLottoMachine) + getLottos(autoLottoMachine)

    private fun getLottos(lottoMachine: LottoMachine): List<Lotto> = lottoMachine.generate(manualLottoNumbers, autoLottoQuantity)

    private fun validateManualLottoQuantity() {
        require(purchaseAmount.isAffordable(manualQuantity)) {
            "[ERROR] 낸 금액보다 많은 수동 로또를 살 수 없습니다. 금액: ${purchaseAmount.value}, 수동 로또: ${manualQuantity}장"
        }
    }

    companion object {
        const val EMPTY_LOTTO_QUANTITY = 0
    }
}

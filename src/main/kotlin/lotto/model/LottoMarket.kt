package lotto.model

class LottoMarket(
    private val amount: Amount,
    private val manualQuantity: Int,
    private val manualLottoMachine: ManualLottoMachine = ManualLottoMachine(),
    private val autoLottoMachine: AutoLottoMachine = AutoLottoMachine(),
) {
    val autoLottoQuantity: Int = amount.getAutoLottoQuantity(manualQuantity)

    init {
        validateManualLottoQuantity()
    }

    fun buy(
        manualNumbers: List<List<Int>> = emptyList(),
        lottoWallet: LottoWallet,
    ) {
        lottoWallet.addAll(
            when (manualNumbers.isNotEmpty()) {
                true -> manualLottoMachine.generate(manualTicket = manualNumbers)
                false -> autoLottoMachine.generate(quantity = autoLottoQuantity)
            },
        )
    }

    private fun validateManualLottoQuantity() {
        require(amount.isAffordable(manualQuantity)) {
            "[ERROR] 낸 금액보다 많은 수동 로또를 살 수 없습니다. 금액: $amount, 수동 로또: ${manualQuantity}장"
        }
    }

    companion object {
        const val EMPTY_LOTTO_QUANTITY = 0
    }
}

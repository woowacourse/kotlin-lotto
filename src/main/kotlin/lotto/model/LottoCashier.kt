package lotto.model

class LottoCashier {
    private val manualLottoMachine = ManualLottoMachine()
    private val autoLottoMachine = AutoLottoMachine()

    fun buyLottos(
        purchaseAmount: Amount,
        manualLottoNumbers: List<List<Int>>,
    ): LottoWallet {
        val manualLottoQuantity = manualLottoNumbers.size
        val lottoMarket = LottoMarket(purchaseAmount, manualLottoQuantity)
        val lottoWallet = LottoWallet()

        val manualLottos = lottoMarket.buy(manualLottoMachine, manualLottoNumbers)
        lottoWallet.addAll(manualLottos)

        val autoLottos = lottoMarket.buy(autoLottoMachine, emptyList())
        lottoWallet.addAll(autoLottos)

        return lottoWallet
    }
}

package lotto.domain

class LottoStore(private val lottoFactory: LottoFactory) {

    fun buyLottoes(purchaseMoney: PurchaseMoney, manualLottoNumbers: List<List<Int>> = listOf()): LottoBunch {
        val totalLottoCount = purchaseMoney.getPurchaseCount()
        return LottoBunch(
            buyManualLottoes(manualLottoNumbers),
            buyAutoLottoes(totalLottoCount - manualLottoNumbers.size),
        )
    }

    private fun buyManualLottoes(manualNumberLines: List<List<Int>>): LottoBunch =
        LottoBunch(manualNumberLines.map { manualNumber -> lottoFactory.createLotto(manualNumber) })

    private fun buyAutoLottoes(lottoCount: Int): LottoBunch =
        LottoBunch(List(lottoCount) { lottoFactory.createLotto() })
}

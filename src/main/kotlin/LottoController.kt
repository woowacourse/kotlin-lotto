class LottoController(
    private val numericValidator: NumericValidator = NumericValidator(),
    private val lottoGenerator: LottoGenerator = LottoGenerator(),
    private val profitCalculator: ProfitCalculator = ProfitCalculator()
) {

    private val purchaseMoney: PurchaseMoney by lazy {
        val money = numericValidator.validate(InputView.requestPurchaseMoney())

        PurchaseMoney(money)
    }

    private val winningNumbers: WinningNumbers by lazy {
        val catchNumbers = InputView.requestCatchNumbers().map { number ->
            numericValidator.validate(number)
        }.toSet()

        val bonusNumber = numericValidator.validate(InputView.requestBonusNumber())

        WinningNumbers(catchNumbers, bonusNumber)
    }

    fun play() {
        val purchasedLottos = PurchasedLottos(lottoGenerator.generateLottos(purchaseMoney))
        ResultView.printPurchasedNumberOfLottos(purchasedLottos.lottos.size)
        ResultView.printPurchasedLottos(purchasedLottos)
        val lottoResults = purchasedLottos.getTotalLottoResults(winningNumbers)
        val profit = profitCalculator.getProfit(purchaseMoney, lottoResults)
    }
}

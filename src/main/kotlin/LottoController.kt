class LottoController(
    private val numericValidator: NumericValidator = NumericValidator(),
    private val lottoGenerator: LottoGenerator = LottoGenerator()
) {

    private val purchaseMoney: PurchaseMoney by lazy {
        val money = numericValidator.validate(InputView.requestPurchaseMoney())
        PurchaseMoney(money)
    }

    fun play() {
        val lottos = PurchasedLottos(lottoGenerator.generateLottos(purchaseMoney))
    }
}

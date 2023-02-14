class LottoGenerator(
    private val money: Int,
    private val lottoPrice: Int = LOTTO_PRICE
) {
    init {
        require(money % lottoPrice == 0) {
            moneyError(lottoPrice)
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
        private fun moneyError(lottoPrice: Int): String = "[ERROR] %s원 단위로 입력해주세요.".format(lottoPrice)

    }
}

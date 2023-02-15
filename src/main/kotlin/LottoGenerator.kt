class LottoGenerator(
    money: Int,
    private val lottoPrice: Int = LOTTO_PRICE
) {
    init {
        require(money % lottoPrice == ZERO_MONEY) {
            moneyError(lottoPrice)
        }
        require(money > ZERO_MONEY) {
            NEGATIVE_MONEY_ERROR
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val ZERO_MONEY = 0
        private const val NEGATIVE_MONEY_ERROR = "[ERROR] 음수는 입력하실 수 없습니다."
        private fun moneyError(lottoPrice: Int): String = "[ERROR] %s원 단위로 입력해주세요.".format(lottoPrice)
    }
}

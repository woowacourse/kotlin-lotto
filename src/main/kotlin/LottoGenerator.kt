class LottoGenerator() {

    fun generateLottos(purchaseMoney: PurchaseMoney) {
        require(purchaseMoney.money % LOTTO_PRICE == ZERO) {
            NUMBER_UNIT_ERROR
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val NUMBER_UNIT_ERROR = "[ERROR] 천원 단위로 입력해주세요."
        private const val ZERO = 0
    }
}

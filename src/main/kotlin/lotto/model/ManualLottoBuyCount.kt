package lotto.model

class ManualLottoBuyCount(val count: Int, val lottoBuyPrice: LottoBuyPrice) {
    init {
        require(count <= lottoBuyPrice.getBuyCount()) { PRICE_ERROR_MESSAGE }
    }

    companion object {
        private const val PRICE_ERROR_MESSAGE = "수동으로 구입하는 로또의 가격은 로또 구입 가격이하여야 합니다."
    }
}

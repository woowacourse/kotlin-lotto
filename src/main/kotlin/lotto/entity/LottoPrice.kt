package lotto.entity

class LottoPrice(val value: Int) {
    init {
        require(value > 0) { ERROR_MESSAGE_LOTTO_PRICE_IS_NOT_POSITIVE_INTEGER }
    }

    companion object {
        const val DEFAULT_LOTTO_PRICE = 1000
        private const val ERROR_MESSAGE_LOTTO_PRICE_IS_NOT_POSITIVE_INTEGER = "로또 가격은 양의 정수여야 합니다"
    }
}

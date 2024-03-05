package lotto.model

data class LottoMachine(
    val amount: Int,
    val manual: Int = 0,
    val auto: Int = amount / 1000 - manual
) {
    init {
        require(amount >= LOTTO_PRICE) { "구입금액은 ${amount}원보다 큰 ${LOTTO_PRICE}원 이상이어야 합니다." }
        require(amount - manual * LOTTO_PRICE >= 0) { "수동 로또 ${manual}개를 ${amount}원으로 살 수 없습니다." }
    }

    fun calculateMargin(prize: Prize): Margin {
        return Margin(prize.amount * LOTTO_PRICE / amount / LOTTO_PRICE.toDouble())
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}

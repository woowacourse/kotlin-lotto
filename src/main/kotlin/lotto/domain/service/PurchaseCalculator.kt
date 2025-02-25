package lotto.domain.service

import lotto.domain.model.Lotto

class PurchaseCalculator {
    fun getLottoCount(
        amount: Int,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): Int {
        val count = amount / lottoPrice
        require(count >= MIN_LOTTO_COUNT_SIZE) { INVALID_LOTTO_COUNT_MESSAGE.format(amount) }
        return count
    }

    private companion object {
        const val INVALID_LOTTO_COUNT_MESSAGE = "%d원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다."
        const val MIN_LOTTO_COUNT_SIZE = 1
    }
}

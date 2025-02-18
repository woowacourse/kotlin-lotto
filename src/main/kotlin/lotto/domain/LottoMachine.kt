package lotto.domain

class LottoMachine {
    val price: Int = LOTTO_PRICE

    fun getLottoCount(purchaseAmount: Int): Int {
        return purchaseAmount / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
    }
}

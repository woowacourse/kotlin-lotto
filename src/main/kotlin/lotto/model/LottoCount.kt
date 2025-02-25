package lotto.model

class LottoCount(
    val number: Int,
) {
    fun isAvailPurchaseLottoCount(lottoCount: LottoCount): Boolean = this.number >= lottoCount.number
}

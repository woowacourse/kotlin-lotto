package lotto.model

data class LottoCount(
    private var _number: Int,
) {
    val number
        get() = if (_number < 0) 0 else _number

    fun minus(lottoCount: LottoCount): LottoCount = LottoCount(this.number - lottoCount.number)

    fun isPurchasableLottoCount(lottoCount: LottoCount): Boolean = this.number >= lottoCount.number
}

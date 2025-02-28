package lotto.model

data class LottoCount(
    private var _number: Int,
) {
    val number = _number

    init {
        if (_number < 0) _number = 0
    }

    fun minus(lottoCount: LottoCount): LottoCount = LottoCount(this.number - lottoCount.number)

    fun isPurchasableLottoCount(lottoCount: LottoCount): Boolean = this.number >= lottoCount.number
}

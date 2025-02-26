package domain.model.number

@JvmInline
value class LottoNumber(val value: Int) {
    init {
        if (value !in (LOTTO_MIN..LOTTO_MAX)) {
            throw LottoNumberException.InvalidLottoNumberRange()
        }
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
    }
}

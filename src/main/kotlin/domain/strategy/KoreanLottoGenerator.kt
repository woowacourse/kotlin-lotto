package domain.strategy

class KoreanLottoGenerator : LottoCountry {
    override fun generateNumber(): List<Int> {
        return (LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE)
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
        const val LOTTO_SIZE = 6
    }
}

package lotto.domain

class LottoFactory {
    fun generateLottoNumbers(): List<Int> {
        return (MIN_RANGE..MAX_RANGE).shuffled().take(DEFAULT_LOTTO_SIZE).sorted()
    }

    fun generateLottos(amount: Int): List<Lotto> {
        return List(amount) { Lotto(generateLottoNumbers()) }
    }

    private companion object {
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
        const val DEFAULT_LOTTO_SIZE = 6
    }
}

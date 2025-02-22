package lotto.domain

class LottoFactory {
    fun generateLottoNumbers(): Lotto {
        val lottoNumbers = (MIN_RANGE..MAX_RANGE).shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }

    fun generateLottos(amount: Int): List<Lotto> {
        return List(amount) { generateLottoNumbers() }
    }

    private companion object {
        const val MIN_RANGE = 1
        const val MAX_RANGE = 45
        const val LOTTO_SIZE = 6
    }
}

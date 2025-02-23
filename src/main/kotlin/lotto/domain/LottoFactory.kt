package lotto.domain

class LottoFactory {
    private fun generateLottoNumbers(): Lotto {
        val lottoNumbers = (MIN_RANGE..MAX_RANGE).shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }

    fun generateLottos(amount: Int): List<Lotto> {
        return List(amount) { generateLottoNumbers() }
    }

    companion object {
        private const val MIN_RANGE = 1
        private const val MAX_RANGE = 45
        private const val LOTTO_SIZE = 6
    }
}

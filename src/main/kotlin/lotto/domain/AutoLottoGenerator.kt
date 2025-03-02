package lotto.domain

class AutoLottoGenerator : NumberGenerator {
    override fun generate(): List<LottoNumber> {
        return (MIN_RANGE..MAX_RANGE).shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber.of(it) }
    }

    companion object {
        private const val MIN_RANGE = 1
        private const val MAX_RANGE = 45
        private const val LOTTO_SIZE = 6
    }
}

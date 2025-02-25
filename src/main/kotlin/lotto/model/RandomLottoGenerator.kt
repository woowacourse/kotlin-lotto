package lotto.model

class RandomLottoGenerator : LottoGenerator {
    override fun generate(count: Int): List<Lotto> =
        List(count) {
            Lotto(
                (MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER)
                    .shuffled()
                    .take(LOTTO_SIZE)
                    .sorted()
                    .map { LottoNumber(it) },
            )
        }

    companion object {
        private const val LOTTO_SIZE: Int = 6
        private const val MINIMUM_LOTTO_NUMBER: Int = 1
        private const val MAXIMUM_LOTTO_NUMBER: Int = 45
    }
}

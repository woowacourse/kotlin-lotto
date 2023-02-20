package domain

class RandomLottoGenerator : LottoGenerator {

    override fun generateLotto(): Lotto {
        return Lotto(
            (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER)
                .shuffled()
                .subList(0, LOTTO_LIMIT_SIZE)
                .map { LottoNumber.from(it) }
                .sortedBy { it.number },
        )
    }

    companion object {
        const val LOTTO_LIMIT_SIZE = 6
        const val LOTTO_MIN_NUMBER = 1
        const val LOTTO_MAX_NUMBER = 45
    }
}

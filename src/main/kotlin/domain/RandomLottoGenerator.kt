package domain

class RandomLottoGenerator : LottoGenerator {

    override fun generateLotto(): Lotto {
        return Lotto(
            LottoNumber.NUMBERS.values
                .shuffled()
                .take(LOTTO_LIMIT_SIZE)
                .sortedBy { it.number },
        )
    }

    companion object {
        const val LOTTO_LIMIT_SIZE = 6
    }
}

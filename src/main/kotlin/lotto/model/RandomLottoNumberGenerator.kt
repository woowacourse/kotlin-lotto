package lotto.model

class RandomLottoNumberGenerator : LottoNumberGenerator {
    override fun generate(): Int = (MINIMUM_RANDOM_NUMBER..MAXIMUM_RANDOM_NUMBER).random()

    companion object {
        const val MINIMUM_RANDOM_NUMBER = 1
        const val MAXIMUM_RANDOM_NUMBER = 45
    }
}

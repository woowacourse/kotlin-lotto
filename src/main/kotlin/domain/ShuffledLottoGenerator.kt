package domain

import model.LottoNumber

class ShuffledLottoGenerator : LottoGenerator {
    override fun generate(): List<LottoNumber> {
        val numbers: List<Int> = range.toList().shuffled()

        return numbers
            .map { number -> LottoNumber(number) }
            .take(LOTTO_NUMBER_COUNT)
            .sortedBy { lottoNumber -> lottoNumber.number }
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private const val LOTTO_NUMBER_COUNT = 6
        private val range = MINIMUM_LOTTO_NUMBER..MAXIMUM_LOTTO_NUMBER
    }
}

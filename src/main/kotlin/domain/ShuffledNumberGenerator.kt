package domain

import model.LottoNumber

class ShuffledNumberGenerator : NumberGenerator {
    override fun generate(): List<LottoNumber> {
        val lottoNumbers: MutableList<LottoNumber> = mutableListOf()
        var lottoNumber: Int = MINIMUM_LOTTO_NUMBER

        repeat(MAXIMUM_LOTTO_NUMBER) { lottoNumbers.add(LottoNumber(lottoNumber++)) }

        return lottoNumbers.shuffled()
            .take(LOTTO_NUMBER_COUNT)
            .sortedBy { lottoNumber -> lottoNumber.number }
    }

    companion object {
        private const val MINIMUM_LOTTO_NUMBER = 1
        private const val MAXIMUM_LOTTO_NUMBER = 45
        private const val LOTTO_NUMBER_COUNT = 6
    }
}

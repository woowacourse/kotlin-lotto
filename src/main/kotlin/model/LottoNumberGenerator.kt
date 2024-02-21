package lotto.model

import model.LottoNumber

class LottoNumberGenerator : NumberGenerator {
    override fun generate(): LottoNumbers =
        LottoNumbers((LOTTO_NUMBER_RANGE).shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it) })


    companion object {
        private const val LOTTO_MIN_NUMBER = 1
        private const val LOTTO_MAX_NUMBER = 45
        val LOTTO_NUMBER_RANGE: IntRange = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER
        const val LOTTO_SIZE = 6
    }
}

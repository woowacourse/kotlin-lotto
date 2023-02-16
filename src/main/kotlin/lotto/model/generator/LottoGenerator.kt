package lotto.model.generator

import lotto.model.LottoNumber

class LottoGenerator : NumberGenerator {
    override fun generate(): List<LottoNumber> {
        return ((START_LOTTO_RANGE..END_LOTTO_RANGE).shuffled().subList(0, SIZE_OF_LOTTO).sorted()).map { LottoNumber(it) }
    }

    companion object {
        private const val SIZE_OF_LOTTO = 6
        private const val START_LOTTO_RANGE = 1
        private const val END_LOTTO_RANGE = 45
    }
}

package lotto.model.generator

import lotto.model.LottoNumber.Companion.RANGE_LOTTO_NUMBER

class LottoGenerator : NumberGenerator {
    override fun generate(): List<Int> {
        return (RANGE_LOTTO_NUMBER.shuffled().subList(0, SIZE_OF_LOTTO).sorted())
    }

    companion object {
        private const val SIZE_OF_LOTTO = 6
    }
}

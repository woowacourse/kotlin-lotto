package lotto.model.generator

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoNumber.Companion.END_LOTTO_RANGE
import lotto.model.LottoNumber.Companion.START_LOTTO_RANGE

class LottoNumberGenerator : NumberGenerator {
    override fun generate(): Lotto {
        val result = (START_LOTTO_RANGE..END_LOTTO_RANGE).shuffled().subList(0, SIZE_OF_LOTTO).sorted()
        return Lotto(result.map { LottoNumber.from(it) })
    }

    companion object {
        private const val SIZE_OF_LOTTO = 6
    }
}

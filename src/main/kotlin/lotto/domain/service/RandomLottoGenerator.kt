package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoNumber.Companion.LOTTO_MAX_NUMBER
import lotto.domain.model.LottoNumber.Companion.LOTTO_MIN_NUMBER

class RandomLottoGenerator(minNumber: Int = LOTTO_MIN_NUMBER, maxNumber: Int = LOTTO_MAX_NUMBER) :
    LottoGenerator {
    private val lottoNumbers =
        (minNumber..maxNumber).map { LottoNumber(it) }

    override fun generate(lottoSize: Int): Lotto {
        val lotto = getLottoNumbers(lottoSize).sorted()
        return Lotto(lotto.toSet())
    }

    private fun getLottoNumbers(lottoNumbersSize: Int): List<LottoNumber> {
        val randomLottoNumbers = lottoNumbers.shuffled()
        return randomLottoNumbers.take(lottoNumbersSize)
    }
}

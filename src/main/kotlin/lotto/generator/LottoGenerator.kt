package lotto.generator

import lotto.domain.LottoNumber

class LottoGenerator {
    fun getRandomLottoNumbers(size: Int): List<LottoNumber> {
        return (LottoNumber.MIN_BOUND..LottoNumber.MAX_BOUND).shuffled().take(size).map { LottoNumber(it) }
    }
}

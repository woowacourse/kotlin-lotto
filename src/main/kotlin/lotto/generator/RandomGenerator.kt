package lotto.generator

import lotto.domain.LottoNumber

class RandomGenerator {
    fun getRandomLottoNumbers(
        size: Int,
        min: Int,
        max: Int,
    ): List<LottoNumber> {
        return (min..max).shuffled().take(size).map { LottoNumber(it) }
    }
}

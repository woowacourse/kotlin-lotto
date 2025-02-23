package lotto.domain.model

import lotto.domain.service.RandomLottoNumbersGenerator
import lotto.domain.value.LottoNumber

class Lotto private constructor(
    val lottoNumbers: Set<LottoNumber>,
) {
    init {
        require(lottoNumbers.size == NUMBER_OF_LOTTO_NUMBERS) {
            TOO_MANY_LOTTO_NUMBERS.format(
                lottoNumbers.size,
            )
        }
    }

    fun getLottoNumbers(): List<LottoNumber> = lottoNumbers.sortedBy { it.number }

    companion object {
        const val NUMBER_OF_LOTTO_NUMBERS = 6

        private const val TOO_MANY_LOTTO_NUMBERS =
            "${NUMBER_OF_LOTTO_NUMBERS}개로 구성된 번호를 중복 없이 입력하세요 (현재 중복 없는 숫자는 %d개 입니다)"

        fun createRandom(): Lotto = Lotto(RandomLottoNumbersGenerator().generateLottoNumbers())

        fun createManual(numbers: Set<Int>): Lotto = Lotto(numbers.map { LottoNumber(it) }.toSet())
    }
}

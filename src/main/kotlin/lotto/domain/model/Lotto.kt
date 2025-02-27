package lotto.domain.model

import lotto.domain.service.RandomLottoNumbersGenerator
import lotto.domain.valueobject.LottoNumber

class Lotto private constructor(
    private val _lottoNumbers: Collection<LottoNumber>,
) {
    val lottoNumbers: Set<LottoNumber>
        get() = _lottoNumbers.toSet()

    init {
        require(lottoNumbers.size == NUMBER_OF_LOTTO_NUMBERS) {
            INACCURATE_LOTTO_NUMBERS.format(
                lottoNumbers.size,
            )
        }
    }

    fun getSortedLottoNumbers(): List<LottoNumber> = lottoNumbers.sortedBy { it.number }

    companion object {
        const val NUMBER_OF_LOTTO_NUMBERS = 6

        private const val INACCURATE_LOTTO_NUMBERS =
            "${NUMBER_OF_LOTTO_NUMBERS}개로 구성된 번호를 중복 없이 입력하세요 (현재 중복 없는 숫자는 %d개 입니다)"

        fun createSelfRandomly(): Lotto = Lotto(RandomLottoNumbersGenerator().generateLottoNumbers())

        fun createSelfByManualLottoNumbers(numbers: Collection<LottoNumber>): Lotto = Lotto(numbers)
    }
}

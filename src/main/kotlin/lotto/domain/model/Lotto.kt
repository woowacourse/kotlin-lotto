package lotto.domain.model

import lotto.domain.value.LottoNumber

class Lotto(
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
            "%d개가 아닌 \n${NUMBER_OF_LOTTO_NUMBERS}개로 구성된 번호를 입력하세요"
    }
}

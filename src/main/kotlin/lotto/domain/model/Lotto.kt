package lotto.domain.model

import lotto.constants.LottoConstants
import lotto.domain.value.LottoNumber

class Lotto(
    val lottoNumbers: List<LottoNumber>,
) {
    init {
        require(lottoNumbers.size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS) {
            TOO_MANY_LOTTO_NUMBERS.format(
                lottoNumbers.size,
            )
        }
        require(lottoNumbers.map { it.number }.distinct().size == LottoConstants.NUMBER_OF_LOTTO_NUMBERS) {
            HAS_DUPLICATE_NUMBERS.format(
                lottoNumbers
                    .map { it.number }
                    .groupBy { it }
                    .filter { it.value.size > 1 }
                    .keys
                    .toList()
                    .joinToString(", "),
            )
        }
        require(lottoNumbers.map { it.number }.zipWithNext().all { (a, b) -> a < b }) {
            ASCENDING_ORDER_IS_REQUIRED
        }
    }

    companion object {
        private const val TOO_MANY_LOTTO_NUMBERS =
            "%d개가 아닌 \n${LottoConstants.NUMBER_OF_LOTTO_NUMBERS}개로 구성된 lottoNumbers 리스트는를 입력하세요"
        private const val HAS_DUPLICATE_NUMBERS = "lottoNumbers 리스트에 중복된 번호(%s)가 존재합니다."
        private const val ASCENDING_ORDER_IS_REQUIRED = "lottoNumbers 리스트는 오름차순 정렬 후 입력되어야 합니다."
    }
}

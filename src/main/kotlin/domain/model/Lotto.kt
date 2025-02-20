package domain.model

import domain.service.LottoGenerator.Companion.LOTTO_MAX
import domain.service.LottoGenerator.Companion.LOTTO_MIN
import util.ErrorConstants.ERROR

class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
        require(numbers.all { it in (LOTTO_MIN..LOTTO_MAX) }) { INVALID_LOTTO_NUMBERS }
        require(numbers.size == numbers.toSet().size) { DUPLICATED_LOTTO_NUMBERS }
    }

    companion object {
        const val LOTTO_SIZE = 6
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 ${LOTTO_SIZE}개 입니다."
        const val INVALID_LOTTO_NUMBERS = "$ERROR 로또 번호는 ${LOTTO_MIN}부터 $LOTTO_MAX 사이입니다."
        const val DUPLICATED_LOTTO_NUMBERS = "$ERROR 로또 번호는 중복이 없습니다."
    }
}

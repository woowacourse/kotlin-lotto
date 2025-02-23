package domain.model

import domain.strategy.KoreanLottoGenerator.Companion.LOTTO_SIZE
import ERROR

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
        require(numbers.size == numbers.toSet().size) { DUPLICATED_LOTTO_NUMBERS }
    }

    companion object {
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 6개 입니다."
        const val DUPLICATED_LOTTO_NUMBERS = "$ERROR 로또 번호는 중복이 없습니다."
    }
}

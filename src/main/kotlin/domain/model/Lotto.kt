package domain.model

import domain.strategy.KoreanLottoGenerator.Companion.LOTTO_MAX
import domain.strategy.KoreanLottoGenerator.Companion.LOTTO_MIN
import domain.strategy.KoreanLottoGenerator.Companion.LOTTO_SIZE

class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.all { it in (LOTTO_MIN..LOTTO_MAX) }) { INVALID_LOTTO_NUMBERS }
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
        require(numbers.size == numbers.toSet().size) { DUPLICATED_LOTTO_NUMBERS }
    }

    companion object {
        const val ERROR = "[ERROR]"
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 6개 입니다."
        const val INVALID_LOTTO_NUMBERS = "$ERROR 로또 번호는 1부터 45 사이입니다."
        const val DUPLICATED_LOTTO_NUMBERS = "$ERROR 로또 번호는 중복이 없습니다."
    }
}

package domain.model

import ERROR
import domain.strategy.KoreanLottoGenerator.Companion.LOTTO_SIZE

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
        require(numbers.size == numbers.toSet().size) { DUPLICATED_LOTTO_NUMBERS }
    }

    companion object {
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 ${LOTTO_SIZE}개 입니다."
        const val DUPLICATED_LOTTO_NUMBERS = "$ERROR 로또 번호는 중복이 없습니다."

        fun from(vararg numbers: Int): Lotto {
            return Lotto(numbers.map { LottoNumber(it) })
        }

        fun extractionNumber(lotto: Lotto): List<Int> {
            return lotto.numbers.map { it.number }
        }
    }
}

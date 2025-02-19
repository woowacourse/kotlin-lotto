package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBERS_SIZE
import lotto.model.Lotto.Companion.MAXIMUM_LOTTO_RANGE
import lotto.model.Lotto.Companion.MINIMUM_LOTTO_RANGE

class WinningNumbers(private val numbers: List<Int>) {
    init {
        require(numbers.distinct().size == numbers.size) { "당첨 번호는 중복될 수 없습니다." }
        numbers.forEach { number ->
            require(number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { "로또 번호는 1에서 45 범위 내에서 있어야 합니다." }
        }
        require(numbers.size == LOTTO_NUMBERS_SIZE) { "당첨 번호는 6개여야 합니다." }
    }
}

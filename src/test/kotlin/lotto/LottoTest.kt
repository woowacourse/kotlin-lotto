package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Lotto(
    vararg numbers: Int,
) {
    val numbers: Set<Int> = numbers.toSet()

    init {
        require(this.numbers.size == LOTTO_SIZE) { ERROR_MESSAGE_LOTTO_NEEDS_6_NUMBERS }
        require(this.numbers.all { number: Int -> number in LOTTO_NUMBER_MIN..LOTTO_NUMBER_MAX }) {
            ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45
        }
    }

    companion object {
        const val LOTTO_PRICE = 1_000
        const val LOTTO_NUMBER_MIN = 1
        const val LOTTO_NUMBER_MAX = 45

        private const val LOTTO_SIZE = 6
        private const val ERROR_MESSAGE_LOTTO_NEEDS_6_NUMBERS = "로또는 6개의 숫자를 갖고 있어야 합니다."
        private const val ERROR_MESSAGE_LOTTO_NUMBER_SHOULD_BE_IN_1_TO_45 = "로또는 1부터 45 사이의 숫자만 가질 수 있습니다."
    }
}

class LottoTest {
    @Test
    fun `당첨 번호는 총 6개를 입력해야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto() }
        assertThrows<IllegalArgumentException> { Lotto(1) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5) }
    }

    @Test
    fun `당첨 번호는 1부터 45 사이의 숫자여야 한다`() {
        assertThrows<IllegalArgumentException> { Lotto(0, 1, 2, 3, 4, 5) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5, 46) }
    }

    @Test
    fun `로또 번호가 중복되면 안 된다`() {
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 1, 2, 3) }
    }
}

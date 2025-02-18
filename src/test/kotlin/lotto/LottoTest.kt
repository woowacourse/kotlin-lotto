package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Lotto(
    vararg number: Int,
) {
    val numbers: Set<Int> = number.toSet()

    init {
        require(number.size == 6) { "로또는 6개의 숫자를 갖고 있어야 합니다." }
        require(numbers.all { number: Int -> number in 1..45 }) {
            "로또는 1부터 45 사이의 숫자만 가질 수 있습니다."
        }
    }
}

class LottoTest {
    @Test
    fun `로또는 1부터 45중 6개의 숫자를 갖고 있다`() {
        assertThrows<IllegalArgumentException> { Lotto(0) }
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5, 6, 7) }
        assertThat(Lotto(1, 2, 3, 4, 5, 6).numbers).isEqualTo(setOf(1, 2, 3, 4, 5, 6))
    }
}

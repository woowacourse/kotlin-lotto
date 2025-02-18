package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @Test
    fun `로또가 6개가 아닌 번호를 가질 때 오류를 반환한다`() {
        val numbers = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
    }

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `로또 번호 범위가 1 이상 45 이하가 아닐 때 오류를 반환한다`(number: Int) {
        val numbers = listOf(1, 2, 3, 4, 5, number)
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
    }
}

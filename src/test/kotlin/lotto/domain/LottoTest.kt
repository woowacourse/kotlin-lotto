package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoTest {
    @ValueSource(ints = [0, 1, 2, 3, 4, 5])
    @ParameterizedTest
    fun `로또는 6개의 번호를 갖고 있다`(size: Int) {
        val lottoNumbers = List(size) { index: Int -> index + 1 }
        assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }
    }

    @Test
    fun `로또 번호는 겹칠 수 없다`() {
        val duplicatedNumbers: List<Int> = listOf(1, 2, 3, 4, 5, 1)
        assertThrows<IllegalArgumentException> { Lotto(duplicatedNumbers) }
    }
}

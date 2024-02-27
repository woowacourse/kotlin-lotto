package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FixedLottoNumberGeneratorTest {
    @Test
    fun `구매할 로또는 중복이 허용되지 않습니다`() {
        val fixedNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
                listOf(13, 14, 15, 16, 17, 18),
            )

        assertThrows<IllegalArgumentException> { FixedLottoNumbersGenerator(fixedNumbers) }
    }
}

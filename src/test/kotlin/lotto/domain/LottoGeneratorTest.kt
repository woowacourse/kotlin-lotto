package lotto.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoGeneratorTest {
    @Test
    fun `수동으로 구매할 로또의 총 금액은 구입 금액을 초과할 수 없다`() {
        assertThrows<IllegalArgumentException> { LottoGenerator(1, 2) }
    }

    @Test
    fun `수동으로 구매할 로또의 개수는 0 미만일 수 없다`() {
        assertThrows<IllegalArgumentException> { LottoGenerator(1, -1) }
    }
}

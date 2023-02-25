package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `번호의 개수가 6개면 로또 생성`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        assertThat(lotto.size()).isEqualTo(6)
    }

    @Test
    fun `번호의 개수가 6개가 아니면 로또가 생성되지 않음`() {
        assertThrows<IllegalArgumentException> { Lotto(1, 2, 3, 4, 5) }
    }
}

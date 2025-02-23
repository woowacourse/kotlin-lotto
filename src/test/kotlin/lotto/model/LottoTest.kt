package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 1매에는 6개의 로또 번호가 존재한다`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto.from(listOf(1, 2, 3, 4, 5)) }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 번호는 6개여야 합니다.")
    }

    @Test
    fun `로또 번호는 중복되지 않는다`() {
        val exception = assertThrows<IllegalArgumentException> { Lotto.from(listOf(1, 1, 2, 3, 4, 5)) }
        assertThat(exception.message).isEqualTo("[ERROR] 로또 번호는 중복될 수 없습니다.")
    }
}

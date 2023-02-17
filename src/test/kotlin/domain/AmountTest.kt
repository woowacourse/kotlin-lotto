package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AmountTest {
    @Test
    fun `접근하면 값을 반환한다`() {
        val a = Amount.from(1000)
        assertThat(a.value).isEqualTo(1000)
    }

    @Test
    fun `동일성 테스트`() {
        val a = Amount.from(1000)
        val b = Amount.from(1000)
        assertThat(a).isEqualTo(b)
        assertThat(a).isSameAs(b)
    }
}

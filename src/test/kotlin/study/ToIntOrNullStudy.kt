package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToIntOrNullStudy {
    @Test
    fun `공백이 존재하면 숫자로 인식할 수 없다`() {
        val value = " 3 "
        assertThat(value.toIntOrNull()).isNull()
    }
}

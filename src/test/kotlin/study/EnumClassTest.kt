package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

enum class RGB(
    val title: String,
) {
    RED("레드"),
    GREEN("그린"),
    BLUE("블루"),
}

class EnumClassTest {
    @Test
    fun `valueOf 테스트`() {
        val a: RGB = RGB.valueOf("GREEN")

        assertThat(RGB.RED).isEqualTo(a)
        assertThrows<IllegalArgumentException> { RGB.valueOf("PINK") }
    }
}

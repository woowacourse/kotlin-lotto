package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CountOfMatchTest {
    @Test
    fun `일치하는 로또 번호 개수는 6을 넘을 수 없다`() {
        val exception = assertThrows<IllegalArgumentException> { CountOfMatch(Count(7)) }
        val expected = "[ERROR] 일치하는 개수는 6을 넘을 수 없습니다!"
        assertThat(exception.message).isEqualTo(expected)
    }
}
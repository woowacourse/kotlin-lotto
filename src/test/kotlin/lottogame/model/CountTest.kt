package lottogame.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow

class CountTest {
    @Test
    fun `Count 는 0 보다 작으면 null 반환`() {
        assertAll(
            { assertThat(Count.createOrNull(-1)).isNull() },
            { assertDoesNotThrow { Count.createOrNull(0) } },
        )
    }
}

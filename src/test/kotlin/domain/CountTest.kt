package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CountTest {
    @Test
    fun `장수가 0 이상이 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { Count(-1) }
    }

    @Test
    fun `장수가 구매 금액에 로또 가격을 나눈 값 이하가 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Count(11)
        }
    }
}

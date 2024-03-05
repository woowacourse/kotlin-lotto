package model.lottery.strategy

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertDoesNotThrow

class RandomNumbersStrategyTest {
    @RepeatedTest(50)
    fun `랜덤으로 숫자 리스트를 정렬하여 만든다`() {
        assertDoesNotThrow { println(RandomNumbersStrategy.generate()) }
    }
}

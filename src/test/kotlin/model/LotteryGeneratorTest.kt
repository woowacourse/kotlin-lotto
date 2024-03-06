package model

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryGeneratorTest {
    private val lotteryGenerator = LotteryGenerator()

    @RepeatedTest(1_000)
    fun `6자리의 중복되지 않은 번호의 로또를 생성한다`() {
        assertDoesNotThrow {
            println(lotteryGenerator.generate(LotteryType.Auto))
        }
    }
}

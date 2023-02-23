package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LotteryGeneratorTest {

    @Test
    fun `랜덤 번호들을 발행하여 로또를 생성할 수 있다`() {
        assertDoesNotThrow {
            val lotteryGenerator = LotteryGenerator()
            val lotteries = lotteryGenerator.generateLotteries(1)
            assertThat(lotteries.size).isEqualTo(1)
        }
    }

    @Test
    fun `구매 횟수에 맞는 개수의 로또를 발행한다`() {
        val lotteryGenerator = LotteryGenerator()
        val lotteries = lotteryGenerator.generateLotteries(2)
        assertThat(lotteries.size).isEqualTo(2)
    }
}

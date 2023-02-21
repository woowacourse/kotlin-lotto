package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteryGeneratorTest {

    @Test
    fun `구매 횟수에 맞는 개수의 로또를 발행한다`() {
        val lotteryGenerator = LotteryGenerator()
        val lotteries = lotteryGenerator.generateLotteries(1)
        assertThat(lotteries.size).isEqualTo(1)
    }
}

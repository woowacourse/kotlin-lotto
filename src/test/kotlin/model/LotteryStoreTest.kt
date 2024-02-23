package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import utils.ExplicitTicketGenerationStrategy
import utils.RandomTicketGenerationStrategy

class LotteryStoreTest {
    @ParameterizedTest
    @CsvSource("5000, 5", "10000, 10", "1000, 1", "3500, 3", "15500, 15")
    fun `로또가 구매한 갯수만큼 발행에 성공`(
        input: String,
        count: Int,
    ) {
        val lotteryStore = LotteryStore(RandomTicketGenerationStrategy(Amount.fromInput(input)))
        assertThat(lotteryStore.issueTicket().userLotteries.size).isEqualTo(count)
    }

    @Test
    fun `전략 패턴을 이용하여 로또 값을 지정해서 테스트`() {
        val explicitAmount = Amount.fromInput("2000")
        val explicitNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(7, 8, 9, 10, 11, 12),
            )
        val explicitLotteries = explicitNumbers.map { Lottery.fromList(it) }.toList()

        val lotteryStore = LotteryStore(ExplicitTicketGenerationStrategy(explicitAmount, explicitNumbers))

        val ticket = lotteryStore.issueTicket()
        ticket.userLotteries.forEachIndexed { index, userLotto ->
            assertThat(userLotto.getCountOfMatch(explicitLotteries[index])).isEqualTo(6)
        }
    }
}

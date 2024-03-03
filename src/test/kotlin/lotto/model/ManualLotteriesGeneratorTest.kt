package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLotteriesGeneratorTest {
    @Test
    fun `시도 횟수와 수동으로 숫자를 입력하는 로직에 부합하는 수동 로또 리스트를 반환한다`() {
        var trial = 0
        val manualLotteriesGenerator =
            ManualLotteriesGenerator(
                manualCount = Count(5),
                onReceiveNumbers = {
                    trial++
                    onReceiveSampleNumbers(trial)
                },
            )
        val actualLotteries = manualLotteriesGenerator.generate()
        val expectedLotteries =
            listOf(
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(2, 3, 4, 5, 6, 7),
                Lotto(3, 4, 5, 17, 18, 19),
                Lotto(11, 22, 31, 34, 36, 40),
                Lotto(21, 25, 33, 38, 40, 42),
            )
        assertThat(actualLotteries).isEqualTo(expectedLotteries)
    }

    private fun onReceiveSampleNumbers(trial: Int) =
        when (trial) {
            1 -> listOf(1, 2, 3, 4, 5, 6)
            2 -> listOf(2, 3, 4, 5, 6, 7)
            3 -> listOf(3, 4, 5, 17, 18, 19)
            4 -> listOf(11, 22, 31, 34, 36, 40)
            else -> listOf(21, 25, 33, 38, 40, 42)
        }
}

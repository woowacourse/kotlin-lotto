package model.lottery

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoNumberComparatorTest {
    @Test
    fun `로또의 번호와 당첨번호의 번호를 비교해서 일치하는 번호의 개수를 구한다`() {
        val lottoNumberComparator = LottoNumberComparator()

        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val winningLottery = Lottery.of(2, 3, 4, 5, 6, 10)

        val expectedMatchCount = 5
        val actualMatchCount = lottoNumberComparator.compareNumbers(lottery, winningLottery)
        assertThat(actualMatchCount).isEqualTo(expectedMatchCount)
    }
}

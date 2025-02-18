package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningNumbersTest {
    //    - 로또를 당첨확인하는 기기
// //    - [ ] 로또 번호가 몇개 일치하는 지 확인
// //    - [ ] 수익률 계산 : 각 당첨 금액 * 당첨 개수 / 구입금액
    @Test
    fun `당첨번호와 로또 번호를 비교해, 몇개 일치하는 지 확인`() {
        val lottoNumbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
        val winningNumbers: List<Int> = listOf(1, 2, 7, 8, 9, 10)
        val bonusNumber = 12
        assertThat(winningNumbers.intersect(lottoNumbers).size).isEqualTo(2)
        assertThat(bonusNumber in lottoNumbers).isFalse()
    }
}

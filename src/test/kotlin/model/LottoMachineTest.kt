package model

import lotto.model.LottoMachine
import lotto.model.Margin
import lotto.model.Prize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMachineTest {

    @Test
    fun `구입금액이 1000 미만일 떄 예외를 던지는 지`() {
        val purchaseAmount = 999

        val exception = assertThrows<IllegalArgumentException> { LottoMachine(purchaseAmount) }

        assertThat(exception.message).isEqualTo("구입금액은 999원보다 큰 1000원 이상이어야 합니다.")
    }

    @Test
    fun `구입 금액 대비 수익 금액의 비율을 구한다`() {
        val prize = Prize(5_000)
        val purchaseAmount = 14_000
        val lottoMachine = LottoMachine(purchaseAmount)
        val actual = lottoMachine.calculateMargin(prize)

        val expected = 0.357

        assertThat(actual).isEqualTo(Margin(expected))
    }

    @Test
    fun `구입금액으로 살 수 있는 수동 로또수가 아닐 떄 예외를 던지는 지`() {
        val purchaseAmount = 1_000
        val countOfManualLotto = 10

        val exception = assertThrows<IllegalArgumentException> { LottoMachine(purchaseAmount, countOfManualLotto) }

        assertThat(exception.message).isEqualTo("수동 로또 10개를 1000원으로 살 수 없습니다.")
    }
}
